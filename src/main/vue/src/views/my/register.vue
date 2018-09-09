<template>
  <div id="register-wrap">
    <van-nav-bar
      v-bind:title="title"
      left-text="返回"
      left-arrow
      @click-left="onClickLeft"
    />
    <div class="login-logo"><router-link to="/home"><img :src="imgSrc" /></router-link></div>
    <register-form></register-form>
    <div class="my-router-tab">
      <router-link to="/my">登录</router-link>|<router-link to="/my/register">注册</router-link>|<router-link to="/my/forgotPassword">忘记密码？</router-link>
    </div>
  </div>
</template>

<script>
  import Vue from 'vue';
  import { NavBar } from 'vant';
  import registerForm from './components/register.form.component/';

  Vue.use(NavBar);

  export default {
    name: 'register',
    data() {
      return {
        title: '注册新用户',
        imgSrc: require('@/assets/images/logo-gold.jpg')
      };
    },
    components: {
      'register-form': registerForm
    },
    methods: {
      onClickLeft() {
        this.$router.go(-1);
      }
    }
  };
</script>

<style>
  body{
    background: #ffffff;
  }
  #register-wrap{
    margin: 0 auto 45px;
  }
  #register-wrap .login-logo{
    color: #dddddd;
    font-weight: bold;
    height: 165px;
    margin-bottom: 45px;
    text-align: center;
  }
  #register-wrap .login-logo img{
    display: block;
    width: 165px;
    height: 165px;
    margin: 0 auto;
  }
  #register-wrap .van-hairline--top-bottom::after{
    border: none;
  }
  .my-router-tab{
    margin: 10px auto 0;
    width: 90%;
    text-align: center;
  }
  .my-router-tab  a{
    display: inline-block;
    height: 24px;
    line-height: 24px;
    width: 90px;
  }
</style>
