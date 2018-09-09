package com.datas.easyorder.controller.administrator.report.logic;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;

import com.datas.easyorder.controller.administrator.report.ChartData;
import com.datas.easyorder.db.dao.OrderItemRepository;
import com.datas.easyorder.db.dao.OrderRepository;
import com.datas.easyorder.db.dao.PageViewRepository;
import com.datas.easyorder.db.dao.ProductRepository;
import com.datas.easyorder.db.entity.PageView;
import com.datas.easyorder.db.entity.Product;
import com.plugin.utils.DateHelper;


@Component
public class ReportLogic {

	@Autowired
	OrderRepository orderRepository;
	@Autowired
	PageViewRepository pageViewRepository;
	@Autowired
	OrderItemRepository orderItemRepository;
	@Autowired
	ProductRepository productRepository;
	
	/**
	 * getPageView Number
	 */
	public long getPageViewNumber(Date from, Date to){
		Long ret = pageViewRepository.findTotalByDateBetween(from, to);
		if(ret==null){
			ret = 0L;
		}
		return ret;
	}
	
	/**
	 * 获取订单统计排序
	 * @param from
	 * @param to
	 * @return List<Object[]>
	 */
	public String[] getOrderReportByDate(Date from, Date to) {
		List<Object[]> list = orderRepository.findByCreateTimeBetweenAndStatusNot(from, to,OrderRepository.status_canceled, new Sort(Direction.ASC, "createTime"));
		return getOrderData(from,to,list);
	}
	
	/**
	 * CREATE ORDER REPORT
	 * @param from
	 * @param to
	 * @param list
	 * @return
	 */
	private String[] getOrderData(Date from, Date to,List<Object[]> list) {

		

		String[] array = new String[3];
		StringBuilder xDate = new StringBuilder();
		StringBuilder totalNumber =  new StringBuilder();
		StringBuilder totalPrice =  new StringBuilder();
		
		
		Map<String, Object[]> dataMap = new HashMap<String, Object[]>();
		list.forEach(obj ->{
			dataMap.put(((Object[])obj)[0].toString(), obj);
		});
		
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(from);
		while(calendar.getTime().before(to)){
			String d = DateHelper.getDDMMYYYY(calendar.getTime());
			xDate.append(d).append(",");
			if(dataMap.containsKey(d)){
				totalNumber.append(dataMap.get(d)[1].toString()).append(",");
				totalPrice.append(dataMap.get(d)[2].toString()).append(",");
			}else{
				totalNumber.append(0).append(",");
				totalPrice.append(0).append(",");
			}
			calendar.add(Calendar.DATE, 1);
			
		}
		
		array[0] = xDate.toString();
		array[1] = totalNumber.toString();
		array[2] = totalPrice.toString();
		return array;
	}

	/**
	 * 获取销售订单价格统计
	 * @param parseYYYYMMDD
	 * @param parseYYYYMMDD2
	 * SELECT date_format(o.createTime,'%d/%m/%Y'), count(o.id),SUM(o.totalProductPrice),customerBySalesId.id,customerBySalesId.name
	 * @return String[]
	 */
	public ChartData getSalesReportByDate(Date from, Date to) {
		List<Object[]> list = orderRepository.findSalesOrderByCreateTimeBetween(from, to,OrderRepository.status_canceled);
		
		
		Map<Long, List<Object[]>> salesIdMap = new HashMap<>();
		
		StringBuilder lineName = new StringBuilder();
		list.forEach(obj ->{
			long saleId = Long.valueOf(((Object[])obj)[3].toString());
			if(!salesIdMap.containsKey(saleId)){
				List<Object[]> yList = new ArrayList<>();
				yList.add(((Object[])obj));
				salesIdMap.put(saleId, yList);
				lineName.append(obj[4]).append(",");
			}else{
				salesIdMap.get(saleId).add(((Object[])obj));
			}
		});
		
		ChartData chartData = new ChartData();
		Object[] salse = new Object[salesIdMap.size()];
		
		//循环获取每一个销售的数据
		int i = 0;
		for(Map.Entry<Long, List<Object[]>> entity: salesIdMap.entrySet()){
			
			String[] array = new String[5];
			StringBuilder xDate = new StringBuilder();
			StringBuilder totalNumber =  new StringBuilder();
			StringBuilder totalPrice =  new StringBuilder();
			StringBuilder saleId =  new StringBuilder();
			StringBuilder saleName =  new StringBuilder();
			
			
			Map<String, Object[]> dataMap = new HashMap<String, Object[]>();
			entity.getValue().forEach(obj ->{
				dataMap.put(((Object[])obj)[0].toString(), obj);
			});
			
			
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(from);
			while(calendar.getTime().before(to)){
				String d = DateHelper.getDDMMYYYY(calendar.getTime());
				xDate.append(d).append(",");
				if(dataMap.containsKey(d)){
					totalNumber.append(dataMap.get(d)[1].toString()).append(",");
					totalPrice.append(dataMap.get(d)[2].toString()).append(",");
					saleId.append(dataMap.get(d)[3].toString()).append(",");
					saleName.append(dataMap.get(d)[4].toString()).append(",");
				}else{
					totalNumber.append(0).append(",");
					totalPrice.append(0).append(",");
					saleId.append("").append(",");
					saleName.append("").append(",");
				}
				calendar.add(Calendar.DATE, 1);
				
			}
			chartData.setX(xDate.toString());
			array[0] = xDate.toString();
			array[1] = totalNumber.toString();
			array[2] = totalPrice.toString();
			array[3] = saleId.toString();
			array[4] = saleName.toString();
			
			salse[i] = array;
			i++;
		}
		chartData.setY(salse);
		chartData.setLineName(lineName.toString());
		
		return chartData;
	}

