<template>
  <div class="index-brand-wrap">
    <h1>
      <router-link to="/brand/">
        品牌推荐<van-icon name="arrow" />
      </router-link>
      </h1>
    <div class="brand-content">
      <van-row>
        <van-col v-for="(brand, index) in brands" :key="brand.index" span="12">
          <router-link class="img-link" :to="{name:'brandDetail',params:{id:brand.productAttrValueId}}">
            <img v-bind:src="baseUrl + brand.defaultSrc" />
            <div class="brand-description">
             <p>{{brand.title}}</p>
            </div>
          </router-link>
        </van-col>
      </van-row>
    </div>
  </div>
</template>

<script>
  import Vue from 'vue';
  import { Row, Col, Icon } from 'vant';
  import axios from '@/http';
  Vue.use(Icon).use(Row).use(Col);

  export default {
    name: 'brands',
    data() {
      return {
        brands: [],
        baseUrl: ''
      };
    },
    created: function() {
      const _this = this;
      _this.baseUrl = _this.$store.state.baseUrl;
      axios.get('/m/api/attributes?size=6&sortBy=position')
        .then(response => {
          _this.brands = response.data.content;
        })
        .catch();
    }
  };
</script>

<style lang="less">
  .index-brand-wrap {
    background: #f7f7f7;
    margin-top: 10px;
    h1 {
      background: #ffffff;
      color: #5f5f5f;
      font-size: 15px;
      font-weight: 200;
      line-height: 44px;
      text-align: center;
      i {
        font-size: 12px;
        position: relative;
        top: 1px;
      }
    }
    .brand-content {
      .van-row {
        padding: 5px;
      }
      .van-col {
        padding: 3px;
        a {
          background: #ffffff;
          display: block;
          padding: 5px;
          position: relative;
          .brand-description{
            height: 28px;
            p {
              color: #5f5f5f;
              line-height: 28px;
              text-align: center;
            }
          }
        }
      }
    }
  }
</style>
