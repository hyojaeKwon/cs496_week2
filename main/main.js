const express = require('express');
const app = express();
const PORT = 80;
const db = require('./config/db');



//사용자 정보 주는 api
app.get('/user',(req,res) => {
  queryState = `SELECT Uid,Uname,Usay,github,Llang1,Llang2,Llang3 FROM user,user_language where Uid=Lid`;
  db.query(queryState, (error, data) => {
    var list = new Array();
    list.push(data);
    var resultData = new Object();
    resultData.user = data;

    if(!error){
      res.send(data);
      console.log(data);
    } 
    else res.send(error);
  });
});


//사용자 심층 정보 api
app.get('/userd/',async (req,res)=> {
  
  console.log(req.query.id);

queryState = `SELECT * FROM deep_users where id="` + String(req.query.id)+`"`;
console.log(queryState)
db.query(queryState,(error,data) => {
  if(!error){
    res.send(data);
  }
  else{
    res.send(error);
  }
  });
});


//get ideas
app.get('/ideas/', async(req,res) => {
  queryState = `select * from ideas order by Iid desc`;
  db.query(queryState,(error,data) => {
    if(!error){
      res.send(data);
    }
    else{
      res.send(error)
    }
  })
})



app.listen(PORT, () => {
  console.log(`Server On : http://192.249.18.118:${PORT}/`);
})
