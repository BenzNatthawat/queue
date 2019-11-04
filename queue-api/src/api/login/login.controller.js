import express from 'express'
import bcrypt from 'bcrypt'
import loadDB from '../../config/db'

const router = express.Router()

const login = async (req, res, next) => {
  const { username, password } = req.body
  const db = await loadDB()
  const user = await db.query(`SELECT * FROM users WHERE username = '${username}'`, (err, results) => {
    if (err) throw err
    res.setHeader('Content-Type', 'application/xml')
    if (results.length) {
      // const saltRounds = 10;
      // bcrypt.hash(password, saltRounds).then(hash => hash)
      const loginStatus = bcrypt.compareSync(password, results[0].password)
      if (loginStatus) {
        res.json({ success: 'login success', username }, 0, 500)
      }
    }
    res.json({ error: 'login failed' }, 0, 500)
  })
}

router.post('/', login)
module.exports = router
