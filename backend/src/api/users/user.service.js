import jwt from 'jsonwebtoken'
import 'moment/locale/th'

import config from './../../config'
import { users } from '../../mock/users'

const token = jwt.sign({
  exp: Math.floor(Date.now() / 1000) + (24 * 3600), //24hrs
  iat: Math.floor(Date.now() / 1000)
}, config.secretKey)

async function getUsers() {
  return users
}

async function showUser(req) {
  const { id: params } = req.params
  return users.filter((user) => user.id === params)
}

export default {
  getUsers,
  showUser
}
