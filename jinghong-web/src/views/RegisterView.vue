<template>
  <div>
    <el-form v-model="form" label-position="right" label-width="100px">
      <el-form-item label="用户名">
        <el-input v-model="form.username"/>
      </el-form-item>
      <el-form-item label="密码">
        <el-input v-model="form.password"/>
      </el-form-item>
      <el-form-item label="确认密码">
        <el-input v-model="form.confirmPassword"/>
      </el-form-item>
      <el-form-item label="性别">
        <el-radio-group v-model="form.sex">
          <el-radio label="保密">保密</el-radio>
          <el-radio label="男">男</el-radio>
          <el-radio label="女">女</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="生日">
        <el-date-picker v-model="form.birthday" value-format="YYYY-MM-DD"/>
      </el-form-item>
      <el-form-item>
        <span>已有账号？去 <el-link type="info" :underline="true" @click="toLogin">登陆</el-link></span>
      </el-form-item>
      <el-form-item label="">
        <el-button type="primary" @click="register">注册</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  name: "RegisterView",
  data() {
    return {
      form: {
        username: '',
        password: '',
        confirmPassword: '',
        sex: '保密',
        birthday: ''
      }
    }
  },
  methods: {
    toLogin() {
      this.$router.push({
        path: '/login',
        query: {}
      })
    },
    register() {
      console.log('form: ', this.form)
    }
  }
}
</script>

<style scoped>

</style>