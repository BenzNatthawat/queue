import express from 'express'
import loadDB from '../../config/db'

const router = express.Router()

const getAll = async (req, res, next) => {
  const db = await loadDB()
  await db.query('SELECT * FROM users', (err, results) => {
    if (err) throw err
    return res.json(results)
  })
}

router.get('/', getAll)
module.exports = router
