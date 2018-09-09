<template>
  <div>
    <van-nav-bar
      v-bind:title="title"
      left-text="返回"
      left-arrow
      @click-left="onClickLeft"
    />
    <van-coupon-list
      :coupons="coupons"
      @exchange="onExchange"
      :show-close-button="showClose"
    />
    <bottom-tabbar :routeActive="4"></bottom-tabbar>
  </div>
</template>

<script>
  import Vue from 'vue';
  import { mapGetters } from 'vuex';
  import axios from '@/http';
  import api from '@/constant/api';
  import { CouponCell, CouponList, NavBar, Toast } from 'vant';

  import bottomTabBar from '@/views/global/bottom.tabbar.component/';

  Vue.use(CouponCell).use(CouponList).use(NavBar);

  export default {
    name: 'my-coupons',
    data() {
      return {
        title: '我的优惠券',
        code: '',
        showList: false,
        showClose: false,
        chosenCoupon: -1,
        coupon: {
          id: 1,
          available: 1,
          discount: 0,
          denominations: 150,
          origin_condition: 0,
          reason: '',
          value: 150,
          name: '优惠券名称',
          start_at: 1489104000,
          end_at: 1514592000
        },
        coupons: []
      };
    },
    components: {
      'bottom-tabbar': bottomTabBar
    },
    computed: {
      ...mapGetters(['token'])
    },
    created() {
      this.getCouponList();
    },
    methods: {
      parseJwt() {
        const token = this.token;
        const base64Url = token.split('.')[1];
        const base64 = base64Url.replace('-', '+').replace('_', '/');
        return JSON.parse(window.atob(base64));
      },
      getCouponList() {
        const _this = this;
        const tokenJwt = this.parseJwt();
        axios.get(api.info + tokenJwt.userId + api.myAvailableCoupon)
          .then(response => {
            if (response.data.length > 0) {
              response.data.forEach((item, i) => {
                const couponItem = _this.createItem(item);
                this.coupons.push(couponItem);
              });
            }
          });
        // 转换货币符号
        setTimeout(() => {
          const currencyIconDoms = document.querySelectorAll('.van-coupon-item__gradient h2 span');
          currencyIconDoms.forEach(item => {
            item.innerText = '$';
          });
        }, 500);
      },
      onExchange(code) {
        const _this = this;
        const tokenJwt = this.parseJwt();
        axios.get(api.info + tokenJwt.userId + api.couponCollectCode + code)
          .then(response => {
            if (response.data.collected) {
              const couponItem = _this.createItem(response.data.coupon);
              couponItem.reason = '已领取';
              this.coupons.push(couponItem);
            } else {
              Toast('领取失败，请联系客服');
            }
          });
      },
      createItem(item) {
        const couponItem = {
          ...this.coupon,
          id: item.id,
          name: item.name,
          origin_condition: item.reg * 100,
          start_at: item.createTime / 1000,
          end_at: item.expiredTime / 1000
        };
        if (item.type === 'discount') {
          couponItem.discount = item.value * 100;
          couponItem.denominations = 0;
          couponItem.value = 100 - item.value * 100;
        } else if (item.type === 'cash') {
          couponItem.discount = 0;
          couponItem.denominations = item.value * 100;
          couponItem.value = item.value * 100;
        }
        return couponItem;
      },
      onClickLeft() {
        this.$router.go(-1);
      }
    }
  };
</script>

<style scoped>

</style>
