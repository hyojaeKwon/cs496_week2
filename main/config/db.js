var mysql = require('mysql');
var options = require('./option');
var loginData = {
  user: options.storageConfig.where,
  password : options.storageConfig.pass
}
const db = mysql.createConnection({
  host : 'localhost',
  user : loginData.user,
  password : loginData.password,
  port: 3306,
  database : 'program'
});

module.exports = db;