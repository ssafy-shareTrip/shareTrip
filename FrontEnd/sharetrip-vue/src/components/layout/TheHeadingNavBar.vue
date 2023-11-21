<script setup></script>

<template>
	<nav id="navBar">
		<router-link :to="{ name: 'main' }">Logo</router-link>
		<ul>
			<li><router-link :to="{ name: 'board' }">게시판</router-link></li>
			<li><router-link :to="{ name: 'attr' }">관광지</router-link></li>
			<li><router-link :to="{ name: 'mypage' }">마이페이지</router-link></li>
			<li><router-link :to="{ name: 'board' }">로그인</router-link></li>
			<li><router-link :to="{ name: 'attr' }">회원가입</router-link></li>
		</ul>
	</nav>
</template>

<style scoped>
ul,
ol {
	list-style: none;
}
a {
	text-decoration: none;
	color: black;
	font-size: 15px;
}
nav {
	width: 80%;
	overflow: hidden;
	height: 80px;
	background-color: rgb(218, 239, 255);
	margin: 0px auto;
}
#navBar {
	width: 100%;
	position: relative;
	text-align: center;
}
#navBar > a {
	line-height: 80px;
	display: block;
	font-size: 30px;
	font-weight: 900;
	position: absolute;
	left: 30px;
}
#navBar > ul {
	display: inline-block;
}
#navBar > ul li {
	float: left;
	line-height: 80px;
	padding: 0 30px;
}
</style>
