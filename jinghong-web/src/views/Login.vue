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
import { user } from '@/store/user'

export default {
  name: "Login",
  data() {
    return {
      form: {
        uid: '',
        password: '',
        rememberMe: false
      },
      user: {}
    }
  },
  created() {
    this.user = user()
  },
  methods: {
    toRegisterView() {
      this.$router.push({
        path: 'register',
        query: {}
      })
    },
    login() {
      console.log('form: ', this.form)
      this.user.set(this.form)
    }
  }
}
</script>

<style scoped>

</style>