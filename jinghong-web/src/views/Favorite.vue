<template>
  <div>
    <h3>我的收藏</h3>
    <el-table :data="favoriteList"
              :header-cell-style="{'text-align': 'center'}"
              :cell-style="{'text-align': 'center'}">
      <el-table-column prop="image" label="">
        <template v-slot:="scope">
          <el-image class="image" :src="imagePrefix + scope.row.image + imageSuffix" fit="cover"/>
        </template>
      </el-table-column>
      <el-table-column prop="goodsNo" label="商品编号"></el-table-column>
      <el-table-column prop="goodsName" label="商品名称"></el-table-column>
      <el-table-column prop="price" label="商品单价"></el-table-column>
      <el-table-column label="操作" width="280">
        <template v-slot:="scope">
          <el-button type="danger" size="medium" @click="removeFavorite(scope.row.goodsNo)">移除</el-button>
          <el-button type="primary" size="medium" @click="addCarts(scope.row.goodsNo)">加入购物车</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
export default {
  name: "Cart",
  data() {
    return {
      favoriteList: [],
      imagePrefix: '/images/',
      imageSuffix: '.jpg',
      selectedGoodsList: [],
      uid: ''
    }
  },
  created() {
    this.uid = localStorage.getItem('uid')
    this.queryFavorite()
  },
  methods: {
    queryFavorite() {
      this.$request.get('/api/goods/favorite/' + this.uid).then((res) => {
        this.favoriteList = res.data
      })
    },
    removeFavorite(goodsNo) {
      this.$request.delete('/api/goods/removeFavorite/' + this.uid + '/' + goodsNo).then((res) => {
        this.$message.success('移除成功')
        this.queryFavorite()
      })
    },
    addCarts(goodsNo) {
      this.$request.post('/api/goods/cart/' + this.uid + '/' + goodsNo).then((res) => {
        this.$message.success('已添加到购物车')
      })
    }
  }
}
</script>

<style scoped>
.image {
  height: 80px;
  width: 80px;
  border-radius: 5px;
}
</style>