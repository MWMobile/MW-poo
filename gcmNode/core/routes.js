module.exports = function (app) {
	
	// Mock do que vai ser o DB no middleserver
	
	var User = require('./userMock.js')

	var user = User.MockUser
	User.calculaIra()

	var crypto = require('crypto')

	function createToken() {
		return crypto.createHash('md5').update(user.toString()).digest('hex')
	}
	var globalToken = createToken();
	console.log(globalToken)
	// Fim do mock do db

	//Funcao de autenticacao provisoria
	function auth(req, res, next) {
		console.log(req.headers)
		if (req.headers.token == globalToken)
			next()
		else
			res.sendStatus(401)
	}

	//Rota que retorna o que lhe foi enviado, para teste
	app.post('/echo', function (req, res) {
		res.json(req.body)
	})

	//Procura o usuario no DB de usuarios e retorna seu token de autentiacacao
	//Token provisorio
	app.post('/login', function (req, res) {
		if (req.body.matricula == user.matricula)
			if (req.body.senha == user.senha)
				res.json({
					token: globalToken,
					user: user
				})
			else
				res.sendStatus(401)
		else
			res.sendStatus(401)

	})

	app.get('/getCurso', auth, function (req, res) {
		res.json({
			curso: user.curso
		})
	})

	app.get('/getNome', auth, function (req, res) {
		res.json({
			nome: user.nome
		})
	})

	app.get('/getPeriodo', auth, function (req, res) {
		res.json({
			periodo: user.periodo
		})
	})

	app.get('/getMaterias', auth, function (req, res) {
		res.json({
			materias: user.materias
		})
	})

	app.get('/getHistorico', auth, function (req, res) {
		res.json({
			historico: user.historico
		})
	})

	app.get('/getUser', auth, function (req, res) {
		res.json({
			user: user
		})
	})

	// TODO Andrei: implementar getIra
	// app.get('/getIra', function(req, res){
	// ...		 
	// }) 

}