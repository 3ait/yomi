package com.payment.iemoney;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.payment.IPayment;
import com.payment.epayment.MapKeyComparator;
import com.plugin.utils.DateHelper;
import com.plugin.utils.Md5;

@Component
public class IEMoney implements IPayment {

	private static Logger logger = LogManager.getLogger(IEMoney.class);
	private String gateway = "https://mypay.iemoney.co.nz/api/online";
	private String apiKey = "e560fb2e61e4d1fe6a11c278388cb965";
	
	
	private String payRate = "1.4";
	int mid = 10224;
	int tid = 10224;

	/**
	 *   'IE0011' = qrcode_alipay; 'IE0012' = web_alipay; 'IE0013' =
	 *   wap_alipay; 'IE0021' = qrcode_wechat; 'IE0022' = wap_aliy
	 */
	@Override
	public String aliPcPay(String total_fee, String goods, String goods_detail, String out_trade_no) {
		return getPayUrl(total_fee, goods, goods_detail, out_trade_no, "IE0012",getAlipayPcReturnUrl(), getAlipayPcNotifyUrl());
	}

	
	@Override
	public String aliMobilePay(String total_fee, String goods, String goods_detail, String out_trade_no) {
		return getPayUrl(total_fee, goods, goods_detail, out_trade_no, "IE0015",getAlipayPcReturnUrl(), getAlipayPcNotifyUrl());
	}


	@Override
	public String wechatPcPay(String total_fee, String goods, String goods_detail, String out_trade_no) {
		return getPayUrl(total_fee, goods, goods_detail, out_trade_no, "IE0021",getAlipayPcReturnUrl(), getAlipayPcNotifyUrl());
	}


	@Override
	public String wechatMobilePay(String total_fee, String goods, String goods_detail, String out_trade_no) {
		return getPayUrl(total_fee, goods, goods_detail, out_trade_no, "IE0012",getAlipayPcReturnUrl(), getAlipayPcNotifyUrl());
	}


	

