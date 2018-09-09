<template>
  <div>
    <van-row v-if="categories.length > 0">
      <van-col v-for="(category, index) in categories" :key="index" span="8">
        <router-link :to="'/category/list/' + category.id">
          <img :src="category.defaultSrc" />
          <p>{{ category.name }}</p>
        </router-link>
      </van-col>
    </van-row>
    <h1 v-else class="empty-list">暂无二级分类</h1>
  </div>
</template>

<script>
  export default {
    name: 'category-content',
    props: {
      categories: {
        id: Number,
        name: String,
        defaultSrc: String
      }
    }
  };
</script>

<style>
  .empty-list{
    text-align: center;
    line-height: 100px;
  }
</style>
