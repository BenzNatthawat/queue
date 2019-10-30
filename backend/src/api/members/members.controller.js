import express from 'express'
import memberService from './member.service'

const router = express.Router()

const getAll = (req, res, next) => {
  memberService.getAll()
    .then(users => res.json(users))
    .catch(err => next(err))
}

router.get('/', getAll)
module.exports = router
