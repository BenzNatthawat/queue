import express from 'express'
import bcrypt from 'bcrypt'
import loadDB from '../../config/db'

const router = express.Router()

const login = async (req, res, next) => {
  const { username, password } = req.body
  const db = await loadDB()
  const user = db.query(`SELECT * FROM users WHERE username = '${username}'`, async (err, results) => {
    if (err) throw err
    if (results.length > 0) {
      const saltRounds = 10;
      const hash = await bcrypt.hash(password, saltRounds).then(hash => hash)
      const loginStatus = bcrypt.compareSync(password, results[0].password)
      if (loginStatus) {
        return res.json({ success: 'login success', username, hash })
      }
    }
    return res.json({ error: 'login failed' })
  })
}

router.post('/', login)
module.exports = router
