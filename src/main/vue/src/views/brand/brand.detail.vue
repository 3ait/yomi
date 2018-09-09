<template>
  <div>
    <van-nav-bar
      v-bind:title="title"
      left-text="返回"
      left-arrow
      @click-left="onClickLeft"
    />
    <div class="products-wrap">
      <div class="brand-story" v-html="storyHtml"></div>
      <div class="products-content">
        <van-list
          v-model="loading"
          :finished="finished"
          :offset="offset"
          @load="onLoad"
        >
          <van-row>
            <van-col v-for="(product, index) in products" :key="index" span="12">
              <router-link :to="{ name: 'productDetail', params: { id: product.id }}">
                <div class="img-wrap">
                  <img v-bind:src="product.defaultSrc" />
                  <div class="product-description">{{product.productNameAlias}}</div>
                </div>
                <div class="product-text">
                  <p>{{product.productName}}</p>
                  <p v-if="product.price2 === 0">
                    <span class="product-price">热销价:${{Number(product.price1).toFixed(2)}}</span>
                  </p>
                  <p v-else>
                    <span class="product-price">${{Number(product.price2).toFixed(2)}}</span>&nbsp;
                    <span class="product-price">原价:<i class="del-line">${{Number(product.price1).toFixed(2)}}</i></span>
                  </p>
                </div>
              </router-link>
            </van-col>
          </van-row>
          <h1>{{ emptyText }}</h1>
        </van-list>
      </div>
      <div style="height: 200px"></div>
    </div>
    <bottom-tabbar :routeActive="1"></bottom-tabbar>
  </div>
</template>

<script>
  import Vue from 'vue';
  import { Row, Col, NavBar, List } from 'vant';
  import axios from '@/http';
  import api from '@/constant/api';
  import bottomTabBar from '@/views/global/bottom.tabbar.component/';

  Vue.use(Row).use(Col).use(NavBar).use(List);

  export default {
    name: 'list',
    data() {
      return {
        title: '品牌推荐',
        storyHtml: '',
        baseUrl: '',
        size: 10,
        page: -1,
        offset: 300,
        loading: false,
        finished: false,
        products: [
        ],
        emptyText: ''
      };
    },
    components: {
      'bottom-tabbar': bottomTabBar
    },
    created() {
      const _this = this;
      const id = this.$route.params.id;
      axios.get(api.brand + id)
        .then(response => {
          if (response.data.content.length > 0) {
            _this.storyHtml = response.data.content[0].content;
          } else {
            console.log('no brand story');
          }
        });
    },
    methods: {
      getList(page) {
        const _this = this;
        const id = this.$route.params.id;
        axios.get(api.brand + id + '/products' + '?size=' + _this.size + '&page=' + page)
          .then(response => {
            if (response.data.content.length > 0) {
              response.data.content.forEach((item, i) => {
                item.defaultSrc = _this.$store.state.baseUrl + item.defaultSrc;
                _this.products.push(item);
              });
              if (response.data.last) {
                _this.emptyText = '暂无更多产品';
              }
            } else {
              _this.emptyText = '暂无更多产品';
            }
            _this.finished = response.data.last;
            _this.loading = false;
          });
      },
      onLoad() {
        const _this = this;
        setTimeout(() => {
          if (!_this.finished) {
            this.page++;
            this.getList(this.page);
          }
        }, 500);
      },
      onClickLeft() {
        this.$router.go(-1);
      }
    }
  };
</script>

<style lang="less">
  .van-loading{
    margin:0 auto;
  }
  .products-wrap{
    background: #ffffff;
    padding-top: 20px;
    h1{
      color: #5f5f5f;
      font-size: 15px;
      font-weight: 200;
      line-height: 44px;
      text-align: center;
    }
    .list-empty{
      text-align: center;
      line-height: 100px;
    }
    .van-row{
      padding-left: 10px;
    }
    .van-col{
      padding:0 10px 10px 0;
      .img-wrap{
        background: #f7f7f7;
        display: block;
        position: relative;
        .product-description{
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
    }
    .product-text{
      p{
        color: #999999;
        font-size: 13px;
        height: 36px;
        line-height: 18px;
        word-break: break-all;
        overflow: hidden;
        text-overflow: ellipsis;
        text-align: left;
        display: -webkit-box;
        -webkit-line-clamp: 2;
        -webkit-box-orient: vertical;
      }
      .product-price{
        color: #b4282d;
        .del-line{
          text-decoration: line-through;
        }
      }
    }
  }
</style>
