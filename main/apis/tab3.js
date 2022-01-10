let url = `select distinct Uid from user left outer join ideas on user.Uid = ideas.IauthorId and Iid = NULL and Utype !=`;

const express = require('express');
const db = require('./config/db');

function
db.query(url)