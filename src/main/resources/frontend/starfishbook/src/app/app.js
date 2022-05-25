const express = require('express');
const app = express();

app.get('/notes', (req,res) =>{})

app.post('/notes', (req,res) => {})

app.patch('/notes/:id', (req,res) => {})

app.delete('/notes/:id', (req,res) => {})


app.use(function(req, res, next) {
  res.header("Access-Control-Allow-Origin", "YOUR-DOMAIN.TLD"); // update to match the domain you will make the request from
  res.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
  res.header("Access-Control-Allow-Methods: GET, POST, PATCH, PUT, DELETE, OPTIONS");
  next();
});