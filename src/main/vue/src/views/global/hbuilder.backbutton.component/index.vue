<template>
</template>

<script>
  export default {
    name: 'back',
    created() {
      const _this = this;
      console.log('back button component is loading');
      document.addEventListener('plusready', function() {
        // const webview = plus.webview.currentWebview();
        plus.key.addEventListener('backbutton', function() {
          _this.$router.go(-1);
          // webview.canBack(function(e) {
          //   if (e.canBack) {
          //     webview.back();
          //   } else {
          //     let first = null;
          //     plus.key.addEventListener('backbutton', function() {
          //       if (!first) {
          //         first = new Date().getTime();
          //         console.log('再按一次退出应用');
          //         setTimeout(function() {
          //           first = null;
          //         }, 1000);
          //       } else {
          //         if (new Date().getTime() - first < 1500) {
          //           plus.runtime.quit();
          //         }
          //       }
          //     }, false);
          //   }
          // });
        });
      });
    }
  };
</script>

<style scoped>

</style>
