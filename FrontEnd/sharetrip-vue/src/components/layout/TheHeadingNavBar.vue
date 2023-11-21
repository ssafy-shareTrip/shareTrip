<script setup>
import { ref } from "vue";
import { useRouter } from "vue-router";
const router = useRouter();

const mvPage = (to) => {
	router.push({ name: to });
};

const navToggle = ref(false);
</script>

<template>
	<div>
		<v-app-bar title="ShareTrip" class="nav">
			<router-link :to="{ name: 'board' }" class="navItem">게시판</router-link>
			<router-link :to="{ name: 'attr' }" class="navItem">관광지</router-link>
			<router-link :to="{ name: 'mypage' }" class="navItem">마이페이지</router-link>
			<span @click="navToggle = !navToggle">유저이미지</span>
		</v-app-bar>

		<v-navigation-drawer location="right" v-model="navToggle" temporary>
			<v-list density="compact" nav>
				<v-list-item
					><v-btn variant="plain" @click="mvPage('mypage')" class="dBtn"
						>회원프로필이미지</v-btn
					></v-list-item
				>
				<v-divider></v-divider>
				<v-list-item
					><v-btn variant="plain" @click="mvPage('main')" class="dBtn"
						><v-icon color="success" icon="mdi-antenna" size="large"></v-icon>
						로그인</v-btn
					></v-list-item
				>

				<v-list-item>
					<v-btn variant="plain" @click="mvPage('main')" class="dBtn">
						회원가입
					</v-btn></v-list-item
				>
			</v-list>
		</v-navigation-drawer>
	</div>
</template>

<style scoped>
.nav {
	background-color: rgb(218, 239, 255);
}
.navItem {
	margin: 0 2%;
}
a {
	color: inherit;
	text-decoration: none;
}
.dBtn {
	width: 100%;
}
</style>
