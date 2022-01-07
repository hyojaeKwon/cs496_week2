const express = require('express');
const app = express();
const PORT = 80;
const db = require('./config/db');


app.get('/users',(req,res) => {
  db.connect()
  queryState = `SELECT JSON_OBJECT('id',id,'name', name) FROM users`;
  db.query(queryState, (error, data) => {

    if(!error){
      res.send(data);
      console.log(data);
    } 
    else res.send(error);
  });
});

app.listen(PORT, () => {
  console.log(`Server On : http://172.10.5.75:${PORT}/`);
})
