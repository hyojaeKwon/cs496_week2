const { doesNotThrow } = require('assert');
const exp = require('constants');
const express = require('express');
const { stringify } = require('querystring');
const app = express();
const PORT = 80;
const db = require('./config/db');


app.use(express.json());
app.use(express.urlencoded({extended:true}));

app.listen(PORT, () => {
  console.log(`Server On : http://192.249.18.118:${PORT}/`);
})


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

//get ideas detail
app.get('/ideas/:Iid', async(req,res) => {
  let id = [req.params.Iid];
  id = String(id);
  console.log(id);
  var returnObj = new Object();

  let que = `select * from ideas where Iid = ` + id; 

  console.log(que);

  db.query(que, async (error, data) => {
    if(!error){
      console.log(data);
      returnObj.info = data[0];
    } else if(data == []){
      returnObj.info = null;
    } else{
      returnObj.info= null;
    }
  })

  que = `select PUid from participant where PIid =` + id;
  console.log(que);
  db.query(que, async (error,data) => {
    if(!error){
      console.log(data)
      returnObj['participantInfo'] = data[0];
    } else if (data == []) {
      returnObj['participantInfo'] = "none";
    } else {
      returnObj['participantInfo'] = "error";
    }
  });

  res.send(returnObj)
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

app.get('/hi',async(req,res) => {
  res.send('아자아자 화이팅!!');
})

//idea에 같이하는 사람 넣기
app.post('/ideas/participant',(req,res)=> {

  let sql = `insert into participant values (?,?)`;
  let params = [req.body.Iid,req.body.participant];
  console.log(sql);

  console.log(params);
  db.query(sql,params,
    (err,rows,fields) => {
    try{
      res.send.apply(rows);
    } catch {
      // console.log(err);
    }
  });
});

//idea post api
app.post( '/ideas/create',(req,res) => {
  let sql = 'insert into ideas values (null,?,?,?)';

  let Ititle = req.body.title;
  let Idescription = req.body.description;
  let IauthorId = req.body.id;
  let fE = req.body.frontEnd;
  let bE = req.body.backEnd;
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

  db.query(sql, async (req,res) => {
    if(!req){
      // console.log(res);
      nowId = res[0].Iid;
      // console.log(res[0].Iid);      
    } else {
      nowId = -1;
    }
    postIdeaStack(nowId,bE,fE)
  });    
});

function postIdeaStack(nowId,bE,fE){
  console.log(nowId);
  sql = `insert into idea_stack values (?,?)`
  
  if(fE!=undefined){
    let param=[nowId,fE];
    db.query(sql,param),
      function (err,rows,fields) {
      if(err){
        res.send(rows);
      }
    };
  }
  if(bE!=undefined){
    let param=[nowId,bE];
    db.query(sql,param,(err,rows,fields) => {
      if(err){
        res.send(rows);
      }
    });
  }
}

// app.get('/ideas/:Iid', async(req,res) => {
//   let id = [req.params.Iid];
//   id = String(id);

//   let que = `select * from ideas where iId =` + id;

//   db.query(que,(error, data) => {
//     if(!error){
//       res.send(data);
//     }else{
//       res.send(error);
//     }
//   })
// })