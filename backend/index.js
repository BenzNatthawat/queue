require('rootpath')()

import express from 'express'
import log4js from 'log4js'

import config from './src/config'
import setup from './src/setup'
import database from './src/config/db'


log4js.configure(require('./src/config/log'))
const app = express()
const log = log4js.getLogger('startup')
const logErr = log4js.getLogger('error')

setup(app)

const startApp = function () {
  app.listen(config.port, () => {
    log.info(`Application:: ${config.app.name} v.${config.app.version} running on port ${config.port}`)
  })
}

database().then(startApp).catch(err => {
  logErr.error(new Error(err))
  throw new Error(err)
})
