<template>
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
          <el-menu-item index="1">
            <a slot="title" href="#zhushi">🍚 主食</a>
          </el-menu-item>
          <el-menu-item index="2">
            <span slot="title">🌭️ 小菜</span>
          </el-menu-item>
          <el-menu-item index="3">
            <span slot="title">🧂 佐料</span>
          </el-menu-item>
          <el-menu-item index="4">
            <span slot="title">🧃 饮品</span>
          </el-menu-item>
        </el-menu>
      </el-aside>

      <el-container>
        <el-main>
          <div id="zhushi" style="text-align: left">
            <h2>🍚 主食类</h2>
          </div>
          <div style="display: flex; flex-direction: row; flex-wrap: wrap">
            <div v-for="item in menuList1">
              <menu-item :id="item.id" :menuName="item.menuName" :menuDesc="item.menuDesc"
                         :imgSrc="item.imgSrc" :price="item.price" @add-item="addItem">
              </menu-item>
            </div>
          </div>

          <div style="text-align: left">
            <h2>🌭️ 小吃</h2>
          </div>
          <div style="display: flex; flex-direction: row; flex-wrap: wrap">
            <div v-for="item in menuList1">
              <menu-item :id="item.id" :menuName="item.menuName" :menuDesc="item.menuDesc"
                         :imgSrc="item.imgSrc" :price="item.price" @add-item="addItem">
              </menu-item>
            </div>
          </div>

          <div style="text-align: left">
            <h2>🧂 佐料</h2>
          </div>
          <div style="display: flex; flex-direction: row; flex-wrap: wrap">
            <div v-for="item in menuList1">
              <menu-item :id="item.id" :menuName="item.menuName" :menuDesc="item.menuDesc"
                         :imgSrc="item.imgSrc" :price="item.price" @add-item="addItem">
              </menu-item>
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
        <el-table :data="finalOrderList">
          <el-table-column property="id" label="商品编号" :v-if="false"></el-table-column>
          <el-table-column property="menuName" label="商品名称"></el-table-column>
          <el-table-column property="price" label="单价"></el-table-column>
          <el-table-column property="count" label="数量"></el-table-column>
          <el-table-column property="amount" label="价格合计"></el-table-column>
          <el-table-column fixed="right" label="操作" width="170">
            <template slot-scope="scope">
              <el-input-number v-model="scope.row.count" :min="0" :max="99" step="1"
                               label="描述文字" style="width: 130px"
                               @change="changeItemCount">
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
      imgSrc: 'https://cube.elemecdn.com/6/94/4d3ea53c084bad6931a56d5158a48jpeg.jpeg',
      menuList1: [{
        id: '1001',
        menuName: '🌶️ 爆炒辣椒皮',
        menuDesc: '香喷喷的爆炒浩瀚无垠皮',
        imgSrc: 'https://cube.elemecdn.com/6/94/4d3ea53c084bad6931a56d5158a48jpeg.jpeg',
        price: '12.00'
      }, {
        id: '1002',
        menuName: '🍔 汉堡包',
        menuDesc: '香喷喷的爆炒浩瀚无垠皮',
        imgSrc: 'https://cube.elemecdn.com/6/94/4d3ea53c084bad6931a56d5158a48jpeg.jpeg',
        price: '10.00'
      }, {
        id: '1003',
        menuName: '🌶️ 爆炒辣椒皮',
        menuDesc: '香喷喷的爆炒浩瀚无垠皮',
        imgSrc: 'https://cube.elemecdn.com/6/94/4d3ea53c084bad6931a56d5158a48jpeg.jpeg',
        price: '13.05'
      }, {
        id: '1004',
        menuName: '🌶️ 爆炒辣椒皮',
        menuDesc: '香喷喷的爆炒浩瀚无垠皮',
        imgSrc: 'https://cube.elemecdn.com/6/94/4d3ea53c084bad6931a56d5158a48jpeg.jpeg',
        price: '31.40'
      }],
      orderList: [],
      finalOrderList: [],
      cartVisible: false
    }
  },
  methods: {
    addItem(data) {
      this.$message.success('商品成功添加购物车')
      this.orderList.push(data.id)
      this.itemCount = this.orderList.length
      this.amountSum = Number(this.amountSum) + Number(data.price)

      // 处理提交的订单
      // 如果不是添加第一个商品
      if (this.finalOrderList && this.finalOrderList.length > 0 &&
        this.finalOrderList.map(e => e.id).indexOf(data.id) !== -1) {
        let list = this.finalOrderList
        let length = list.length
        for (let i = 0; i < length; i++) {
          if (list[i].id === data.id) {
            this.finalOrderList[i].count = Number(this.finalOrderList[i].count) + 1
            this.finalOrderList[i].amount = (this.finalOrderList[i].count * Number(data.price)).toFixed(2)
          }
        }
      } else {
        let orderItem = {
          id: data.id,
          menuName: data.menuName,
          price: data.price,
          count: 1,
          amount: data.price
        }
        this.finalOrderList.push(orderItem)
      }
    },
    pay() {
      this.$message.success('支付成功')
      setTimeout(() => {
        this.cartVisible = false
      }, 800)
    },
    changeItemCount(val) {

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
