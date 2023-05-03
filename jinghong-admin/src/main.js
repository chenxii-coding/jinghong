import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import ElementPlus from 'element-plus'
import ElMessage from 'element-plus'
import 'element-plus/dist/index.css'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
import request from '@/util/request.js'

const app = createApp(App)
app.use(router)
app.use(ElementPlus)
app.use(ElMessage)

for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
  app.component(key, component)
}

app.mount('#app')

app.config.globalProperties.$request = request
