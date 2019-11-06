import jwt from 'jsonwebtoken'
import conf from '../config'
const signin = (username) => {
  return jwt.sign({ username: username },
    conf.secretKey,
    {
      expiresIn: conf.expiresIn // expires in 24 hours
    }
  )
}

export default signin