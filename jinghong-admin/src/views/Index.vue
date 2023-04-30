<template>
  <div class="common-layout">
    <el-container class="main">
      <el-header class="header">
        <Header></Header>
      </el-header>
      <el-divider style="margin: 5px 0"></el-divider>
      <el-container>
        <el-aside :style="asideStyle">
          <Aside :is-collapse="isCollapse"></Aside>
          <div style="padding: 15px 15px">
            <el-switch v-model="isCollapse" size="small" @change="changeAside"/>
          </div>
        </el-aside>
        <el-main>
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
  },
  data() {
    return {
      isCollapse: false,
      asideStyle: {
        width: '200px'
      }
    }
  },
  computed: {

  },
  methods: {
    changeAside() {
      console.log('this.isCollapse: ', this.isCollapse)
      this.isCollapse ? this.asideStyle.width = 'auto' : this.asideStyle.width = '200px'
    }
  }
}
</script>

<style>
.el-main {
  margin: 20px 0;
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