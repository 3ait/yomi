package com.datas.easyorder.controller.administrator.order;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.internal.util.StringHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.datas.easyorder.controller.BaseController;
import com.datas.easyorder.controller.administrator.order.logic.OrderItemLogic;
import com.datas.easyorder.controller.administrator.order.logic.OrderLogic;
import com.datas.easyorder.controller.administrator.setting.logic.SettingLogic;
import com.datas.easyorder.db.dao.OrderRepository;
import com.datas.easyorder.db.entity.Invoice;
import com.datas.easyorder.db.entity.Order;
import com.datas.utils.SearchForm;
import com.plugin.utils.DateHelper;

/**
 * 
 * @author leo
 *
 */
@RestController
@RequestMapping("/management/order")
public class OrderController extends BaseController<Order>{

	@Autowired
	OrderLogic orderLogic;
	@Autowired
	OrderItemLogic orderItemLogic;

	@Autowired
	private SettingLogic settingLogic;
	
	/**
	 * 
	 * @param page
	 * @param size
	 * @param orderBy
	 * @param order
	 * @return
	 */
	@RequestMapping(value={"","/"})
	public ModelAndView index( @ModelAttribute(value = "searchForm") SearchForm searchForm) {
		
		ModelAndView modelAndView = new ModelAndView("administrator/order/index");
		
		if (StringHelper.isEmpty(searchForm.getDateFrom())) {
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.MONTH, -36);
			searchForm.setDateFrom(DateHelper.getYYYYMMDD(calendar.getTime().getTime()));
		}
		if (StringHelper.isEmpty(searchForm.getDateTo())) {
			searchForm.setDateTo(DateHelper.getYYYYMMDD());
		}

		Pageable pageable = new PageRequest(searchForm.getPage()-1<1?0:searchForm.getPage()-1, searchForm.getSize(), Direction.fromString(searchForm.getSort()), searchForm.getSortBy());

		Page<Order> list = orderLogic.getOrderList(searchForm, pageable);
		

		modelAndView.addObject("orderList", list.getContent());

		modelAndView.addObject("salesList", orderLogic.geSalesList());
		modelAndView.addObject("salesArray", orderLogic.getSales().toString());
		
		modelAndView.addObject("totalPrice",list.getContent().stream().map(order -> order.getTotalProductPrice()).reduce(0D,(a,b) -> a+b).doubleValue());
		modelAndView.addObject("totalFreight",list.getContent().stream().map(order -> order.getTotalFreight()).reduce(0D,(a,b) -> a+b).doubleValue());
		
		super.setPage(modelAndView, list, searchForm);
		
		modelAndView.addObject("user", super.getLognUser());
		
		return modelAndView;
	}

	
	/**
	 * order detail
	 * @param orderId
	 * @return
	 */
	@RequestMapping("/{orderId}")
	@ResponseBody
	public ModelAndView orderDetail(@PathVariable("orderId") Long orderId){
		ModelAndView mv = new ModelAndView("administrator/order/detail");
		
		Order order = orderLogic.getOrderById(orderId);
		mv.addObject("orderItems", orderItemLogic.getItemsByOrderId(orderId));
		mv.addObject("salesArray", orderLogic.getSales().toString());
		mv.addObject("order", order);
		return mv;
	}
	

	/**
	 * order/transfer transferToInvoice
	 */
	@RequestMapping("/transfer/{orderId}")
	@ResponseBody
	public boolean transferToInvoice(@PathVariable Long orderId,HttpServletRequest request){
		
		Invoice invoice = orderLogic.transferOrderToInvoice(orderId,super.getLognUser());
		
		return invoice!=null;
	}
	/**
	 * Duplicate Order
	 */
	@RequestMapping("/duplicate/{orderId}")
	@ResponseBody
	public boolean duplicate(@PathVariable Long orderId,HttpServletRequest request){
		
		Order order = orderLogic.duplicate(orderId,super.getLognUser());
		
		return order==null;
	}
	

	
	
	/**
	 * 更新value属性
	 */
	@RequestMapping("/update/colomn/")
	public void valueUpdate(
			@RequestParam("pk") Long  pk, 
			@RequestParam("name") String name , 
			@RequestParam("value") String value,
			HttpServletRequest request
			){
		orderLogic.update(pk,name,value,super.getLognUser());
		if(name.equals("status") && value.equals(OrderRepository.status_send)){
			orderLogic.emailOrder(pk,super.getLognUser());
		}
	}
	
	
	/**
	 * OrderItem
	 * 更新value属性
	 */
	@RequestMapping("/orderitem/update/colomn/")
	public void orderItemValueUpdate(
			@RequestParam("pk") Long  pk, 
			@RequestParam("name") String name , 
			@RequestParam("value") String value,
			HttpServletRequest request
			){
		orderItemLogic.update(pk,name,value);
	}
	
	/**
	 * OrderItem
	 * 删除订单产品
	 */
	@RequestMapping("/item/remove/{orderItemId}")
	@ResponseBody
	public void removeOderItem(@PathVariable("orderItemId") Long orderItemId){
		orderItemLogic.removeOrderItem(orderItemId);
	}
	
	/**
	 * 打印订单 pdf View | download
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value="/print.pdf",produces="application/pdf;")
	public void view(@RequestParam("orderIds") Long[] orderIds,HttpServletRequest request,HttpServletResponse response) throws IOException{
		
		response.setContentType("application/pdf");
		orderLogic.pdfOutputStream(orderIds, request, response);

		response.getOutputStream().flush();
        response.getOutputStream().close();
	}
	
	/**
	 * 打印订单 pdf View | download
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value="/batch/print")
	public ModelAndView batchPrint(@RequestParam("orderIds") Long[] orderIds) throws IOException{
		
		ModelAndView mv = new ModelAndView("administrator/order/pdf/print");
		mv.addObject("orderPdfViewList",orderLogic.batchPrint(orderIds));
		return mv;
		
	}
	
	/**
	 * 打印小票
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value="/ticket/print/{orderId}")
	public ModelAndView ticketPrint(@PathVariable("orderId") Long orderId) throws IOException{
		
		ModelAndView mv = new ModelAndView("administrator/order/pdf/ticket");
		mv.addObject("userCompany",settingLogic.getUserCompany());
		mv.addObject("orderPdfViewList",orderLogic.batchPrint(new Long[]{orderId}));
		return mv;
	}
	
	/**
	 * 后台增加新订单
	 * @return ModelAndView
	 * @throws IOException 
	 */
	@RequestMapping(value="/add")
	public ModelAndView newOrder() throws IOException{
		ModelAndView mv = new ModelAndView("administrator/order/new");
		return mv;
		
	}
	
	/**
	 * 新订单保存
	 * @return ModelAndView
	 * @throws IOException 
	 */
	@RequestMapping(value="/add/save") 
	@ResponseBody
	public void newOrderSave(@RequestParam("customerId") Long customerId,@RequestParam("products") String[] products,@RequestParam("adminMsg") String adminMsg,@RequestParam("gst") Double gst) throws IOException{
		
		orderLogic.saveOrder(customerId,products,gst,adminMsg);
	}
	
}
