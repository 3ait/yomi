/**
 * 运费计算
 */

function getFreight(productList){
	//基础费用
	var basePrice = 0;
	//每公斤单价
	var pricePerKg = 5.0;
	
	var freight = basePrice;
	for(var i=0;i< productList.length;i++){
		  if (!isNaN(productList[i].weight)) {
			  freight += productList[i].weight*pricePerKg*productList[i].num
	        }
	  }
	if(freight<5){
		freight = 5.0;
	}
	return freight.toFixed(2);
}


/**
 * 添加到浏览历史记录
 */
function addToHistory(id,productName,productNameAlias,defaultSrc,price){
	
	var history = {
			id:id,
			productName:productName,
			productNameAlias:productNameAlias,
			defaultSrc:defaultSrc,
			price:price,
		}
	var viewHistoryList = getHistory();

	
	if(viewHistoryList==null){
		viewHistoryList = new Array();
	}
	
	//检查是否重复
	var flag = false;
	for(var i=0;i<viewHistoryList.length;i++){
		if(viewHistoryList[i].id==history.id){
			flag = true;
			break;
		}
	}
	if(!flag){
		viewHistoryList.unshift(history);
	}
	
	if(viewHistoryList.length>8){
		viewHistoryList.pop();
	}
	
	localStorage.setItem("historyList", JSON.stringify(viewHistoryList));
}

function getHistory(){
	var historyList = localStorage.getItem("historyList");
	
	return JSON.parse(historyList);
}


utils = {
	setParam : function(name, value) {
		localStorage.setItem(name, value);
	},
	getParam : function(name) {
		return localStorage.getItem(name);
	}
}


//获取产品总数
function getTotalNumber(){
	return localStorage.getItem('totalNumber')=='NaN'?0:localStorage.getItem('totalNumber');
}
//获取产品总价格
function getTotalAmount(){
	return localStorage.getItem('totalAmount')=='NaN'?0: parseFloat(localStorage.getItem('totalAmount')).toFixed(2);
}

/**
 * 列表页，显示哪些产品加入购物车
 */
function showProductAfterAdd(){
	if(cart.getproductlist()!=null){
		for(var i=0;i<cart.getproductlist().length;i++){
				var id = cart.getproductlist()[i].id;
				if(document.getElementById(id)!=null){
					document.getElementById(id).innerHTML = '<i class="fa fa-shopping-cart" aria-hidden="true"></i>(' + cart.getproductlist()[i].num + ")";
				}
			}
		}
}

/**
 * 
 * 添加购物车
 * @param id
 * @param productName
 * @param productNameAlias
 * @param location
 * @param weight
 * @param defaultSrc
 * @param price
 * @param num
 */
function productAdd(id, productName, productNameAlias, location,weight, defaultSrc, price, num) {
	
	//检查登陆
	$.ajax({
		url:"/login/check" ,
		method: "GET",
		data:{
		}
	}).done(function(ret){
		alert("abc");
		if(ret==false){
			window.location.href = "/customer";
		}else{
			alert(ret);
			var product = {
					id : id,
					productName : productName,
					productNameAlias : productNameAlias,
					location : location,
					weight : weight,
					defaultSrc : defaultSrc,
					price : price,
					num : num
			}
			cart.addproduct(product);
			
			
			$(".totalNum").html(getTotalNumber());
			$("#totalAmount").html(getTotalAmount());
		}
	});
	

}

