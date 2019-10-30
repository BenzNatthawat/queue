import mongodb from 'mongodb'
// import config from '.';
// const MongoClient = mongodb.MongoClient

let db
const loadDB = async () => {
  if (db) {
    return db
  }
  try {
    db = {name:'user'}

  } catch (err) {
    throw new Error(err)
  }

  return db
}

export default loadDB
