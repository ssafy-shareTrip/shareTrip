<script setup></script>

<template>
	<div id="navBar">
		<router-link :to="{ name: 'main' }">Logo</router-link>

		<router-link :to="{ name: 'baord' }">게시판</router-link>
		<router-link :to="{ name: 'main' }">관광지</router-link>
		<router-link :to="{ name: 'main' }">마이페이지</router-link>
		<a-button type="primary" @click="showDrawer">Open</a-button>
		<a-drawer
			title="Basic Drawer"
			placement="right"
			:closable="false"
			v-model:visible="visible"
			:after-visible-change="afterVisibleChange"
		>
			<p>Some contents...</p>
			<p>Some contents...</p>
			<p>Some contents...</p>
		</a-drawer>
	</div>
</template>

<style scoped></style>
