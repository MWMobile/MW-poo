var express = require('express')
var http = require('http')
var bodyParser = require('body-parser')

var app = express()
var httpServer = http.createServer(app)


app.use(bodyParser.json())
app.use(bodyParser.urlencoded({
    extended: true
}))

app.post('/echo', function (req, res) {
    res.json(req.body)
})

httpServer.listen(3000)

console.log('Server running at port 3000')
