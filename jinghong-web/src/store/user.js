import { defineStore } from 'pinia'

export const user = defineStore({
  id: 'user',
  state: () => ({
    uid: '',
    username: '',
    sex: '',
    birthday: '',
    lastLoginTime: '',
    status: ''
  }),
  actions: {
    set(userInfo) {
      this.user = userInfo
      console.info('this.user: ', this.user)
    }
  }
})
