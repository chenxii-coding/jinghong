<template>
  <div style="margin: 15px auto 0; width: 350px;">
    <el-form v-model="form" label-position="right" label-width="80">
      <el-form-item label="用户名">
        <el-input v-model="form.uid"/>
      </el-form-item>
      <el-form-item label="密码">
        <el-input v-model="form.password"/>
      </el-form-item>
      <el-form-item label="">
        <el-checkbox label="记住我" v-model="form.rememberMe"/>
      </el-form-item>
      <el-form-item label="">
        <el-button type="primary" @click="login">登陆</el-button>
      </el-form-item>
      <el-form-item>
        <span>没有账号？去 <el-link type="info" :underline="true" @click="toRegisterView">注册</el-link></span>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import { useUser } from '@/store/user'

export default {
  name: 'Login',
  data() {
    return {
      form: {
        uid: 'U0001',
        password: '12345',
        rememberMe: false
      },
      user: {}
    }
  },
  created() {
    this.user = useUser()
  },
  methods: {
    toRegisterView() {
      this.$router.push({
        path: 'register',
        query: {}
      })
    },
    login() {
      let param = {
        uid: this.form.uid,
        password: this.form.password
      }
      this.$request.get('/api/gateway/userLogin', { params: param }).then((res) => {
        let userInfo = res.data
        localStorage.setItem('uid', userInfo.uid)
        localStorage.setItem('username', userInfo.username)
        localStorage.setItem('token', userInfo.token)
        this.user.setUserInfo(userInfo)
        console.info('userInfo: ', this.user.userInfo)
        this.$message.success('登陆成功')
        this.$router.push({
          path: '/',
          query: {}
        })
      })
    }
  }
}
</script>

<style scoped>

</style>
