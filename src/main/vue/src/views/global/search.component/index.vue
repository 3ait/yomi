<template>
  <div class="search-wrap" id="search">
    <header>
      <div class="logo-wrap"><router-link :to="{name: 'home'}"><img :src="logoImg" /></router-link></div>
      <div class="search-bar">
        <div class="content">
          <form action="javascript:return true;">
            <input @keyup.13="search()" ref="inputSearch" type="search" name="q" maxlength="30" required="required" v-model="keyword" placeholder="搜索" />
            <van-icon @click="search" name="search" />
          </form>
        </div>
      </div>
      <div class="scan-wrap">
        <router-link to="/scanner">
          <i class="iconfont icon-scanning"></i>
          <p>扫一扫</p>
        </router-link>
      </div>
    </header>
  </div>
</template>

<script>
  import Vue from 'vue';
  import { icon } from 'vant';
  // import RouterLink from 'vant/packages/mixins/router-link';

  Vue.use(icon);

  export default {
    name: 'search',
    data() {
      return {
        logoImg: require('@/assets/images/logo.jpg'),
        keyword: ''
      };
    },
    methods: {
      search() {
        this.$refs.inputSearch.blur();
        if (this.keyword !== '') {
          this.$router.push({ name: 'qList', params: { keyword: this.keyword }});
        }
      }
    }
  };
</script>

<style lang="less">
  /*search bar*/
  .search-wrap{
    background: #fbfbfb;
    position: fixed;
    left: 0;
    top: 0;
    right: 0;
    margin: auto;
    width: 100%;
    max-width: 640px;
    min-width: 300px;
    height: 45px;
    z-index: 9999;
    header {
      position: relative;
      width: 100%;
      height: 100%;
      .logo-wrap{
        position: absolute;
        left: 7px;
        top: 0;
        width: 45px;
        height: 45px;
        text-align: center;
        img{
          display: block;
          height: 45px;
          width: 45px;
        }
      }
      .scan-wrap{
        position: absolute;
        right: 7px;
        top: 7px;
        width: 44px;
        height: 30px;
        text-align: center;
        .iconfont{
          font-size: 18px;
        }
        p{
          color: #d1d1d1;
          font-size: 12px;
        }
      }
      .search-bar{
        width: 100%;
        height: 100%;
        padding-left: 56px;
        padding-right: 56px;
        .content{
          background: #ededed;
          display: inline-block;
          width: 100%;
          height: 32px;
          border-radius: 5px;
          margin-top: 7px;
          overflow: hidden;
          padding: 0 5px 0 0;
          border: none;
          /*background-color:rgba(255,255,255,0.2);*/
          form {
            height: 32px;
            input{
              color: #000000;
              width: 90%;
              height: 100%;
              line-height: 32px;
              display: inline-block;
              background: none;
              padding-left: 3%;
              -webkit-appearance: none;
            }
          }
        }
        .van-icon{
          position: relative;
          top: 2px;
        }
      }
    }
  }
</style>