	/**
	 * page view 曲线图
	 * @param from
	 * @param to
	 * @return ChartData
	 */
	public ChartData getPageViewReportByDate(Date from, Date to) {
		
		List<PageView> list = pageViewRepository.findByDateBetween(from, to, new PageRequest(0, Integer.MAX_VALUE)).getContent();
		
		
		String[] array = new String[3];
		StringBuilder xDate = new StringBuilder();
		StringBuilder numbers =  new StringBuilder();
		
		
		Map<String, PageView> dataMap = new HashMap<>();
		list.forEach(obj ->{
			dataMap.put(DateHelper.getDDMMYYYY(obj.getDate()), obj);
		});
		
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(from);
		while(calendar.getTime().before(to)){
			String d = DateHelper.getDDMMYYYY(calendar.getTime());
			xDate.append(d).append(",");
			if(dataMap.containsKey(d)){
				numbers.append(dataMap.get(d).getViewNumber()).append(",");
			}else{
				numbers.append(0).append(",");
			}
			calendar.add(Calendar.DATE, 1);
			
		}
		
		array[0] = xDate.toString();
		array[1] = numbers.toString();
		
		ChartData chartData = new ChartData();
		chartData.setX(array[0]);
		chartData.setY(array);
		
		
		return chartData;
	}
	
	/**
	 * 根据订单内容获取产品排序
	 * @param dateFrom
	 * @param dateTo
	 * @param topProductNum
	 * @return
	 */
	public ChartData getTopProduct(Date dateFrom, Date dateTo, Integer topProductNum) {
		List<Object[]> list = orderItemRepository.findTopProductByCreateTimeBetween(dateFrom, dateTo, OrderRepository.status_canceled, topProductNum);
		
		StringBuilder xProductNames = new StringBuilder();
		StringBuilder yNumbers =  new StringBuilder();
		
		list.forEach(obj -> {
			xProductNames.append(obj[1]).append(",");
			yNumbers.append(obj[2]).append(",");
		});
		ChartData chartData = new ChartData();
		chartData.setX(xProductNames.toString());
		chartData.setY(new String[]{yNumbers.toString()});
		
		return chartData;
	}
	
	
	/**
	 * 
	 * @param productId
	 * @param dateFrom
	 * @param dateTo
	 * @return ChartData
	 *  date_format(o.createTime,'%d/%m/%Y'), oi.product_Id, oi.product_Name_En, sum(oi.num)
	 */
	public ChartData getSingleProductReport(Long productId, Date dateFrom, Date dateTo) {
		List<Object[]> list = orderItemRepository.findProductByIdAndCreateTimeBetween(productId, dateFrom, dateTo, OrderRepository.status_canceled);
		
		StringBuilder xDate = new StringBuilder();
		StringBuilder totalNumber =  new StringBuilder();
		
		
		Map<String, Object[]> dataMap = new HashMap<String, Object[]>();
		list.forEach(obj ->{
			
			dataMap.put(((Object[])obj)[0]==null?"":((Object[])obj)[0].toString(), obj);
		});
		
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dateFrom);
		while(calendar.getTime().before(dateTo)){
			String d = DateHelper.getDDMMYYYY(calendar.getTime());
			xDate.append(d).append(",");
			if(dataMap.containsKey(d)){
				totalNumber.append(dataMap.get(d)[3].toString()).append(",");
			}else{
				totalNumber.append(0).append(",");
			}
			calendar.add(Calendar.DATE, 1);
			
		}
		
		ChartData chartData = new ChartData();
		
		chartData.setX(xDate.toString());
		chartData.setY(new String[]{totalNumber.toString()});
		
		return chartData;
		
	}
	
	
	/**
	 * pageView
	 * save pageView
	 */
	public void savePageView() {
		
		
		PageView pageView = pageViewRepository.findOneByDate(Calendar.getInstance().getTime());
		if(pageView==null){
			pageView = new PageView();
			pageView.setDate(Calendar.getInstance().getTime());
			pageView.setViewNumber(1);
		}else{
			pageView.setViewNumber(pageView.getViewNumber()+1);
		}
		pageViewRepository.save(pageView);
		
	}
	
	/**
	 * 多产品报表
	 * @param productId
	 * @param dateTo
	 * @return ChartData
	 *  oi.product_Id, oi.product_Name_En, sum(oi.num)
	 *  
	 */
	public ChartData getMultipuleProductReport(Long[] productIds,Date dateFrom, Date dateTo) {
		
		List<Object[]> list = orderItemRepository.findProductByIdAndCreateTimeBetween(productIds, dateFrom, dateTo, OrderRepository.status_canceled);
		
		StringBuilder x = new StringBuilder();
		StringBuilder y =  new StringBuilder();
		
		Set<Long> set = new HashSet<>();
		list.forEach(row ->{
			x.append(row[1]).append(",");
			y.append(row[2]).append(",");
			set.add(Long.valueOf(row[0].toString()));
		});
		
		for(Long id: productIds){
			if(!set.contains(id)){
				Product product = productRepository.findOne(id);
				x.append(product.getProductName()).append(",");
				y.append(0).append(",");
			}
		}
		
		ChartData chartData = new ChartData();
		
		chartData.setX(x.toString());
		chartData.setY(new String[]{y.toString()});
		
		return chartData;
		
	}

}
