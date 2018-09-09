<template>
  <div id="slider-wrap">
    <van-swipe class="swipe-wrap" :autoplay="3000">
      <van-swipe-item class="swipe-item" v-for="(image, index) in images" :key="index">
        <img v-bind:src="baseUrl + image.filePath" />
      </van-swipe-item>
    </van-swipe>
    <van-row class="index-service-policy">
      <van-col span="8">
        <van-icon name="passed" />&nbsp;<span>新西兰一线品牌</span>
      </van-col>
      <van-col span="8">
        <van-icon name="passed" />&nbsp;海外仓一键代发
      </van-col>
      <van-col span="8">
        <van-icon name="passed" />&nbsp;全程追溯品质保障
      </van-col>
    </van-row>
  </div>
</template>

<script>
  import { Swipe, SwipeItem, Lazyload, Row, Col, Icon } from 'vant';
  import axios from '@/http';

  export default {
    data() {
      return {
        images: [],
        baseUrl: ''
      };
    },
    created: function() {
      const _this = this;
      _this.baseUrl = _this.$store.state.baseUrl;
      axios.get('/m/api/index/silder/indexSlider')
        .then(response => {
          _this.images = response.data;
        })
        .catch();
    },
    components: {
      [Swipe.name]: Swipe,
      [SwipeItem.name]: SwipeItem,
      [Lazyload.name]: Lazyload,
      [Row.name]: Row,
      [Col.name]: Col,
      [Icon.name]: Icon
    }
  };
</script>
