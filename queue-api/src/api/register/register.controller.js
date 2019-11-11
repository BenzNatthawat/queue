import express from 'express'
import bcrypt from 'bcrypt'
import loadDB from '../../config/db'
import signin from '../../config/signin'

const router = express.Router()

const register = async (req, res, next) => {
  const { username, password, name, role } = req.body
  if (username && password && name && role) {
    const db = await loadDB()
    const saltRounds = 10;
    const hash = await bcrypt.hash(password, saltRounds).then(hash => hash)
    const user = await db.query(`INSERT INTO users (username, password, name, role, status) VALUES ('${username}', '${hash}', '${name}', '${role}', 1)`, async (err, results) => {
      if (!err) {
        const token = signin(username)
        return res.json({ success: 'register success', token, name })
      }
      return res.json({ error: 'register failed', username, password, name, role, messageErr: err.sqlMessage })
    })
  } else {
    return res.json({ error: 'required', username, password, name, role })
  }
}

router.post('/', register)
module.exports = router
