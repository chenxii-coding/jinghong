<template>
  <div class="common-layout">
    <el-container class="main">
      <el-header class="header">
        <Header></Header>
      </el-header>
      <el-divider style="margin: 5px 0"></el-divider>
      <el-container>
        <el-aside width="200px">
          <Aside></Aside>
        </el-aside>
        <el-main style="margin: 20px 0">
          <router-view/>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
import Header from '@/components/Header'
import Aside from '@/components/Aside'

export default {
  name: 'Index',
  components: {
    Header,
    Aside
  }
}
</script>

<style>
.el-main {
  padding: 0 20px
}

.main {

}

.header {
  padding: 0;
  margin: 0;
  height: 50px;
}
</style>