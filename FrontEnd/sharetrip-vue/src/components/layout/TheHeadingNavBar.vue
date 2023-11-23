<script setup>
import { ref } from "vue";
import { useRouter } from "vue-router";
import { useUserStore } from "@/stores/user";
const router = useRouter();
const store = useUserStore();
const userStore = useUserStore();
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
			<v-app-bar-title style="margin-left: 3%"
				><router-link :to="{ name: 'main' }" class="navItem"
					><img
						src="https://raw.githubusercontent.com/Tarikul-Islam-Anik/Animated-Fluent-Emojis/master/Emojis/Travel%20and%20places/Airplane.png"
						width="37"
						height="37"
					/><span
						style="
							font-family: 'PyeongChangPeace-Bold';
							background-image: linear-gradient(
								to right,
								rgb(218, 169, 213),
								rgb(129, 185, 207)
							);
							background-clip: text;
							-webkit-background-clip: text;
							color: transparent;
						"
						>&nbsp; ShareTrip</span
					></router-link
				></v-app-bar-title
			>

			<v-tabs
				color="blue lighten-4"
				align-with-title
				next-icon="mdi-chevron-right"
				prev-icon="mdi-chevron-left"
				show-arrows
			>
				<router-link :to="{ name: 'main' }" class="navItem" replace
					><v-tab><v-icon icon="mdi-home-circle"></v-icon>&nbsp; Main</v-tab></router-link
				>
				<router-link :to="{ name: 'share' }" class="navItem" replace
					><v-tab
						><v-icon icon="mdi-share-variant"></v-icon>&nbsp; Share</v-tab
					></router-link
				>
				<router-link :to="{ name: 'path' }" class="navItem" replace
					><v-tab
						><v-icon icon="mdi-map-marker-path"></v-icon>&nbsp; Path</v-tab
					></router-link
				>
				<router-link :to="{ name: 'attr' }" class="navItem" replace
					><v-tab
						><v-icon icon="mdi-balloon"></v-icon>&nbsp;Attraction</v-tab
					></router-link
				>
				<router-link :to="{ name: 'mypage' }" class="navItem" replace
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
					<v-list-item>
						<v-card align="center">
							<template v-slot:title>
								<v-img
									class="bg-white"
									width="30%"
									:aspect-ratio="1"
									src="https://raw.githubusercontent.com/Tarikul-Islam-Anik/Animated-Fluent-Emojis/master/Emojis/Smilies/Cat%20with%20Wry%20Smile.png"
									style="border-radius: 20%"
									cover
								></v-img>
							</template>
							<template v-slot:subtitle> {{ userStore.userName }} </template>
						</v-card>
					</v-list-item>
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
