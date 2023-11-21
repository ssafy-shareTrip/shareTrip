<script setup>
import TheHeadingNavBar from "./components/layout/TheHeadingNavBar.vue";
</script>

<template>
	<div style="width: 100%">
		<v-layout class="rounded rounded-md">
			<the-heading-nav-bar></the-heading-nav-bar>
			<!-- <v-navigation-drawer>
			<v-list>
				<v-list-item title="Drawer left"></v-list-item>
			</v-list>
		</v-navigation-drawer> -->

			<!-- <v-navigation-drawer location="right">
			<v-list>
				<v-list-item title="Drawer right"></v-list-item>
			</v-list>
		</v-navigation-drawer> -->

			<v-main class="d-flex align-center justify-center" style="min-height: 300px">
				<router-view></router-view>
			</v-main>
		</v-layout>
	</div>
</template>

<style scope>
.nav {
	background-color: rgb(218, 239, 255);
}
</style>
