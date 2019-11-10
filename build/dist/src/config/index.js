'use strict';

Object.defineProperty(exports, "__esModule", {
  value: true
});

var _path = require('path');

var _path2 = _interopRequireDefault(_path);

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

if (process.env.NODE_ENV !== 'production') {
  var dotenv = require('dotenv');
  dotenv.config();
}
var rootPath = _path2.default.normalize(__dirname + '/..');
var env = process.env.NODE_ENV || 'development';
var pjson = require('./../../package.json');

var conf = {
  development: {
    root: rootPath,
    app: {
      name: pjson.name,
      version: pjson.version
    },
    port: process.env.AUTHENAPP_PORT || 3000,
    webDomain: process.env.AUTHENAPP_DOMAIN || 'localhost',
    secretKey: process.env.SECRETKEY || 'demo_secret_key',
    expiresIn: '24hr',
    db: {
      uri: process.env.AUTHENAPP_DB_URI,
      host: process.env.AUTHENAPP_DB_DATABASEHOST,
      dbname: process.env.AUTHENAPP_DB_DATABASENAME,
      name: process.env.AUTHENAPP_DB_NAME,
      password: process.env.AUTHENAPP_DB_PASSWORD
    },
    apiName: 'api'
  },
  production: {
    root: rootPath,
    app: {
      name: pjson.name,
      version: pjson.version
    },
    port: process.env.AUTHENAPP_PORT,
    webDomain: process.env.AUTHENAPP_DOMAIN,
    secretKey: process.env.SECRETKEY,
    expiresIn: '24hr',
    db: {
      uri: process.env.AUTHENAPP_DB_URI,
      host: process.env.AUTHENAPP_DB_DATABASEHOST,
      dbname: process.env.AUTHENAPP_DB_DATABASENAME,
      name: process.env.AUTHENAPP_DB_NAME,
      password: process.env.AUTHENAPP_DB_PASSWORD
    },
    apiName: 'api'
  }
};

exports.default = conf[env];