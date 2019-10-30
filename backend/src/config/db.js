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
    var mysql_conn = mysql.createConnection({
      host: 'localhost',
      user: 'root',
      password: '',
      database: 'chadalotto'
    })
    console.log(mysql_conn)

  } catch (err) {
    logErr.error('Database Error Connection', new Error(err))
    throw new Error(err)
  }

  return db
}

export default loadDB
