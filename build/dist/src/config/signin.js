'use strict';

Object.defineProperty(exports, "__esModule", {
  value: true
});

var _jsonwebtoken = require('jsonwebtoken');

var _jsonwebtoken2 = _interopRequireDefault(_jsonwebtoken);

var _config = require('../config');

var _config2 = _interopRequireDefault(_config);

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

var signin = function signin(username) {
  return _jsonwebtoken2.default.sign({ username: username }, _config2.default.secretKey, {
    expiresIn: _config2.default.expiresIn // expires in 24 hours
  });
};

exports.default = signin;