<template>
  <div style="padding: 20px">
    <el-table :data="roleList" :header-cell-style="{ 'text-align': 'center' }"
              :cell-style="{ 'text-align': 'center' }">
      <el-table-column prop="roleCode" label="角色编码" min-width="140"></el-table-column>
      <el-table-column prop="roleName" label="角色名称"></el-table-column>
      <el-table-column prop="createdBy" label="创建人"></el-table-column>
      <el-table-column prop="createdTime" label="创建时间" min-width="150"></el-table-column>
      <el-table-column fixed="right" label="操作" min-width="160">
        <template slot-scope="scope">
          <el-button type="primary" @click="editAction(scope.$index)" size="small">编辑</el-button>
          <el-button type="danger" @click="delRole" size="small">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 编辑弹窗 -->
    <el-dialog title="角色信息编辑" :visible.sync="editDialogVisible">
      <el-form :model="editForm" label-position="top">
        <el-form-item label="角色编码">
          <el-input v-model="editForm.roleCode" placeholder="角色编码" disabled></el-input>
        </el-form-item>
        <el-form-item label="用户姓名">
          <el-input v-model="editForm.roleName" placeholder="角色名称"></el-input>
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

import { Message } from 'element-ui'
import { findAllRoles, updateRoleName } from '@/api/user'

export default {
  data() {
    return {
      roleList: [],
      editForm: {
        roleName: ''
      },
      editDialogVisible: false
    }
  },
  created() {
    this.queryRoles()
  },
  methods: {
    queryRoles() {
      findAllRoles().then((res) => {
        this.roleList = res.data
      })
    },
    editAction(rowIndex) {
      let row = this.roleList[rowIndex]
      Object.assign(this.editForm, row)
      this.editDialogVisible = true
    },
    confirmUpdate() {
      let params = {
        roleCode: this.editForm.roleCode,
        roleName: this.editForm.roleName
      }
      updateRoleName(params).then((res) => {
        Message.success(res.message)
        this.editDialogVisible = false
        this.queryRoles()
      })
    },
    delRole() {
      Message.warning('暂不支持删除角色信息')
      return false
    }
  }
}
</script>
<style>
.el-dialog__body {
  padding: 15px 20px;
}
</style>
