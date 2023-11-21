<script setup></script>

<template>
	<nav id="navBar">
		<router-link :to="{ name: 'main' }">Logo</router-link>
		<ul>
			<li><a href="#">menu1</a></li>
			<li><a href="#">menu2</a></li>
			<li><a href="#">menu3</a></li>
			<li><a href="#">menu4</a></li>
			<li><a href="#">menu5</a></li>
		</ul>

		<select>
			<option>=test=</option>
			<option>=test=</option>
			<option>=test=</option>
		</select>
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
#navBar > select {
	padding: 0 20px;
	height: 30px;
	background-color: #1b2035;
	color: #fff;
	position: absolute;
	right: 30px;
	top: 50%;
	transform: translateY(-15px);
	border: 2px solid #fff;
	border-radius: 30px;
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
