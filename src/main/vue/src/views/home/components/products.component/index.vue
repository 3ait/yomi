<template>
  <div class="products-wrap">
    <h1>
       热销产品
    </h1>
    <div class="products-content">
      <van-row>
        <van-col v-for="(product, index) in products" :key="index" span="12">
          <router-link class="product-price" :to="{name:'productDetail',params:{id:product.id}}">
            <div class="img-wrap ">
              <img v-bind:src="baseUrl + product.defaultSrc" />
              <van-tag mark type="danger" v-if="product.label!=''">{{product.label}}</van-tag>
              <van-tag mark type="danger" v-if="product.label==''">HOT</van-tag>
            </div>
            <div class="product-text">
              <p>{{product.productName}}</p>
              <p class="product-price">
                <span :style="{'text-decoration': product.price2>0? 'line-through':''}">${{Number(product.price1).toFixed(2)}}</span> &nbsp;
                <span v-if="product.price2>0">{{Number(product.price2).toFixed(2)}}</span>
              </p>
            </div>
          </router-link>
        </van-col>
      </van-row>
    </div>
  </div>
</template>

<script>
  import { Row, Col, Tag } from 'vant';
  import axios from '@/http';

  export default {
    components: {
      [Row.name]: Row,
      [Col.name]: Col,
      [Tag.name]: Tag
    },
    data() {
      return {
        products: [],
        baseUrl: ''
      };
    },
    created: function() {
      const _this = this;
      _this.baseUrl = _this.$store.state.baseUrl;
      axios.get('/m/api/products/hot?size=6&sortBy=position')
        .then(response => {
          _this.products = response.data.content;
        })
        .catch();
    }
  };
</script>

<style lang="less">
  .products-wrap {
    background: #f7f7f7;
    h1 {
      background: #ffffff;
      color: #5f5f5f;
      font-size: 15px;
      font-weight: 200;
      line-height: 44px;
      margin: 0 8px;
      text-align: center;
    }
    .products-content {
      background: #f7f7f7;
      .van-row {
        padding: 5px;
      }
      .van-col {
        padding: 3px;
        a.img-link {
          background: #ffffff;
          display: block;
          position: relative;
          .product-description {
            background: #e7ddd1;
            bottom: 0;
            color: #ac9d7c;
            display: -webkit-box;
            font-size: 13px;
            height: 28px;
            line-height: 28px;
            overflow: hidden;
            position: absolute;
            width: 100%;
            word-break: break-all;
            text-indent: 0.5em;
            text-overflow: ellipsis;
            -webkit-line-clamp: 1;
            -webkit-box-orient: vertical;
          }
        }
        a.product-price {
          background: #f7f7f7;
          display: block;
          .img-wrap{
            .van-tag {
              margin-left: 5px;
            }
          }
          .product-text {
            font-size: 13px;
            line-height: 18px;
            padding: 0 5px;
            .product-price {
              color: #b4282d;
            }
          }
        }
      }
    }
  }
</style>
