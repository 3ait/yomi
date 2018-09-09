<template>
  <div class="nav-wrap" id="nav">
    <nav class="clearfix">
      <router-link to="/home">
        <div class="img-wrap">
          <img :src="navIcon1">
        </div>
        <span>商品分类</span>
      </router-link>
      <router-link to="/my">
        <div class="img-wrap">
          <img :src="navIcon2">
        </div>
        <span>公告</span>
      </router-link>
      <router-link to="/my">
        <div class="img-wrap">
          <img :src="navIcon3">
        </div>
        <span>订单</span>
      </router-link>
      <router-link to="/my">
        <div class="img-wrap">
          <img :src="navIcon4">
        </div>
        <span>浏览历史</span>
      </router-link>
    </nav>
  </div>
</template>

<script>
    export default {
      data() {
        return {
          navIcon1: require('../../../../assets/images/img-categroy-ico@2x.png'),
          navIcon2: require('../../../../assets/images/img-notification-ico@2x.png'),
          navIcon3: require('../../../../assets/images/img-order-ico@2x.png'),
          navIcon4: require('../../../../assets/images/img-history-ico@2x.png')
        };
      }
    };
</script>

<style scoped>

</style>
