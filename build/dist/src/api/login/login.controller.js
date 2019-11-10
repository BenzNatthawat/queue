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

var login = function () {
  var _ref = _asyncToGenerator( /*#__PURE__*/_regenerator2.default.mark(function _callee(req, res, next) {
    var _req$body, username, password, db, user;

    return _regenerator2.default.wrap(function _callee$(_context) {
      while (1) {
        switch (_context.prev = _context.next) {
          case 0:
            _req$body = req.body, username = _req$body.username, password = _req$body.password;
            _context.next = 3;
            return (0, _db2.default)();

          case 3:
            db = _context.sent;

            if (!(username && password)) {
              _context.next = 8;
              break;
            }

            user = db.query('SELECT * FROM users WHERE username = \'' + username + '\'', function (err, results) {
              if (!err) {
                if (results.length > 0) {
                  var loginStatus = _bcrypt2.default.compareSync(password, results[0].password);
                  if (loginStatus) {
                    var token = (0, _signin2.default)(username);
                    return res.json({ success: 'login success', token: token, username: results[0].username, name: results[0].name });
                  }
                }
              }
              return res.json({ error: 'login failed' });
            });
            _context.next = 9;
            break;

          case 8:
            return _context.abrupt('return', res.json({ error: 'required', username: username, password: password }));

          case 9:
          case 'end':
            return _context.stop();
        }
      }
    }, _callee, undefined);
  }));

  return function login(_x, _x2, _x3) {
    return _ref.apply(this, arguments);
  };
}();

router.post('/', login);
module.exports = router;