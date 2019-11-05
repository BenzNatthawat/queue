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
      debug: false,
      multipleStatements: true
    })
    con.connect(function (err) {
      if (!err) {
        console.log("Database is connected ... nn");
        db = con
      } else {
        console.log("Error connecting database ... nn");
      }
    })
  } catch (err) {
    logErr.error('Database Error Connection', new Error(err))
    throw new Error(err)
  }

  return db
}

export default loadDB
