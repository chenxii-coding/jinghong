<template>
  <div class="header">
    <el-link href="/">首页</el-link>
    <el-link href="/cart">购物车</el-link>
    <el-link>收藏</el-link>
    <el-link>订单</el-link>
    <el-link @click="toLogin" v-if="user.userInfo.token">退出</el-link>
    <el-link href="login" v-else>登陆</el-link>
  </div>
</template>

<script>
import { useUser } from '@/store/user'

export default {
  name: "Header",
  data() {
    return {
      user: {}
    }
  },
  created() {
    this.user = useUser()
    console.info('userInfo: ', this.user.userInfo)
  },
  methods: {
    toLogin() {
      this.user.reset()
      console.info('userInfo: ', this.user.userInfo)
      this.$router.push({
        path: '/login',
        query: {}
      })
    }
  }
}
</script>

<style scoped>
.header {
  display: flex;
  flex-direction: row;
  justify-content: flex-start;
  align-items: center;
  height: 25px;
  margin: 5px 0 0 0;
  padding: 0 10px 10px 0;
}

.el-link {
  margin-right: 20px;
}
</style>
