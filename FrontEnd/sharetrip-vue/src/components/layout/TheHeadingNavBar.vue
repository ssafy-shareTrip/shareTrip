<script setup></script>

<template>
	<div class="navBar">
		<router-link :to="{ name: 'main' }" class="navItem">LOGO</router-link>
		<router-link :to="{ name: 'board' }" class="navItem">게시판</router-link>
		<router-link :to="{ name: 'attr' }" class="navItem">관광지</router-link>
		<router-link :to="{ name: 'mypage' }" class="navItem">마이페이지</router-link>
	</div>
</template>

<style scoped>
.navBar {
	display: flex;
	width: 100%;
	height: 50px;
	line-height: 50px;
	background-color: rgb(218, 239, 255);
}

.navItem {
	margin: 0 2%;
}
</style>
