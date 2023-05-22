import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: '/',
      component: () => import('@/views/Index.vue')
    },
    {
      path: '/cart',
      name: '/cart',
      component: () => import('@/views/Cart.vue')
    },
    {
      path: '/login',
      name: 'login',
      component: () => import('@/views/Login.vue')
    },
    {
      path: '/register',
      name: 'register',
      component: () => import('@/views/Register.vue')
    },
    {
      path: '/goodsDetail',
      name: 'goodsDetail',
      component: () => import('@/views/GoodsDetail.vue')
    },
    {
      path: '/favorite',
      name: 'favorite',
      component: () => import('@/views/Favorite.vue')
    },
  ]
})

export default router
