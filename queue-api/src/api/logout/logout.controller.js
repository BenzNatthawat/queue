import express from 'express'
import loadDB from '../../config/db'

const router = express.Router()

const logout = async (req, res, next) => {
  const { id } = req.decoded
  const db = await loadDB()
  console.log('logout' + id)
  if (id) {
    const user = await db.query(`UPDATE users SET status=0 WHERE id=${id}`, (err, results) => {
      if (!err) { return res.json({ success: 'success logout' }) }
      return res.json({ error: 'error logout' })
    })
  } else {
    return res.json({ error: 'error' })
  }
}

router.get('/', logout)
module.exports = router
