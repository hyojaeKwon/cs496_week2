let mysql = require('mysql');
let connection = mysql.createConnection(
  {
    host:'localhost',
    user:'root',
    port:'3306',
    password : "rnjsgywo01",
    database:'main'
  }
)

connection.connect();
connection.query('SELECT * FROM users',function(error,results,fields){
  if(error){
    console.log(error);
  }
  console.log(results);

});


connection.end();