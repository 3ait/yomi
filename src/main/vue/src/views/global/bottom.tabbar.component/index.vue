<template>
  <div id="bottom-wrap">
    <van-tabbar v-model="active">
      <van-tabbar-item icon="home" @click="routerTo('home')">首页</van-tabbar-item>
      <van-tabbar-item icon="shop" @click="routerTo('brandList')">品牌</van-tabbar-item>
      <van-tabbar-item icon="records" @click="routerTo('categoryList')">分类</van-tabbar-item>
      <van-tabbar-item icon="cart" @click="routerTo('cart')" v-bind:info="totalNum">购物车</van-tabbar-item>
      <van-tabbar-item icon="contact" @click="routerTo('userCentre')">我的</van-tabbar-item>
    </van-tabbar>
  </div>
</template>

<script>
import Vue from 'vue';
import { mapGetters } from 'vuex';
import { Tabbar, TabbarItem } from 'vant';

Vue.use(Tabbar).use(TabbarItem);

export default {
  data() {
    return {
      active: 0
    };
  },
  props: ['routeActive'],
  created() {
    this.active = this.routeActive;
  },
  computed: {
    ...mapGetters(['totalNum'])
  },
  methods: {
    routerTo(next) {
      this.$router.push({ name: next });
    }
  }
};
</script>

<style lang="less">
  /*皮肤颜色*/
  #bottom-wrap .van-tabbar-item--active{
    color: #357363;
  }
</style>
