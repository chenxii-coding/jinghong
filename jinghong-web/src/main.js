import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import ElementPlus from 'element-plus'
import ElMessage from 'element-plus'
import request from '@/util/request'
import 'element-plus/dist/index.css'

const app = createApp(App)

app.use(router)
app.use(ElementPlus)
app.use(ElMessage)

app.config.globalProperties.$request = request

app.mount('#app')
