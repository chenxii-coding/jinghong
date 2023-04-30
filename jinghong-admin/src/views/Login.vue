<template>
  <div class="login-form">
    <el-form ref="form" :model="form" label-position="right" label-width="100px" :rules="rules">
      <el-form-item label="用户名">
        <el-input v-model="form.username"/>
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
        <span>没有账号？请联系管理员添加</span>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  name: "Login",
  data() {
    return {
      form: {
        username: '',
        password: '',
        rememberMe: false
      },
      rules: {
        username: [{ required: true, message: '请输入用户名', trigger: 'blur' }],
        password: [{ required: true, message: '请输入密码', trigger: 'blur' }]
      }
    }
  },
  methods: {
    login() {
      this.$refs['form'].validate(validate => {
        console.log('validate: ', validate)
        if (validate) {
          this.$router.push({
            path: '/home',
            query: {}
          })
        }
      })
    }
  }
}
</script>

<style scoped>
.login-form {
  width: 350px;
  margin: auto;
  margin-top: 25px;
}
</style>