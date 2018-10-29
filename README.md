#online wholesale system

##首页
front-page -> 精选品牌
hot -> 热销产品
recommend -> 自有品牌(热卖推荐)
###首页Slider编辑
@{/api/web/slider/indexSlider} System -> attachemnt title字段设置为indexSlider
###首页内容编辑，后台content 增加文章，设置urlTitle为以下值
健康专场
<th:block th:replace="/web/component/index/Article-urltitle :: urltitle('health')"></th:block>
辣妈萌宝
<th:block th:replace="/web/component/index/Article-urltitle :: urltitle('motherandchild')"></th:block>
大牌汇聚
<th:block th:replace="/web/component/index/Article-urltitle :: urltitle('brands')"></th:block>
 资讯速递
<th:block th:replace="/web/component/index/Article-urltitle :: urltitle('news')"></th:block>
合作伙伴
<th:block th:replace="/web/component/index/Article-urltitle :: urltitle('partner')"></th:block>


##多价格
System->Customer rank -> new
增加一个customer rank,多一个价格等级，product 表 price1 表示促销价格
当有客人在某一个价格等级时候，这个价格等级因为关联关系不能删除，需先将客人改选其它，才能删除。

###默认价格，促销价，客户等级价格关系
默认价格price1, 促销价price2,客户等级价格 price3(由产品和客人等级获取)
如果促销价>0，则显示默认价格price1,和促销价
如果促销价==0,如果登陆(price3>0),则显示price1(line-through) 和price3. 如果price3==0,则只显示price1


##product 字段说明
summary, 放在产品明细的价格的下面,norms放在跟description并排


#更新增加 

多库存增加操作：
System->Branch->new 
多价格
rank_customer
rank_product_price

customer 表，增加rank_customer_id字段

##增加
supplier
supplier_product
supplier_order
supplier_order_item

product_stock_history

##数据库同ledfocus保持一致
ALTER TABLE `product`
ADD COLUMN `warning_stock`  double(10,2) NULL AFTER `stock`,
ADD COLUMN `volume`  varchar(255) NULL AFTER `weight`;
ADD COLUMN `group`  varchar(255) NULL AFTER `product_name_alias`;

##2018-10-15
修改admin product, promote_price 改为price1,移除price2，增加 group, volume, warning_stock
new_container,edit, new
ALTER TABLE `product`
MODIFY COLUMN `warning_stock`  double(10,2) NULL DEFAULT 0 AFTER `stock`;
update product set warning_stock=0;



