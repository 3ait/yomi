package com.payment.epayment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Pattern;


public class ApiDemoForJava {
	//商户ID  使用时请用从ePayments获取的替换
	public String merchant_id="7494e3ac8f3545449aa30dbd2b666cdc";  
	//商户秘钥 使用时请用从ePayments获取的替换
	public String sign_key="10885025";
	
	/**
	 * 使用 Map按key进行排序
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
	 *  API 1.0版 获取自定义的加密方式  非微信  key的拼接方式不一样
	 * @param  Object obj 对象
	 * @param String  sign_key  签名秘钥
	 *  @return String  签名
	 * */
	public  String getSignObjForAPI1_0(Map<String,String> paramMap,String sign_key,String sign_type){
		String sign = "";
		try {
			//map排序
			Map<String, String> resultMap = sortMapByKey(paramMap);

			StringBuffer sb = new StringBuffer();
			Set es = resultMap.entrySet();
			Iterator it = es.iterator();
			while(it.hasNext()) {
				Map.Entry entry = (Map.Entry) it.next();
				String k = (String) entry.getKey();
				String value = (String) entry.getValue();
				if (null != value && !"".equals(value) && !"sign".equals(k) && !"key".equals(k)) {
					sb.append(k + "=" + value);
					sb.append("&");
				}
			}
			sb.deleteCharAt(sb.length()-1);//删除最后一个“&”
			sb.append(sign_key);
			System.out.println("加密字符串："+sb.toString());
//			sign = MD5Util.MD5Encode(sb.toString());
			sign = EncryptUtil.doEncrypt(sb.toString(),sign_type);
		}catch (Exception ex){
			ex.printStackTrace();
		}
		return sign;
	}
		
