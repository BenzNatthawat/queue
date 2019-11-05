import express from 'express'
import bcrypt from 'bcrypt'
import loadDB from '../../config/db'
import jwt from 'jsonwebtoken'
import conf from '../../config'
const router = express.Router()

const login = async (req, res, next) => {
  const { username, password } = req.body
  const db = await loadDB()
  const user = db.query(`SELECT * FROM users WHERE username = '${username}'`, (err, results) => {
    if (err) throw err
    if (results.length > 0) {
      const loginStatus = bcrypt.compareSync(password, results[0].password)
      if (loginStatus) {
        let token = jwt.sign({ username: username },
          conf.secretKey,
          {
            expiresIn: conf.expiresIn // expires in 24 hours
          }
        )
        return res.json({ success: 'login success', username, token })
      }
    }
    return res.json({ error: 'login failed' })
  })
}

router.post('/', login)
module.exports = router
