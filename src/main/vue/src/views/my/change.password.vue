<template>
  <div class="my-form">
    <van-nav-bar
      v-bind:title="title"
      left-text="返回"
      left-arrow
      @click-left="onClickLeft"
    />
    <van-cell-group>
      <van-field
        v-model="oldPassword"
        type="password"
        label="原密码"
        placeholder="请输入原密码"
        :error-message="oldPasswordErr"
        icon="clear"
        required
        @click-icon="oldPassword = ''"
        @blur="checkOldPassword"
      />

      <van-field
        v-model="password"
        type="password"
        label="密码"
        placeholder="请输入密码"
        :error-message="passwordErr"
        required
        @click-icon="password = ''"
        @blur="checkPassword"
      />

      <van-field
        v-model="passwordConfirm"
        type="password"
        label="确认密码"
        placeholder="确认密码"
        :error-message="passwordConfirmErr"
        required
        @click-icon="passwordConfirm = ''"
        @blur="checkPasswordConfirm"
      />
    </van-cell-group>
    <van-button :class="formValidated ? 'my-submit' : 'my-submit disabled'" type="primary" bottom-action @click="submit">提交</van-button>
    <bottom-tabbar :routeActive="4"></bottom-tabbar>
  </div>
</template>

<script>
  import Vue from 'vue';
  import axios from '@/http';
  import api from '@/constant/api';
  import { mapGetters } from 'vuex';
  import { Field, Button, Cell, CellGroup, NavBar, Dialog, Toast } from 'vant';
  import bottomTabBar from '@/views/global/bottom.tabbar.component/';

  Vue.use(Field).use(Button).use(Cell).use(CellGroup).use(NavBar);

  export default {
    name: 'change-password',
    components: {
      'bottom-tabbar': bottomTabBar
    },
    data() {
      return {
        title: '修改密码',
        // old password
        oldPassword: '',
        oldPasswordErr: '',
        oldPasswordRequired: false,
        // password
        password: '',
        passwordErr: '',
        passwordRequired: false,
        // passwordConfirm
        passwordConfirm: '',
        passwordConfirmErr: '',
        passwordConfirmValidated: false,
        passwordConfirmRequired: false,
        passwordConfirmIsEqual: false,
        sms: ''
      };
    },
    computed: {
      formValidated: function() {
        return this.oldPasswordRequired && this.passwordRequired && this.passwordConfirmValidated;
      },
      ...mapGetters(['token'])
    },
    methods: {
      parseJwt() {
        const token = this.token;
        const base64Url = token.split('.')[1];
        const base64 = base64Url.replace('-', '+').replace('_', '/');
        return JSON.parse(window.atob(base64));
      },
      submit() {
        const _this = this;
        const tokenJwt = this.parseJwt();
        if (_this.formValidated) {
          axios.get(api.info + tokenJwt.userId + '/password/?prePassword=' + _this.oldPassword + '&newPassword=' + _this.password)
            .then(response => {
              if (response.data) {
                Dialog.alert({
                  title: '提示',
                  message: '密码修改成功！'
                }).then(() => {
                  // on confirm
                  _this.$router.push({ name: 'home' });
                });
              } else {
                Toast('原密码无效，修改失败');
              }
            })
            .catch(function(error) {
              console.log(error);
            });
        } else {
          return false;
        }
      },
      checkOldPassword() {
        const _this = this;
        if (!_this.oldPassword) {
          _this.oldPasswordErr = '请输入您的原密码';
          _this.oldPasswordRequired = false;
        } else {
          _this.oldPasswordErr = '';
          _this.oldPasswordRequired = true;
        }
      },
      checkPassword() {
        const _this = this;
        if (!_this.password) {
          _this.passwordErr = '请输入您的注册密码';
          _this.passwordRequired = false;
        } else {
          _this.passwordErr = '';
          _this.passwordRequired = true;
        }
      },
      checkPasswordConfirm() {
        const _this = this;
        if (!_this.passwordConfirm) {
          _this.passwordConfirmErr = '请再此输入注册密码';
          _this.passwordConfirmRequired = false;
        } else {
          _this.passwordConfirmRequired = true;
          if (_this.passwordConfirm !== _this.password) {
            _this.passwordConfirmErr = '两次输入密码必须一致';
            _this.passwordConfirmIsEqual = false;
          } else {
            _this.passwordConfirmErr = '';
            _this.passwordConfirmIsEqual = true;
          }
        }
      },
      onClickLeft() {
        this.$router.go(-1);
      }
    },
    watch: {
      passwordConfirmRequired(val) {
        (val && this.passwordConfirmIsEqual) ? this.passwordConfirmValidated = true : this.passwordConfirmValidated = false;
      },
      passwordConfirmIsEqual(val) {
        (val && this.passwordConfirmRequired) ? this.passwordConfirmValidated = true : this.passwordConfirmValidated = false;
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
  .my-form.van-button--bottom-action.van-button--primary{
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
