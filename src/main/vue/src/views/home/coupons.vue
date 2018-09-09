<template>
  <div>
    <van-nav-bar
      v-bind:title="title"
      left-text="返回"
      left-arrow
      @click-left="onClickLeft"
    />
    <adv-component></adv-component>
    <van-coupon-list
      v-model="code"
      :coupons="coupons"
      :disabled-coupons="collectedCoupons"
      @change="onChange"
      :show-exchange-bar="showExchange"
      :show-close-button="showClose"
      disabled-list-title="已领取优惠券"
    />
    <bottom-tabbar :routeActive="0"></bottom-tabbar>
  </div>
</template>

<script>
  import Vue from 'vue';
  import { mapGetters } from 'vuex';
  import axios from '@/http';
  import api from '@/constant/api';
  import { CouponCell, CouponList, NavBar, Dialog, Toast } from 'vant';
  import advComponent from '@/views/home/components/adv.component/';
  import bottomTabBar from '@/views/global/bottom.tabbar.component/';

  Vue.use(CouponCell).use(CouponList).use(NavBar);

  export default {
    name: 'coupons',
    components: {
      'adv-component': advComponent,
      'bottom-tabbar': bottomTabBar
    },
    data() {
      return {
        title: '福利社',
        code: '',
        showExchange: false,
        showClose: false,
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
        coupons: [],
        collectedCoupons: []
      };
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
        if (this.token === null) {
          return null;
        } else {
          const base64Url = token.split('.')[1];
          const base64 = base64Url.replace('-', '+').replace('_', '/');
          return JSON.parse(window.atob(base64));
        }
      },
      getCouponList() {
        const _this = this;
        const tokenJwt = this.parseJwt();
        let requestUrl = api.couponList;
        if (tokenJwt !== null) {
          requestUrl += '?customerId=' + tokenJwt.userId;
        }
        axios.get(requestUrl)
          .then(response => {
            if (response.data.length > 0) {
              response.data.forEach((item, i) => {
                const couponItem = _this.createItem(item);
                if (item.collected === true) {
                  couponItem.reason = '已领取';
                  this.collectedCoupons.push(couponItem);
                } else {
                  this.coupons.push(couponItem);
                }
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
      onChange(index) {
        const _this = this;
        const tokenJwt = this.parseJwt();
        const couponId = this.coupons[index].id;
        if (tokenJwt === null) {
          Dialog.alert({
            title: '提示',
            message: '领取优惠券之前，请您先完成登录'
          }).then(() => {
            _this.$router.push({ name: 'login' });
            localStorage.returnPath = 'coupons';
          });
        } else {
          Dialog.confirm({
            title: '提示',
            message: '是否确认领取' + this.coupons[index].name + '?'
          }).then(() => {
            // on confirm
            axios.get(api.info + tokenJwt.userId + api.couponCollect + couponId)
              .then(response => {
                if (response.data.collected) {
                  _this.collectedCoupons.push(_this.coupons[index]);
                  _this.coupons.splice(index, 1);
                  Toast('领取成功');
                } else {
                  Toast('领取失败，请联系客服');
                }
              });
          }).catch(() => {
            // on cancel
            return false;
          });
        }
      },
      createItem(item) {
        const couponItem = {
          ...this.coupon,
          id: item.coupon.id,
          name: item.coupon.name,
          origin_condition: item.coupon.reg * 100,
          start_at: item.coupon.createTime / 1000,
          end_at: item.coupon.expiredTime / 1000
        };
        if (item.coupon.type === 'discount') {
          couponItem.discount = item.coupon.value * 100;
          couponItem.denominations = 0;
          couponItem.value = 100 - item.coupon.value * 100;
        } else if (item.coupon.type === 'cash') {
          couponItem.discount = 0;
          couponItem.denominations = item.coupon.value * 100;
          couponItem.value = item.coupon.value * 100;
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
