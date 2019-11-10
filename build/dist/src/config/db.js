'use strict';

Object.defineProperty(exports, "__esModule", {
  value: true
});

var _regenerator = require('babel-runtime/regenerator');

var _regenerator2 = _interopRequireDefault(_regenerator);

var _mysql = require('mysql');

var _mysql2 = _interopRequireDefault(_mysql);

var _log4js = require('log4js');

var _log4js2 = _interopRequireDefault(_log4js);

var _ = require('.');

var _2 = _interopRequireDefault(_);

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

function _asyncToGenerator(fn) { return function () { var gen = fn.apply(this, arguments); return new Promise(function (resolve, reject) { function step(key, arg) { try { var info = gen[key](arg); var value = info.value; } catch (error) { reject(error); return; } if (info.done) { resolve(value); } else { return Promise.resolve(value).then(function (value) { step("next", value); }, function (err) { step("throw", err); }); } } return step("next"); }); }; }

var logErr = _log4js2.default.getLogger('error');

var db = void 0;
var loadDB = function () {
  var _ref = _asyncToGenerator( /*#__PURE__*/_regenerator2.default.mark(function _callee() {
    var con;
    return _regenerator2.default.wrap(function _callee$(_context) {
      while (1) {
        switch (_context.prev = _context.next) {
          case 0:
            if (!db) {
              _context.next = 2;
              break;
            }

            return _context.abrupt('return', db);

          case 2:
            _context.prev = 2;
            con = _mysql2.default.createConnection({
              host: _2.default.db.host,
              database: _2.default.db.dbname,
              user: _2.default.db.name,
              password: _2.default.db.password,
              debug: false,
              multipleStatements: true
            });

            con.connect(function (err) {
              if (!err) {
                console.log("Database is connected ... nn");
                db = con;
              } else {
                console.log("Error connecting database ... nn");
              }
            });
            _context.next = 11;
            break;

          case 7:
            _context.prev = 7;
            _context.t0 = _context['catch'](2);

            logErr.error('Database Error Connection', new Error(_context.t0));
            throw new Error(_context.t0);

          case 11:
            return _context.abrupt('return', db);

          case 12:
          case 'end':
            return _context.stop();
        }
      }
    }, _callee, undefined, [[2, 7]]);
  }));

  return function loadDB() {
    return _ref.apply(this, arguments);
  };
}();

exports.default = loadDB;