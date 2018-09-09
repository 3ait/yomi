package com.payment.epayment;

import java.net.URLEncoder;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class EPayment {

	// 商户ID 使用时请用从ePayments获取的替换
	//33GO的支付
	public String merchant_id = "64201802260834120371413";
//	public String merchant_id = "";
	// 商户秘钥 使用时请用从ePayments获取的替换
	public String sign_key = "103b5dd17b4843ceb9a6660a5800a23a";
//	public String sign_key = "";
	String encode = "MD5";
	/**
	 * 使用 Map按key进行排序
	 * 
	 * @param map
	 * @return
	 */
	public static Map<String, String> sortMapByKey(Map<String, String> map) {
		if (map == null || map.isEmpty()) {
			return null;
		}

		Map<String, String> sortMap = new TreeMap<String, String>(new MapKeyComparator());

		sortMap.putAll(map);

		return sortMap;
	}

	/**
	 *
	 * API 1.0版 获取自定义的加密方式 非微信 key的拼接方式不一样
	 * 
	 * @param Object
	 *            obj 对象
	 * @param String
	 *            sign_key 签名秘钥
	 * @return String 签名
	 */
	public String getSignObjForAPI1_0(Map<String, String> paramMap, String sign_key, String sign_type) {
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
			sb.append(sign_key);
			// sign = MD5Util.MD5Encode(sb.toString());
			sign = EncryptUtil.doEncrypt(sb.toString(), sign_type);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return sign;
	}

	/**
	 * 将对象的属性名与属性值拼接成URL格式的字符串 obj 参数对象 如果字符串中包含中文 或者http 则进行转码
	 * 
	 * @return String 拼接好的字符串
	 *
	 */
	public String getEncodeUrlStrFromObj(Map<String, String> paramsMap) {
		try {
			StringBuffer sb = new StringBuffer();
			Set es = paramsMap.entrySet();
			Iterator it = es.iterator();
			String pattern = ".*[\u4e00-\u9fa5]+[\\[\\{\\]\\}]+.*$";

			while (it.hasNext()) {
				Map.Entry entry = (Map.Entry) it.next();
				String k = (String) entry.getKey();
				String value = (String) entry.getValue();
				if (null != value && !"".equals(value) && !"sign".equals(k) && !"key".equals(k)) {
					if (value.indexOf("http:") != -1 || value.indexOf("https:") != -1 || value.indexOf("{") != -1
							|| Pattern.matches(pattern, value)) { // 验证字符串是否包含中文或者是否为网址
						sb.append(k + "=" + URLEncoder.encode(value, "UTF-8"));
						sb.append("&");
					} else {
						sb.append(k + "=" + value);
						sb.append("&");
					}
				}
			}
			String encodeUrlString = sb.toString();
			return encodeUrlString.substring(0, encodeUrlString.length() - 1);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * H5支付 需要再微信浏览器访问的支付方式 （例如微商城） 1 商户 ID merchant_id string 商户 ID
	 * (由ePayments进行分配)N 2 订单号 increment_id int 订单号 N 3 订单金额 grandtotal
	 * Number(8,2) 订单金额（范围为0.01～1000000.00）N 4 币种 currency String(256)
	 * 币种(参考币种详情) N 5 返回链接 return_url URL 交易付款成功之后，则结果返回URL，仅适用于立即返回处理结果的接口。N 6
	 * 通知链接 notify_url URL 针对该交易支付成功之后的通知接收 URL。N 7 支付标题 subject String(256)
	 * 支付标题 Y 8 支付描述 describe String(256) 商品描述 Y 9 签名方式 sign_type string
	 * 签名方式（MD5） N 10 签名 signature string 签名(详情参考签名加密) N 11 请求服务 service 50
	 * 请求方式(参考请求服务类型) N
	 * @param memo 
	 * @param fee 
	 */
	public String MWEB_PAY_DEMO(String increment_id, Double fee, String memo) {
		Map<String, String> params = getSingParams(increment_id, fee, memo);
		params.put("signature", getSignObjForAPI1_0(params, sign_key, encode));    // 计算签名
																						// 为商户分配的sign_key
		params.put("sign_type", encode);
		String paramsStr = getEncodeUrlStrFromObj(params);
		String payUrl = "https://www.kiwifast.com/api/v1/info/smartpay?" + paramsStr; // 接口调用请求地址
		return payUrl;
	}

	/**
	 * 构造签名方式
	 */
	public Map<String, String> getSingParams(String increment_id, Double fee, String memo){
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("merchant_id", this.merchant_id);// 分配的商户ID
		params.put("increment_id", increment_id);// 商户系统的订单号
													// “C222333”测试订单号，实际测试请使用自己的订单号
		params.put("currency", "NZD");
		params.put("nonce_str", "nonce_str");// random string
		params.put("grandtotal", fee+"");// 订单金额
		params.put("return_url", "http://33go.nz/m/cart/pay/sync/ret");
		params.put("notify_url", "http://33go.nz/m/cart/pay/async/ret");// 支付异步结果通知地址，在支付完成后支付结构会调用此结果推送支付结果
		params.put("subject", memo);// 订单描述
		params.put("describe", memo);
		params.put("service", "create_smart_pay");
		
		return params;
	}
	
	/**
	 * 获取签名
	 * @param params
	 * @return
	 */
	public String getSign(String increment_id, Double fee, String memo){
		
		return getSignObjForAPI1_0(getSingParams(increment_id, fee, memo), this.sign_key, encode);
	}

	public String getSign(HttpServletRequest request){
		EPayment ePayment = new EPayment();
		Map<String, String> params = new HashMap<String, String>();
		
		
		params.put("return_code", request.getParameter("return_code"));// 分配的商户ID
		params.put("merchant_id", request.getParameter("merchant_id"));// 分配的商户ID
		params.put("increment_id", request.getParameter("increment_id"));// 商户系统的订单号
		params.put("notify_time", request.getParameter("notify_time"));
		params.put("payment_time", request.getParameter("payment_time"));
		params.put("payment_channels", request.getParameter("payment_channels"));
		params.put("code", request.getParameter("code"));
		params.put("message", request.getParameter("message"));
		params.put("currency", request.getParameter("currency"));
		params.put("grandtotal", request.getParameter("grandtotal"));
		params.put("nonce_str", request.getParameter("nonce_str"));
		params.put("result_code", request.getParameter("result_code"));
		params.put("trade_status", request.getParameter("trade_status"));
		params.put("rate", request.getParameter("rate"));
		
		return ePayment.getSignObjForAPI1_0(params, this.sign_key, encode);
		
		
	}
	/**
	 1	商户 ID	merchant_id	String	商户 ID (由ePayments分配)	N
	2	币种	currency	String(16)	查询币种(参考币种详情)	N
	3	支付通道	payment_channels	String	支付渠道 WECHAT\ALIPAY\JDPAY\BESTPAY	N
	4	经营类型	business_type	String	经营类型取值：ONLINE 线上 、INSTORE 线下 支付宝必须传入	Y
	5	随机字符串	nonce_str	String	随机字符串	N
	6	签名方式	sign_type	String(8)	签名方式（MD5）	N
	7	签名	signature	String(64)	签名(详情参考签名加密)	N
	8	请求服务	service	String(64)	请求方式(参考请求服务类型) create_rate_query	N
	 */
	public Double getRate(){
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("merchant_id", this.merchant_id);//  
		params.put("currency", "NZD");//  
		params.put("payment_channels", "WECHAT");//  
		params.put("business_type", "ONLINE");//  
		params.put("nonce_str", Calendar.getInstance().getTimeInMillis()+"");//  
		params.put("service", "create_rate_query");// 
		
		params.put("signature", getSignObjForAPI1_0(params, sign_key, encode));    // 计算签名
		params.put("sign_type", this.encode);//  
		
																						// 为商户分配的sign_key
		String paramsStr = getEncodeUrlStrFromObj(params);
		String url = "https://www.kiwifast.com/api/v1/info/smartpay";
		String ret = GetPostUtil.sendGet(url, paramsStr);
		//{"code":"0","message":"汇率查询成功","rate_time":"20180308","currency":"NZD","rate":4.6317,"nonce_str":"9lzyvkyai0m62gm","signtaure":"49184c16230c9fc390165238fc552a5b","sign_type":"MD5"}

		return new JSONObject(ret).getDouble("rate");
	}
	
	
	public String appPay(){
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("merchant_id", this.merchant_id);//  
		params.put("increment_id", Calendar.getInstance().getTimeInMillis()+"");//  
		params.put("sub_appid", "?");//  
		params.put("grandtotal", "0.01");//  
		params.put("currency", "NZD");//  
		//WECHAT 微信
		//ALIPAY 支付宝
		//JDPAY 京东
		//BESTPAY 翼支付
		params.put("payment_channels", "WECHAT");//  
		
		
		params.put("payment_channels", "WECHAT");//  
		params.put("business_type", "ONLINE");//  
		params.put("nonce_str", Calendar.getInstance().getTimeInMillis()+"");//  
		params.put("service", "create_rate_query");// 
		
		params.put("signature", getSignObjForAPI1_0(params, sign_key, encode));    // 计算签名
		params.put("sign_type", this.encode);//  
		
																						// 为商户分配的sign_key
		String paramsStr = getEncodeUrlStrFromObj(params);
		String url = "https://www.kiwifast.com/api/v1/info/smartpay";
		String ret = GetPostUtil.sendGet(url, paramsStr);
		
		return ret;
	}
	
	public static void main(String[] args){
		EPayment ePayment = new EPayment();
		ePayment.getRate();
	}
}
