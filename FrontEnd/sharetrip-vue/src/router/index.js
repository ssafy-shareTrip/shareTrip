import { createRouter, createWebHistory } from "vue-router";
import { useUserStore } from "@/stores/user";
import TheMainView from "@/views/TheMainView.vue";
import TheAttractionView from "@/views/TheAttractionView.vue";
import TheBoardView from "@/views/TheBoardView.vue";
import TheMyPageView from "@/views/TheMyPageView.vue";
import TheLoginView from "@/views/TheLoginView.vue";
import TheJoinView from "@/views/TheJoinView.vue";
import ThePathView from "@/views/ThePathView.vue";

const router = createRouter({
	history: createWebHistory(import.meta.env.BASE_URL),
	routes: [
		{
			path: "/",
			name: "main",
			component: TheMainView,
		},
		{
			path: "/attr",
			name: "attr",
			component: TheAttractionView,
		},
		
		{
			path: "/board",
			name: "board",
			component: TheBoardView,
		},
		{
			path: "/path",
			name: "path",
			component: ThePathView,
		},
		{
			path: "/mypage",
			name: "mypage",
			component: TheMyPageView,
			beforeEnter: () => {
				const store = useUserStore();
				if (store.userId == null) return { name: "login" };
			},
		},
		{
			path: "/login",
			name: "login",
			component: TheLoginView,
		},
		{
			path: "/join",
			name: "join",
			component: TheJoinView,
		},
	],
});

export default router;
