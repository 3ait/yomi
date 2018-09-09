<template>
  <div>
    <van-nav-bar
      v-bind:title="title"
      left-text="返回"
      left-arrow
      @click-left="onClickLeft"
    />
    <div class="h10"></div>
    <van-pull-refresh v-model="refreshing" @refresh="onRefresh">
      <van-list
        v-model="loading"
        :finished="finished"
        :offset="offset"
        @load="onLoad"
      >
        <order-item :order="order" v-for="order in orders" :key="order.id"  @paymentFunc="payment"></order-item>
      </van-list>
    </van-pull-refresh>
    <div class="h50"></div>
    <payment :paymentData="paymentData"></payment>
    <bottom-tabbar :routeActive="4"></bottom-tabbar>
  </div>
</template>

<script>
  import Vue from 'vue';
  import axios from '@/http';
  import api from '@/constant/api';
  import { mapGetters } from 'vuex';
  import { NavBar, PullRefresh, List } from 'vant';

  import orderItem from './components/order.item.component/';
  import payment from '@/views/cart/payment.component/';
  import bottomTabBar from '@/views/global/bottom.tabbar.component/';

  Vue.use(NavBar).use(PullRefresh).use(List);

  export default {
    name: 'my-order',
    data() {
      return {
        title: '我的订单',
        offset: 300,
        loading: false,
        finished: false,
        refreshing: false,
        isLast: false,
        size: 5,
        page: 0,
        orders: [],
        paymentData: {
          show: false,
          orderId: null
        }
      };
    },
    components: {
      'order-item': orderItem,
      'payment': payment,
      'bottom-tabbar': bottomTabBar
    },
    computed: {
      ...mapGetters(['token'])
    },
    methods: {
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
      getOrders(page) {
        const _this = this;
        const tokenJwt = this.parseJwt();
        if (tokenJwt !== null) {
          axios.get(api.info + tokenJwt.userId + '/orders?size=' + _this.size + '&page=' + page)
            .then(response => {
              const orders = response.data.content;
              if (orders.length > 0) {
                orders.forEach(item => {
                  _this.orders.push(item);
                });
              }
              _this.finished = response.data.last;
              _this.loading = false;
            });
        } else {
          _this.$router.push({ name: 'login' });
        }
      },
      onLoad() {
        setTimeout(() => {
          if (!this.last) {
            this.page++;
            this.getOrders(this.page);
          }
        }, 2000);
      },
      onRefresh() {
        setTimeout(() => {
          this.orders = [];
          this.finished = false;
          this.refreshing = false;
          this.page = 1;
          window.scrollTo(0, 10);
          this.getOrders(this.page);
        }, 1000);
      },
      payment(orderId) {
        this.paymentData = {
          show: true,
          orderId: orderId
        };
      },
      onClickLeft() {
        this.$router.go(-1);
      }
    }
  };
</script>

<style lang="postcss">
  .h10{
    height: 10px;
  }
  .van-cell {
    text-align: center;
  }
  .page-desc {
    padding: 5px 0;
    line-height: 1.4;
    font-size: 14px;
    text-align: center;
    color: #666;
  }
  .h50 {
    height: 50px;
  }
</style>
