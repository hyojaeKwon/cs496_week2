var mysql = require('mysql');
const db = mysql.createConnection({
  host : 'localhost',
  user : 'root',
  password : config.pass,
  port: 3306,
  database : 'program'
});

module.exports = db;