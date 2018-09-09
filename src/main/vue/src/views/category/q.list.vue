<template>
  <div>
    <van-nav-bar
      v-bind:title="title"
      left-text="返回"
      left-arrow
      @click-left="onClickLeft"
    />
    <div class="products-wrap">
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
                  <p class="tag-line" v-if="product.label"><van-tag mark type="danger">{{ product.label }}</van-tag></p>
                  <p class="tag-line" v-else>&nbsp;</p>
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
    </div>
    <bottom-tabbar :routeActive="2"></bottom-tabbar>
  </div>
</template>

<script>
  import Vue from 'vue';
  import { Row, Col, NavBar, Loading, List, Tag } from 'vant';
  import axios from '@/http';
  import api from '@/constant/api';
  import bottomTabBar from '@/views/global/bottom.tabbar.component/';

  Vue.use(Row).use(Col).use(NavBar).use(Loading).use(List).use(Tag);

  export default {
    name: 'q-list',
    data() {
      return {
        title: '搜索结果',
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
    methods: {
      getList(page) {
        const _this = this;
        const keyword = this.$route.params.keyword;
        axios.get(api.product + '/?q=' + keyword + '?size=' + _this.size + '&page=' + page)
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

<style scoped>

</style>
