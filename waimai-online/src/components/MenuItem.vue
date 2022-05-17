<template>
  <div>
    <div class="demo-image__placeholder" style="margin: 10px">
      <div style="width: 200px; height: 215px">
        <el-image :src="imgSrc" fit="fill">
          <div slot="placeholder" class="image-slot">加载中
            <span class="dot">...</span>
          </div>
          <div slot="error" class="image-slot">
            <i class="el-icon-picture-outline"></i>
          </div>
        </el-image>
        <div>{{ menuName }}</div>
        <div>{{ menuDesc }}</div>
        <div>
          <el-button type="default" size="mini" @click="addItem">添加到购物车 🛒</el-button>
          &emsp;&emsp;<span>¥ {{ price }}</span>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {}
  },
  props: ['id', 'menuName', 'menuDesc', 'imgSrc', 'price'],
  methods: {
    addItem() {
      let data = {
        id: this.id,
        menuName: this.menuName,
        menuDesc: this.menuDesc,
        imgSrc: this.imgSrc,
        price: this.price
      }
      this.$emit('add-item', data, 1)
    }
  }
}
</script>
<style>
.el-card {
  padding: 5px;
}
</style>
