import axios from 'axios'
import Cookies from 'js-cookie'
import { ElMessage } from 'element-plus'

const service = axios.create({
  baseURL: '',
  timeout: 5 * 1000
})

// service.defaults.headers['Content-Type'] = 'application/json;charset=utf-8'

service.interceptors.request.use(
  config => {
    const isToken = (config.headers || {}).isToken = false
    // config.headers['token'] = '11'
    var jinghongCookie = Cookies.get('jinghong-token')
    if (jinghongCookie) {
      // config.headers['JINGHONG-COOKIE'] = jinghongCookie
    }
    return config
  },
  error => {
    return Promise.reject(error)
  }
)

service.interceptors.response.use(
  response => {
    if (response.status === 200) {
      const code = response.data.code
      const message = response.data.message
      if (code === -1) {
        ElMessage.error({ message: message })
        return null
      } else {
        return response.data
      }
    } else {
      ElMessage.error({ message: '网络错误' })
      return Promise.reject(new Error('网络错误'))
    }
  },
  error => {
    return Promise.reject(error)
  }
)

export default service
