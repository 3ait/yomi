<template>
  <div class="goods">
    <van-nav-bar
      v-bind:title="title"
      left-text="返回"
      left-arrow
      @click-left="onClickLeft"
    />
    <van-swipe class="goods-swipe" :autoplay="3000">
      <van-swipe-item v-for="slider in product.defaultSliders" :key="slider">
        <img :src="slider" >
      </van-swipe-item>
    </van-swipe>

    <van-cell-group>
      <van-cell>
        <div class="goods-title">{{ product.productName }}</div>
        <div class="goods-alias">{{ product.productNameAlias }}</div>
        <div class="goods-price">
          <span>热销价：{{ formatPrice(product.price) }}</span>
          &nbsp;&nbsp;
          <span v-if="product.originalPrice !== 0">原价：<i class="del-line">{{ formatPrice(product.originalPrice) }}</i></span>
        </div>
        <div v-if="product.rmbPrice > 0" class="goods-price">
          <span>约折合人民币：￥{{ product.rmbPrice.toFixed(2) }}</span>
        </div>
      </van-cell>
      <van-cell class="goods-express">
        <van-col span="10">运费：{{ product.express }}</van-col>
        <van-col span="14">剩余：{{ product.stock }}</van-col>
      </van-cell>
    </van-cell-group>

    <van-cell-group class="goods-cell-group">
      <van-cell value="进入店铺" icon="shop" is-link @click="sorry">
        <template slot="title">
          <span class="van-cell-text">Ultimate Life</span>
          <van-tag type="danger">官方</van-tag>
        </template>
      </van-cell>
      <!--<van-cell title="线下门店" icon="location" is-link @click="sorry" />-->
    </van-cell-group>

    <div class="product-description" v-html="product.description"></div>

    <van-goods-action>
      <van-goods-action-mini-btn icon="chat" @click="sorry">
        客服
      </van-goods-action-mini-btn>
      <van-goods-action-mini-btn icon="cart" v-bind:info="totalNum" @click="routerToCart">
        购物车
      </van-goods-action-mini-btn>
      <van-goods-action-big-btn @click="addToCartFn(product)">
        加入购物车
      </van-goods-action-big-btn>
      <van-goods-action-big-btn primary @click="purchase">
        立即购买
      </van-goods-action-big-btn>
    </van-goods-action>
  </div>
</template>

<script>
  import Vue from 'vue';
  import { mapActions, mapGetters } from 'vuex';
  import axios from '@/http';
  import api from '@/constant/api';
  import {
    Tag,
    Col,
    Icon,
    Cell,
    CellGroup,
    Swipe,
    Toast,
    SwipeItem,
    GoodsAction,
    GoodsActionBigBtn,
    GoodsActionMiniBtn,
    NavBar
  } from 'vant';

  Vue.use(NavBar);

  export default {
    components: {
      [Tag.name]: Tag,
      [Col.name]: Col,
      [Icon.name]: Icon,
      [Cell.name]: Cell,
      [CellGroup.name]: CellGroup,
      [Swipe.name]: Swipe,
      [SwipeItem.name]: SwipeItem,
      [GoodsAction.name]: GoodsAction,
      [GoodsActionBigBtn.name]: GoodsActionBigBtn,
      [GoodsActionMiniBtn.name]: GoodsActionMiniBtn
    },
    data() {
      return {
        product: {},
        baseUrl: '',
        title: '产品详情'
      };
    },
    mounted() {
      this.baseUrl = this.$store.state.baseUrl;
      this.getProduct();
    },
    computed: {
      ...mapGetters(['cartProducts', 'totalNum']),
      productObj() {
        return {
          productName: '',
          price: 0,
          rmbPrice: 0,
          originalPrice: 0,
          express: '免运费',
          stock: 0,
          num: 1,
          defaultSrc: require('@/assets/images/product/defalut-product.png'),
          defaultSliders: [
            require('@/assets/images/product/defalut-product.png')
          ]
        };
      }
    },
    methods: {
      formatPrice(price) {
        return '$' + Number(price).toFixed(2);
      },
      purchase() {
        const id = Number(this.$route.params.id);
        let idExisted = false;
        this.cartProducts.forEach(item => {
          if (id === item.id) {
            idExisted = true;
          }
        });
        if (!idExisted) {
          this.addToCart(this.product);
        }
        this.routerToCart();
      },
      routerToCart() {
        this.$router.push({ name: 'cart' });
      },
      sorry() {
        this.$router.push({ name: 'home' });
      },
      getProduct() {
        const _this = this;
        const id = this.$route.params.id;
        axios.get(api.product + '/' + id)
          .then(response => {
            if (response.data.productNameAlias) {
              let price, originalPrice;
              if (response.data.price2 === 0) {
                price = response.data.price1;
                originalPrice = 0;
              } else {
                price = response.data.price2;
                originalPrice = response.data.price1;
              }
              _this.product = {
                ..._this.productObj,
                id: response.data.id,
                productName: response.data.productName,
                productNameAlias: response.data.productNameAlias,
                price: price,
                originalPrice: originalPrice,
                description: response.data.mobileDefaultDesc,
                defaultSrc: _this.baseUrl + response.data.defaultSrc,
                stock: response.data.stock
              };
              _this.getSlider();
              _this.getRate();
            } else {
              Toast('暂无产品');
              _this.$router.push({ name: 'home' });
              return false;
            }
          });
      },
      getRate() {
        const _this = this;
        axios.get(api.rate)
          .then(response => {
            if (response.data > 0) {
              _this.product.rmbPrice = _this.product.price * response.data;
            } else {
              _this.product.rmbPrice = -1;
            }
          });
      },
      getSlider() {
        const _this = this;
        const id = this.$route.params.id;
        axios.get(api.product + '/' + id + '/attachments')
          .then(response => {
            const sliders = [];
            if (response.data.length > 0) {
              response.data.forEach((item, key) => {
                sliders[key] = _this.baseUrl + item.filePath;
              });
              _this.product.defaultSliders = sliders;
            } else {
              _this.product.defaultSliders = [_this.product.defaultSrc];
            }
          });
      },
      addToCartFn(product) {
        this.addToCart(product);
        Toast(product.productName + '已添加至购物车');
      },
      onClickLeft() {
        this.$router.go(-1);
      },
      ...mapActions(['addToCart'])
    }
  };
</script>

<style lang="less">
  .goods {
    padding-bottom: 50px;
    /*&-swipe {*/
      /*img {*/
        /*width: 7.5rem;*/
        /*height: 7.5rem;*/
        /*display: block;*/
      /*}*/
    /*}*/
    &-title {
      color: #666666;
      font-size: 18px;
      font-weight: 600;
    }
    &-alias {
      color: #b1b1b1;
    }
    &-price {
      color: #f44;
      .del-line{
        text-decoration:line-through;
      }
    }
    &-express {
      color: #999;
      font-size: 12px;
      padding: 5px 15px;
    }
    &-cell-group {
      margin: 15px 0;
      .van-cell__value {
        color: #999;
      }
    }
  }
  .product-description{
    background: #ffffff;
    padding: 0 20px;
    p, li{
      color: #666666;
      line-height: 24px;
      text-align: justify;
    }
  }
</style>
