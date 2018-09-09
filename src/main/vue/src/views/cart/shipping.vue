<template>
  <div>
    <van-nav-bar
      v-bind:title="title"
      left-text="返回"
      left-arrow
      @click-left="onClickLeft"
    />
    <van-address-edit
      :area-list="areaList"
      show-postal
      :show-delete="false"
      :show-set-default="false"
      :save-button-text="buttonText"
      :is-saving="isSaving"
      @save="onSave"
    />
    <payment :paymentData="paymentData"></payment>
  </div>
</template>

<script>
  import Vue from 'vue';
  import { AddressEdit, NavBar, Toast, Actionsheet } from 'vant';
  import axios from '@/http';
  import 'url-search-params-polyfill';
  import * as types from '@/store/types';
  import { mapGetters } from 'vuex';
  import areaList from '@/constant/area';
  import payment from '@/views/cart/payment.component/';

  Vue.use(NavBar).use(AddressEdit).use(Actionsheet);

  export default {
    data() {
      return {
        title: '收货地址',
        buttonText: '提交订单',
        isSaving: false,
        areaList,
        orderId: null,
        paymentData: {
          show: false,
          orderId: null
        }
      };
    },
    components: {
      'payment': payment
    },
    computed: {
      ...mapGetters(['cartProducts', 'totalPrice', 'totalNum', 'token', 'couponId'])
    },
    created() {
      if (this.cartProducts.length === 0 || this.tokenJwt === null) this.$router.push({ name: 'home' });
    },
    methods: {
      onSave(content) {
        this.isSaving = true;
        const _this = this;
        const tokenJwt = _this.parseJwt();
        const customerId = tokenJwt.userId;
        const params = new URLSearchParams();
        params.append('productList', JSON.stringify(this.cartProducts));
        params.append('customerId', customerId);
        params.append('customerMsg', '');
        params.append('toCustomerName', content.name);
        params.append('toCustomerCompanyName', '');
        params.append('toTel', '');
        params.append('toPhone', content.tel);
        params.append('toAddress', '');
        params.append('toShippingAddress', content.address_detail);
        params.append('toProvince', content.province);
        params.append('toCity', content.city);
        params.append('toDistrict', content.county);
        params.append('salesId', '');
        params.append('couponId', this.couponId);
        console.log(params);
        axios.post('/m/api/customers/' + customerId + '/orders/place', params)
          .then(response => {
            const data = response.data;
            _this.isSaving = false;
            if (data) {
              // 记录订单ID
              _this.orderId = data.id;
              // 清空购物车
              _this.$store.commit(types.CLEAR_ALL);
              // 唤醒支付控件
              _this.paymentPopup();
            }
          })
          .catch(function(error) {
            console.log('error:' + error);
            Toast('下单失败，请联系客服');
          });
      },
      paymentPopup() {
        this.paymentData = {
          show: true,
          orderId: this.orderId
        };
      },
      cancel() {
        Toast('取消支付');
      },
      parseJwt() {
        const token = this.token;
        if (this.token === null) {
          return null;
        } else {
          const base64Url = token.split('.')[1];
          const base64 = base64Url.replace('-', '+').replace('_', '/');
          return JSON.parse(window.atob(base64));
        }
      },
      onClickLeft() {
        this.$router.go(-1);
      }
    }
  };
</script>

<style lang="less">
  .van-button__text{
    content: '提交'
  }
</style>
