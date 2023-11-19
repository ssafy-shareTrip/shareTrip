<script setup>
import { RouterView } from "vue-router";
import TheHeadingNavBar from './components/layout/TheHeadingNavBar.vue';
</script>

<template>
  <header>
    <div>

    <TheHeadingNavBar></TheHeadingNavBar>
    <router-view></router-view>
    </div>
  </header>

</template>

<style scoped>
</style>