		/**
	 *将对象的属性名与属性值拼接成URL格式的字符串
	 * obj 参数对象 如果字符串中包含中文 或者http 则进行转码
	 * @return String 拼接好的字符串
	 *
	 * */
	public String getEncodeUrlStrFromObj(Map<String,String> paramsMap){
		try {
			StringBuffer sb = new StringBuffer();
			Set es = paramsMap.entrySet();
			Iterator it = es.iterator();
			String pattern = ".*[\u4e00-\u9fa5]+[\\[\\{\\]\\}]+.*$";
			
			while(it.hasNext()) {
				Map.Entry entry = (Map.Entry) it.next();
				String k = (String) entry.getKey();
				String value = (String) entry.getValue();
				if (null != value && !"".equals(value) && !"sign".equals(k) && !"key".equals(k)) {
					if(value.indexOf("http:")!=-1 || value.indexOf("https:")!=-1 ||value.indexOf("{")!=-1|| Pattern.matches(pattern, value)){ //验证字符串是否包含中文或者是否为网址
						sb.append(k + "=" + URLEncoder.encode(value,"UTF-8"));
						sb.append("&");
					}
					else{
						sb.append(k + "=" + value);
						sb.append("&");
					}
				}
			}
			String encodeUrlString=sb.toString();
			return encodeUrlString.substring(0, encodeUrlString.length()-1);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	
		/***
	 *  二维码支付方式，后台调用方法  
	  *1 商户 ID merchant_id string 商户 ID (由ePayments进行分配)N
	 *2 订单号 increment_id int 订单号 N
	 *3 订单金额 grandtotal Number(8,2) 订单金额（范围为0.01～1000000.00）N
	 *4 币种 currency String(256) 币种(参考币种详情) N
	 *5 返回链接 return_url URL 交易付款成功之后，则结果返回URL，仅适用于立即返回处理结果的接口。N
	 *6 通知链接 notify_url URL 针对该交易支付成功之后的通知接收 URL。N
	 *7 支付标题 subject String(256) 支付标题 Y
	 *8 支付描述 describe String(256) 商品描述 Y
	 *9 签名方式 sign_type string 签名方式（MD5） N
	 *10 签名 signature string 签名(详情参考签名加密) N
	 *11 请求服务 service 50 请求方式(参考请求服务类型) N
	 *
	 * */
	
	public  void   codePay_DEMO(String increment_id) {
		Map<String,String> params =new HashMap<String,String>();
		params.put("merchant_id", this.merchant_id);//分配的商户ID
		params.put("increment_id",increment_id);//商户系统的订单号	 “C222333”测试订单号，实际测试请使用自己的订单号
		params.put("currency", "NZD");
		params.put("grandtotal","0.1");//订单金额
		params.put("nonce_str","nonce_str");//random string
		params.put("return_url", "http://xxxxx/test/return_url");  
		params.put("notify_url", "http://127.0.0.1/test/notify_url");//支付异步结果通知地址，在支付完成后支付结构会调用此结果推送支付结果
		params.put("subject", "test");//订单描述
		params.put("payment_channels", "WECHAT");
		params.put("describe","{\"goods_detail\":[{ \"goods_name\":\"product name\",\"quantity\":1}]}");
		params.put("service", "create_scan_code");
		params.put("signature", getSignObjForAPI1_0(params, this.sign_key,"SHA2"));  //计算签名  详细见getSignObjForAPI1_0方法  "845943" 为商户分配的sign_key
		params.put("sign_type", "SHA2");
	    String paramsStr = getEncodeUrlStrFromObj(params);
	    String getCodeUrl = "https://www.kiwifast.com/api/v1/info/smartpay"; //接口调用请求地址
	    String JsonString=GetPostUtil.sendGet(getCodeUrl, paramsStr);
	    System.out.println("调用接口返回结果"+JsonString);
	    
//	    if (acceptInfo.response.code == "0" && acceptInfo.response.message == "success") {
//            var code_url = acceptInfo.response.qrcode_url;  //该参数为二维码的支付地址  转换成二维码图片 可以用于微信扫码支付
//            //TODO  。。。。。
//            //请求成功处理
//        }
//        else {
//           	//请求结果失败处理（未成功生成支付的二维码连接）
//        }
	
	}   
	/**
		H5支付 需要再微信浏览器访问的支付方式  （例如微商城）
		 *1 商户 ID merchant_id string 商户 ID (由ePayments进行分配)N
	 *2 订单号 increment_id int 订单号 N
	 *3 订单金额 grandtotal Number(8,2) 订单金额（范围为0.01～1000000.00）N
	 *4 币种 currency String(256) 币种(参考币种详情) N
	 *5 返回链接 return_url URL 交易付款成功之后，则结果返回URL，仅适用于立即返回处理结果的接口。N
	 *6 通知链接 notify_url URL 针对该交易支付成功之后的通知接收 URL。N
	 *7 支付标题 subject String(256) 支付标题 Y
	 *8 支付描述 describe String(256) 商品描述 Y
	 *9 签名方式 sign_type string 签名方式（MD5） N
	 *10 签名 signature string 签名(详情参考签名加密) N
	 *11 请求服务 service 50 请求方式(参考请求服务类型) N
		*/
	 public void  MWEB_PAY_DEMO(String increment_id) {
		 Map<String,String> params =new HashMap<String,String>();
		params.put("merchant_id", this.merchant_id);//分配的商户ID
		params.put("increment_id",increment_id);//商户系统的订单号	 “C222333”测试订单号，实际测试请使用自己的订单号
		params.put("currency", "NZD");
		params.put("nonce_str","nonce_str");//random string
		params.put("grandtotal","0.01");//订单金额
		params.put("return_url", "http://xxxxx/test/return_url");  
		params.put("notify_url", "http://127.0.0.1/test/notify_url");//支付异步结果通知地址，在支付完成后支付结构会调用此结果推送支付结果
		params.put("subject", "test");//订单描述
		 params.put("describe","{\"goods_detail\":[{ \"goods_name\":\"product name\",\"quantity\":1}]}");
		params.put("service", "create_smart_pay");
		params.put("signature", getSignObjForAPI1_0(params, this.sign_key,"SHA2"));  //计算签名  详细见getSignObjForAPI1_0方法  "845943" 为商户分配的sign_key
		params.put("sign_type", "SHA2");
	    String paramsStr = getEncodeUrlStrFromObj(params);
	    String payUrl = "https://www.kiwifast.com/api/v1/info/smartpay?"+paramsStr; //接口调用请求地址
	    System.out.println("调用请求地址："+payUrl);
	      //后面使用PAY进行重定向的界面跳转  实现页面跳转支付。 PC端网页不适用
	}
		
	/**
		刷卡支付  
		 *  对外刷卡支付接口
	 *1 商户 ID merchant_id string 商户 ID (由ePayments进行分配)N
	 *2 订单号 increment_id int 订单号 N
	 *3 订单金额 grandtotal Number(8,2) 订单金额（范围为0.01～1000000.00）N
	 *4 币种 currency String(256) 币种(参考币种详情) N
	 * 4 授权码 auth_code String(18) 授权码  18为数字  N
	 *6 通知链接 notify_url URL 针对该交易支付成功之后的通知接收 URL。N
	 *7 支付标题 subject String(256) 支付标题 Y
	 *8 支付描述 describe String(256) 商品描述 Y
	 *9 签名方式 sign_type string 签名方式（MD5） N
	 *10 签名 signature string 签名(详情参考签名加密) N
	 *11 请求服务 service 50 请求方式(参考请求服务类型) N
	  * 返回：
	 * response:{
	 *      code:"SUCCESS",
	 *      message:"OK",
	 *      increment_id:
	 *      merchant_id:
	 *      grandtotal:
	 *      currency
	 * }
			*/	
	public void MICRO_PAY_DEMO(String increment_id) {
		 Map<String,String> params =new HashMap<String,String>();
		 params.put("merchant_id",this.merchant_id);
		 params.put("increment_id",increment_id);//商户系统的订单号
		 params.put("currency","NZD");
		 params.put("nonce_str","nonce_str");//random string
		 params.put("grandtotal","0.01");//订单金额
		 params.put("auth_code","123456789012345678");//微信支付的授权码（微信钱包付钱页面的条形码）
		 params.put("notify_url","http://demo.yourservice.com/test/notify_url");//异步回调地址支付结果会请求该该地址通知支付结果
		 params.put("subject","micropay test");
		 params.put("describe","{\"goods_detail\":[{ \"goods_name\":\"product name\",\"quantity\":1}]}");
		 params.put("service","create_micro_pay");
		 params.put("signature",getSignObjForAPI1_0(params, this.sign_key,"SHA2"));
		 params.put("sign_type","SHA2");
		 String paramsStr = getEncodeUrlStrFromObj(params);
		 String micropayUrl = "https://www.kiwifast.com/api/v1/info/smartpay?"; //刷卡支付请求地址
		 String resultStirng=GetPostUtil.sendGet(micropayUrl, paramsStr);
		 System.out.println("刷卡支付结果："+resultStirng);
	}

	/**
		订单查询  查询订单
		采用POST形式请求  传入商户ID，订单号 查询该订单的状态
		*/
	 public void queryOrder(String increment_id){
		 Map<String,String> params =new HashMap<String,String>();
		 params.put("merchant_id",this.merchant_id);
		 params.put("increment_id",increment_id);//商户系统的订单号
		 params.put("nonce_str","nonce_str");//random string
		 params.put("service","create_trade_query");//商户系统的订单号
		 params.put("signature",getSignObjForAPI1_0(params, this.sign_key,"SHA2"));//商户系统的订单号
		 params.put("sign_type","SHA2");//商户系统的订单号
		 String paramsStr = getEncodeUrlStrFromObj(params);
		 String queryUrl="https://www.kiwifast.com/api/v1/info/smartpay";
		 String queryResult=GetPostUtil.sendPost(queryUrl, paramsStr);
		 System.out.println("订单查询结果："+queryResult);
	 }
		
	   /** # 字段名 变量名 类型 说明 可空
	 *    1  商户      ID merchant_id string 商户 ID N
	 *    2  订单号    increment_id int 订单号 N
	 *    3  币种       currency String(256) 币种 N
	 *    4  退款金额   refund_fee Number(8,2) 退款金额 N
	 *    5  退款原因   refund_reason String(256) 退款原因说明 Y
	 *    6  签名方式   sign_type string 签名方式（MD5） N
	 *    7  签名       signature string 签名(详情参考签名加密) N
	 *    8  请求服务   service string 请求方式(参考请求服务类型) N
	 *    9  交易号     trade_no String(64) 交易号 Y
	 	
	 *    10 退款渠道   payment_channels    String 退款渠道（详细参考交易渠道） N*
	 *    11 退款账号 buyer_payment_account string 退款账号 Y  
	 *   12 创建时间 created_at Date 退 款 创 建 的 时 间 。 格 式 为 yyyy-MM-dd HH:mm:ss  Y
	 	13	refund_trade_no 退款交易号：如果传入则为重新提交退款  Y  refund_trade_no为上次退款返回的参数
	 
	      上面参数为Y 可空的 可以不传入
	      
	      *  返回参数
	 *  # 字段名 变量名 类型 说明 可空
	 *   1 退款金额 refund_fee Number(8,2) 退款金额 N
	 *   2 退款交易号 refund_trade_no String(64) 退款交易号 N
	 *   3 退款支付时 间 refund_pay_time Date 退 款 支 付 的 时 间 。 格 式 为 yyyy-MM-dd HH:mm:ss N
	 *   4 退款账号 buyer_payment_account string 退款账号 Y
	 *  例如：{"response":{"refund_fee":"88.88","refund_trade_no":"2088101117955611","refund_pay_time":"20
	 *           16-06-21 11:00:00","buyer_payment_account":"124544531"}}
	    */
	 public void refund(String increment_id) {
		 Map<String,String> params =new HashMap<String,String>();
		 params.put("merchant_id",this.merchant_id);
		 params.put("increment_id",increment_id);//商户系统的订单号
		 params.put("currency","NZD");//交易币种
		 params.put("nonce_str","nonce_str");//random string
		 params.put("refund_fee","0.01");//退款金额
		 params.put("service","create_trade_refund");//退款请求代码 目前指定：create_trade_refund
		 params.put("refund_reason","退款原因");//退款原因
		 params.put("signature",getSignObjForAPI1_0(params, this.sign_key,"SHA2"));//商户系统的订单号
		 params.put("sign_type","SHA2");//商户系统的订单号
	     String refundUrl = "https://www.kiwifast.com/api/v1/info/smartpay";
		 String paramsStr = getEncodeUrlStrFromObj(params);
		 String refundResult=GetPostUtil.sendPost(refundUrl, paramsStr);
		 System.out.println("订单退款结果："+refundResult);
	}

	/**
	 * 退款查询接口
	 *	 *  传入参数：
	 *  merchant_id  String
	 *  increment_id 商户订单号
	 *  sign_type  加密方式 默认MD5
	 *  signature  签名
	 *  service 请求方式
	 *  refund_trade_no  退款时返回的退款号
	 * */
	  public void refundQuery(String increment_id,String refund_trade_no) {
		  Map<String,String> params =new HashMap<String,String>();
		  params.put("merchant_id",this.merchant_id);
		  params.put("increment_id",increment_id);//商户系统的订单号
		  params.put("nonce_str","nonce_str");//random string
		  params.put("refund_trade_no",refund_trade_no);//退款成功后返回的退款交易号
		  params.put("service","create_trade_refund_query");//请求服务代码
		  params.put("signature",getSignObjForAPI1_0(params, this.sign_key,"SHA2"));//签名
		  params.put("sign_type","SHA2");
	      String  queryUrl = "https://www.kiwifast.com/api/v1/info/smartpay";
	      String paramsStr = getEncodeUrlStrFromObj(params);
		  String refundResult=GetPostUtil.sendPost(queryUrl, paramsStr);
		  System.out.println("订单退款查询结果："+refundResult);
	}
	    
	 //以下 两个webserver接口 为商户提供的对外访问的hTTP接口，用来接收支付结构返回的支付结果 及H5支付跳转的处理页面   
	    //异步通知接口
	//	router.post("/notify_url", function (req, res) {
	//	    logger.info("(客户端)支付异步获取通知结果：" + JSON.stringify(req.body));
	//	    res.send("success");	
	//	});
		//同步通知接口
	//	router.get("/return_url", function (req, res) {
	//	    console.info("收到同步通知：" + JSON.stringify(req.query));
	//
	//	});

	
	public static void main(String[] args) {
		try {
			ApiDemoForJava api=new ApiDemoForJava();
			BufferedReader strin=new BufferedReader(new InputStreamReader(System.in));  
			System.out.println("请输入支付订单编号：");
			String incrment_id = strin.readLine(); 
			api.codePay_DEMO(incrment_id); //二维码支付
			System.out.println("请输入查询订单编号：");
			incrment_id = strin.readLine(); 
			api.queryOrder(incrment_id);
			System.out.println("请输入退款订单编号：");
			incrment_id = strin.readLine(); 
			api.refund(incrment_id);
			System.out.println("请输入已退款订单编号：");
			incrment_id = strin.readLine(); 
			System.out.println("请输入退款码：");
			String refund_trade_no = strin.readLine(); 
			api.refundQuery(incrment_id, refund_trade_no);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
