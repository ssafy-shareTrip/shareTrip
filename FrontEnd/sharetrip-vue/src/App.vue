<script setup>
import { ref } from "vue";
import TheHeadingNavBar from "./components/layout/TheHeadingNavBar.vue";

const isLike = ref(false);
</script>

<template>
	<div>
		<the-heading-nav-bar></the-heading-nav-bar>

		<img :src="`/public/icon/like_${isLike}.png`" alt="" @click="isLike = !isLike" />
		<router-view></router-view>
	</div>
</template>

<style scope>
img:hover {
	cursor: pointer;
}
</style>
