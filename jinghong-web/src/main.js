import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import ElementPlus from 'element-plus'
import ElMessage from 'element-plus'
import request from '@/util/request'
import 'element-plus/dist/index.css'
import { createPinia } from 'pinia'

const app = createApp(App)
const pinia = createPinia()

app.use(router)
app.use(ElementPlus)
app.use(ElMessage)
app.use(pinia)

app.config.globalProperties.$request = request

app.mount('#app')
