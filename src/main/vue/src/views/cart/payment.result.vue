<template>
  <div id="payment-result">
    <van-nav-bar
      v-bind:title="title"
    />
    <van-row>
      <van-col v-if="result === 'success'" span="24">
        <h1><van-icon name="success" />支付成功</h1>
      </van-col>
      <van-col v-if="result === 'success'" span="24">
        您的订单已支付成功，感谢您对Ultimate Life的支持。点击“我的订单”可随时查询订单状态。
      </van-col>
      <van-col v-if="result === 'fail'" span="24">
        <h1><van-icon name="fail" />支付失败</h1>
      </van-col>
      <van-col v-if="result === 'fail'" span="24">
        非常抱歉支付失败，点击“我的订单”可继续完成支付。或返回首页重新选购产品。
      </van-col>
      <van-col class="button-wrap" span="24">
        <router-link :to="{ name: 'myOrders' }"><van-button type="primary">我的订单</van-button></router-link>
        <router-link :to="{ name: 'home' }"><van-button type="warning">返回首页</van-button></router-link>
      </van-col>
    </van-row>
    <bottom-tabbar :routeActive="3"></bottom-tabbar>
  </div>
</template>

<script>
  import Vue from 'vue';
  import { Row, Col, NavBar, Icon, Button } from 'vant';
  import bottomTabBar from '@/views/global/bottom.tabbar.component/';

  Vue.use(Row).use(Col).use(NavBar).use(Icon).use(Button);

  export default {
    name: 'payment-result',
    data() {
      return {
        title: '支付结果',
        result: ''
      };
    },
    components: {
      'bottom-tabbar': bottomTabBar
    },
    created() {
      this.result = this.$route.params.result;
    }
  };
</script>

<style lang="less">
  #payment-result{
    .van-col{
      text-align: center;
      line-height: 32px;
      h1 {
        line-height: 48px;
        padding: 40px 0;
        .van-icon{
          margin-right: 15px;
          &.van-icon-success:before {
            color: #44bb00;
          }
          &.van-icon-fail:before {
            color: #ff4444;
          }
        }
      }
      &.button-wrap {
        margin-top: 40px;
        a {
          display: inline-block;
          margin: 0 15px;
        }
      }
    }
  }
</style>
