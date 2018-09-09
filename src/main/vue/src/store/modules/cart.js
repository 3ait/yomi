import { Toast } from 'vant';
import 'ie-array-find-polyfill';
import * as types from '../types';

// 初始化数据
const localAdded = localStorage.getItem('added') == null ? new Array(0) : JSON.parse(localStorage.getItem('added'));
const state = {
  // 添加到购物车的商品
  added: localAdded,
  couponId: -1
};

// getter 抛出去的数据
const getters = {
  // 购物车的列表
  cartProducts: state => state.added,
  // 优惠券
  couponId: state => state.couponId,
  // 计算总价
  totalPrice: (state, getters) => {
    let total = 0;
    getters.cartProducts.forEach(n => {
      total += Number(n.price) * n.num;
    });
    return total;
  },
  // 计算总数量
  totalNum: (state, getters) => {
    let total = 0;
    getters.cartProducts.forEach(n => {
      total += n.num;
    });
    return total;
  }

};

// action 异步的操作
const actions = {
  // 添加到购物车操作
  addToCart({ commit }, product) {
    commit(types.ADD_PRODUCT, product);
  },
  // 添加到购物车操作
  reduceFromCart({ commit }, product) {
    commit(types.REDUCE_PRODUCT, product);
  },
  // 清除购物车
  clearAllCart({ commit }) {
    commit(types.CLEAR_ALL);
  },
  // 删除购物车的指定的商品
  delProduct({ commit }, product) {
    commit(types.DEL_PRODUCT, product);
  },
  // 保存优惠券
  saveCoupon({ commit }, couponId) {
    commit(types.SAVE_COUPON, couponId);
  }
};

// mutation
const mutations = {
  // 添加到购物车操作
  [types.ADD_PRODUCT]: (state, product) => {
    try {
      const record = state.added.find(n => n.id === product.id);
      if (!record) {
        state.added.push(product);
      } else {
        record.num++;
      }
      localStorage.added = JSON.stringify(state.added);
    } catch (err) {
      alert(err);
    }
  },
  // 减少购物车商品
  [types.REDUCE_PRODUCT]: (state, product) => {
    const record = state.added.find(n => n.id === product.id);
    if (record.num !== 0) {
      record.num--;
    }
    localStorage.added = JSON.stringify(state.added);
  },
  // 清除购物车
  [types.CLEAR_ALL]: (state) => {
    state.added = [];
    localStorage.removeItem('added');
  },
  // 删除购物车的指定的商品
  [types.DEL_PRODUCT]: (state, product) => {
    state.added.forEach((n, i) => {
      if (n.id === product.id) {
        // console.info(11,n)
        // 找到index的下标值
        state.added.splice(i, 1);
      }
    });
    Toast(product.productName + '已从购物车删除');
    localStorage.added = JSON.stringify(state.added);
  },
  // 保存优惠券
  [types.SAVE_COUPON]: (state, couponId) => {
    state.couponId = couponId;
  }
};

export default {
  state,
  mutations,
  actions,
  getters
};
