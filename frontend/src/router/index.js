import { createRouter, createWebHistory } from 'vue-router'
import Home from '@/views/Home.vue'
import Requestors from '@/views/Requestors.vue'
// Импортируйте другие компоненты представлений

const routes = [
  { path: '/', name: 'Home', component: Home },
  { path: '/requestors', name: 'Requestors', component: Requestors },
  // Добавьте другие маршруты
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router