package com.payment.latipay;

/**
 * 
 * @author yaoliang
 * 2018/05/24
 * Pay Demo
 */
public class LatipayConfig {
	
	//商户账号用户 id
	private String user_id = "U000000825";
	//商户账号wallet id
	private String wallet_id = "W000000734";
	//支付完成后浏览器继续加载的地址
	private String return_url;
	//支付完成后异步通知地址
	private String callback_url;
	//参数签名，算法为SHA-256 HMAC
	private String signature;
	//客户端ip
	private String ip="127.0.0.1";
	//版本号 "2.0"
	private String version="2.0";
	//使用该参数可以获取一个支持微信扫码支付的网页
	private String present_qr;
	
	
	
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getWallet_id() {
		return wallet_id;
	}
	public void setWallet_id(String wallet_id) {
		this.wallet_id = wallet_id;
	}
	public String getReturn_url() {
		return return_url;
	}
	public void setReturn_url(String return_url) {
		this.return_url = return_url;
	}
	public String getCallback_url() {
		return callback_url;
	}
	public void setCallback_url(String callback_url) {
		this.callback_url = callback_url;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getPresent_qr() {
		return present_qr;
	}
	public void setPresent_qr(String present_qr) {
		this.present_qr = present_qr;
	}

	
	
}
