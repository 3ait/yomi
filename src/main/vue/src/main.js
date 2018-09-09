import Vue from 'vue';
import App from './App.vue';
import { router } from './router';
import axios from './http';
import store from './store';
require('./assets/css/reset.css');
require('./assets/css/mobile.css');
require('./assets/iconfont/iconfont.css');

new Vue({
  router,
  store,
  axios,
  el: '#app',
  render: h => h(App)
});