	@Override
	public boolean callBackCheck(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean notifyCheck(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public Double getRate() {
		Double rate = 5D;
		String  rateUrl = "https://mypay.iemoney.co.nz/rate/query_rate";
		try {
			HttpsURLConnection conn = (HttpsURLConnection) new URL(rateUrl).openConnection();
			conn.connect();
			BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			StringBuffer sb = new StringBuffer();
			String readline = null;
			while ((readline = rd.readLine()) != null) {
				sb.append(readline);
			}
			JSONObject json = new JSONObject(sb.toString());
			if(json.getString("is_success").equals("TRUE")){
				rate = json.getJSONArray("extra").getJSONObject(1).getDouble("rate");
			}
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return rate;
	}

	
	@Override
	public String getAlipayPcReturnUrl() {
		return "http://www.ulife.co.nz/customer/payment/return";
	}

	@Override
	public String getAlipayPcNotifyUrl() {
		return "http://www.ulife.co.nz/customer/payment/notice";
	}
	
	@Override
	public String getAlipayMobileReturnUrl() {
		return "http://www.ulife.co.nz/m/api/customer/payment/return";
		
	}

	@Override
	public String getAlipayMobileNotifyUrl() {
		return "http://www.ulife.co.nz/m/api/customer/payment/notice";
	}
	
	
	@Override
	public String getWechatPcReturnUrl() {
		return "http://www.ulife.co.nz/customer/payment/return";
	}

	@Override
	public String getWechatPcNotifyUrl() {
		return "http://www.ulife.co.nz/customer/payment/notice";
	}

	@Override
	public String getWechatMobileReturnUrl() {
		return "http://www.ulife.co.nz/m/api/customer/payment/return";
	}
	
	@Override
	public String getWechatMobileNotifyUrl() {
		return "http://www.ulife.co.nz/m/api/customer/payment/notice";
	}

	/**
	 * 支付请求
	 * @param total_fee
	 * @param goods
	 * @param goods_detail
	 * @param out_trade_no
	 * @param payCode
	 * @return
	 */
	private String getPayUrl(String total_fee, String goods, String goods_detail, String out_trade_no,String payCode,String returnUrl,String notifyUrl){
		String retUrl = "";
		try {

			HttpsURLConnection conn = (HttpsURLConnection) new URL(gateway).openConnection();
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Connection", "Keep-Alive");
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
			conn.setRequestProperty("Accept", "application/json");
			conn.setRequestProperty("User-agent", "Mozilla/4.0");
			// 设置文件长度
			String data = this.getFormParam(total_fee, goods, goods_detail, out_trade_no,payCode, returnUrl,notifyUrl);
			// conn.setRequestProperty("Content-Length",
			// String.valueOf(data.length));
			conn.connect();
			DataOutputStream writer = new DataOutputStream(conn.getOutputStream());
			writer.write(data.getBytes());
			writer.flush();
			writer.close();

			BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			StringBuffer sb = new StringBuffer();
			String readline = null;
			while ((readline = rd.readLine()) != null) {
				sb.append(readline);
			}
			System.out.println(sb.toString());
			JSONObject json = new JSONObject(sb.toString());
			if ("SUCCESS".equals(json.getString("message"))) {
				retUrl = json.getJSONObject("extra").getString("pay_url");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return retUrl;
	}

	/**
	 * 获取表单参数
	 * 
	 * @param total_fee
	 * @param rmb_fee
	 * @param goods
	 * @param goods_detail
	 * @param out_trade_no
	 * @param pay_type
	 *            'IE0011' = qrcode_alipay; 'IE0012' = web_alipay; 'IE0013' =
	 *            wap_alipay; 'IE0021' = qrcode_wechat; 'IE0022' = wap_alipay
	 * @param sign
	 * @return param String
	 */
	private String getFormParam(String total_fee, String goods, String goods_detail, String out_trade_no,
			String pay_type, String returnUrl,String notify_url) {
		StringBuffer sb = new StringBuffer();
		sb.append("mid").append("=").append(mid).append("&");
		sb.append("version").append("=").append("v1").append("&");
		sb.append("expired").append("=").append("3600").append("&");
		sb.append("total_fee").append("=").append(total_fee).append("&");
		// sb.append("rmb_fee").append("=").append(rmb_fee).append("&");
		sb.append("pay_rate").append("=").append(payRate).append("&");
		sb.append("out_trade_no").append("=").append(out_trade_no).append("&");
		sb.append("pay_type").append("=").append(pay_type).append("&");
		sb.append("goods_detail").append("=").append(goods_detail).append("&");
		sb.append("tid").append("=").append(tid).append("&");
		sb.append("goods").append("=").append(goods).append("&");
		sb.append("return_url").append("=").append(returnUrl).append("&");
		sb.append("notify_url").append("=").append(notify_url).append("&");

		// **构造签名参数
		Map<String, String> signMap = new HashMap<>();
		signMap.put("mid", mid + "");
		signMap.put("version", "v1");
		signMap.put("expired", "3600");
		signMap.put("total_fee", total_fee + "");
		//实际收取的手续费
		signMap.put("pay_rate", payRate);
		signMap.put("out_trade_no", out_trade_no);
		signMap.put("pay_type", pay_type);
		signMap.put("tid", tid + "");
		signMap.put("version", "v1");
		signMap.put("goods", goods);
		signMap.put("goods_detail", goods_detail);
		signMap.put("return_url", returnUrl);
		signMap.put("notify_url", notify_url);

		sb.append("sign").append("=").append(this.getSign(signMap));

		return sb.toString();
	}

	/**
	 * 生成签名
	 * 
	 * @param paramMap
	 * @param sign_key
	 * @return
	 */
	public String getSign(Map<String, String> paramMap) {
		String sign = "";
		try {
			// map排序
			Map<String, String> resultMap = sortMapByKey(paramMap);

			StringBuffer sb = new StringBuffer();
			Set es = resultMap.entrySet();
			Iterator it = es.iterator();
			while (it.hasNext()) {
				Map.Entry entry = (Map.Entry) it.next();
				String k = (String) entry.getKey();
				String value = (String) entry.getValue();
				if (null != value && !"".equals(value) && !"sign".equals(k) && !"key".equals(k)) {
					sb.append(k + "=" + value);
					sb.append("&");
				}
			}
			sb.deleteCharAt(sb.length() - 1);// 删除最后一个“&”
			sb.append(this.apiKey);
			System.out.println("Sign:" + sb.toString());
			sign = Md5.getMd5String(sb.toString());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return sign;
	}

	/**
	 * 使用 Map按key进行排序
	 * 
	 * @param map
	 * @return
	 */
	private static Map<String, String> sortMapByKey(Map<String, String> map) {
		if (map == null || map.isEmpty()) {
			return null;
		}

		Map<String, String> sortMap = new TreeMap<String, String>(new MapKeyComparator());

		sortMap.putAll(map);

		return sortMap;
	}

	
	/**
	 * @param pay_type 
	 * 			'IE0011' = qrcode_alipay; 'IE0012' = web_alipay; 'IE0013' =
	 *            wap_alipay; 'IE0021' = qrcode_wechat; 'IE0022' = wap_alipay
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {

		IEMoney ieMoney = new IEMoney();
//		String s = ieMoney.aliPcPay("1.1", "goods", "good deatils",100 + "-" + DateHelper.getYYYYMMDDhhmmss());
		
		System.out.println(ieMoney.getRate());
	}

}
