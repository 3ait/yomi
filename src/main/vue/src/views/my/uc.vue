<template>
  <div>
    <!--<img class="user-poster" src="https://img.yzcdn.cn/public_files/2017/10/23/8690bb321356070e0b8c4404d087f8fd.png">-->
    <div class="uc-top-wrap">
      <div class="avatar">
        <van-icon name="contact" />
      </div>
      <div class="title">{{ user.email }}</div>
    </div>
    <van-row class="user-links">
      <!--<van-col span="6">-->
        <!--<van-icon name="pending-payment" />-->
        <!--待付款-->
      <!--</van-col>-->
      <!--<van-col span="6">-->
        <!--<van-icon name="pending-orders" />-->
        <!--待接单-->
      <!--</van-col>-->
      <!--<van-col span="6">-->
        <!--<van-icon name="pending-deliver" />-->
        <!--待发货-->
      <!--</van-col>-->
      <!--<van-col span="6">-->
        <!--<van-icon name="logistics" />-->
        <!--待发货-->
      <!--</van-col>-->
    </van-row>

    <van-cell-group class="user-group">
      <van-cell icon="records" title="全部订单" is-link @click="toMyOrders" />
    </van-cell-group>

    <van-cell-group>
      <!--<van-cell icon="info-o" title="个人信息" is-link @click="test" />-->
      <van-cell icon="edit" title="修改密码" is-link @click="toChangePassword" />
      <van-cell icon="gold-coin" title="我的优惠券" is-link @click="toMyCoupons" />
    </van-cell-group>
    <div class="logout-wrap">
      <van-button v-on:click="logout" type="primary" bottom-action>退出</van-button>
    </div>
    <bottom-tabbar :routeActive="4"></bottom-tabbar>
  </div>
</template>

<script>
  import Vue from 'vue';
  import axios from '@/http';
  import { mapGetters } from 'vuex';
  import * as types from '@/store/types';
  import api from '@/constant/api';
  import { Row, Col, Icon, Cell, CellGroup, Button } from 'vant';
  import bottomTabBar from '@/views/global/bottom.tabbar.component/';

  Vue.use(Button);

  export default {
    data() {
      return {
        user: {}
      };
    },
    components: {
      [Row.name]: Row,
      [Col.name]: Col,
      [Icon.name]: Icon,
      [Cell.name]: Cell,
      [CellGroup.name]: CellGroup,
      'bottom-tabbar': bottomTabBar
    },
    computed: {
      ...mapGetters(['token'])
    },
    created() {
      const _this = this;
      const tokenJwt = this.parseJwt();
      if (tokenJwt !== null) {
        axios.get(api.info + tokenJwt.userId)
          .then(response => {
            const userInfo = JSON.stringify(response.data);
            _this.$store.commit(types.INFO, userInfo);
            _this.user = response.data;
          });
      } else {
        _this.$router.push({ name: 'home' });
      }
    },
    methods: {
      parseJwt() {
        const token = this.token;
        if (this.token === null) {
          return null;
        } else {
          const base64Url = token.split('.')[1];
          const base64 = base64Url.replace('-', '+').replace('_', '/');
          return JSON.parse(window.atob(base64));
        }
      },
      toMyOrders() {
        this.$router.push({ name: 'myOrders' });
      },
      toMyCoupons() {
        this.$router.push({ name: 'myCoupons' });
      },
      toChangePassword() {
        this.$router.push({ name: 'changePassword' });
      },
      logout() {
        this.$store.commit(types.LOGOUT);
        this.$router.push({ name: 'login' });
      }
    }
  };
</script>

<style lang="less">
  .uc-top-wrap{
    background: #b6a270;
    height: 180px;
    .avatar{
      background: #ffffff;
      border-radius: 48px;
      width: 80px;
      height: 80px;
      margin: 50px 20px 0 30px;
      float: left;
      .van-icon{
        color: #b6a270;
        font-size: 40px;
        margin: 20px 0 0 20px;
      }
    }
    .title{
      width: 240px;
      height: 32px;
      color: #ffffff;
      line-height: 32px;
      font-size: 20px;
      margin-top: 74px;
      float: left;
    }
  }
  .user {
    /*&-poster {*/
      /*width: 7.5rem;*/
      /*height: 4rem;*/
      /*display: block;*/
    /*}*/
    &-group {
      margin-bottom: .3rem;
    }
    &-links {
      padding: 15px 0;
      font-size: 12px;
      text-align: center;
      background-color: #fff;
      .van-icon {
        display: block;
        font-size: 24px;
      }
    }
  }
  .logout-wrap{
    padding: 15px;
  }
</style>
