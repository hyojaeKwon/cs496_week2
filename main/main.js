const { doesNotThrow } = require('assert');
const exp = require('constants');
const express = require('express');
const req = require('express/lib/request');
const res = require('express/lib/response');
const { stringify } = require('querystring');
const app = express();
const PORT = 80;
const db = require('./config/db');


app.use(express.json());
app.use(express.urlencoded({extended:true}));

//로그인시 사용
app.post('/login',async (req,res) => {
  let que0 = `insert into user values (?,?,?,?,?,null,null,?)`;
  let que1 = `insert into user_language values (?,?,?,?)`
  let paramLanguage = [req.body.Uid,req.body.lang1,req.body.lang2,req.body.lang3];
  let params = [req.body.Uid,req.body.Upw,req.body.Uname,req.body.Usay,req.body.Utype,req.body.github];
  
  // if(isThere==false){
  //   return;
  // }
  
  await db.query(que0,params,
    (err,rows,fields)=>{
    try{
      res.send.apply(rows);
    }catch{
      console.log("중복 아이디!")
    }
  })
  await db.query(que1,paramLanguage,(err,rows,fields)=> {
    // if(!err){
    //   res.send.apply(rows);
    // }
    try{
      res.send.apply(rows);
    } catch{
      console.log(err)
    }
  });
});

//사용자 정보 주는 api
app.get('/user',(req,res) => {
  queryState = `SELECT Uid,Uname,Usay,Utype,github,Llang1,Llang2,Llang3 FROM user,user_language where Uid=Lid`;
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

queryState = `SELECT * FROM user join user_language where Uid=Lid and Uid="` + String(req.params.id)+`";`;
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
app.get('/ideas/:Iid', async (req,res) => {
  let id = [req.params.Iid];
  id = String(id);
  var returnList = [];

  let que = `select * from ideas where Iid = ` + id; 

    console.log(returnList);
    db.query(que, async (error, data) => {
      if(!error){
        // console.log(data[0]);
        returnList.push(data);
        // console.log(data[0]);
      } else if(data == []){
        return null;
      } else{
        return null;
      }
      
      que = `select PUid from participant where PIid =` + id;
      db.query(que,async (error,data) => {
        if(!error){
          // console.log(data)
          returnList.push(data);
        } else if (data == []) {
          return null;
        } else {
          return null;
        }
        res.send(returnList);

      });  
  
    })
    

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

function isThere(name){

  let ids;
  db.query(`select Uid from user`,(error,data) => {
    if(!error){
      ids = data[0];
    }
  });

  console.log(ids);
  for(i=0;i<ids.length;i++){
    if(name == ids[i]){
      return false;
    }
  }
  return true;
}


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
app.post( '/ideas/create',async (req,res) => {
  let sql = 'insert into ideas values (null,?,?,?)';

  let {title, description, id, frontEnd, backEnd} = req.body;  
  let params = [title,description,id];

  //디비 저장
  var nowId;
  //ideas에서 Iid가져오기
  var hi = await sequence(sql,params,res,backEnd,frontEnd);
});

 function sequence(sql,param,res,backEnd,frontEnd){
  postCall(sql,param,res,backEnd,frontEnd);
  
  // var id = await postCall(sql,param,res);
  // var id = await getIdeaId(sql2,res);
  // var result = await postIdeaStack(id,backEnd,frontEnd,res);
}

async function postCall(sql,param,res,backEnd,frontEnd){
  db.query(sql,param,(err,rows,fields) => {
      try{
        res.send(rows);
      }catch(err){
        console.log(err);
      }
      getIdeaId(sql,res,backEnd,frontEnd);
    });
}

function getIdeaId(sql,rest,bE,fE){
  var nowId;
  let sql2 = "select Iid from ideas order by Iid desc limit 1";
  db.query(sql2, async (req,resp) => {
    console.log(resp);
    try{
      nowId = resp[0].Iid;
    }catch(err){
      console.log(err);
      nowId = -1;
    }
    postIdeaStack(nowId,bE,fE,rest);
  });
}

function postIdeaStack(nowId,bE,fE,res){
  sql = `insert into idea_stack values (?,?)`
  console.log(fE)
  if(fE!=undefined){
    let param=[nowId,fE];
    console.log(param)
    db.query(sql,param),
      function (err,rows,fields) {
      try{
        res.send.apply(rows)
      }catch(err){
        console.log(err);
      }
    };
  }
  if(bE!=undefined){
    let param=[nowId,bE];
    db.query(sql,param,(err,rows,fields) => {
      try{
        res.send.apply(rows)
      }catch(err){
        console.log(err);
      }
    });
  }
}





//matching api
app.get('/match/:id',(req,res)=>{
  console.log("1")
  let useId = req.params.id;
  isType(useId,res);
});

//type 리턴하는 코드
function isType(id,res){
  console.log("2")
  var url = `select Utype from user where Uid="`+String(id)+`"`;
  db.query(url,(error,data)=>{
    try{
      match(data[0].Utype,res);
    } catch(error){
      console.log(error)
    }
  })
}
//매칭 시켜주는 프로그램
function match(type,res){

  let url = `select distinct Uid,Uname,Usay,Utype,github,Llang1,Llang2,Llang3 from user left outer join ideas on user.Uid = ideas.IauthorId and Iid = NULL and Utype=`+String(type)+` join user_language on Lid=Uid`;
  db.query(url,(error,data)=>{
    try{
      res.send(data);
    } catch {
      res.send(error);
    }  
  })
}
app.listen(PORT, () => {
  console.log(`Server On : http://192.249.18.118:${PORT}/`);
})
