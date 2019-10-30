const errorHandler = (err, req, res, next) => {  
  if (typeof (err) === 'string') {
    return res.status(400).json({ 'errors': { 'message': err } })
  }

  if (err.name === 'UnauthorizedError') {
    return res.status(401).json({ 'errors': { 'message': 'No authorization token was found !' } })
  }

  if (err.message.indexOf('MongoError') > -1) {
    if (err.message.indexOf('E11000') > -1) {
      return res.status(500).json({ 'errors': { 'message': 'Duplicate Key' } })
    }
  }
  return res.status(err.status || 500).json({ 'errors': { 'message': err.message, 'code': err.code} })
}

export default errorHandler
