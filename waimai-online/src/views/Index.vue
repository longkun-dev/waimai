<template xmlns="">
  <div>
    <el-container>
      <el-header style="height: 70px">
        <div class="logo">
          <h1>欢迎点外卖 🥡</h1>
        </div>
      </el-header>
    </el-container>
    <el-container style="width: 1100px; margin: 20px auto">
      <el-aside width="200px">
        <el-menu :default-openeds="['1', '2', '3', '4']">
          <el-menu-item v-for="(menu, index) in menuTypeList" :key="index" :index="index">
            <a slot="title" :href="'#' + menu" style="text-decoration: none; color: initial">
              {{ menu }}
            </a>
          </el-menu-item>
        </el-menu>
      </el-aside>

      <el-container>
        <el-main>
          <div v-for="(item, index) in menuAll" :key="index" style="margin-bottom: 30px">
            <div :id="item.menuType" style="text-align: left;">
              <h2>{{ item.menuType }}</h2>
            </div>
            <div style="display: flex; flex-direction: row; flex-wrap: wrap">
              <div v-for="(subItem, index1) in item.menuList" :key="index1">
                <menu-item :id="subItem.id" :menuName="subItem.menuName" :menuDesc="subItem.menuDesc"
                           :imgSrc="subItem.imgSrc" :price="subItem.price" @add-item="addItem">
                </menu-item>
              </div>
            </div>
          </div>
        </el-main>
      </el-container>
    </el-container>

    <!-- 购物车 -->
    <div class="cart">
      <div>共有 {{ itemCount }} 件商品</div>
      <div>合计 {{ amountSum.toFixed(2) }} 元</div>
      <div style="margin-top: 5px">
        <el-button type="primary" size="mini" @click="cartVisible = true">✅ 结 账</el-button>
      </div>

      <!-- 弹窗 -->
      <el-dialog title="购物车 🛒" :visible.sync="cartVisible" :append-to-body="true">
        <el-table :data="orderList">
          <el-table-column property="id" label="商品编号" :v-if="false"></el-table-column>
          <el-table-column property="menuName" label="商品名称"></el-table-column>
          <el-table-column property="price" label="单价"></el-table-column>
          <el-table-column property="count" label="数量"></el-table-column>
          <el-table-column property="amount" label="价格合计"></el-table-column>
          <el-table-column fixed="right" label="操作" width="170">
            <template slot-scope="scope">
              <el-input-number v-model="scope.row.count" :min="0" :max="20"
                               label="描述文字" style="width: 130px"
                               @change="changeItemCount($event, scope.row)">
              </el-input-number>
            </template>
          </el-table-column>
        </el-table>
        <div slot="footer" class="dialog-footer">
          <el-button type="primary" size="mini" @click="pay">支 付</el-button>
        </div>
      </el-dialog>
    </div>
  </div>
</template>
<script>
import MenuItem from '@/components/MenuItem'

export default {
  components: {
    MenuItem
  },
  data() {
    return {
      itemCount: 0,
      amountSum: 0.00,
      menuAll: [],
      menuTypeList: [],
      orderList: [],
      tableKey: '1234',
      cartVisible: false
    }
  },
  created() {
    this.menuTypeList = []
    this.menuAll = [
      {
        "menuType": "🍚 主食",
        "menuList": [
          {
            "id": '1001',
            "menuName": "爆炒辣椒皮",
            "menuDesc": "相关的描述",
            "imgSrc": "https://cube.elemecdn.com/6/94/4d3ea53c084bad6931a56d5158a48jpeg.jpeg",
            "price": "12.00"
          }, {
            "id": '1002',
            "menuName": "爆炒辣椒皮",
            "menuDesc": "相关的描述",
            "imgSrc": "https://cube.elemecdn.com/6/94/4d3ea53c084bad6931a56d5158a48jpeg.jpeg",
            "price": "15.05"
          }
        ]
      }, {
        "menuType": "🍣 小吃",
        "menuList": [
          {
            "id": '1003',
            "menuName": "爆炒辣椒皮",
            "menuDesc": "相关的描述",
            "imgSrc": "https://cube.elemecdn.com/6/94/4d3ea53c084bad6931a56d5158a48jpeg.jpeg",
            "price": "10.00"
          }
        ]
      }, {
        "menuType": "🧂 佐料",
        "menuList": [
          {
            "id": '1004',
            "menuName": "爆炒辣椒皮",
            "menuDesc": "相关的描述",
            "imgSrc": "https://cube.elemecdn.com/6/94/4d3ea53c084bad6931a56d5158a48jpeg.jpeg",
            "price": "10.00"
          }
        ]
      }, {
        "menuType": "🥤 饮品",
        "menuList": [
          {
            "id": '1005',
            "menuName": "爆炒辣椒皮",
            "menuDesc": "相关的描述",
            "imgSrc": "https://cube.elemecdn.com/6/94/4d3ea53c084bad6931a56d5158a48jpeg.jpeg",
            "price": "10.00"
          }
        ]
      }]
    for (let i = 0; i < this.menuAll.length; i++) {
      this.menuTypeList.push(this.menuAll[i].menuType)
    }
  },
  methods: {
    addItem(data, count) {

      // 处理提交的订单
      // 如果不是添加第一个商品
      let orderList = this.orderList
      this.orderList = []
      let orderLength = orderList.length
      let firstItem = true
      for (let i = 0; i < orderLength; i++) {
        if (data.id === orderList[i].id) {
          // 商品数量加 1
          orderList[i].count = Number(orderList[i].count) + Number(count)
          // 重新计算价格
          orderList[i].amount = Number(orderList[i].price) * Number(orderList[i].count)
          firstItem =  false
        }
      }
      if (firstItem) {
        // 如果第一次添加该商品则添加进订单中
        orderList.push({
          'id': data.id,
          'menuName': data.menuName,
          'price': data.price,
          'count': 1,
          'amount': data.price
        })
      }

      this.itemCount = 0
      this.amountSum = 0.00
      orderList.forEach(function(item, index, arr) {
        if(item.count <= 0) {
          arr.splice(index, 1);
        }
      })

      for (let i = 0; i < orderList.length; i++) {
        this.itemCount = this.itemCount + Number(orderList[i].count)
        this.amountSum = this.amountSum + Number(orderList[i].amount)
      }

      this.orderList = orderList
      console.log('orderListL: ', this.orderList)

      if (count > 0) {
        this.$message.success('商品添加成功')
      } else {
        this.$message.success('商品移除成功')
      }
    },
    pay() {
      if (!this.orderList || this.orderList.length === 0) {
        this.$message.warning('至少添加一件商品才能结算')
        return false
      }
      this.$message.success('支付成功')
      setTimeout(() => {
        this.cartVisible = false
      }, 800)
    },
    changeItemCount(val, row) {
      this.addItem(row, val - row.count)
    }
  }
}
</script>
<style>
.el-header {
  background-color: #B3C0D1;
  color: #333333;
}

.logo {
  text-align: left;
  margin-left: calc((100% - 950px) / 2);
}

.cart {
  padding: 15px;
  position: fixed;
  bottom: 25px;
  right: 25px;
  width: 130px;
  height: 75px;
  background: #d3dce6;
  text-align: left;
  margin: auto 0;
}

.el-main {
  padding: 0 20px;
}
</style>
