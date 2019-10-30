import expressJWT from 'express-jwt'
import config from './'
const jwt = () => {
  const secret = config.secretKey
  return expressJWT({
    secret
  }).unless({
    path: [
      /^\/api\/users/,
      /^\/api\/users\/.*/,
    ]
  })
}
export default jwt
