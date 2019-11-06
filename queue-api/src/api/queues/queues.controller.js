import express from 'express'
import loadDB from '../../config/db'

const router = express.Router()

const create = async (req, res, next) => {
  const { comment } = req.body
  const db = await loadDB()
  await db.query(`SELECT queueNumber, technicians__id FROM queues ORDER BY id DESC LIMIT 1; SELECT id, name, role, status FROM users WHERE role = 'technician' AND status = 1; SELECT id FROM users WHERE username = '${req.decoded.username}';`, async (err, results) => {
    if (err) throw err
    const userId = results[2][0].id
    let queueNumber, technicians__id
    let techniciansObj = JSON.parse(JSON.stringify(results[1]))
    let nextId = techniciansObj.findIndex((tec) => tec.id == results[0][0].technicians__id)
    if (results[0].length === 0) {
      queueNumber = 1
      technicians__id = results[1][0].id
    } else {
      queueNumber = Number(results[0][0].queueNumber) + 1
      if (nextId < techniciansObj.length - 1)
        technicians__id = results[1][nextId + 1].id
      else {
        technicians__id = results[1][0].id
      }
    }
    await db.query(`INSERT INTO queues (queueNumber, comment, technicians__id, users__id) VALUES (${queueNumber}, ${comment}, ${technicians__id}, ${userId})`, (err, results) => {
      if (err) throw err
      return res.json(results)
    })
  })
}

router.post('/create', create)
module.exports = router
