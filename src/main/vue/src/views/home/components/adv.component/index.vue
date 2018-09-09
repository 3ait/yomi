<template>
  <div id="adv">
    <router-link to="/coupons">
      <img style="max-height: 200px;" v-bind:src="baseUrl + img.filePath" v-for="(img, index) in adImgs" :key="index"/>
    </router-link>
  </div>
</template>

<script>
  import axios from '@/http';

  export default {
    data() {
      return {
        baseUrl: '',
        adImgs: ''
      };
    },
    created: function() {
      const _this = this;
      _this.baseUrl = _this.$store.state.baseUrl;
      axios.get('/m/api/index/silder/indexAd')
        .then(response => {
          _this.adImgs = response.data;
        })
        .catch(function(e) {
          console.log(e);
        });
    }
  };
</script>

<style scoped>

</style>
