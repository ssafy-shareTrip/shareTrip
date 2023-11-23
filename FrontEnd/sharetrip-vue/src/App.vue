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

			<v-main class="d-flex align-center justify-center" style="min-height: 100px">
				<router-view></router-view>
			</v-main>
		</v-layout>
	</div>
</template>

<style>
@font-face {
	font-family: "PyeongChangPeace-Bold";
	src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2206-02@1.0/PyeongChangPeace-Bold.woff2")
		format("woff2");
	font-weight: 700;
	font-style: normal;
}
@font-face {
	font-family: "NanumSquareRound";
	src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_two@1.0/NanumSquareRound.woff")
		format("woff");
	font-weight: normal;
	font-style: normal;
}
* {
	font-family: "NanumSquareRound";
	font-weight: bold;
}
a {
	color: inherit;
	text-decoration: none;
}
</style>
