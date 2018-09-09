package com.payment.latipay;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.payment.IPayment;

@Component
public class Latipay implements IPayment {
	public final String api_key = "5o6OmYTBlW";

	// 商户账号用户 id
	private String user_id = "U000000825";
	// 商户账号wallet id
	private String wallet_id = "W000000734";

	// 参数签名，算法为SHA-256 HMAC
	private String signature;
	// 客户端ip
	private String ip = "172.104.114.204";
	// 版本号 "2.0"
	private String version = "2.0";
	// 使用该参数可以获取一个支持微信扫码支付的网页
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

	@Override
	public String aliPcPay(String total_fee, String goods, String goods_detail, String out_trade_no) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String aliMobilePay(String total_fee, String goods, String goods_detail, String out_trade_no) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String wechatPcPay(String total_fee, String goods, String goods_detail, String out_trade_no) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String wechatMobilePay(String total_fee, String goods, String goods_detail, String out_trade_no) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean callBackCheck(HttpServletRequest request) {
		
		boolean ret = false;
		// "merchant_reference": "dsi39ej430sks03",
		// "amount": "120.00",
		// "currency": "NZD",
		// "payment_method": "alipay",
		// "pay_time": "2017-07-07 10:53:50",
		// "status" : "paid",
		// "signature":
		// "14d5b06a2a5a2ec509a148277ed4cbeb3c43301b239f080a3467ff0aba4070e3",

		// 签名文本: merchant_reference + payment_method + status + currency +
		// amount
		String merchant_reference = request.getParameter("merchant_reference");
		String amount = request.getParameter("amount");
		String currency = request.getParameter("currency");
		String payment_method = request.getParameter("payment_method");
		String status = request.getParameter("status");
		String signature = request.getParameter("signature");
		// 签名文本: merchant_reference + payment_method + status + currency +
		// amount
		String msg = merchant_reference + payment_method + status + currency + amount;
		String signatureCheck = Sha256_HMAC.sha256_HMAC(msg, this.api_key);

		if (signature.equals(signatureCheck)) {
			ret =  true;
			// Latipay服务器期望收到此文本
			ret = true;
		}
		return ret;
	}

	@Override
	public boolean notifyCheck(HttpServletRequest request) {
		// 签名文本: merchant_reference + payment_method + status + currency +
		// amount
		String merchant_reference = request.getParameter("merchant_reference") + "";
		String amount = request.getParameter("amount");
		String currency = request.getParameter("currency");
		String payment_method = request.getParameter("payment_method");
		String status = request.getParameter("status") + "";
		String signature = request.getParameter("signature") + "";
		// 签名文本: merchant_reference + payment_method + status + currency +
		// amount
		String msg = merchant_reference + payment_method + status + currency + amount;
		String signatureCheck = Sha256_HMAC.sha256_HMAC(msg, this.api_key);

		String ret = "";
		if (signature.equals(signatureCheck)) {
			// pending, paid, 或 failed
			if (status.equals("paid")) {
				ret = "支付成功。";
			} else {
				ret = "支付失败。";
			}
		} else {
			ret = "订单不存在。";
		}
		return false;
	}

