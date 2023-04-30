import axios from 'axios'
import Cookies from 'js-cookie'
import Message from 'element-plus'

axios.defaults.headers['Content-Type'] = 'application/json;charset=utf-8'

const service = axios.create({
  baseURL: '',
  timeout: 5 * 1000
})

service.interceptors.request.use(
  config => {
    const isToken = (config.headers || {}).isToken = false
    config.headers['token'] = '11'
    var jinghongCookie = Cookies.get('jinghong-token')
    if (jinghongCookie) {
      config.headers['JINGHONG-COOKIE'] = jinghongCookie
    }
  },
  error => {
    console.error(error)
    Promise.reject(error)
  }
)

service.interceptors.response.use(
  response => {
    const code = response.data.code || 200
    const message = response.data.message || '默认消息'
    if (code === 401) {
      Message.error('没有权限')
      return Promise.reject(new Error(message))
    } else {
      return response.data
    }
  },
  error => {
    Message.error(error)
    return Promise.reject(error)
  }
)

export default service
