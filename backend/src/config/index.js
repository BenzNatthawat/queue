import path from 'path'

if (process.env.NODE_ENV !== 'production') {
  const dotenv = require('dotenv');
  dotenv.config();
}
const rootPath = path.normalize(__dirname + '/..')
const env = process.env.NODE_ENV || 'development'
const pjson = require('./../../package.json')

const conf = {
  development: {
    root: rootPath,
    app: {
      name: pjson.name,
      version: pjson.version,
    },
    port: process.env.AUTHENAPP_PORT || 3000,
    webDomain: process.env.AUTHENAPP_DOMAIN || 'localhost',
    secretKey: process.env.SECRETKEY || 'demo_secret_key',
    db: {
      uri: process.env.AUTHENAPP_DB_URI,
      dbname: process.env.AUTHENAPP_DB_DATABASENAME
    },
    apiName: 'api'
  },
  production: {
    root: rootPath,
    app: {
      name: pjson.name,
      version: pjson.version,
    },
    port: process.env.AUTHENAPP_PORT,
    webDomain: process.env.AUTHENAPP_DOMAIN,
    secretKey: process.env.SECRETKEY,
    db: {
      uri: process.env.AUTHENAPP_MONGODB_URI,
      dbname: process.env.AUTHENAPP_MONGODB_DATABASENAME
    },
    apiName: 'api'
  }
}

export default conf[env]
