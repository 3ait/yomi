<template>
    <div id="tabs-wrap">
      <van-tabs v-model="active" swipeable  @click="onClick">
        <van-tab v-for="tab in tabs" :key="tab.index" :title="tab.name"></van-tab>
      </van-tabs>
      <div id="collapse-wrap" v-bind:class=" isCollapseActive ? 'opened' : 'closed' ">
        <van-collapse v-model="activeNames" @change="onChange">
          <van-collapse-item class="channel-list" title="全部频道" name="1">
            <van-row>
              <van-col v-for="tab in tabs" :key="tab.index" span="6">
                <router-link :class="tab.index === 0 ? 'active' : '' " :to="'/category/list/' + tab.id">
                  {{ tab.name }}
                </router-link>
              </van-col>
            </van-row>
          </van-collapse-item>
        </van-collapse>
      </div>
    </div>
</template>

<script>
  import Vue from 'vue';
  import axios from '@/http';
  import api from '@/constant/api';
  import { Tab, Tabs, Toast, Collapse, CollapseItem, Row, Col } from 'vant';

  Vue.use(Tab).use(Tabs).use(Collapse).use(CollapseItem).use(Row).use(Col);

  export default {
    name: 'toptabs',
    data() {
      return {
        active: 0,
        activeNames: [],
        isCollapseActive: false,
        tabs: [
          {
            name: '推荐',
            index: 0
          }
        ]
      };
    },
    mounted() {
      this.getTabs();
    },
    methods: {
      onClick(index, title) {
        const categoryId = this.tabs[index].id;
        this.$router.push({ name: 'productList', params: { id: categoryId }});
      },
      onChange() {
        this.isCollapseActive = !this.isCollapseActive;
      },
      getTabs() {
        const _this = this;
        axios.get(api.categories)
          .then(response => {
            if (response.data.length > 0) {
              response.data.forEach((item, i) => {
                _this.$set(item, 'index', i + 1);
                _this.tabs.push(item);
              });
            } else {
              Toast('网站内部错误，请联系管理员');
              return false;
            }
          }).catch(function(error) {
            console.log('error: ' + error);
          });
      }
    }
  };
</script>

<style lang="less">
  /*top-tabs*/
  #tabs-wrap{
    position: relative;
    .van-tabs--line{
      padding-top: 32px;
      .van-tabs__wrap{
        height: 32px;
      }
    }
    .van-tab{
      line-height: 32px;
      background-color: #fbfbfb;
      &__content{
        display: none;
      }
      &__nav{
        background-color: #fbfbfb;
      }
    }
    .van-hairline--top-bottom::after{
      border: none;
    }
  }
  #collapse-wrap{
    position: absolute;
    top: 0;
    right: 0;
    z-index: 1000;
    .van-cell{
      background-color: #fbfbfb;
      font-size: 12px;
      padding: 4px 12px;
      &__value{
        display: none;
      }
      &:not(:last-child)::after{
        left: 0;
      }
    }
    .van-collapse-item__content{
      background: #fbfbfb;
      padding: 6px 0;
    }
    .channel-list{
      background: #fbfbfb;
      .van-col {
        padding: 8px 12px;
        text-align: center;
        a{
          border: 1px solid #e5e5e5;
          border-radius: 3px;
          display: block;
        }
      }
    }
    &.closed, &.closed .van-collapse, &.closed .van-collapse-item, &.closed .van-cell{
      width: 35px;
    }
    &.opened .van-cell__value{
      display: block;
    }
    &.opened, &.closed .van-collapse, &.closed .van-collapse-item, &.closed .van-cell{
      width: 100%;
    }
  }

  /*皮肤颜色*/
  #tabs-wrap .van-tab--active{
    color: #357363;
  }
  #tabs-wrap .van-tabs__line{
    background-color: #357363;
  }
  #tabs-wrap .van-tabs__nav-bar{
    background-color: #357363;
  }
  #tabs-wrap .channel-list .van-col a.active{
    border: 1px solid #357363;
    color: #357363;
  }
</style>
