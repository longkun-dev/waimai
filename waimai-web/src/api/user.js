import request from '@/utils/request'

export function login(data) {
  return request({
    url: 'user/doLogin',
    method: 'get',
    params: data
  })
}

export function getInfo() {
  return request({
    url: 'user/currentUserInfo',
    method: 'get'
  })
}

export function logout() {
  return request({
    url: '/vue-admin-template/user/logout',
    method: 'post'
  })
}

export function findByPage(data) {
  return request({
    url: 'user/findByPage',
    method: 'get',
    params: data
  })
}
