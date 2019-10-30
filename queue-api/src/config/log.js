let thisYear = new Date().getFullYear();
let thisMonth = ('0' + (new Date().getMonth() + 1).toString()).slice(-2);
module.exports = {
  "appenders": {
    "out": {
      "type": "console"
    },
    "default": {
      "type": "dateFile",
      "filename": `logs/${thisYear}/${thisMonth}/log`,
      "pattern": "-yyyy-MM-dd.log",
      "alwaysIncludePattern": true
    },
    "error": {
      "type": "dateFile",
      "filename": `logs/${thisYear}/${thisMonth}/error`,
      "pattern": ".log",
      "alwaysIncludePattern": true
    }
  },
  "categories": {
    "default": {
      "appenders": ["out", "default"],
      "level": "info"
    },
    "error": {
      "appenders": ["error"],
      "level": "error"
    }
  }
}
