import { createRouter, createWebHistory } from 'vue-router'
import TheMainView from "@/views/TheMainView.vue"
import TheAttractionView from "@/views/TheAttractionView.vue"
import TheBoardView from "@/views/TheBoardView.vue"
import LoginView from "@/components/user/LoginView.vue"
import JoinView from "@/components/user/JoinView.vue"
import TheMypageView from "@/views/TheMypageView.vue"

//찬혁이한테 api받으면 path 수정해야함
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

    //에러발생: 로그인 페이지 이동 시 Uncaught TypeError: Cannot read properties of undefined (reading 'backdrop')
    {
      path: '/login',
      name: 'login',
      component: LoginView
    },

    {
      path: '/join',
      name: 'join',
      component: JoinView
    },

    {
      path: '/mypage',
      name: 'mypage',
      component: TheMypageView
    },

    {
      path: '/logout',
      name: 'logout',
      component: TheMainView
    },

  ]
})

export default router
