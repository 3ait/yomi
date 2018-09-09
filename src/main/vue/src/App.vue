<template>
  <div id="app">
    <router-view></router-view>
    <back-button></back-button>
  </div>
</template>

<script>
/* 公共组件引用 */
import backButton from '@/views/global/hbuilder.backbutton.component/';

export default {
  name: 'app',
  components: {
    'back-button': backButton
  }
};
</script>

<style>
body {
  background-color: #ffffff;
}
.h45{
  height: 45px;
}
#app{
  width: 100%;
  height: 100%;
  max-width: 640px;
  min-width: 300px;
  margin: 0 auto;
}
.van-cell__title .van-icon {
  font-size: 18px;
}
</style>
