<script setup>
import { ref } from "vue";
import { useRouter } from "vue-router";
import { useUserStore } from "@/stores/user";
const router = useRouter();
const store = useUserStore();
const mvPage = (to) => {
	navToggle.value = false;
	router.push({ name: to });
};
const logout = () => {
	store.setId(null, null);
	mvPage("main");
};
const navToggle = ref(false);
</script>

<template>
	<div>
		<v-app-bar color="blue-lighten-4">
			<v-app-bar-title
				><router-link :to="{ name: 'main' }" class="navItem"
					>로고이미지</router-link
				></v-app-bar-title
			>

			<v-tabs color="blue lighten-4" align-with-title>
				<router-link :to="{ name: 'board' }" class="navItem"
					><v-tab
						><v-icon icon="mdi-bulletin-board"></v-icon>&nbsp; Board</v-tab
					></router-link
				>
				<router-link :to="{ name: 'attr' }" class="navItem"
					><v-tab
						><v-icon icon="mdi-balloon"></v-icon>&nbsp;Attraction</v-tab
					></router-link
				>
				<router-link :to="{ name: 'mypage' }" class="navItem"
					><v-tab
						><v-icon icon="mdi-badge-account"></v-icon>&nbsp; Mypage</v-tab
					></router-link
				>
				<v-btn variant="plain" @click="navToggle = !navToggle" style="height: 100%"
					><v-icon icon="mdi-account"></v-icon
				></v-btn>
			</v-tabs>
		</v-app-bar>

		<v-navigation-drawer location="right" v-model="navToggle" temporary>
			<v-list density="compact" nav>
				<template v-if="store.userId == null">
					<v-list-item
						><v-icon icon="mdi-account" style="width: 100%"></v-icon
					></v-list-item>
					<v-divider></v-divider>
					<v-list-item
						><v-btn variant="plain" @click="mvPage('login')" class="dBtn"
							><v-icon icon="mdi-account-key"></v-icon>&nbsp;로그인</v-btn
						></v-list-item
					>

					<v-list-item>
						<v-btn variant="plain" @click="mvPage('join')" class="dBtn">
							<v-icon icon="mdi-account-multiple-plus"></v-icon>&nbsp;회원가입
						</v-btn></v-list-item
					>
				</template>
				<template v-else>
					<v-list-item
						><v-btn variant="plain" @click="mvPage('mypage')" class="dBtn"
							>회원 프로필 이미지</v-btn
						></v-list-item
					>
					<v-divider></v-divider>
					<v-list-item
						><v-btn variant="plain" @click="logout" class="dBtn"
							><v-icon icon="mdi-account-cancel"></v-icon>&nbsp;로그아웃</v-btn
						></v-list-item
					>
				</template>
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