	@Override
	public Double getRate() {
		String urlAddress = "https://api.latipay.net/v2/all_rate?" + getFXRateParam().toString();

		double rate = 5D;
		try {
			HttpsURLConnection conn = (HttpsURLConnection) new URL(urlAddress).openConnection();
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Connection", "Keep-Alive");
			conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
			conn.setRequestProperty("Accept", "application/json");
			conn.setRequestProperty("User-agent", "Mozilla/4.0");
			// 设置文件长度
			// conn.setRequestProperty("Content-Length",
			// String.valueOf(data.length));
			conn.connect();

			BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			StringBuffer sb = new StringBuffer();
			String readline = null;
			while ((readline = rd.readLine()) != null) {
				sb.append(readline);
			}
			JSONObject json = new JSONObject(sb.toString());
			if ("SUCCESS".equals(json.getString("message"))) {
				rate = json.getDouble("alipay");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return rate;
	}

	@Override
	public String getAlipayPcReturnUrl() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAlipayPcNotifyUrl() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAlipayMobileReturnUrl() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAlipayMobileNotifyUrl() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getWechatPcReturnUrl() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getWechatPcNotifyUrl() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getWechatMobileReturnUrl() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getWechatMobileNotifyUrl() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 支付生成获取Token参数
	 * 
	 * @param paymentMethod
	 *            payment_method String 支付方式 wechat, alipay, or onlineBank
	 * @param amount
	 * @param merchantReference
	 * @param productName
	 * @return
	 */
	private JSONObject getParam(String paymentMethod, String amount, String merchantReference, String productName,String returnUrl,String notifyUrl) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("user_id", this.user_id);
		jsonObject.put("wallet_id", this.wallet_id);
		jsonObject.put("payment_method", paymentMethod);
		jsonObject.put("amount", amount);
		jsonObject.put("return_url", returnUrl);
		jsonObject.put("callback_url", notifyUrl);
		// 待签名文本: user_id + wallet_id + amount + payment_method + return_url +
		// callback_url
		// 密钥: api_key
		String message = this.user_id + this.wallet_id + amount + paymentMethod + returnUrl + notifyUrl;
		jsonObject.put("signature", Sha256_HMAC.sha256_HMAC(message, this.api_key));
		jsonObject.put("merchant_reference", merchantReference);
		jsonObject.put("ip", this.getIp());
		jsonObject.put("version", this.getVersion());
		jsonObject.put("product_name", productName);
		jsonObject.put("present_qr", 1);

		return jsonObject;

	}

	/**
	 * 获取汇率参数
	 * 
	 * @param paymentMethod
	 *            payment_method String 支付方式 wechat, alipay, or onlineBank
	 * @param amount
	 * @param merchantReference
	 * @param productName
	 * @return
	 */
	public String getFXRateParam() {
		// 密钥: api_key
		String message = "user_id=" + this.user_id + "&wallet_id=" + this.wallet_id;
		String signature = Sha256_HMAC.sha256_HMAC(message + this.api_key, this.api_key);
		String ret = message + "&signature=" + signature;

		return ret;

	}

	/**
	 * 
	 * @param paymentMethod
	 *            payment_method String 支付方式 wechat, alipay, or onlineBank
	 * @param amount
	 * @param merchantReference
	 * @param productName
	 * @return
	 * @throws IOException
	 */
	public String getPayUrl(String paymentMethod, String amount, String merchantReference, String productName,String returnUrl,String notifyUrl)
			throws IOException {
		String postUrl = "https://api.latipay.net/v2/transaction";

		HttpsURLConnection conn = (HttpsURLConnection) new URL(postUrl).openConnection();
		conn.setDoOutput(true);
		conn.setDoInput(true);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Connection", "Keep-Alive");
		conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
		conn.setRequestProperty("Accept", "application/json");
		conn.setRequestProperty("User-agent", "Mozilla/4.0");
		// 设置文件长度
		String data = getParam(paymentMethod, amount, merchantReference, productName,returnUrl,notifyUrl).toString();
		// conn.setRequestProperty("Content-Length",
		// String.valueOf(data.length));
		conn.connect();
		DataOutputStream writer = new DataOutputStream(conn.getOutputStream());
		writer.writeBytes(data);
		writer.flush();
		writer.close();

		BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));

		StringBuffer sb = new StringBuffer();
		String readline = null;
		while ((readline = rd.readLine()) != null) {
			sb.append(readline);
		}

		// {"code":0,"message":"SUCCESS","nonce":"75ccf6201805280057331af16a14c065464dacff923540a119",
		// "host_url":"https://api.latipay.co.nz/v2/gatewaydata_inapp",
		// "signature":"ab671b606ebe2c387e6caeaad558d6e8d34e819c81918680b25a903565e0eb38"}
		JSONObject json = new JSONObject(sb.toString());
		String nonce = json.getString("nonce");
		String host_url = json.getString("host_url");
		String signature = Sha256_HMAC.sha256_HMAC(nonce + host_url, this.api_key);

		String payUrl = "";
		if (signature.equals(json.getString("signature"))) {
			payUrl = host_url + "/" + nonce;
		}
		return payUrl;

	}


	public static void main(String[] args) throws IOException {
		Latipay latipay = new Latipay();
		// latipay.getPayUrl("wechat", "10", "merchantReference",
		// "productName");
		latipay.getRate();
	}

}
