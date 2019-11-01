import mysql from 'mysql'
import log4js from 'log4js'
import config from '.';
const logErr = log4js.getLogger('error')

let db
const loadDB = async () => {
  if (db) {
    return db
  }
  try {
    var con = mysql.createConnection({
      host: config.db.host,
      database: config.db.dbname,
      user: config.db.name,
      password: config.db.password,
    });
    con.connect(function (err) {
      if (err) throw err;
      console.log('Connected Success!')
      db = con
    });
  } catch (err) {
    logErr.error('Database Error Connection', new Error(err))
    throw new Error(err)
  }

  return db
}

export default loadDB
