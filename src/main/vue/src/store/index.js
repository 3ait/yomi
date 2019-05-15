// 组装模块并导出 store 的地方
import Vue from 'vue';
import Vuex from 'vuex';

import cart from './modules/cart';
import auth from './modules/auth';

Vue.use(Vuex);

const store = new Vuex.Store({
  state: {
    baseUrl: 'http://yomi.co.nz/'
    // baseUrl: 'http://www.ulife.co.nz/'
    // baseUrl: 'http://localhost:8080/'
  },
  modules: {
    cart,
    auth
  },
  strict: process.env.NODE_ENV !== 'production' // 严格模式
});

export default store;
