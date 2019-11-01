import bodyParser from 'body-parser'
import cors from 'cors'
import logger from 'morgan'
import expressValidator from 'express-validator'

import jwt from './config/jwt'
import errorHandler from './config/errorHandler'
import conf from './config'

// ต้อง login ก่อน
const setupRoutesNotLS = function (app) {
}

// ไม่ต้อง login
const setupRoutes = function (app) {
  app.use(`/${conf.apiName}/members`, require('./api/users/users.controller'))
  app.use(`/${conf.apiName}/queues`, require('./api/queues/queues.controller'))
  app.use(`/${conf.apiName}/login`, require('./api/login/login.controller'))
}

const invalidRoute = (app) => {
  app.use((req, res, next) => {
    const error = new Error()
    error.message = 'Invalid route'
    error.status = 404
    next(error)
  })
}

export default function (app, config) {
  app.use(cors())
  process.env.NODE_ENV === 'development' && app.use(logger('dev'))
  app.use(bodyParser.urlencoded({ extended: true }))
  app.use(bodyParser.json())

  // app.use(jwt())

  app.use(expressValidator({
    customValidators: {
      isArray: function (value) {
        return Array.isArray(value)
      },
      arrayNotEmpty: function (array) {
        return array.length > 0
      }
    }
  }))

  setupRoutesNotLS(app)
  setupRoutes(app)
  invalidRoute(app)
  app.use(errorHandler)
}
