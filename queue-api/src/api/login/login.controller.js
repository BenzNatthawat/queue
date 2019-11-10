import express from 'express'
import bcrypt from 'bcrypt'
import loadDB from '../../config/db'
import signin from '../../config/signin'

const router = express.Router()

const login = async (req, res, next) => {
  const { username, password } = req.body
  const db = await loadDB()
  if (username && password) {
    const user = db.query(`SELECT * FROM users WHERE username = '${username}'`, (err, results) => {
      if (!err) {
        if (results.length > 0) {
          const loginStatus = bcrypt.compareSync(password, results[0].password)
          if (loginStatus) {
            const token = signin(username)
            return res.json({ success: 'login success', token, username: results[0].username, name: results[0].name })
          }
        }
      }
      return res.json({ error: 'login failed' })
    })
  } else {
    return res.json({ error: 'required', username, password })
  }
}

router.post('/', login)
module.exports = router
