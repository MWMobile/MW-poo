module.exports = function (app) {
	
	// Mock do que vai ser o DB no middleserver
	
	var User = require('./userMock.js')

	var user = User.MockUser
	User.calculaIra()

	var crypto = require('crypto')

	function createToken() {
		return crypto.createHash('md5').update(user.toString()).digest('hex')
	}
	// Fim do mock do db

	//Rota que retorna o que lhe foi enviado, para teste
	app.post('/echo', function (req, res) {
		res.json(req.body)
	})

	//Procura o usuario no DB de usuarios e retorna seu token de autentiacacao
	//Token provisorio
	app.post('/login', function (req, res) {
		if (req.body.matricula == user.matricula)
			if (req.body.senha == user.senha)
				res.json({ token: createToken() })
			else
				res.sendStatus(401)
		else
			res.sendStatus(401)

	})

	app.get('/getCurso', function (req, res) {
		res.json({
			curso: user.curso
		})
	})

	app.get('/getNome', function (req, res) {
		res.json({
			materias: user.nome
		})
	})

	app.get('/getPeriodo', function (req, res) {
		res.json({
			materias: user.periodo
		})
	})
	
	app.get('/getMaterias', function (req, res) {
		res.json({
			materias: user.materias
		})
	})
	
	app.get('/getHistorico', function (req, res) {
		res.json({
			materias: user.historico
		})
	})

	// TODO
	// app.get('/getIra', function(req, res){
	// ...		 
	// }) 

}