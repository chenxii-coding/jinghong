<template>
  <div class="card">
    <el-image class="image" :src="imagePrefix + image + imageSuffix" fit="cover"/>
    <div style="padding: 0 8px 5px 8px">
      <p>{{ goodsName }}</p>
      <p>{{ brand }} - {{ categoryName }}</p>
      <p>
        <el-tag v-for="tag in tagsList" :key="tag" style="margin-right: 5px" size="small">
          {{ tag }}
        </el-tag>
      </p>
      <p>¥ {{ formatNum(price) }}</p>
      <div style="text-align: left">
        <el-button-group>
          <el-button type="parmary" size="small" @click="share">详情</el-button>
          <el-button type="parmary" size="small" @click="share">分享</el-button>
          <el-button type="parmary" size="small" @click="favorite">收藏</el-button>
        </el-button-group>
        <el-button-group style="margin-top: 5px">
          <el-button type="parmary" size="small" @click="addCarts">加入购物车</el-button>
        </el-button-group>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "GoodsItem",
  props: ['goodsName', 'image', 'categoryName', 'brand', 'price', 'tagsList'],
  data() {
    return {
      imagePrefix: '/images/',
      imageSuffix: '.jpg'
    }
  },
  methods: {
    favorite() {
      this.$message.success('收藏成功')
    },
    share() {
      this.$message.success('分享成功')
    },
    addCarts() {
      this.$message.success('已添加到购物车')
    },
    formatNum(num) {
      var value = Math.round(parseFloat(num) * 100) / 100
      var arrayNum = value.toString().split(".")
      if (arrayNum.length === 1) {
        return value.toString() + '.00'
      }
      if (arrayNum.length > 1) {
        if (arrayNum[1].length < 2) {
          return value.toString() + '0'
        }
        return value
      }
    }
  }
}
</script>

<style scoped>
.card {
  width: 230px;
  height: auto;
  margin: 0 20px 20px 0;
  border-radius: 10px;
}

.image {
  width: 100%;
  height: 230px;
  border-radius: 5px;
}
</style>
