import { createRouter, createWebHistory } from 'vue-router'

const routes = [
  {
    path: '/',
    name: 'login',
    component: () => import('@/views/Login.vue')
  },
  {
    path: '/home',
    name: 'home',
    component: () => import('@/views/Index.vue'),
    children: [
      {
        path: '/goods-home',
        name: 'goods-home',
        component: () => import('@/components/Blank.vue')
      },
      {
        path: '/category',
        name: 'category',
        component: () => import('@/components/Category.vue')
      }, {
        path: '/goods',
        name: 'goods',
        component: () => import('@/components/Goods.vue')
      },
      {
        path: '/user',
        name: 'user',
        component: () => import('@/components/User.vue')
      }
    ]
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
