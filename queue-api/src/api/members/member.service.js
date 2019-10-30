import 'moment/locale/th'
import loadDB from '../../config/db'

async function getAll() {
  let results
  const db = await loadDB()
  await db.query('SELECT * FROM users', function (err, result, fields) {
    if (err) throw err
    console.log(result)
    results = result
    return
  });
  console.log(results)
  return results
}

export default {
  getAll
}
