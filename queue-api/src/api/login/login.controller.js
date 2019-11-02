import express from 'express'
import bcrypt from 'bcrypt'
import loadDB from '../../config/db'

const router = express.Router()

const login = async (req, res, next) => {
  const { username, password } = req.body
  const saltRounds = 10;
  const hash = await bcrypt.hash(password, saltRounds).then(hash => hash)
  const loginStatus = bcrypt.compareSync(password, '$2b$10$2U6dPyms1ppqU9TlUpJXL.SF43rRjE4vUbCiOdu0.T6vpxAf4XwSm')
  if (loginStatus) {
    res.json({ username, password, hash })
  }
  res.json({ status: 400, error: 'login failed' })
}

router.post('/', login)
module.exports = router
