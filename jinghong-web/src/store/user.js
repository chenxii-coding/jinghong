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
    },
    reset() {
      localStorage.setItem('userInfo', null)
      Object.assign(this.user, {})
    }
  }
})

export default useUser
