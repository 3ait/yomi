<template>
  <div>
    <search-bar></search-bar>

    <div class="h45"></div>

    <top-tabs></top-tabs>

    <slider-component></slider-component>

    <adv-component></adv-component>

    <brands-component></brands-component>

    <products-component></products-component>

    <footer>
      <div class="copyright">
        <h2>Copyright © 2018.Ultimate Life All rights reserved.</h2>
      </div>
      <br/>
      <div class="contact-us">
        <h2>新西兰热线：64-9-2189828</h2>
      </div>
    </footer>
    <bottom-tabbar :routeActive="0"></bottom-tabbar>
  </div>
</template>

<script>
  import searchBar from '@/views/global/search.component/';
  import topTabs from '@/views/global/toptabs.component/';
  import bottomTabBar from '@/views/global/bottom.tabbar.component/';
  import advComponent from '@/views/home/components/adv.component/';
  import brandsComponent from '@/views/home/components/brands.component/';
  import productsComponent from '@/views/home/components/products.component/';
  import sliderComponent from '@/views/home/components/slider.component/';

  export default {
    data() {
      return {
      };
    },
    created: function() {
    },
    components: {
      'search-bar': searchBar,
      'top-tabs': topTabs,
      'adv-component': advComponent,
      'brands-component': brandsComponent,
      'products-component': productsComponent,
      'bottom-tabbar': bottomTabBar,
      'slider-component': sliderComponent
    }
  };
</script>

<style lang="less">
  .swipe-wrap {
    width: 100%;
    .swipe-item {
      width: 100%;
      img {
        width: 100%;
      }
    }
    &__indicator {
      background: #ffffff;
    }
    &__indicator--active {
      background: #b4282d;
    }
  }

  .swipe-wrap .swipe-item {
    width: 100%;
  }

  .swipe-wrap .swipe-item img {
    width: 100%;
  }

  .van-swipe__indicator {
    background: #ffffff;
  }

  .van-swipe__indicator--active {
    background: #b4282d;
  }

  .index-service-policy {
    background: #ffffff;
    padding: 0 10px;
    height: 32px;
    line-height: 32px;
    .van-col {
      font-size: 12px;
      .van-icon {
        color: #ff4444;
      }
      .van-icon, span {
        line-height: 32px;
        display: inline-block;
      }
    }
  }

</style>

