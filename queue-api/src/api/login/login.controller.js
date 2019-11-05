import express from 'express'
import bcrypt from 'bcrypt'
import loadDB from '../../config/db'

const router = express.Router()

const login = async (req, res, next) => {
  const { username, password } = req.body
  const db = await loadDB()
  const user = db.query(`SELECT * FROM users WHERE username = '${username}'`, (err, results) => {
    if (err) throw err
    if (results.length > 0) {
      const loginStatus = bcrypt.compareSync(password, results[0].password)
      if (loginStatus) {
        const payload = {
          sub: username,
          iat: new Date().getTime() //มาจากคำว่า issued at time (สร้างเมื่อ)
        }
        const SECRET = 'QUEUE_APP_KEY' //ในการใช้งานจริง คีย์นี้ให้เก็บเป็นความลับ
        const token = jwt.encode(payload, SECRET)
        return res.json({ success: 'login success', username, token })
      }
    }
    return res.json({ error: 'login failed' })
  })
}

router.post('/', login)
module.exports = router
