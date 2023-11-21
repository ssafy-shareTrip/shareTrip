<script setup>
import TheHeadingNavBar from "./components/layout/TheHeadingNavBar.vue";
</script>

<template>
	<div>
		<the-heading-nav-bar></the-heading-nav-bar>

		<router-view></router-view>
	</div>
</template>

<style>
* {
	padding: 0;
	margin: 0;
}
</style>
