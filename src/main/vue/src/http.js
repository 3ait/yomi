import axios from 'axios';
import store from './store/index';
import * as types from './store/types';
import { router } from './router';

// axios 配置
axios.defaults.timeout = 5000;
// axios.defaults.baseURL = 'http://localhost:8080';
// axios.defaults.baseURL = 'http://www.ulife.co.nz';
axios.defaults.baseURL = 'http://yomi.co.nz';
axios.defaults.withCredentials = true;
// axios.defaults.mode = 'no-cors';
// axios.defaults.credentials = 'same-origin';

// http request 拦截器
axios.interceptors.request.use(
  config => {
    if (config.method === 'post') {
      config.headers = { 'content-type': 'application/x-www-form-urlencoded' };
    }
    if (store.getters.token) {
      config.headers.Authorization = `${store.getters.token}`;
      // CORS
      // config.headers = {
      //   'Access-Control-Allow-Origin': 'http://localhost:8081',
      //   'Access-Control-Allow-Methods': 'GET, PUT, POST, DELETE, OPTIONS',
      //   'Authorization': store.getters.token
      // };
    }
    // console.log(config);
    return config;
  },
  err => {
    return Promise.reject(err);
  });

// http response 拦截器
axios.interceptors.response.use(
  response => {
    return response;
  },
  error => {
    if (error.response) {
      switch (error.response.status) {
        case 401:
          // 401 清除token信息并跳转到登录页面
          store.commit(types.LOGOUT);
          router.replace({
            path: '/#/my/',
            query: { redirect: router.currentRoute.fullPath }
          });
      }
    }
    // console.log(JSON.stringify(error));//console : Error: Request failed with status code 402
    return Promise.reject(error.response.data);
  });

export default axios;
