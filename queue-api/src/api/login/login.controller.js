import express from 'express'
import bcrypt from 'bcrypt'
import loadDB from '../../config/db'

const router = express.Router()

const login = async (req, res, next) => {
  const { username, password } = req.body
  const saltRounds = 10;
  const hash = await bcrypt.hash(password, saltRounds).then(hash => hash).catch()
  res.json({ username, password, hash })
}

router.post('/', login)
module.exports = router