cart = {
	// 向购物车中添加商品
	addproduct : function(product) {
		
		var ShoppingCart = utils.getParam("ShoppingCart");
		if (ShoppingCart == null || ShoppingCart == "") {
			if(product.num<0){
				return;
			}
			
			// 第一次加入商品
			var jsonstr = {
				"productlist" : [ {
					"id" : product.id,
					"productName" : product.productName,
					"productNameAlias" : product.productNameAlias,
					"defaultSrc" : product.defaultSrc,
					"price" : product.price,
					"location" : product.location,
					"weight" : product.weight,
					"num" : product.num
				} ],
				"totalNumber" : product.num,
				"totalAmount" : (product.price * product.num)
			};
			
			localStorage.setItem("totalNumber", jsonstr.totalNumber);
			localStorage.setItem("totalAmount", jsonstr.totalAmount);
			utils.setParam("ShoppingCart", "'" + JSON.stringify(jsonstr));
			
			//添加购物车后更新页面显示数量
			if(document.getElementById(product.id)!=null){
				document.getElementById(product.id).innerHTML = '<i class="fa fa-shopping-cart" aria-hidden="true"></i>(' + 1 + ")";
			}
			
		} else {
			var jsonstr = JSON.parse(ShoppingCart.substr(1, ShoppingCart.length));
			var productlist = jsonstr.productlist;
			var result = false;
			// 查找购物车中是否有该商品
			for ( var i in productlist) {
				if (productlist[i].id == product.id) {
					productlist[i].num = parseInt(productlist[i].num) + parseInt(product.num);
					if(productlist[i].num < 1){
						cart.deleteproduct(product.id);
						document.getElementById(product.id).innerHTML="";
						return;
					}
					
					
					result = true;
					
					//添加购物车后更新页面显示数量
					if(document.getElementById(product.id)!=null){
						document.getElementById(product.id).innerHTML = '<i class="fa fa-shopping-cart" aria-hidden="true"></i>(' + productlist[i].num + ")";
					}
				}
			}
			if (!result) {
				// 没有该商品就直接加进去
				if(product.num<0){
					return;
				}
				productlist.push({
					"id" : product.id,
					"productName" : product.productName,
					"productNameAlias" : product.productNameAlias,
					"location" : product.location,
					"weight" : product.weight,
					"defaultSrc" : product.defaultSrc,
					"price" : product.price,
					"num" : product.num
				});
				
				//添加购物车后更新页面显示数量
				if(document.getElementById(product.id)!=null){
					document.getElementById(product.id).innerHTML = '<i class="fa fa-shopping-cart" aria-hidden="true"></i>(' + 1 + ")";
				}
			}
			// 重新计算总价
			jsonstr.totalNumber = parseInt(jsonstr.totalNumber) + parseInt(product.num);
			jsonstr.totalAmount = parseFloat(jsonstr.totalAmount) + (parseInt(product.num) * parseFloat(product.price));
			localStorage.setItem("totalNumber", jsonstr.totalNumber);
			localStorage.setItem("totalAmount", jsonstr.totalAmount);
			
			// 保存购物车
			utils.setParam("ShoppingCart", "'" + JSON.stringify(jsonstr));
			
			
		}
		

		
	},
	// 修改给买商品数量
	updateproductnum : function(id, num) {
		var ShoppingCart = utils.getParam("ShoppingCart");
		var jsonstr = JSON.parse(ShoppingCart.substr(1, ShoppingCart.length));
		var productlist = jsonstr.productlist;
		
		for ( var i in productlist) {
			if (productlist[i].id == id) {
				jsonstr.totalNumber = parseInt(jsonstr.totalNumber)
						+ (parseInt(num) - parseInt(productlist[i].num));
				jsonstr.totalAmount = parseFloat(jsonstr.totalAmount)
						+ ((parseInt(num) * parseFloat(productlist[i].price)) - parseInt(productlist[i].num)
								* parseFloat(productlist[i].price));
				productlist[i].num = parseInt(num);
				localStorage.setItem("totalNumber", jsonstr.totalNumber);
				localStorage.setItem("totalAmount", jsonstr.totalAmount);
				utils.setParam("ShoppingCart", "'" + JSON.stringify(jsonstr));
				return;
			}
		}
	},
	// 获取购物车中的所有商品
	getproductlist : function() {

		var ShoppingCart = utils.getParam("ShoppingCart");
		if(ShoppingCart==null){
			return;
		};
		var jsonstr = JSON.parse(ShoppingCart.substr(1, ShoppingCart.length));
		var productlist = jsonstr.productlist;
		
		localStorage.setItem("totalNumber", jsonstr.totalNumber);
		localStorage.setItem("totalAmount", jsonstr.totalAmount);
		
		return productlist;
	},
	// 判断购物车中是否存在商品
	existproduct : function(id) {
		var result = false;
		var ShoppingCart = utils.getParam("ShoppingCart");
		if (ShoppingCart != null) {
			var jsonstr = JSON.parse(ShoppingCart
					.substr(1, ShoppingCart.length));
			var productlist = jsonstr.productlist;
			for ( var i in productlist) {
				if (productlist[i].id == id) {
					result = true;
				}
			}
		}
		return result;
	},
	// 删除购物车中商品
	deleteproduct : function(id) {
		var ShoppingCart = utils.getParam("ShoppingCart");
		var jsonstr = JSON.parse(ShoppingCart.substr(1, ShoppingCart.length));
		var productlist = jsonstr.productlist;
		var list = [];
		for ( var i in productlist) {
			if (productlist[i].id == id) {
				jsonstr.totalNumber = parseInt(jsonstr.totalNumber)
						- parseInt(productlist[i].num);
				jsonstr.totalAmount = parseFloat(jsonstr.totalAmount)
						- parseInt(productlist[i].num)
						* parseFloat(productlist[i].price);
			} else {
				list.push(productlist[i]);
			}
		}
		jsonstr.productlist = list;
		localStorage.setItem("totalNumber", jsonstr.totalNumber);
		localStorage.setItem("totalAmount", jsonstr.totalAmount);
		utils.setParam("ShoppingCart", "'" + JSON.stringify(jsonstr));
	}
};
