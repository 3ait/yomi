<template>
  <div class="order-item">
    <van-panel :title="title" :desc="createTime" :status="status">
      <div>
        <div v-if="orderItems.length === 0" class="show-more"><span @click="getItems">查看全部商品</span></div>
        <div v-else class="order-content">
          <van-card
            v-for="item in orderItems" :key="item.id"
            :title="item.productNameCn"
            :desc="item.productNameEn"
            :num="item.num"
            :price="Number(item.productPrice).toFixed(2)"
            :thumb="baseUrl + item.productDefaultSrc"
            currency="$"
          />
          <h3 class="coupon-info">{{ couponInfo }}</h3>
        </div>
        <div class="order-total-price">
          合计：<span>${{ Number(order.totalProductPrice).toFixed(2) }}</span>
        </div>
      </div>
      <div slot="footer">
        <div class="order-button-wrap">
          <van-button @click="showTracking = !showTracking" size="small">物流详情</van-button>
          <van-button v-if="order.status === 3" size="small" type="primary">确认订单</van-button>
          <van-button v-if="order.isPaid === 0" size="small" type="danger" @click="handlePayment">完成支付</van-button>
        </div>
        <div v-if="showTracking" class="tracking-info">
          收件人：{{ order.toCustomerName }}
          收件地址：{{ order.toProvince }}&nbsp;{{ order.toCity }}&nbsp;{{ order.toCity }}&nbsp;{{ order.toShippingAddress }}
        </div>
      </div>
    </van-panel>
    <div class="h20"></div>
  </div>
</template>

<script>
  import Vue from 'vue';
  import axios from '@/http';
  import api from '@/constant/api';
  import { mapGetters } from 'vuex';
  import { Panel, Button, Card } from 'vant';

  Vue.use(Panel).use(Button).use(Card);

  export default {
    name: 'order-item',
    props: {
      order: {
        id: Number
      }
    },
    data() {
      return {
        baseUrl: '',
        showConfirmBtn: false,
        showTracking: false,
        orderItems: [],
        couponInfo: ''
      };
    },
    created() {
      this.baseUrl = this.$store.state.baseUrl;
    },
    computed: {
      ...mapGetters(['token']),
      title: function() {
        return '订单号：' + this.order.id;
      },
      status: function() {
        let status;
        if (this.order.isPaid === 0) {
          status = '客户尚未支付';
        } else {
          switch (this.order.status) {
            case 0 :
              status = '订单已取消';
              break;
            case 1 :
              status = '已付款，等待发货';
              break;
            case 2 :
              status = '已付款，等待发货';
              break;
            case 3 :
              status = '订单已发货';
              break;
            case 4 :
              status = '已收货';
              this.showConfirmBtn = true;
              break;
            default:
              status = '已付款，等待发货';
              break;
          }
        }
        return status;
      },
      createTime: function() {
        const time = new Date(this.order.createTime);
        return '下单时间：' + time.getFullYear() + '/' + (time.getMonth() + 1) + '/' + time.getDate() + ' ' + time.getHours() + ':' + time.getMinutes() + ':' + time.getSeconds();
      }
    },
    methods: {
      parseJwt() {
        const token = this.token;
        const base64Url = token.split('.')[1];
        const base64 = base64Url.replace('-', '+').replace('_', '/');
        return JSON.parse(window.atob(base64));
      },
      getItems() {
        const _this = this;
        const tokenJwt = this.parseJwt();
        axios.get(api.info + tokenJwt.userId + '/orders/' + this.order.id + '/items')
          .then(response => {
            _this.orderItems = response.data;
          });
        axios.get(api.info + 'orders/' + this.order.id + '/coupons')
          .then(response => {
            if (response.data) {
              const couponItem = response.data;
              _this.couponInfo = '您使用了"' + couponItem.name + '",';
              if (couponItem.reg > 0) {
                _this.couponInfo += '满' + couponItem.reg.toFixed(2) + '元';
              } else {
                _this.couponInfo += '无门槛';
              }
              if (couponItem.type === 'discount') {
                _this.couponInfo += couponItem.value * 10 + '折优惠';
              } else {
                _this.couponInfo += '立减$' + couponItem.value.toFixed(2) + '优惠';
              }
            } else {
              _this.couponInfo = '此订单未使用优惠券';
            }
          });
      },
      handlePayment() {
        this.$emit('paymentFunc', this.order.id);
      }
    }
  };
</script>

<style>
  .h20{
    height: 20px;
  }
  .order-item .van-cell{
    text-align: left;
  }
  .show-more{
    height: 40px;
    line-height: 40px;
    text-align: center;
  }
  .show-more span{
    color: cornflowerblue;
  }
  .order-total-price{
    font-size: 15px;
    height: 32px;
    line-height: 32px;
    padding: 0 15px;
    text-align: right;
  }
  .order-total-price span{
    color: #ee3333;
  }
  .order-button-wrap{
    text-align: right;
  }
  .coupon-info{
    color: #999999;
    padding: 10px 15px;
  }
</style>
