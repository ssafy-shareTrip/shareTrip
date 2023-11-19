<script setup>
import { RouterView } from "vue-router";
import TheHeadingNavBar from './components/layout/TheHeadingNavBar.vue';
import TheAttractionView from './views/TheAttractionView.vue';
</script>

<template>
  <header>
    <TheHeadingNavBar></TheHeadingNavBar>
  </header>
  <div>
    <TheAttractionView></TheAttractionView>
    <!-- <router-view></router-view> -->
  </div>
</template>

<style scoped>
</style>
