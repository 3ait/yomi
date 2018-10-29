package com.payment;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

/**
 * 
 * @author leo
 * payment Interface
 */
@Component
public interface IPayment {

	
	
	public String aliPcPay(String total_fee, String goods, String goods_detail, String out_trade_no);
	public String aliMobilePay(String total_fee, String goods, String goods_detail, String out_trade_no);
	
	
	public String wechatPcPay(String total_fee,String goods,String goods_detail,String out_trade_no);
	public String wechatMobilePay(String total_fee,String goods,String goods_detail,String out_trade_no);
	
	
	public String callBackCheck(HttpServletRequest request);
	
	public String notifyCheck(HttpServletRequest request);
	
	public Double getRate();
	
	/**
	 * ali PC 返回
	 * @return
	 */
	public String getAlipayPcReturnUrl();
	/**
	 * ali PC 返回
	 * @return
	 */
	public String getAlipayPcNotifyUrl();
	/**
	 * ali Mobile 返回
	 * @return
	 */
	public String getAlipayMobileReturnUrl();
	/**
	 * ali Mobile 返回
	 * @return
	 */
	public String getAlipayMobileNotifyUrl();
	
	/**
	 * 微信 PC 返回
	 * @return
	 */
	public String getWechatPcReturnUrl();
	/**
	 * 微信 PC 通知
	 * @return
	 */
	public String getWechatPcNotifyUrl();
	/**
	 * 微信 手机 返回
	 * @return
	 */
	public String getWechatMobileReturnUrl();
	/**
	 * 微信 手机 通知
	 * @return
	 */
	public String getWechatMobileNotifyUrl();
	
	
	
	
}
