import 'moment/locale/th'

async function getAll() {
  const users = [
    { name: 'Benz1', password: 'password1' },
    { name: 'Benz2', password: 'password2' },
    { name: 'Benz3', password: 'password3' },
    { name: 'Benz4', password: 'password4' },
  ]
  return users
}

export default {
  getAll
}
