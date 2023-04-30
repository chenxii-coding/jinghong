<template>
  <el-breadcrumb separator="/" style="margin-bottom: 20px">
    <el-breadcrumb-item :to="{ path: '/goods-home' }">商品管理</el-breadcrumb-item>
    <el-breadcrumb-item><a href="/goods">商品管理</a></el-breadcrumb-item>
  </el-breadcrumb>
  <el-form :inline="true" v-model="queryForm" class="demo-form-inline" style="float: right">
    <el-form-item label="">
      <el-select v-model="queryForm.searchType" style="width: 120px">
        <el-option value="goodsNo" label="商品编号"></el-option>
        <el-option value="goodsName" label="商品名称"></el-option>
      </el-select>
      <el-input v-model="queryForm.searchContent" style="width: 200px"></el-input>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" icon="search" size="default" @click="query">查询</el-button>
    </el-form-item>
  </el-form>
  <el-table :data="goodsList" style="width: 100%">
    <el-table-column prop="goodsNo" label="商品编号" width="180"/>
    <el-table-column prop="goodsName" label="商品名称" width="180"/>
    <el-table-column prop="categoryName" label="商品分类"/>
    <el-table-column prop="brandName" label="品牌"/>
    <el-table-column prop="price" label="价格"/>
    <el-table-column label="商品标签">
      <template v-slot:default="scope">
        <el-tag v-for="item in scope.row.tags" :key="item">
          {{ item }}
        </el-tag>
      </template>
    </el-table-column>
    <el-table-column prop="isOnSale" label="在售"></el-table-column>
  </el-table>
</template>

<script>
export default {
  name: "Goods",
  data() {
    return {
      goodsList: [
        {
          goodsNo: 'G0001',
          goodsName: '海天酱油',
          categoryName: '调味品',
          brandName: '海天',
          price: 9.11,
          image: '',
          tags: ['调味品', '酱油'],
          isOnSale: '在售'
        }, {
          goodsNo: 'G0001',
          goodsName: '海天酱油',
          categoryName: '调味品',
          brandName: '海天',
          price: 9.11,
          image: '',
          tags: ['调味品', '酱油'],
          isOnSale: '在售'
        }, {
          goodsNo: 'G0001',
          goodsName: '海天酱油',
          categoryName: '调味品',
          brandName: '海天',
          price: 9.11,
          image: '',
          tags: ['调味品', '酱油'],
          isOnSale: '在售'
        }, {
          goodsNo: 'G0001',
          goodsName: '海天酱油',
          categoryName: '调味品',
          brandName: '海天',
          price: 9.11,
          image: '',
          tags: ['调味品', '酱油'],
          isOnSale: '在售'
        }, {
          goodsNo: 'G0001',
          goodsName: '海天酱油',
          categoryName: '调味品',
          brandName: '海天',
          price: 9.11,
          image: '',
          tags: ['调味品', '酱油'],
          isOnSale: '在售'
        }],
      queryForm: {}
    }
  },
  methods: {
    query() {

    }
  }
}
</script>

<style scoped>

</style>
