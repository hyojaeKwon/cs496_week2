const { doesNotThrow } = require('assert');
const exp = require('constants');
const express = require('express');
const app = express();
const PORT = 80;
const db = require('./config/db');


app.use(express.json());
app.use(express.urlencoded({extended:true}));

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
      } 
    else res.send(error);
  });
});


//사용자 심층 정보 api
app.get('/user/:id',async (req,res)=> {
  console.log(req.params.id);

queryState = `SELECT * FROM user where Uid="` + String(req.params.id)+`";`;
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

//idea post api
app.post( '/ideas/create',(req,res) => {
  let sql = 'insert into ideas values (null,?,?,?)';

  console.log(req.body);
  let Ititle = req.body.title;
  let Idescription = req.body.description;
  let IauthorId = req.body.id;
  let stack = req.body.arr;

  console.log(stack);
  
  let params = [Ititle,Idescription,IauthorId];
  
  //디비 저장
  db.query(sql,params,
    (err,rows,fields) => {
      try{
        res.send(rows);
      }catch{
        // console.log(err);
      }
    }
    );
  
  var nowId;
  //ideas에서 Iid가져오기
  sql = "select Iid from ideas order by Iid desc limit 1";

  db.query(sql, (req,res) => {
    if(!req){
      nowId = res[0].Iid;      
    } else {
      nowId = -1;
    }
  });

  console.log()
  //디비 저장
  for(let i= 0 ; i < stack.length ; i++ ){
    let param=[nowId,stack[i]];
    sql = `insert into ideas values (?,?)`
    db.query(sql,param,(err,rows,fields) => {
      if(err){
        console.log(err);
      }
    })
  }
  

});

app.listen(PORT, () => {
  console.log(`Server On : http://192.249.18.118:${PORT}/`);
})
