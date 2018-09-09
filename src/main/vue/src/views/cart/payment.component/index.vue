<template>
  <div>
    <van-actionsheet v-model="paymentData.show" :actions="actions" :close-on-click-overlay="clickOverlay" cancel-text="取消支付" @cancel="cancel" />
  </div>
</template>

<script>
  import Vue from 'vue';
  import axios from '@/http';
  import api from '@/constant/api';
  import { Actionsheet, Toast } from 'vant';

  Vue.use(Actionsheet);
  export default {
    name: 'payment',
    props: {
      paymentData: {
        show: Boolean,
        orderId: Number
      }
    },
    data() {
      return {
        clickOverlay: false,
        returnURL: '',
        actions: [
          {
            name: '请选择您的支付方式',
            className: 'title'
          },
          {
            name: '微信支付',
            className: 'wechat',
            callback: this.payment,
            loading: false
          },
          {
            name: '支付宝支付',
            className: 'alipay',
            callback: this.payment,
            loading: false
          }
        ]
      };
    },
    created() {
      const loginURL = localStorage.getItem('loginurl');
      const i = loginURL.indexOf('#', 0);
      this.returnURL = loginURL.substring(0, i);
    },
    methods: {
      payment(item) {
        const _this = this;
        const orderId = _this.paymentData.orderId;
        let payType, index;
        if (item.name === '微信支付') {
          payType = 'wechat';
          index = 1;
        } else {
          payType = 'alipay';
          index = 2;
        }
        _this.actions[index].loading = true;
        if (orderId !== null) {
          axios.get(api.payment + payType + '/' + orderId + '?returnUrl=' + _this.returnURL)
            .then(response => {
              // todo
              _this.actions[index].loading = false;
              window.location.href = response.data;
            })
            .catch(function(error) {
              console.log('error:' + error);
              Toast('支付失败，请联系客服');
            });
        } else {
          Toast('支付失败，请联系客服');
        }
      },
      cancel() {
        Toast('取消支付');
      }
    }
  };
</script>

<style>
  .wechat{
    color: #06bf04;
  }
  .alipay{
    color:#38f;
  }
</style>
