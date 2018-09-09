<template>
  <div id="login-form" class="my-form">
    <van-cell-group>
      <van-field
        v-model="username"
        label="用户名"
        icon="clear"
        placeholder="请输入用户名"
        required
        @click-icon="username = ''"
      />

      <van-field
        v-model="password"
        type="password"
        label="密码"
        placeholder="请输入密码"
        required
      />
    </van-cell-group>
    <van-button class="my-submit" type="primary" bottom-action @click="login">登录</van-button>
  </div>
</template>

<script>
  import Vue from 'vue';
  import axios from '@/http';
  import api from '@/constant/api';
  import 'url-search-params-polyfill';
  import * as types from '@/store/types';
  import { Field, Button, Cell, CellGroup, Toast } from 'vant';

  Vue.use(Field).use(Button).use(Cell).use(CellGroup);

  export default {
    name: 'login-form',
    data() {
      return {
        username: '',
        password: ''
      };
    },
    methods: {
      login() {
        const _this = this;
        const returnPath = localStorage.getItem('returnPath');
        // axios serializes JavaScript objects to JSON. To send data in the application/x-www-form-urlencoded format instead, you can use one of the following options.
        const params = new URLSearchParams();
        params.append('username', _this.username);
        params.append('password', _this.password);
        // alert(params);
        // let params = '';
        // params += 'username=' + _this.username;
        // params += '&password=' + _this.password;
        // console.log(params);
        axios.post(api.login, params)
          .then(response => {
            const token = response.data;
            if (token !== '') {
              _this.$store.commit(types.LOGIN, token);
              if (returnPath === null) {
                _this.$router.push({ name: 'userCentre' });
              } else {
                localStorage.removeItem('returnPath');
                _this.$router.push({ name: returnPath });
              }
            } else {
              Toast('用户名密码错误，请稍后再试！');
            }
          })
          .catch(function(error) {
            console.log(error);
          });
      }
    }
  };
</script>

<style lang="less">
  .my-form{
    padding: 0 12px;
    .van-cell, .van-cell input{
      background: #ffffff;
    }
    .van-button.my-submit{
      margin-top: 25px;
    }
  }
  /*皮肤颜色*/
  #login-wrap .van-button--bottom-action.van-button--primary{
    background-color: #357363;
  }
</style>
