import Vue from 'vue'
import Router from 'vue-router'
import Index from '@/views/Index'
import MenuItem from '@/components/MenuItem'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Index',
      component: Index
    }, {
      path: '/MenuItem',
      name: 'MenuItem',
      component: MenuItem
    }
  ]
})
