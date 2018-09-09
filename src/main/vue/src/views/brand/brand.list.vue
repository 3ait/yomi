<template>
  <div>
    <van-nav-bar
      v-bind:title="title"
      left-text="返回"
      left-arrow
      @click-left="onClickLeft"
    />

    <div class="brand-wrap">
      <div class="brand-content">
        <van-list
          v-model="loading"
          :finished="finished"
          :offset="offset"
          @load="onLoad"
        >
          <van-row>
            <van-col v-for="(brand, index) in brands" :key="index" span="12">
              <router-link  class="img-link" :to="{ name: 'brandDetail', params: { id: brand.productAttrValueId }}">
                <img v-bind:src="baseUrl + brand.defaultSrc" />
                <div class="brand-text">
                  <!--<p><van-tag mark type="danger">限时热销</van-tag></p>-->
                  <p>{{brand.title}}&nbsp;&nbsp;</p>
                  <!--<p>$20.00起</p>-->
                </div>
              </router-link>
            </van-col>
          </van-row>
        </van-list>
      </div>
    </div>

    <bottom-tabbar :routeActive="1"></bottom-tabbar>
  </div>
</template>

<script>
  import Vue from 'vue';
  import axios from '@/http';
  import api from '@/constant/api';
  import { Row, Col, Toast, NavBar, Loading, List, Tag } from 'vant';
  import bottomTabBar from '@/views/global/bottom.tabbar.component/';

  Vue.use(Row).use(Col).use(NavBar).use(Loading).use(Toast).use(List).use(Tag);

  export default {
    name: 'brand-list',
    components: {
      'bottom-tabbar': bottomTabBar
    },
    data() {
      return {
        title: '品牌推荐',
        baseUrl: '',
        size: 10,
        page: 0,
        offset: 300,
        loading: false,
        finished: false,
        isLast: false,
        brands: [],
        imgSrc: ''
      };
    },
    created() {
      this.baseUrl = this.$store.state.baseUrl;
      this.imgSrc = require('@/assets/images/product/product-01.png');
    },
    methods: {
      getBrands(page) {
        const _this = this;
        axios.get(api.brand + '?size=' + _this.size + '&page=' + page)
          .then(response => {
            const brands = response.data.content;
            if (brands.length > 0) {
              brands.forEach(item => {
                _this.brands.push(item);
              });
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
            this.getBrands(this.page);
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

  .brand-wrap {
    background: #f5f5f5;
    padding: 10px 0 100px;
    h1 {
      color: #5f5f5f;
      font-size: 15px;
      font-weight: 200;
      line-height: 44px;
      text-align: center;
    }
    .brand-content{
      .van-row {
        padding: 0 5px;
      }
      .van-col {
        background: #f5f5f5;
        padding: 5px 5px 5px;
        a.img-link {
          background: #ffffff;
          display: block;
          padding: 5px 0 0;
          .brand-text {
            padding: 5px 0 0;
            p {
              color: #5f5f5f;
              font-size: 14px;
              line-height: 28px;
              text-align: center;
              text-overflow:ellipsis;//让超出的用...实现
              white-space:nowrap;//禁止换行
              overflow:hidden;//超出的隐藏

            }
          }
        }
      }
    }
  }
</style>
