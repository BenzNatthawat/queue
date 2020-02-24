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
    port: process.env.PORT,
    webDomain: process.env.DOMAIN,
    secretKey: process.env.SECRETKEY,
    expiresIn: '24hr',
    db: {
      uri: process.env.AUTHENAPP_DB_URI,
      host: process.env.AUTHENAPP_DB_DATABASEHOST,
      dbname: process.env.AUTHENAPP_DB_DATABASENAME,
      name: process.env.AUTHENAPP_DB_NAME,
      password: process.env.AUTHENAPP_DB_PASSWORD,
    },
    apiName: process.env.PATH_API
  },
  production: {
    root: rootPath,
    app: {
      name: pjson.name,
      version: pjson.version,
    },
    port: process.env.PORT,
    webDomain: process.env.DOMAIN,
    secretKey: process.env.SECRETKEY,
    expiresIn: '24hr',
    db: {
      uri: process.env.AUTHENAPP_DB_URI,
      host: process.env.AUTHENAPP_DB_DATABASEHOST,
      dbname: process.env.AUTHENAPP_DB_DATABASENAME,
      name: process.env.AUTHENAPP_DB_NAME,
      password: process.env.AUTHENAPP_DB_PASSWORD,
    },
    apiName: process.env.PATH_API
  }
}

export default conf[env]
