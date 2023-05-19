<template>
  <div v-for="type in dataList">
    <div>
      <h3>{{ type.typeName }}</h3>
    </div>
    <div class="index">
      <goods-item
          v-for="item in type.goodsList"
          :goods-no="item.goodsNo"
          :goods-name="item.goodsName"
          :image="item.image"
          :category-name="item.categoryName"
          :brand="item.brand"
          :price="item.price"
          :tagsList="item.tagsList">
      </goods-item>
    </div>
  </div>
</template>

<script>
import GoodsItem from '@/components/GoodsItem.vue'

export default {
  name: "Index",
  components: {
    GoodsItem
  },
  data() {
    return {
      dataList: []
    }
  },
  created() {
    this.queryGoods()
  },
  methods: {
    queryGoods() {
      this.$request.get('/api/goods/onSale').then((res) => {
        this.dataList = res.data
      })
    }
  }
}
</script>

<style scoped>
.index {
  margin: 0 auto;
  display: flex;
  flex-direction: row;
  justify-content: flex-start;
  align-items: flex-start;
  flex-wrap: wrap;
}
</style>
