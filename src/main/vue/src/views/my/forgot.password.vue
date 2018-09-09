<template>
  <div class="my-form" id="forgot-password-wrap">
    <van-nav-bar
      v-bind:title="title"
      left-text="返回"
      left-arrow
      @click-left="onClickLeft"
    />
    <div class="login-logo"><router-link to="/home"><img :src="imgSrc" /></router-link></div>
    <van-cell-group>
      <van-field
        v-model="email"
        required
        clearable
        label="注册邮箱"
        placeholder="请输入注册邮箱"
        @blur="checkEmail"
        :error-message="errMsg"
      />
    </van-cell-group>
    <van-button class="my-submit" type="primary" bottom-action @click="retrieve" :loading="isLoading">找回密码</van-button>
  </div>
</template>

<script>
  import Vue from 'vue';
  import axios from '@/http';
  import api from '@/constant/api';
  import { NavBar, Field, Cell, CellGroup, Button, Toast, Dialog } from 'vant';

  Vue.use(NavBar).use(Field).use(Cell).use(CellGroup).use(Button).use(Dialog);

  export default {
    name: 'forgot-password',
    data() {
      return {
        title: '找回密码',
        email: '',
        errMsg: '',
        emailValidate: false,
        isLoading: false,
        imgSrc: require('@/assets/images/logo-gold.jpg')
      };
    },
    methods: {
      retrieve() {
        const _this = this;
        if (_this.emailValidate) {
          _this.isLoading = true;
          axios.get(api.retrieve + '?email=' + this.email)
            .then(response => {
              _this.isLoading = false;
              if (response.data) {
                Dialog.alert({
                  title: '操作成功',
                  message: '新密码已发送至您的注册邮箱，请尽快更改密码。'
                }).then(() => {
                  // on close
                  _this.$router.push({ name: 'login' });
                });
              } else {
                Toast('无效邮箱，请重新填写注册邮箱');
              }
            });
        }
      },
      checkEmail() {
        const reg = /^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$/;
        if (reg.test(this.email)) {
          this.errMsg = '';
          this.emailValidate = true;
        } else {
          this.errMsg = '请输入正确的邮箱格式';
          this.emailValidate = false;
        }
      },
      onClickLeft() {
        this.$router.go(-1);
      }
    }
  };
</script>

<style lang="less">
  #forgot-password-wrap{
    padding: 0 12px;
    .van-cell, .van-cell input{
      background: #ffffff;
    }
    .van-button.my-submit{
      margin-top: 25px;
    }
  }
  #forgot-password-wrap{
    margin: 0 auto 45px;
  }
  #forgot-password-wrap .login-logo{
    color: #dddddd;
    font-weight: bold;
    height: 165px;
    margin-bottom: 45px;
    text-align: center;
  }
  #forgot-password-wrap .login-logo img{
    display: block;
    width: 165px;
    height: 165px;
    margin: 0 auto;
  }
  /*皮肤颜色*/
  .my-form .van-button--bottom-action.van-button--primary{
    background-color: #357363;
  }
  .my-form .van-button--primary{
    border-color: #357363;
    background-color: #357363;
  }
  .my-form .van-button--primary.disabled{
    border-color: #838282;
    background-color: #838282;
    color: #ffffff;
  }
</style>
