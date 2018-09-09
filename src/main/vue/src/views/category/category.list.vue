<template>
<div>
  <search-bar></search-bar>

  <div class="h45"></div>

  <div id="category-wrap">
    <left-tabs :tabs="tabs" @getMenuFunc="getLevel3"></left-tabs>
    <div class="category-content">
      <van-loading v-if="isLoading" color="black" size="40" />
      <div v-else>
        <div class="category-title">
          <span>{{ contentTitle }}</span>
        </div>
        <category-content :categories="categories"></category-content>
      </div>
    </div>
  </div>
  <bottom-tabbar :routeActive="2"></bottom-tabbar>
</div>
</template>

<script>
  import Vue from 'vue';
  import { Row, Col, Toast, Loading } from 'vant';
  import axios from '@/http';
  import api from '@/constant/api';

  import searchBar from '@/views/global/search.component/';
  import bottomTabBar from '@/views/global/bottom.tabbar.component/';
  import leftTabs from './components/left.tabs.component/';
  import categoryContent from './components/category.content/';

  Vue.use(Row).use(Col).use(Toast).use(Loading);

  export default {
    name: 'categoryList',
    data() {
      return {
        tabs: [],
        contentTitle: '',
        categories: [],
        isLoading: true,
        baseUrl: ''
      };
    },
    components: {
      'search-bar': searchBar,
      'bottom-tabbar': bottomTabBar,
      'left-tabs': leftTabs,
      'category-content': categoryContent
    },
    mounted() {
      this.getTabs();
      this.baseUrl = this.$store.state.baseUrl;
    },
    methods: {
      getTabs() {
        const _this = this;
        axios.get(api.categories)
          .then(response => {
            if (response.data.length > 0) {
              response.data.forEach((item, i) => {
                if (i === 0) {
                  _this.$set(item, 'active', true);
                } else {
                  this.$set(item, 'active', false);
                }
                _this.tabs.push(item);
              });
              _this.getLevel3(_this.tabs[0]);
            } else {
              Toast('网站内部错误，请联系管理员');
              return false;
            }
          });
      },
      getLevel3(item) {
        const _this = this;
        _this.isLoading = true;
        // 分类标题
        _this.contentTitle = item.name + '分类';
        // 获取子分类内容
        axios.get('/m/api/categories/' + item.id + '/subcategories')
          .then(response => {
            if (response.data.length > 0) {
              _this.categories = [];
              response.data.forEach(category => {
                category.defaultSrc === null ? category.defaultSrc = require('@/assets/images/logo-green.jpg') : category.defaultSrc = _this.baseUrl + category.defaultSrc;
                // _this.$set(category, 'categoryImg', require('../../assets/images/product/product-05.png'));
                _this.categories.push(category);
              });
            } else {
              this.categories = [];
            }
            _this.isLoading = false;
          });
      }
    }
  };
</script>

<style lang="less">
  .van-loading{
   margin: 20px auto;
  }
  #category-wrap{
    border-top: 1px solid #eeeeee;
    margin-top: 10px;
    overflow: hidden;
    zoom:1;
    &:after{
      content: '';
      display: table;
      clear: both;
    }
    .category-left{
      border-right: 1px solid #eeeeee;
      height: 100%;
      width: 82px;
      li{
        border-left: 3px solid #fbfbfb;
        margin-top: 10px;
        height: 34px;
        line-height: 34px;
        text-align: center;
        &.active{
          border-color: #357363;
          color: #357363;
        }
      }
    }
    .category-content{
      margin-left: 83px;
      padding: 0 10px 80px;
      .category-title{
        height: 50px;
        line-height: 50px;
        text-align: center;
        span{
          line-height: 50px;
          position: relative;
          text-align: center;
          &:before, &:after{
            position: absolute;
            content: '';
            top: 0;
            bottom: 0;
            margin: auto;
            height: 1px;
            width: 60px;
            background-color: #d9d9d9;
            -webkit-transform-origin: 50% 100% 0;
            transform-origin: 50% 100% 0;
          }
          &:before{
            left: -80px;
          }
          &:after{
            right: -80px;
          }
        }
      }
      .van-col{
        padding: 10px 10px;
        p{
          text-align: center;
        }
      }
    }
  }
</style>
