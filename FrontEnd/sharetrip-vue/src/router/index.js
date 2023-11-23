import { createRouter, createWebHistory } from "vue-router";
import { useUserStore } from "@/stores/user";
import TheMainView from "@/views/TheMainView.vue";
import TheAttractionView from "@/views/TheAttractionView.vue";
import TheShareView from "@/views/TheShareView.vue";
import TheMyPageView from "@/views/TheMyPageView.vue";
import TheLoginView from "@/views/TheLoginView.vue";
import TheJoinView from "@/views/TheJoinView.vue";
import ThePathView from "@/views/ThePathView.vue";
import AttractionDetail from "@/components/attraction/AttractionDetail.vue";

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
			path: "/share",
			name: "share",
			component: TheShareView,
		},
		{
			path: "/path",
			name: "path",
			component: ThePathView,
			beforeEnter: () => {
				const store = useUserStore();
				if (store.userId == null) return { name: "login" };
			},
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
		{
			path: "/attrDet/:idx",
			name: "attrDet",
			component: AttractionDetail,
		},
	],
});

export default router;
