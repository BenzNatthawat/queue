import express from 'express'
import loadDB from '../../config/db'
import moment from 'moment'

const router = express.Router()

const index = async (req, res, next) => {
  const db = await loadDB()
  await db.query(`SELECT queueNumber, technicians__id FROM queues ORDER BY id DESC LIMIT 1`, async (err, results) => {
    if (err) throw err
    return res.json(results)
  })
}

const create = async (req, res, next) => {
  const { comment } = req.body
  const db = await loadDB()
  await db.query(`SELECT queueNumber, createdAt, technicians__id FROM queues ORDER BY id DESC LIMIT 1; SELECT id, name, role, status FROM users WHERE role = 'technician' AND status = 1; SELECT id FROM users WHERE username = '${req.decoded.username}';`, async (err, results) => {
    if (err) throw err
    const userId = results[2][0].id
    let queueNumber, technicians__id
    let techniciansObj = JSON.parse(JSON.stringify(results[1]))
    if (results[1].length > 1) {
      if (results[0].length === 0) {
        queueNumber = 1
        technicians__id = results[1][0].id
      } else {
        let nextId
        if (moment(results[0][0].createdAt).format("YYYY-MM-DD") === moment(new Date()).format("YYYY-MM-DD")) {
          nextId = techniciansObj.findIndex((tec) => tec.id == results[0][0].technicians__id)
          queueNumber = Number(results[0][0].queueNumber) + 1
        } else {
          nextId = -1
          queueNumber = 1
        }
        if (nextId < techniciansObj.length - 1)
          technicians__id = results[1][nextId + 1].id
        else {
          technicians__id = results[1][0].id
        }
      }
      await db.query(`INSERT INTO queues (queueNumber, comment, technicians__id, users__id) VALUES (${queueNumber}, '${comment}', ${technicians__id}, ${userId})`, async (err, results) => {
        if (err) throw err
        await db.query(`SELECT queues.queueNumber, queues.comment, users.name FROM queues INNER JOIN users ON users.id = queues.technicians__id WHERE queues.id = ${results.insertId}`, async (err, results) => {
          if (err) throw err
          return res.json(results[0])
        })
      })
    } else {
      return res.json({ error: 'กรุณาเพิ่มช่าง' })
    }
  })
}

router.get('/', index).post('/create', create)
module.exports = router
