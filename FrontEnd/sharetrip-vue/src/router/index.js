import { createRouter, createWebHistory } from "vue-router";
import TheMainView from "@/views/TheMainView.vue";
import TheAttractionView from "@/views/TheAttractionView.vue";
import TheBoardView from "@/views/TheBoardView.vue";
import TheMyPageView from "@/views/TheMyPageView.vue";

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
			path: "/mypage",
			name: "mypage",
			component: TheMyPageView,
		},
	],
});

export default router;
