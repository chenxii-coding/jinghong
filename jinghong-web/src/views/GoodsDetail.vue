<template>
  <div>
    <div class="goods-detail">
      <div>
        <el-image class="image" :src="imagePrefix + goodsDetail.image + imageSuffix" fit="cover"/>
      </div>
      <div style="margin: 0 25px">
        <p>商品名：{{ goodsDetail.goodsName }}</p>
        <p>分类：{{ goodsDetail.categoryName }}</p>
        <p>品牌：{{ goodsDetail.brand }}</p>
        <p>
          <el-tag v-for="item in goodsDetail.tagsList" :key="item" style="margin-right: 8px">
            {{ item }}
          </el-tag>
        </p>
        <p>¥ {{ goodsDetail.price }}</p>
      </div>
    </div>
    <div style="margin: 25px 0">
      <el-table :data="goodsDetail.goodsDetailList">
        <el-table-column prop="descriptionItem" label="详情项"></el-table-column>
        <el-table-column prop="description" label="详情描述"></el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
export default {
  name: "GoodsDetail",
  data() {
    return {
      imagePrefix: '/images/',
      imageSuffix: '.jpg',
      goodsDetail: {},
      goodsNo: ''
    }
  },
  created() {
    let goodsNo = this.$route.query.goodsNo
    this.queryGoodsDetail(goodsNo)
  },
  methods: {
    queryGoodsDetail(goodsNo) {
      this.$request.get('/api/goods/' + goodsNo).then((res) => {
        this.goodsDetail = res.data
      })
    }
  }
}
</script>

<style scoped>
.goods-detail {
  display: flex;
}
</style>