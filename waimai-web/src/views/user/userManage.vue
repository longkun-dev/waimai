<template>
  <div style="padding: 20px">
    <el-form :inline="true" :model="condition" class="demo-form-inline" style="text-align: right">
      <el-form-item label="姓名">
        <el-input v-model="condition.username" clearable placeholder="姓名支持模糊搜索"></el-input>
      </el-form-item>
      <el-form-item label="角色">
        <el-select v-model="condition.roleName" clearable multiple placeholder="根绝角色筛选">
          <el-option label="查看" value="ROLE_VIEW"></el-option>
          <el-option label="编辑" value="ROLE_EDIT"></el-option>
          <el-option label="删除" value="ROLE_DEL"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="filterUser" size="medium">查询</el-button>
      </el-form-item>
    </el-form>
    <el-table :data="displayUserList" style="width: 100%">
      <el-table-column prop="id" label="uid"></el-table-column>
      <el-table-column prop="username" label="姓名"></el-table-column>
      <el-table-column prop="password" label="登录密码"></el-table-column>
      <el-table-column prop="roleName" label="权限"></el-table-column>
      <el-table-column prop="createdBy" label="创建人"></el-table-column>
      <el-table-column prop="createdTime" label="创建时间"></el-table-column>
      <el-table-column fixed="right" label="操作">
        <template slot-scope="scope">
          <el-button type="primary" size="small">编辑</el-button>
          <el-button type="danger" size="small">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <div style="margin-top: 15px; text-align: center">
      <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
                     :current-page="page.currentPage" :total="page.total" :page-size="page.pageSize"
                     :page-sizes="[15, 30, 50, 100]" layout="prev, pager, next, sizes, total">
      </el-pagination>
    </div>
  </div>
</template>
<script>

import { findByPage } from '@/api/user'

export default {
  data() {
    return {
      userList: [],
      displayUserList: [],
      condition: {
        username: '',
        roleName: ''
      },
      page: {
        currentPage: 1,
        total: 200,
        pageSize: 20
      }
    }
  },
  created() {
    this.queryUser()
  },
  methods: {
    queryUser() {
      this.userList = []
      this.displayUserList = []
      let params = {
        'pageNum': 1,
        'pageSize': 5
      }
      findByPage(params).then((res) => {
        this.userList = res.data
        this.displayUserList = res.data
      })
    },
    filterUser() {

    },
    handleSizeChange(val) {
    },
    handleCurrentChange(val) {
    }
  }
}
</script>
