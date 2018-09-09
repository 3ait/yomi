<template>
  <div>
    <van-nav-bar
      v-bind:title="title"
      left-text="返回"
      left-arrow
      @click-left="onClickLeft"
    />

    <div class="card-goods" v-if="cartProducts.length">
      <div class="card-goods__item" v-for="item in cartProducts">
        <div class="van-checkbox__label">
          <van-card
            :title="item.productName"
            :desc="item.productNameAlias"
            :num="item.num"
            :price="item.price"
            :thumb="item.defaultSrc"
            :currency="currency"
          >
            <div slot="footer">
              <van-stepper
                v-on:plus="addToCart(item)"
                v-on:minus="reduceFromCart(item)"
                :default-value="item.num"
              />
              <van-icon @click="delFn(item)" name="delete" />
            </div>
          </van-card>
        </div>
      </div>
    </div>
    <h1 class="list-empty" v-else>
      您的购物车空空如也
    </h1>
    <van-coupon-cell
      :coupons="availableCoupons"
      :chosen-coupon="chosenCoupon"
      @click="showList = true"
      ref="couponCell"
    />
    <van-popup v-model="showList" position="bottom" :lazy-render="false">
      <van-coupon-list
        :coupons="availableCoupons"
        :disabled-coupons="disabledCoupons"
        :chosen-coupon="chosenCoupon"
        @change="onChange"
        :show-exchange-bar="showExchange"
        :show-close-button="showClose"
      />
    </van-popup>
    <!-- price 单位分 -->
    <van-submit-bar
      :price="finalPrice*100"
      :disabled="!cartProducts.length"
      :button-text="submitBarText"
      @submit="onSubmit"
      :currency="currency"
    />
  </div>
</template>

<script>
  import Vue from 'vue';
  import { mapGetters, mapActions } from 'vuex';
  import axios from '@/http';
  import api from '@/constant/api';
  import { Button, Checkbox, CheckboxGroup, Card, Stepper, SubmitBar, NavBar, Icon, Dialog, Popup, CouponCell, CouponList } from 'vant';

  Vue.use(Button).use(Stepper).use(NavBar).use(Icon).use(CouponCell).use(CouponList).use(Popup);

  export default {
    components: {
      [Card.name]: Card,
      [Checkbox.name]: Checkbox,
      [SubmitBar.name]: SubmitBar,
      [CheckboxGroup.name]: CheckboxGroup
    },
    data() {
      return {
        baseUrl: '',
        title: '购物车',
        checkedGoods: [],
        currency: '$',
        code: '',
        showList: false,
        showExchange: false,
        showClose: true,
        chosenCoupon: -1, // 已选中优惠券的索引
        chosenCouponId: -1, // 已选中优惠券的id
        discountValue: 0, // 优惠值
        finalPrice: 0, // 折后价格
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
        availableCoupons: [],
        disabledCoupons: [],
        coupons: []
      };
    },
    created() {
      this.baseUrl = this.$store.state.baseUrl;
      // 初始化优惠券
      this.saveCoupon(-1);
      this.getCouponList();
      this.finalPrice = this.totalPrice;
    },
    computed: {
      ...mapGetters(['cartProducts', 'totalPrice', 'totalNum', 'token']),
      submitBarText() {
        const count = this.totalNum;
        return '结算' + (count ? `(${count})` : '');
      }
    },
    methods: {
      formatPrice(price) {
        return (price / 100).toFixed(2);
      },
      onSubmit() {
        // Toast('点击结算');
        this.$router.push({ name: 'shipping' });
      },
      onClickLeft() {
        this.$router.go(-1);
      },
      delFn(product) {
        const _this = this;
        Dialog.confirm({
          title: '提示',
          message: '是否从购物车中删除该产品'
        }).then(() => {
          // on confirm
          _this.delProduct(product);
        }).catch(() => {
          // on cancel
        });
      },
      // token解析
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
        if (tokenJwt !== null) {
          axios.get(api.info + tokenJwt.userId + api.myAvailableCoupon)
            .then(response => {
              if (response.data.length > 0) {
                response.data.forEach((item, i) => {
                  const couponItem = _this.createItem(item);
                  _this.coupons.push(couponItem);
                });
                _this.checkAvailable(_this.totalPrice);
              }
            });
        }
      },
      onChange(index) {
        this.showList = false;
        this.chosenCoupon = index;
        if (index >= 0) {
          this.chosenCouponId = this.availableCoupons[index].id;
          if (this.availableCoupons[index].denominations === 0) {
            this.availableCoupons[index].value = this.totalPrice * (100 - this.availableCoupons[index].discount);
          }
          this.discountValue = this.availableCoupons[index].value;
        } else {
          this.chosenCouponId = -1;
          this.discountValue = 0;
        }
        // Vuex中保存优惠券信息
        this.saveCoupon(this.chosenCouponId);
        // 修改货币符号
        setTimeout(() => {
          const valueText = document.querySelector('.van-cell__value span').innerText;
          document.querySelector('.van-cell__value span').innerText = valueText.replace('￥', '$');
        }, 200);
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
      checkAvailable(totalPrice) {
        const _this = this;
        this.coupons.forEach((item, i) => {
          if (item.origin_condition === 0 || item.origin_condition / 100 <= totalPrice) {
            _this.availableCoupons.push(item);
            // 判断是否已经选中，重新计算折扣
            if (item.id === _this.chosenCouponId) {
              _this.chosenCouponId = item.id;
              _this.chosenCoupon = i;
              if (item.denominations === 0) {
                item.value = _this.totalPrice * (100 - item.discount);
                _this.discountValue = item.value;
              }
            }
          } else {
            // 去除选中
            if (item.id === _this.chosenCouponId) {
              _this.chosenCouponId = -1;
              _this.chosenCoupon = -1;
              _this.discountValue = 0;
              _this.saveCoupon(_this.chosenCouponId);
            }
            _this.disabledCoupons.push(item);
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
      ...mapActions(['addToCart', 'reduceFromCart', 'delProduct', 'saveCoupon'])
    },
    watch: {
      totalPrice: function(val) {
        this.availableCoupons = [];
        this.disabledCoupons = [];
        this.checkAvailable(val);
        this.finalPrice = val - this.discountValue / 100;
      },
      discountValue: function(val) {
        this.finalPrice = this.totalPrice - val / 100;
      }
    }
  };
</script>

<style lang="less">
  .card-goods {
    padding: 10px 0;
    background-color: #fff;
    &__item {
      position: relative;
      background-color: #fafafa;
      margin-bottom: 10px;
      .van-checkbox__label {
        width: 100%;
        padding: 0 10px 0 0;
        box-sizing: border-box;
      }
      .van-icon {
        color: #838282;
        font-size: 26px;
        margin-left: 10px;
      }
      .van-stepper{
        float: left;
      }
      .van-card__price {
        color: #f44;
      }
    }
    &__item:last-child{
      margin-bottom: 0;
    }
  }
  .list-empty{
    color: #c9c9c9;
    text-align: center;
    line-height: 100px;
  }
  .van-coupon-item__gradient h2 span{
    font-size: 22px;
  }
</style>
