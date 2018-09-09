<template>
  <div class="category-left fl">
    <ul>
      <li v-for="tab in tabs" v-on:click="handleClick(tab)" :class="tab.active ? 'active' : ''">{{ tab.name }}</li>
    </ul>
  </div>
</template>

<script>
  export default {
    name: 'left-form',
    props: {
      tabs: {
        id: Number,
        name: String,
        active: Boolean
      }
    },
    methods: {
      handleClick(tab) {
        const _this = this;
        // tab切换
        const currentTab = _this.tabs.find(n => n.active === true);
        const selectedTab = _this.tabs.find(n => n.id === tab.id);
        currentTab.active = false;
        selectedTab.active = true;
        this.$emit('getMenuFunc', tab);
      }
    }
  };
</script>

<style scoped>

</style>
