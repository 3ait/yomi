<template>
  <div class="my-form">

    <van-cell-group>
      <van-field
        v-model="username"
        type="text"
        label="注册邮箱"
        placeholder="请输入注册邮箱"
        :error-message="usernameErr"
        icon="clear"
        required
        @click-icon="username = ''"
        @blur="checkUsername"
      />

      <van-field
        v-model="password"
        type="password"
        label="密码"
        placeholder="请输入密码"
        :error-message="passwordErr"
        required
        @blur="checkPassword"
      />

      <van-field
        v-model="passwordConfirm"
        type="password"
        label="确认密码"
        placeholder="确认密码"
        :error-message="passwordConfirmErr"
        required
        @blur="checkPasswordConfirm"
      />

      <van-cell class="van-cell--required van-field" title="注册门店" :value="branchName" @click="showAction" />

    </van-cell-group>
    <van-actionsheet v-model="show" :actions="actions" cancel-text="取消" />
    <van-button :class="formValidated ? 'my-submit' : 'my-submit disabled'" type="primary" bottom-action @click="register">注册</van-button>
  </div>
</template>

<script>
  import Vue from 'vue';
  import axios from '@/http';
  import api from '@/constant/api';
  import 'url-search-params-polyfill';
  import * as types from '@/store/types';
  import { Field, Button, Cell, CellGroup, Toast, Actionsheet } from 'vant';

  Vue.use(Field).use(Button).use(Cell).use(CellGroup).use(Actionsheet);

  export default {
    name: 'register-form',
    data() {
      return {
        // username
        username: '',
        usernameErr: '',
        usernameValidated: false,
        usernameRequired: false,
        emailValidated: false,
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
        sms: '',
        // 注册门店
        branchName: '',
        branchId: -1,
        show: false,
        actionsItem: {
          id: -1,
          name: '',
          callback: this.onClick
        },
        actions: [{
          name: '请选择您的注册门店'
        }]
      };
    },
    computed: {
      formValidated: function() {
        return this.usernameValidated && this.passwordRequired && this.passwordConfirmValidated;
      }
    },
    created() {
      this.getBranches();
    },
    methods: {
      register() {
        const _this = this;
        if (_this.formValidated) {
          const params = new URLSearchParams();
          const returnPath = localStorage.getItem('returnPath');
          params.append('customer.email', _this.username);
          params.append('customer.password', _this.password);
          params.append('customer.phone', _this.username);
          params.append('customer.branch.id', _this.branchId);
          axios.post(api.register, params, {
            headers: { 'content-type': 'application/x-www-form-urlencoded' }
          })
            .then(response => {
              const token = response.data.token;
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
        } else {
          return false;
        }
      },
      getBranches() {
        const _this = this;
        axios.get(api.branches)
          .then(response => {
            if (response.data.length > 0) {
              _this.branchName = response.data[0].branchName;
              response.data.forEach((item, i) => {
                const actionsItem = {
                  ...this.actionsItem,
                  id: item.id,
                  name: item.branchName
                };
                _this.actions.push(actionsItem);
              });
            } else {
              Toast('加载门店列表失败');
              _this.branchName = '暂无门店信息';
            }
          });
      },
      checkUsername() {
        const _this = this;
        if (!_this.username) {
          _this.usernameErr = '请输入您的注册邮箱';
          _this.usernameRequired = false;
        } else {
          _this.usernameRequired = true;
          _this.checkEmail(_this.username);
        }
      },
      checkEmail(email) {
        const _this = this;
        axios.get(api.checkEmail, {
          params: {
            'customer.email': email
          }
        }).then(response => {
          _this.emailValidated = response.data;
          if (response.data) {
            _this.usernameErr = '';
          } else {
            _this.usernameErr = '改邮箱已注册';
          }
        }).catch(function(error) {
          console.log('error: ' + error);
        });
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
      onClick(item) {
        this.branchName = item.name;
        this.branchId = item.id;
        this.show = false;
      },
      showAction() {
        this.show = true;
      }
    },
    watch: {
      usernameRequired(val) {
        (val && this.emailValidated) ? this.usernameValidated = true : this.usernameValidated = false;
      },
      emailValidated(val) {
        (val && this.usernameRequired) ? this.usernameValidated = true : this.usernameValidated = false;
      },
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
    .van-cell__value {
      text-align: left;
    }
    .van-button.my-submit{
      margin-top: 25px;
    }
  }
  /*皮肤颜色*/
  #register-wrap .van-button--bottom-action.van-button--primary{
    background-color: #357363;
  }
  #register-wrap .van-button--primary{
    border-color: #357363;
    background-color: #357363;
  }
  #register-wrap .van-button--primary.disabled{
    border-color: #838282;
    background-color: #838282;
    color: #ffffff;
  }

</style>
