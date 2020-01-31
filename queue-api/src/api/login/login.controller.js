import express from 'express'
import bcrypt from 'bcrypt'
import loadDB from '../../config/db'
import signin from '../../config/signin'

const router = express.Router()

const login = async (req, res, next) => {
  const { username, password } = req.body
  const db = await loadDB()
  console.log('login' + username)
  if (username && password) {
    const user = await db.query(`SELECT * FROM users WHERE username = '${username}'`, async (err, results) => {
      if (err) throw err
      if (results.length > 0) {
        const loginStatus = bcrypt.compareSync(password, results[0].password)
        if (loginStatus) {
          const resultsLogin = results[0]
          const user = await db.query(`UPDATE users SET status=1 WHERE id=${resultsLogin.id}`, async (err, results) => {
            if (err) throw err
            const token = signin({ id: resultsLogin.id, username })
            return res.json({ success: 'login success', token, username: resultsLogin.username, name: resultsLogin.name, role: resultsLogin.role })
          })
        }
      }
    })
  } else {
    return res.json({ error: 'required', username, password })
  }
}

router.post('/', login)
module.exports = router
