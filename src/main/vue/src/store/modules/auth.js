import * as types from '../types';

// 初始化数据
const localToken = localStorage.getItem('token');
const state = {
  userInfo: {},
  // auth token
  token: localToken
};

// getter 抛出去的数据
const getters = {
  // token
  token: state => state.token,
  // user
  userInfo: state => state.userInfo
};

// mutation
const mutations = {
  [types.LOGIN]: (state, data) => {
    localStorage.token = data;
    state.token = data;
  },
  [types.LOGOUT]: (state) => {
    localStorage.removeItem('token');
    state.token = null;
    localStorage.removeItem('userInfo');
    state.userInfo = {};
  },
  [types.INFO]: (state, data) => {
    localStorage.userInfo = data;
    state.userInfo = data;
  }
};

export default {
  state,
  mutations,
  getters
};
