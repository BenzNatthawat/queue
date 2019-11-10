'use strict';

var _regenerator = require('babel-runtime/regenerator');

var _regenerator2 = _interopRequireDefault(_regenerator);

var _express = require('express');

var _express2 = _interopRequireDefault(_express);

var _db = require('../../config/db');

var _db2 = _interopRequireDefault(_db);

var _moment = require('moment');

var _moment2 = _interopRequireDefault(_moment);

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

function _asyncToGenerator(fn) { return function () { var gen = fn.apply(this, arguments); return new Promise(function (resolve, reject) { function step(key, arg) { try { var info = gen[key](arg); var value = info.value; } catch (error) { reject(error); return; } if (info.done) { resolve(value); } else { return Promise.resolve(value).then(function (value) { step("next", value); }, function (err) { step("throw", err); }); } } return step("next"); }); }; }

var router = _express2.default.Router();

var index = function () {
  var _ref = _asyncToGenerator( /*#__PURE__*/_regenerator2.default.mark(function _callee2(req, res, next) {
    var db;
    return _regenerator2.default.wrap(function _callee2$(_context2) {
      while (1) {
        switch (_context2.prev = _context2.next) {
          case 0:
            _context2.next = 2;
            return (0, _db2.default)();

          case 2:
            db = _context2.sent;
            _context2.next = 5;
            return db.query('SELECT queueNumber, technicians__id FROM queues ORDER BY id DESC LIMIT 1', function () {
              var _ref2 = _asyncToGenerator( /*#__PURE__*/_regenerator2.default.mark(function _callee(err, results) {
                return _regenerator2.default.wrap(function _callee$(_context) {
                  while (1) {
                    switch (_context.prev = _context.next) {
                      case 0:
                        if (!err) {
                          _context.next = 2;
                          break;
                        }

                        throw err;

                      case 2:
                        return _context.abrupt('return', res.json(results));

                      case 3:
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

          case 5:
          case 'end':
            return _context2.stop();
        }
      }
    }, _callee2, undefined);
  }));

  return function index(_x, _x2, _x3) {
    return _ref.apply(this, arguments);
  };
}();

var create = function () {
  var _ref3 = _asyncToGenerator( /*#__PURE__*/_regenerator2.default.mark(function _callee6(req, res, next) {
    var comment, db;
    return _regenerator2.default.wrap(function _callee6$(_context6) {
      while (1) {
        switch (_context6.prev = _context6.next) {
          case 0:
            comment = req.body.comment;
            _context6.next = 3;
            return (0, _db2.default)();

          case 3:
            db = _context6.sent;
            _context6.next = 6;
            return db.query('SELECT queueNumber, createdAt, technicians__id FROM queues ORDER BY id DESC LIMIT 1; SELECT id, name, role, status FROM users WHERE role = \'technician\' AND status = 1; SELECT id FROM users WHERE username = \'' + req.decoded.username + '\';', function () {
              var _ref4 = _asyncToGenerator( /*#__PURE__*/_regenerator2.default.mark(function _callee5(err, results) {
                var userId, queueNumber, technicians__id, techniciansObj, nextId;
                return _regenerator2.default.wrap(function _callee5$(_context5) {
                  while (1) {
                    switch (_context5.prev = _context5.next) {
                      case 0:
                        if (!err) {
                          _context5.next = 2;
                          break;
                        }

                        throw err;

                      case 2:
                        userId = results[2][0].id;
                        queueNumber = void 0, technicians__id = void 0;
                        techniciansObj = JSON.parse(JSON.stringify(results[1]));

                        if (results[0].length === 0) {
                          queueNumber = 1;
                          technicians__id = results[1][0].id;
                        } else {
                          nextId = void 0;

                          if ((0, _moment2.default)(results[0][0].createdAt).format("YYYY-MM-DD") === (0, _moment2.default)(new Date()).format("YYYY-MM-DD")) {
                            nextId = techniciansObj.findIndex(function (tec) {
                              return tec.id == results[0][0].technicians__id;
                            });
                          } else {
                            nextId = 1;
                          }
                          queueNumber = Number(results[0][0].queueNumber) + 1;
                          if (nextId < techniciansObj.length - 1) technicians__id = results[1][nextId + 1].id;else {
                            technicians__id = results[1][0].id;
                          }
                        }
                        _context5.next = 8;
                        return db.query('INSERT INTO queues (queueNumber, comment, technicians__id, users__id) VALUES (' + queueNumber + ', \'' + comment + '\', ' + technicians__id + ', ' + userId + ')', function () {
                          var _ref5 = _asyncToGenerator( /*#__PURE__*/_regenerator2.default.mark(function _callee4(err, results) {
                            return _regenerator2.default.wrap(function _callee4$(_context4) {
                              while (1) {
                                switch (_context4.prev = _context4.next) {
                                  case 0:
                                    if (!err) {
                                      _context4.next = 2;
                                      break;
                                    }

                                    throw err;

                                  case 2:
                                    _context4.next = 4;
                                    return db.query('SELECT queues.queueNumber, queues.comment, users.name FROM queues INNER JOIN users ON users.id = queues.technicians__id WHERE queues.id = ' + results.insertId, function () {
                                      var _ref6 = _asyncToGenerator( /*#__PURE__*/_regenerator2.default.mark(function _callee3(err, results) {
                                        return _regenerator2.default.wrap(function _callee3$(_context3) {
                                          while (1) {
                                            switch (_context3.prev = _context3.next) {
                                              case 0:
                                                if (!err) {
                                                  _context3.next = 2;
                                                  break;
                                                }

                                                throw err;

                                              case 2:
                                                return _context3.abrupt('return', res.json(results[0]));

                                              case 3:
                                              case 'end':
                                                return _context3.stop();
                                            }
                                          }
                                        }, _callee3, undefined);
                                      }));

                                      return function (_x13, _x14) {
                                        return _ref6.apply(this, arguments);
                                      };
                                    }());

                                  case 4:
                                  case 'end':
                                    return _context4.stop();
                                }
                              }
                            }, _callee4, undefined);
                          }));

                          return function (_x11, _x12) {
                            return _ref5.apply(this, arguments);
                          };
                        }());

                      case 8:
                      case 'end':
                        return _context5.stop();
                    }
                  }
                }, _callee5, undefined);
              }));

              return function (_x9, _x10) {
                return _ref4.apply(this, arguments);
              };
            }());

          case 6:
          case 'end':
            return _context6.stop();
        }
      }
    }, _callee6, undefined);
  }));

  return function create(_x6, _x7, _x8) {
    return _ref3.apply(this, arguments);
  };
}();

router.get('/', index).post('/create', create);
module.exports = router;