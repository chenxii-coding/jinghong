<template>
  <div>
    <el-menu :default-active="active" class="el-menu-vertical-demo" :router="true"
             :collapse="isCollapse" @open="handleOpen">
      <el-sub-menu index="1">
        <template #title>
          <el-icon>
            <goods/>
          </el-icon>
          <span>商品管理</span>
        </template>
        <el-menu-item index="category">分类管理</el-menu-item>
        <el-menu-item index="goods">商品管理</el-menu-item>
        <el-menu-item index="1-3">评价管理</el-menu-item>
      </el-sub-menu>
      <el-menu-item index="2">
        <el-icon>
          <tickets/>
        </el-icon>
        <template #title>订单管理</template>
      </el-menu-item>
      <el-menu-item index="3">
        <el-icon>
          <position/>
        </el-icon>
        <template #title>物流管理</template>
      </el-menu-item>
      <el-menu-item index="4">
        <el-icon>
          <edit/>
        </el-icon>
        <template #title>库存管理</template>
      </el-menu-item>
      <el-menu-item index="User">
        <el-icon>
          <user/>
        </el-icon>
        <template #title>用户管理</template>
      </el-menu-item>
    </el-menu>
  </div>
</template>

<script>
export default {
  props: ['isCollapse'],
  data() {
    return {
      active: 'category'
    }
  },
  methods: {
    handleOpen(key, path) {
      this.active = key
    }
  }
}
</script>

<style>
.el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 200px;
  min-height: 400px;
}
</style>