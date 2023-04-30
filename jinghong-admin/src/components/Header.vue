<template>
  <div class="header">
    <h2>惊鸿后台管理系统</h2>
    <div class="login-info">
      <div>
        <span>2023年4月30日</span>
      </div>
      <div style="margin-left: 15px">
        <el-avatar size="small" :src="url"/>
      </div>
      <div style="margin-left: 15px">
        <el-link>退出登陆</el-link>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Header",
  data() {
    return {
      url: 'https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg'
    }
  }
}
</script>

<style scoped>
.header {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-content: center;
  align-items: center;
  padding: 0 20px;
}

.login-info {
  display: flex;
  flex-direction: row;
  justify-content: flex-end;
  align-content: center;
  align-items: center;
}
</style>