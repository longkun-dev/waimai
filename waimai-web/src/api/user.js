import request from '@/utils/request'

export function login(params) {
  return request({
    url: 'user/doLogin',
    method: 'get',
    params: params
  })
}

export function getUserInfoByToken(token) {
  return request({
    url: 'user/findByToken',
    method: 'get',
    params: token
  })
}

export function logout() {
  return request({
    url: 'user/doLogout',
    method: 'get'
  })
}

export function findByPage(params) {
  return request({
    url: 'user/findByPage',
    method: 'get',
    params: params
  })
}

export function findByNameOrRole(params) {
  return request({
    url: 'user/findByNameOrRole',
    method: 'get',
    params: params
  })
}

export function findAllRoles() {
  return request({
    url: 'user/role/findAll',
    method: 'get'
  })
}

export function findAllAccountStatus() {
  return request({
    url: '/user/account/findAll',
    method: 'get'
  })
}

export function updateUserInfo(user) {
  return request({
    url: '/user/',
    method: 'put',
    data: user
  })
}

export function updateAccountStatus(data) {
  return request({
    url: '/user/account/',
    method: 'put',
    data: data
  })
}

export function updateRoleName(data) {
  return request({
    url: '/user/role/',
    method: 'put',
    data: data
  })
}
