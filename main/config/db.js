var mysql = require('mysql');
const db = mysql.createConnection({
  host : 'localhost',
  user : 'root',
  password : 'rnjsgywo01',
  port: 3306,
  database : 'main'
});

module.exports = db;