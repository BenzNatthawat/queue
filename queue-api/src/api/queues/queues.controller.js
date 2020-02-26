import express from 'express'
import loadDB from '../../config/db'
import moment from 'moment'

const router = express.Router()

const index = async (req, res, next) => {
  const { id } = req.decoded
  const db = await loadDB()
  await db.query(`
  SELECT id, queueNumber, technicians__id, createdAt, status, insurance FROM queues 
  WHERE technicians__id = ${id} AND status = 'wait'
  ORDER BY insurance, createdAt, id LIMIT 4;
  SELECT id, queueNumber, technicians__id, createdAt, status, insurance FROM queues 
  WHERE technicians__id = ${id} AND status = 'proceed' LIMIT 1;
  `, async (err, results) => {
    if (err) throw err
    results[0].unshift(...results[1])
    return res.json(results[0])
  })
}

const create = async (req, res, next) => {
  const { comment, insurance, jobnumber } = req.body
  const { id } = req.decoded
  const db = await loadDB()

  await db.query(`
    SELECT queueNumber, createdAt, technicians__id FROM queues ORDER BY id DESC LIMIT 1;
    SELECT users.id, IFNULL(groupQueues.countTechniciansId, 0) AS queuesLoad
    FROM users
    LEFT OUTER JOIN 
      (SELECT technicians__id, COUNT(technicians__id) AS countTechniciansId FROM queues WHERE STATUS = 'wait' GROUP BY technicians__id) AS groupQueues
    ON users.id = groupQueues.technicians__id
    WHERE role = 'technician' AND STATUS = 1;
    SELECT id FROM users WHERE id = ${id};
  `, async (err, results) => {
    if (err) throw err
    let queueNumber, techniciansId, minQueueLoad
    if (results[1].length > 0) {
      if (results[0].length === 0) {
        queueNumber = 1
        techniciansId = results[1][0].id
      } else {
        //    หาว่าช่างคนไหน load queue น้อย สุด
        minQueueLoad = results[1].reduce(function (res, obj) {
          return (obj.queuesLoad < res.queuesLoad) ? obj : res;
        })
        techniciansId = minQueueLoad.id
        if (moment(results[0][0].createdAt).format("YYYY-MM-DD") === moment(new Date()).format("YYYY-MM-DD"))
          queueNumber = parseInt(results[0][0].queueNumber) + 1
        else
          queueNumber = 1
      }
    } else {
      return res.json({ error: 'กรุณาเพิ่มช่าง' })
    }

    await db.query(`INSERT INTO queues (queueNumber, comment, technicians__id, users__id, insurance, jobnumber) VALUES (${queueNumber}, '${comment}', ${techniciansId}, ${id}, '${insurance || ''}', '${jobnumber || ''}')`, async (err, results) => {
      if (err) throw err
      await db.query(`SELECT queues.queueNumber, queues.comment, users.name FROM queues INNER JOIN users ON users.id = queues.technicians__id WHERE queues.id = ${results.insertId}`, async (err, results) => {
        if (err) throw err
        return res.json(results[0])
      })
    })
  })
}

const show = async (req, res, next) => {
  const { id } = req.params
  const db = await loadDB()
  await db.query(`SELECT * FROM queues WHERE id = ${id}`, async (err, results) => {
    if (err) throw err
    return res.json(results[0])
  })
}

const update = async (req, res, next) => {
  const { status } = req.body
  const { id } = req.params
  const db = await loadDB()
  await db.query(`UPDATE queues SET status = '${status}' WHERE id = ${id};`, async (err, results) => {
    if (err) throw err
    return res.json(results)
  })
}

router.get('/', index)
  .get('/:id', show)
  .post('/create', create)
  .post('/:id', update)
module.exports = router
