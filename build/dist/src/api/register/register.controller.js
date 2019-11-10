'use strict';

var _regenerator = require('babel-runtime/regenerator');

var _regenerator2 = _interopRequireDefault(_regenerator);

var _express = require('express');

var _express2 = _interopRequireDefault(_express);

var _bcrypt = require('bcrypt');

var _bcrypt2 = _interopRequireDefault(_bcrypt);

var _db = require('../../config/db');

var _db2 = _interopRequireDefault(_db);

var _signin = require('../../config/signin');

var _signin2 = _interopRequireDefault(_signin);

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

function _asyncToGenerator(fn) { return function () { var gen = fn.apply(this, arguments); return new Promise(function (resolve, reject) { function step(key, arg) { try { var info = gen[key](arg); var value = info.value; } catch (error) { reject(error); return; } if (info.done) { resolve(value); } else { return Promise.resolve(value).then(function (value) { step("next", value); }, function (err) { step("throw", err); }); } } return step("next"); }); }; }

var router = _express2.default.Router();

var register = function () {
  var _ref = _asyncToGenerator( /*#__PURE__*/_regenerator2.default.mark(function _callee2(req, res, next) {
    var _req$body, username, password, name, role, db, saltRounds, hash, user;

    return _regenerator2.default.wrap(function _callee2$(_context2) {
      while (1) {
        switch (_context2.prev = _context2.next) {
          case 0:
            _req$body = req.body, username = _req$body.username, password = _req$body.password, name = _req$body.name, role = _req$body.role;

            if (!(username && password && name && role)) {
              _context2.next = 14;
              break;
            }

            _context2.next = 4;
            return (0, _db2.default)();

          case 4:
            db = _context2.sent;
            saltRounds = 10;
            _context2.next = 8;
            return _bcrypt2.default.hash(password, saltRounds).then(function (hash) {
              return hash;
            });

          case 8:
            hash = _context2.sent;
            _context2.next = 11;
            return db.query('INSERT INTO users (username, password, name, role) VALUES (\'' + username + '\', \'' + hash + '\', \'' + name + '\', \'' + role + '\')', function () {
              var _ref2 = _asyncToGenerator( /*#__PURE__*/_regenerator2.default.mark(function _callee(err, results) {
                var token;
                return _regenerator2.default.wrap(function _callee$(_context) {
                  while (1) {
                    switch (_context.prev = _context.next) {
                      case 0:
                        if (err) {
                          _context.next = 4;
                          break;
                        }

                        token = (0, _signin2.default)(username);

                        console.log({ success: 'register success', token: token });
                        return _context.abrupt('return', res.json({ success: 'register success', token: token }));

                      case 4:
                        return _context.abrupt('return', res.json({ error: 'register failed', username: username, password: password, name: name, role: role, messageErr: err.sqlMessage }));

                      case 5:
                      case 'end':
                        return _context.stop();
                    }
                  }
                }, _callee, undefined);
              }));

              return function (_x4, _x5) {
                return _ref2.apply(this, arguments);
              };
            }());

          case 11:
            user = _context2.sent;
            _context2.next = 15;
            break;

          case 14:
            return _context2.abrupt('return', res.json({ error: 'required', username: username, password: password, name: name, role: role }));

          case 15:
          case 'end':
            return _context2.stop();
        }
      }
    }, _callee2, undefined);
  }));

  return function register(_x, _x2, _x3) {
    return _ref.apply(this, arguments);
  };
}();

router.post('/', register);
module.exports = router;