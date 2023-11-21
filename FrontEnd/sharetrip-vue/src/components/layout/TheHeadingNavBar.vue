<script setup>
import { ref } from "vue";
import { useRouter } from "vue-router";
const router = useRouter();

const mvPage = (to) => {
	router.push({ name: to });
};

const navToggle = ref(false);
// const tabs = ref(1);
</script>

<template>
	<div>
		<v-app-bar title="ShareTrip" color="blue-lighten-4">
			<v-tabs color="blue lighten-4" align-with-title>
				<router-link :to="{ name: 'main' }" class="navItem"
					><v-tab>Main</v-tab></router-link
				>
				<router-link :to="{ name: 'board' }" class="navItem"
					><v-tab>Board</v-tab></router-link
				>
				<router-link :to="{ name: 'attr' }" class="navItem"
					><v-tab>Attraction</v-tab></router-link
				>
				<router-link :to="{ name: 'mypage' }" class="navItem"
					><v-tab>Mypage</v-tab></router-link
				>
				<v-btn variant="plain" @click="navToggle = !navToggle" style="height: 100%"
					>유저이미지</v-btn
				>
				<!-- <v-tab @click="navToggle = !navToggle">유저이미지</v-tab> -->
			</v-tabs>
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
						>로그인</v-btn
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
a {
	color: inherit;
	text-decoration: none;
}
.dBtn {
	width: 100%;
}
</style>
