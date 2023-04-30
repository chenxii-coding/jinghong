<template>
  <div>
    <h1>这是用户管理页面</h1>
  </div>
</template>

<script>
export default {
  name: "User",
  data() {
    return {
      userList: []
    }
  },
  created() {
    this.queryData()
  },
  methods: {
    queryData() {
      this.$axios.post('/yiyan').then((res) => {
        console.info('res: ', res)
      })
    }
  }
}
</script>

<style scoped>

</style>
