<template>
  <el-breadcrumb separator="/" style="margin-bottom: 20px">
    <el-breadcrumb-item :to="{ path: '/goods-home' }">商品管理</el-breadcrumb-item>
    <el-breadcrumb-item><a href="/goods">商品管理</a></el-breadcrumb-item>
  </el-breadcrumb>
  <el-form :inline="true" v-model="queryForm" class="demo-form-inline" style="float: right">
    <el-form-item label="">
      <el-select v-model="queryForm.searchField" style="width: 120px">
        <el-option value="goodsNo" label="商品编号"></el-option>
        <el-option value="goodsName" label="商品名称"></el-option>
      </el-select>
      <el-input v-model="queryForm.searchContent" style="width: 200px"></el-input>
      <el-button type="primary" icon="search" size="default" @click="queryData">查询</el-button>
    </el-form-item>
    <el-form-item>
      <el-button-group>
        <el-button icon="Plus" type="primary" @click="add">新增</el-button>
      </el-button-group>
    </el-form-item>
  </el-form>
  <el-table :data="goodsList" style="width: 100%">
    <el-table-column prop="goodsNo" label="商品编号" width="180"/>
    <el-table-column prop="goodsName" label="商品名称" width="180"/>
    <el-table-column prop="categoryName" label="商品分类"/>
    <el-table-column prop="brand" label="品牌"/>
    <el-table-column prop="price" label="价格"/>
    <el-table-column label="商品标签" width="220">
      <template v-slot:default="scope">
        <el-tag v-for="item in scope.row.tagsList" :key="item" style="margin-left: 5px">
          {{ item }}
        </el-tag>
      </template>
    </el-table-column>
    <el-table-column prop="isOnSale" label="是否上架" width="100">
      <template v-slot:default="scope">
        <span disabled>{{ scope.row.isOnSale ? '是' : '否' }}</span>
      </template>
    </el-table-column>
    <el-table-column label="操作" width="220">
      <template v-slot:default="scope">
        <el-button icon="Edit" type="primary" @click="edit(scope.row)">编辑</el-button>
        <el-popconfirm title="确定删除吗？" @confirm="del(scope.row.goodsNo)" :key="scope.row.id">
          <template #reference>
            <el-button icon="Delete" type="danger">删除</el-button>
          </template>
        </el-popconfirm>
      </template>
    </el-table-column>
  </el-table>

  <el-dialog v-model="showAddDialog" title="商品详情">
    <el-form v-model="goodsForm" label-width="80">
      <el-form-item label="商品编号">
        <el-input v-model="goodsForm.goodsNo" disabled/>
      </el-form-item>
      <el-form-item label="商品名称">
        <el-input v-model="goodsForm.goodsName"/>
      </el-form-item>
      <el-form-item label="商品分类">
        <el-select v-model="goodsForm.categoryNo">
          <el-option v-for="item in categoryList" :key="item.categoryNo" :value="item.categoryNo"
                     :label="item.categoryName">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="品牌">
        <el-input v-model="goodsForm.brand"/>
      </el-form-item>
      <el-form-item label="售价">
        <el-input v-model="goodsForm.price"/>
      </el-form-item>
      <el-form-item label="图片">
        <el-input v-model="goodsForm.image"/>
      </el-form-item>
      <el-form-item label="商品标签">
        <el-tag v-for="tag in goodsForm.tagsList" :key="tag" closable :disable-transitions="false"
                @close="handleClose(tag)" style="margin-left: 5px">
          {{ tag }}
        </el-tag>
        <el-input v-if="inputVisible" ref="InputRef" v-model="inputValue" class="ml-1 w-20"
                  @keyup.enter="handleInputConfirm" @blur="handleInputConfirm"/>
        <el-button v-else class="button-new-tag ml-1" size="small" @click="addTag" style="margin-left: 5px">
          + 新增标签
        </el-button>
      </el-form-item>
      <el-form-item label="是否上架">
        <el-switch v-model="goodsForm.isOnSale"/>
      </el-form-item>
      <el-form-item label="其他属性">
        <el-button type="primary" icon="Plus" size="small" @click="addGoodsDetail">新增</el-button>
        <el-table :data="goodsForm.goodsDetailList">
          <el-table-column prop="descriptionItem" label="名称">
            <template v-slot:default="scope">
              <el-input v-model="scope.row.descriptionItem"></el-input>
            </template>
          </el-table-column>
          <el-table-column prop="description" label="描述">
            <template v-slot:default="scope">
              <el-input v-model="scope.row.description"></el-input>
            </template>
          </el-table-column>
          <el-table-column prop="description" label="操作">
            <template v-slot:default="scope">
              <el-button type="danger" icon="Delete" size="small" @click="delGoodsDetail(scope.$index)">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-form-item>
      <el-form-item label="">
        <el-button type="primary" icon="Check" @click="confirm">确定</el-button>
        <el-button icon="SwitchButton" @click="cancel">取消</el-button>
      </el-form-item>
    </el-form>
  </el-dialog>
</template>

<script>
export default {
  name: "Goods",
  data() {
    return {
      goodsForm: {
        goodsNo: '',
        goodsName: '',
        categoryNo: '',
        categoryName: '',
        brand: '',
        price: 0.00,
        image: '',
        tagsList: [],
        isOnSale: false,
        goodsDetailList: []
      },
      goodsList: [],
      queryForm: {
        searchField: 'goodsNo',
        searchContent: ''
      },
      showAddDialog: false,
      categoryList: [],
      inputVisible: false,
      inputValue: '',
      actionType: ''
    }
  },
  created() {
    this.queryCategory()
    this.queryData()
  },
  methods: {
    add() {
      this.showAddDialog = true
      this.actionType = 'add'
    },
    queryData() {
      this.$request.get('/api/goods').then((res) => {
        this.goodsList = res.data
      })
    },
    queryCategory() {
      this.$request.get('/api/category').then((res) => {
        this.categoryList = res.data.filter(e => e.level === 2)
      })
    },
    handleClose(tag) {
      this.goodsForm.tagsList.splice(this.goodsForm.tagsList.indexOf(tag), 1)
    },
    addTag() {
      this.inputVisible = true
    },
    handleInputConfirm() {
      this.goodsForm.tagsList.push(this.inputValue)
      this.inputVisible = false
    },
    confirm() {
      if (this.actionType === 'add') {
        this.$request.put('/api/goods', this.goodsForm).then((res) => {
          this.$message.success('新增成功')
          this.showAddDialog = false
        })
      } else {
        this.$request.post('/api/goods', this.goodsForm).then((res) => {
          this.$message.success('更新成功')
          this.showAddDialog = false
        })
      }
    },
    cancel() {
      this.showAddDialog = false
      this.goodsForm = {
        goodsNo: '',
        goodsName: '',
        categoryNo: '',
        categoryName: '',
        brandName: '',
        price: 0.00,
        image: '',
        tagsList: [],
        isOnSale: false,
        goodsDetailList: []
      }
    },
    edit(row) {
      Object.assign(this.goodsForm, row)
      this.showAddDialog = true
      this.actionType = 'edit'
    },
    del(goodsNo) {
      this.$message.success('删除成功')
    },
    addGoodsDetail() {
      this.goodsForm.goodsDetailList.push({
        descriptionItem: '',
        description: '',
      })
    },
    delGoodsDetail(index) {
      this.goodsForm.goodsDetailList.splice(index, 1)
    }
  }
}
</script>

<style scoped>

</style>
