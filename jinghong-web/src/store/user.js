import { defineStore } from 'pinia'

export const useUser = defineStore({
  id: 'user',
  state() {
    return {
      userInfo: {
        uid: '',
        username: '',
        sex: '',
        birthday: '',
        lastLoginTime: '',
        status: '',
        token: ''
      }
    }
  },
  actions: {
    setUserInfo(userInfo) {
      this.userInfo = userInfo
      localStorage.setItem('uid', userInfo.uid)
      localStorage.setItem('username', userInfo.username)
      localStorage.setItem('token', userInfo.token)
    },
    reset() {
      this.userInfo = {}
      localStorage.removeItem('userInfo')
      localStorage.removeItem('uid')
      localStorage.removeItem('username')
      localStorage.removeItem('token')
    }
  }
})

export default useUser
