import Vue from 'vue';
import Router from 'vue-router';
import store from './store/index';
// import * as types from '@/store/types';

Vue.use(Router);

const routes = [
  {
    path: '*',
    redirect: '/home'
  },
  {
    name: 'home',
    path: '/home',
    component: () => import('./views/home/home'),
    meta: {
      title: '首页'
    }
  },
  {
    name: 'scanner',
    path: '/scanner',
    component: () => import('./views/home/scanner'),
    meta: {
      title: '优惠券'
    }
  },
  {
    name: 'coupons',
    path: '/coupons',
    component: () => import('./views/home/coupons'),
    meta: {
      title: '优惠券'
    }
  },
  {
    path: '/brand',
    component: () => import('./views/brand/index'),
    children: [
      {
        name: 'brandList',
        path: '/',
        component: () => import('./views/brand/brand.list'),
        meta: {
          title: '所有品牌'
        }
      },
      {
        name: 'brandDetail',
        path: 'detail/:id',
        component: () => import('./views/brand/brand.detail'),
        meta: {
          title: '品牌明细'
        }
      }
    ]
  },
  {
    path: '/category',
    component: () => import('./views/category/index'),
    children: [
      {
        name: 'categoryList',
        path: '/',
        component: () => import('./views/category/category.list'),
        meta: {
          title: '分类'
        }
      },
      {
        name: 'productList',
        path: 'list/:id',
        component: () => import('./views/category/product.list'),
        meta: {
          title: '列表'
        }
      },
      {
        name: 'productDetail',
        path: 'product/:id',
        component: () => import('./views/category/product.detail'),
        meta: {
          title: '产品'
        }
      },
      {
        name: 'qList',
        path: 'qList/:keyword',
        component: () => import('./views/category/q.list'),
        meta: {
          title: '搜索结果'
        }
      }
    ]
  },
  {
    path: '/cart',
    component: () => import('./views/cart/index'),
    children: [
      {
        name: 'cart',
        path: '/',
        component: () => import('./views/cart/cart'),
        meta: {
          title: '购物车'
        }
      },
      {
        name: 'shipping',
        path: 'shipping',
        component: () => import('./views/cart/shipping'),
        meta: {
          title: '物流',
          requireAuth: true
        }
      },
      {
        name: 'paymentResult',
        path: 'paymentResult/:result',
        component: () => import('./views/cart/payment.result'),
        meta: {
          title: '支付结果',
          requireAuth: true
        }
      }
    ]
  },
  {
    path: '/my',
    component: () => import('./views/my/index'),
    children: [
      {
        name: 'login',
        path: '/',
        component: () => import('./views/my/login'),
        meta: {
          title: '登录'
        }
      },
      {
        name: 'register',
        path: 'register',
        component: () => import('./views/my/register'),
        meta: {
          title: '注册'
        }
      },
      {
        name: 'userCentre',
        path: 'uc',
        component: () => import('./views/my/uc'),
        meta: {
          title: '用户中心',
          requireAuth: true
        }
      },
      {
        name: 'myOrders',
        path: 'myOrders',
        component: () => import('./views/my/my.orders'),
        meta: {
          title: '我的订单',
          requireAuth: true
        }
      },
      {
        name: 'myCoupons',
        path: 'myCoupons',
        component: () => import('./views/my/my.coupons'),
        meta: {
          title: '我的优惠券',
          requireAuth: true
        }
      },
      {
        name: 'forgotPassword',
        path: 'forgotPassword',
        component: () => import('./views/my/forgot.password'),
        meta: {
          title: '忘记密码'
        }
      },
      {
        name: 'changePassword',
        path: 'changePassword',
        component: () => import('./views/my/change.password'),
        meta: {
          title: '修改密码'
        }
      }
    ]
  }
];

const router = new Router({ routes });

router.beforeEach((to, from, next) => {
  const nextRoute = ['userCentre', 'shipping', 'myOrders', 'myCoupons'];
  const auth = store.getters.token;
  // 跳转至上述页面
  if (nextRoute.indexOf(to.name) >= 0) {
    // 未登录
    if (auth == null) {
      router.push({ name: 'login' });
    }
  }
  // 已登录的情况再去登录页，跳转至首页
  if (to.name === 'login') {
    if (auth) {
      router.push({ name: 'home' });
    }
  }
  next();
});

export {
  router
};
