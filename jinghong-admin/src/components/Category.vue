<template>
  <el-breadcrumb separator="/" style="margin-bottom: 20px">
    <el-breadcrumb-item :to="{ path: '/goods-home' }">商品管理</el-breadcrumb-item>
    <el-breadcrumb-item><a href="/category">商品分类</a></el-breadcrumb-item>
  </el-breadcrumb>
  <el-form :inline="true" v-model="queryForm" class="demo-form-inline" style="float: right">
    <el-form-item label="">
      <el-select v-model="queryForm.searchField" style="width: 130px">
        <el-option value="categoryName" label="分类名称"></el-option>
        <el-option value="parentCategoryName" label="父级分类名称"></el-option>
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
  <el-table :data="categoryList">
    <el-table-column prop="id" label="id" v-if="false"></el-table-column>
    <el-table-column prop="categoryNo" label="分类编号"></el-table-column>
    <el-table-column prop="categoryName" label="分类名称"></el-table-column>
    <el-table-column prop="parentCategory" label="父级分类编号"></el-table-column>
    <el-table-column prop="parentCategoryName" label="父级分类名称"></el-table-column>
    <el-table-column prop="level" label="分类层级" width="80"></el-table-column>
    <el-table-column prop="createdBy" label="创建人"></el-table-column>
    <el-table-column prop="createdTime" label="创建时间" width="170"></el-table-column>
    <el-table-column label="操作" width="220">
      <template v-slot:default="scope">
        <el-button icon="Edit" type="primary" @click="edit(scope.row)">编辑</el-button>
        <el-popconfirm title="确定删除吗？" @confirm="del(scope.row.categoryNo)" :key="scope.row.id">
          <template #reference>
            <el-button icon="Delete" type="danger">删除</el-button>
          </template>
        </el-popconfirm>
      </template>
    </el-table-column>
  </el-table>

  <el-dialog v-model="showAddDialog" title="分类详情">
    <el-form :model="categoryForm">
      <el-form-item label="分类编号">
        <el-input v-model="categoryForm.categoryNo" disabled/>
      </el-form-item>
      <el-form-item label="分类名称">
        <el-input v-model="categoryForm.categoryName" maxlength="100" show-word-limit/>
      </el-form-item>
      <el-form-item label="父级分类">
        <el-select v-model="categoryForm.parentCategory" clearable @change="changeParentCategory">
          <el-option v-for="item in parentCategoryList" :key="item.categoryNo" :value="item.categoryNo"
                     :label="item.categoryName"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="分类层级">
        <el-input v-model="categoryForm.level" disabled></el-input>
      </el-form-item>
      <el-form-item label="">
        <el-button type="primary" icon="Check" @click="confirm">确定</el-button>
        <el-button type="default" icon="SwitchButton" @click="cancel">取消</el-button>
      </el-form-item>
    </el-form>
  </el-dialog>
</template>
<script>
export default {
  name: 'Category',
  data() {
    return {
      queryForm: {
        searchField: 'categoryName',
        searchContent: ''
      },
      categoryList: [],
      parentCategoryList: [],
      showAddDialog: false,
      categoryForm: {
        categoryNo: '',
        categoryName: '',
        parentCategory: '',
        parentCategoryName: '',
        level: 1
      },
      actionType: ''
    }
  },
  created() {
    this.queryData()
  },
  methods: {
    queryData() {
      this.$request.get('/api/category').then((res) => {
        this.categoryList = res.data
        this.parentCategoryList = this.categoryList.filter(e => e.level === 1)
      })
    },
    cancel() {
      Object.assign(this.categoryForm, {
        categoryNo: '',
        categoryName: '',
        parentCategory: '',
        parentCategoryName: '',
        level: 1
      })
      this.showAddDialog = false
    },
    add() {
      this.showAddDialog = true
      this.actionType = 'add'
    },
    changeParentCategory(val) {
      if (!this.categoryForm.parentCategory) {
        this.categoryForm.level = 1
      } else {
        this.categoryForm.level = 2
      }
    },
    confirm() {
      if (this.actionType === 'add') {
        this.$request.put('/api/category', this.categoryForm).then((res) => {
          this.$message.success('新增成功')
          this.showAddDialog = false
          this.queryData()
        })
      } else {
        this.$request.post('/api/category', this.categoryForm).then((res) => {
          this.$message.success('修改成功')
          this.showAddDialog = false
          this.queryData()
        })
      }
    },
    edit(row) {
      Object.assign(this.categoryForm, row)
      this.showAddDialog = true
      this.actionType = 'edit'
    },
    del(categoryNo) {
      this.$request.delete('/api/category/' + categoryNo).then((res) => {
        this.$message.success('删除成功')
        this.queryData()
      })
    }
  }
}
</script>