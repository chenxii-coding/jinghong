<template>
  <div>
    <h3>我的购物车</h3>
    <el-table :data="cartList"
              @selection-change="handleSelectionChange"
              :header-cell-style="{'text-align': 'center'}"
              :cell-style="{'text-align': 'center'}">
      <el-table-column type="selection"></el-table-column>
      <el-table-column prop="image" label="">
        <template v-slot:="scope">
          <el-image class="image" :src="imagePrefix + scope.row.image + imageSuffix" fit="cover"/>
        </template>
      </el-table-column>
      <el-table-column prop="goodsNo" label="商品编号"></el-table-column>
      <el-table-column prop="goodsName" label="商品名称"></el-table-column>
      <el-table-column prop="price" label="商品单价"></el-table-column>
      <el-table-column prop="count" label="商品数量">
        <template v-slot:="scope">
          <el-input-number v-model="scope.row.count" :min="1" :max="10"/>
        </template>
      </el-table-column>
      <el-table-column prop="count" label="小计">
        <template v-slot:="scope">
          <span>{{ scope.row.count * scope.row.price }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作">
        <template v-slot:="scope">
          <el-button type="danger" size="medium" @click="removeGoods(scope.row.goodsNo)">移除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <div style="margin: 30px; text-align: right">
      <el-button type="primary" size="medium" @click="checkout">结算</el-button>
    </div>
  </div>
</template>

<script>
export default {
  name: "Cart",
  data() {
    return {
      cartList: [],
      imagePrefix: '/images/',
      imageSuffix: '.jpg',
      selectedGoodsList: [],
      uid: ''
    }
  },
  created() {
    this.uid = localStorage.getItem('uid')
    this.queryCart()
  },
  methods: {
    queryCart() {
      this.$request.get('/api/goods/cart/' + this.uid).then((res) => {
        this.cartList = res.data
      })
    },
    removeGoods(goodsNo) {
      this.$request.delete('/api/goods/cart/' + this.uid + '/' + goodsNo).then((res) => {
        this.$message.success('移除成功')
        this.queryCart()
      })
    },
    handleSelectionChange(val) {
      this.selectedGoodsList = val
    },
    checkout() {
      if (!this.selectedGoodsList || this.selectedGoodsList.length === 0) {
        this.$message.warning('请选择要结算的商品')
        return false
      }

      let orderDetailList = []
      let list = this.selectedGoodsList
      for (let i = 0; i < list.length; i++) {
        let item = list[i]
        let goodsItem = {
          goodsNo: item.goodsNo,
          count: item.count,
          price: item.price
        }
        orderDetailList.push(goodsItem)
      }

      let param = {
        uid: this.uid,
        orderDetailList: orderDetailList
      }
      this.$request.post('/api/order/create', param).then((res) => {
        this.$message.success('订单创建成功')
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