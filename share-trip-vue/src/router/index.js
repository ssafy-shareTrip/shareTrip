import { createRouter, createWebHistory } from 'vue-router'
import TheMainView from "@/views/TheMainView.vue"
import TheAttractionView from "@/views/TheAttractionView.vue"
import TheBoardView from "@/views/TheBoardView.vue"
import TheMyPageView from "@/views/TheMyPageView.vue";
import TheLoginView from "@/views/TheLoginView.vue";
import TheJoinView from "@/views/TheJoinView.vue";
import ThePathView from "@/views/ThePathView.vue";
import AttractionDetail from "@/components/attraction/AttractionDetail.vue"
import RouteBoardWrite from '@/components/board/RouteBoardWrite.vue'
import InfoModify from "@/components/user/InfoModify.vue"

//api받으면 path 수정해야함
const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [

    //로고 누르면 메인페이지로 이동
    {
      path: '/main',
      name: 'main',
      component: TheMainView
    },

    {
      path: '/attraction',
      name: 'attraction',
      component: TheAttractionView
    },

    {
      path: '/board',
      name: 'board',
      component: TheBoardView
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
			// beforeEnter: () => {
			// 	const store = useUserStore();
			// 	if (store.userId == null) return { name: "login" };
			// },
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
			path: "/infoMod",
			name: "infoMod",
			component: InfoModify,
		},
    {
      path: '/kakao',
      name: 'kakao',
      component: TheAttractionView
    },

    {
      path: '/attrDet/:idx/:isLike/:isBookmark',
      name: 'attrDet',
      component: AttractionDetail,
    },

    {
      path: '/routeReg',
      name: 'routeReg',
      component: RouteBoardWrite,
    },

  ]
})

export default router
