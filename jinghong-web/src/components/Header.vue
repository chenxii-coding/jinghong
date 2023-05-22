<template>
  <div class="header">
    <el-link href="/">首页</el-link>
    <el-link href="/cart">购物车</el-link>
    <el-link href="/favorite">收藏</el-link>
    <el-link>订单</el-link>
    <el-link @click="toLogin" v-if="uid">退出</el-link>
    <el-link href="login" v-else>登陆</el-link>
  </div>
</template>

<script>
export default {
  name: "Header",
  data() {
    return {
      uid: {}
    }
  },
  created() {
    this.uid = localStorage.getItem('uid')
  },
  methods: {
    toLogin() {
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
