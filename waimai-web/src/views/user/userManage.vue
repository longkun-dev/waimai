<template>
  <div style="padding: 20px">
    <el-form :inline="true" :model="condition" class="demo-form-inline" style="text-align: right">
      <el-form-item label="姓名">
        <el-input v-model="condition.username" clearable placeholder="姓名支持模糊搜索"></el-input>
      </el-form-item>
      <el-form-item label="角色">
        <el-select v-model="condition.roleCode" clearable placeholder="根绝角色筛选">
          <el-option v-for="role in roleList" :item="role" :key="role.roleCode" :value="role.roleCode"
                     :label="role.roleName">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="filterUser" size="small">查询</el-button>
      </el-form-item>
    </el-form>
    <el-table :data="userList" :header-cell-style="{ 'text-align': 'center' }"
              :cell-style="{ 'text-align': 'center' }">
      <el-table-column prop="uid" label="uid" min-width="140"></el-table-column>
      <el-table-column prop="username" label="姓名"></el-table-column>
      <el-table-column prop="password" label="登录密码"></el-table-column>
      <el-table-column prop="phoneNumber" label="联系方式" min-width="120"></el-table-column>
      <el-table-column prop="roleName" label="权限"></el-table-column>
      <el-table-column prop="accountStatusName" label="账户状态"></el-table-column>
      <el-table-column prop="createdBy" label="创建人"></el-table-column>
      <el-table-column prop="createdTime" label="创建时间" min-width="150"></el-table-column>
      <el-table-column fixed="right" label="操作" min-width="160">
        <template slot-scope="scope">
          <el-button type="primary" @click="editAction(scope.$index)" size="small">编辑</el-button>
          <el-button type="danger" size="small" @click="delUser(scope.row.uid)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <div style="margin-top: 15px; text-align: center">
      <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
                     :current-page="page.currentPage" :total="page.total" :page-size="page.pageSize"
                     :page-sizes="[5, 30, 50, 100]" layout="prev, pager, next, sizes, total">
      </el-pagination>
    </div>

    <!-- 编辑弹窗 -->
    <el-dialog title="用户信息编辑" :visible.sync="editDialogVisible">
      <el-form :model="editUserForm" label-position="top">
        <el-form-item label="uid">
          <el-input v-model="editUserForm.uid" placeholder="uid" disabled></el-input>
        </el-form-item>
        <el-form-item label="用户姓名">
          <el-input v-model="editUserForm.username" placeholder="用户姓名"></el-input>
        </el-form-item>
        <el-form-item label="登录密码">
          <el-input v-model="editUserForm.password" placeholder="登录密码"></el-input>
        </el-form-item>
        <el-form-item label="联系方式">
          <el-input v-model="editUserForm.phoneNumber" placeholder="联系方式"></el-input>
        </el-form-item>
        <el-form-item label="权限编码">
          <el-select v-model="editUserForm.roleCode" placeholder="请选择" style="width: 100%">
            <el-option v-for="role in roleList" :item="role" :key="role.roleCode" :value="role.roleCode"
                       :label="role.roleName">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="账户状态">
          <el-select v-model="editUserForm.accountStatusCode" placeholder="请选择" style="width: 100%">
            <el-option v-for="item in accountStatusList" :item="item" :key="item.accountStatusCode"
                       :value="item.accountStatusCode" :label="item.accountStatusName">
            </el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="editDialogVisible = false" size="medium">取 消</el-button>
        <el-button type="primary" @click="confirmUpdate" size="medium">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>
<script>

import {
  findAllAccountStatus,
  findAllRoles,
  findByNameOrRole,
  findByPage,
  updateAccountStatus,
  updateUserInfo
} from '@/api/user'
import { Message } from "element-ui"

export default {
  data() {
    return {
      roleList: [],
      userList: [],
      accountStatusList: [],
      condition: {
        username: '',
        roleCode: ''
      },
      page: {
        currentPage: 1,
        total: 200,
        pageSize: 5
      },
      editDialogVisible: false,
      editUserForm: {
        username: '',
        password: '',
        phoneNumber: '',
        roleCode: '',
        accountStatusCode: ''
      }
    }
  },
  created() {
    this.queryRoles()
    this.queryAccountStatus()
    this.queryUser()
  },
  methods: {
    queryRoles() {
      findAllRoles().then((res) => {
        this.roleList = res.data
      })
    },
    queryAccountStatus() {
      findAllAccountStatus().then((res) => {
        this.accountStatusList = res.data
      })
    },
    queryUser() {
      this.userList = []
      let params = {
        pageNum: this.page.currentPage,
        pageSize: this.page.pageSize
      }
      findByPage(params).then((res) => {
        this.userList = res.data.list
        this.page.total = res.data.total
      })
    },
    filterUser() {
      let params = {
        pageNum: this.page.currentPage,
        pageSize: this.page.pageSize,
        username: this.condition.username,
        roleCode: this.condition.roleCode
      }
      findByNameOrRole(params).then((res) => {
        this.userList = res.data.list
        this.page.total = res.data.total
      })
    },
    handleSizeChange(val) {
      this.page.pageSize = val
      this.queryUser()
    },
    handleCurrentChange(val) {
      this.page.currentPage = val
      this.queryUser()
    },
    editAction(rowIndex) {
      let row = this.userList[rowIndex]
      Object.assign(this.editUserForm, row)
      this.editDialogVisible = true
    },
    confirmUpdate() {
      updateUserInfo(this.editUserForm).then((res) => {
        Message.success(res.message)
        this.editDialogVisible = false
        this.queryUser()
      })
    },
    delUser(uid) {
      let params = {
        uid: uid,
        newAccountStatus: 'ACCOUNT_STATUS_INVALID'
      }
      updateAccountStatus(params).then((res) => {
        Message.success(res.message)
        this.queryUser()
      })
    }
  }
}
</script>
<style>
.el-dialog__body {
  padding: 15px 20px;
}
</style>
