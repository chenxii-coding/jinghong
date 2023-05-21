import axios from 'axios'
import { ElMessage } from 'element-plus'

axios.defaults.timeout = 50000
axios.defaults.headers.common['Content-Type'] = 'application/json;UTF-8'
axios.defaults.headers.common['Access-Control-Allow-Origin-Type'] = "*"


axios.interceptors.request.use(
  config => {
    config.headers['uid'] = localStorage.getItem('uid')
    config.headers['token'] = localStorage.getItem('token')
    return config
  }, error => {
    ElMessage.success('请求失败')
    return Promise.reject(error.message)
  })

axios.interceptors.response.use(
  response => {
    if (response.status === 200) {
      if (response.data.code === 0) {
        return Promise.resolve(response.data)
      } else {
        ElMessage.error(response.data.message)
        return Promise.resolve(response.data)
      }
    } else {
      ElMessage.error('系统错误')
      return Promise.reject(response)
    }
  }
)

export default axios
