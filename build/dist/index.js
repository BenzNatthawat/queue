'use strict';

var _express = require('express');

var _express2 = _interopRequireDefault(_express);

var _log4js = require('log4js');

var _log4js2 = _interopRequireDefault(_log4js);

var _config = require('./src/config');

var _config2 = _interopRequireDefault(_config);

var _setup = require('./src/setup');

var _setup2 = _interopRequireDefault(_setup);

var _db = require('./src/config/db');

var _db2 = _interopRequireDefault(_db);

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

require('rootpath')();

_log4js2.default.configure(require('./src/config/log'));
var app = (0, _express2.default)();
var log = _log4js2.default.getLogger('startup');
var logErr = _log4js2.default.getLogger('error');

(0, _setup2.default)(app);

var startApp = function startApp() {
  app.listen(_config2.default.port, function () {
    log.info('Application:: ' + _config2.default.app.name + ' v.' + _config2.default.app.version + ' running on port ' + _config2.default.port);
  });
};

(0, _db2.default)().then(startApp).catch(function (err) {
  logErr.error(new Error(err));
  throw new Error(err);
});