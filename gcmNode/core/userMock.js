module.exports.MockUser = {
	matricula: '123456789',
	senha: '1234',
	nome: 'Emanuel B.',
	curso: 'Engenharia Mecatronica',
	periodo: 9,
	materias: [
		['Programacao Orientada a Objetos', 116795, 4],
		['Elementos de Automacao', 170798, 4],
		['Robotica Industrial', 169641, 4],
		['Higiene e Seguranca do Trabalho', 168921, 2],
		['Sistemas Digitais Integrados', 117391, 4],
		['Trabalho de Graduacao 1', 167681, 2]
	],
	historico: [
		['Calculo 1', 113034, 6, 'SS', true, 1],
		['Fisica 1', 118001, 4, 'MM', true, 1],
		['Fisica 1 Experimental', 118010, 2, 'MS', true, 1],
		['Computacao Basica', 116301, 6, 'MS', true, 1],
		['Quimica Geral Teorica', 114626, 4, 'MS', true, 1],
		['Quimica Experimental', 114634, 2, 'MS', true, 1],
		['Introducao a Engenharia Mecatronica', 168891, 2, 'MS', false, 1],

		['Calculo 2', 113042, 6, 'SS', true, 2],
		['Fisica 2', 118028, 4, 'MS', true, 2],
		['Fisica 2 Experimental', 118036, 4, 'MS', true, 2],
		['Desenho Mecanico Assistido por Computador 1', 168874, 6, 'MS', true, 2],
		['Probabilidade e Estatistica', 115045, 6, 'MS', true, 2],
		['Introducao a Algebra Linear', 113093, 4, 'MM', true, 2],

		['Calculo 3', 113051, 6, 'SS', true, 3],
		['Fisica 3', 118044, 4, 'MM', true, 3],
		['Fisica 3 Experimental', 118052, 4, 'MM', true, 3],
		['Estruturas de Dados', 116034, 4, 'MS', true, 3],
		['Mecanica 1', 168769, 4, 'SS', true, 3],

		['Variavel Complexa 1', 113069, 6, 'SS', true, 4],
		['Metodos Matematicos da Fisica 1', 113522, 6, 'SS', false, 4],
		['Mecanica dos Materiais 1', 169510, 4, 'MM', true, 4],
		['Mecanica 2', 168777, 4, 'MM', true, 4],
		['Circuitos Eletricos 1', 167011, 6, 'MS', true, 4],
		['Circuitos Digitais', 116351, 6, 'MS', true, 4],

		['Tecnologia de Fabricacao 1', 168831, 3, 'SS', true, 5],
		['Calculo Numerico', 113417, 4, 'SS', false, 5],
		['Introducao a Ciencia dos Materiais', 0, 3, 'MM', true, 5],
		['Circuitos Eletricos 2', 167029, 6, 'MS', true, 5],
		['Transporte de Calor e Massa', 168840, 4, 'MM', true, 5],
		['Conversao Eletromecanica de Energia', 163627, 6, 'MM', true, 5],

		['Analise Dinamica Linear', 160041, 6, 'MS', true, 6],
		['Organizacao e Arquitetura de Computadores', 116394, 4, 'SS', true, 6],
		['Ciencias do Ambiente', 122408, 2, 'MM', true, 6],
		['Tecnologias de Comando Numerico', 164399, 4, 'MS', true, 6],
		['Sistemas de Medicao', 168742, 3, 'MS', true, 6],
		['Dispositivos e Circuitos Eletronicos', 170321, 6, 'MS', true, 6],

		['Transmissao de Dados', 116424, 4, 'MM', true, 7],
		['Software Basico', 116432, 4, 'SS', true, 7],
		['Sistemas Integrados de Manufatura', 168912, 4, 'MS', true, 7],
		['Sistemas Hidraulicos e Pneumaticos', 168238, 4, 'MS', true, 7],
		['Eletronica de Potencia', 169421, 6, 'MS', false, 7],
		['Controle Dinamico', 160032, 6, 'MS', true, 7],

		['Processamento em Tempo Real', 116599, 4, 'MS', true, 8],
		['Organizacao Industrial', 181315, 4, 'MM', true, 8],
		['Controle Digital', 164887, 4, 'MS', true, 8],
		['Instrumentacao de Controle', 167347, 4, 'MS', true, 8],
		['Controle para Automacao', 167657, 4, 'MS', true, 8]
	]
}

module.exports.calculaIra = function () {
	/**
	 * DTb:disciplinas OBRIGATORIAS trancadas
	 * DTp:disciplinas OPTATIVAS trancadas
	 * DC: disciplinas matriculadas
	 * Pi:peso da mencao
	 * Pei:Periodo em que uma disciplina foi cursada
	 * CRi:crÃ©ditos de uma disciplina
	 */
	var constante;
	var disc = 0, disc2 = 0;
	var variavel;

	var Peso_mencao;
	var Periodo_disciplina;
	var Credito_disciplina;

	//--------conta alguns parametros das disciplinas
	var DTb = 0;
	var DTp = 0;
	var DC;

	// TODO calcular ira
	/*
	for (MateriaCursada materia : historico) {
		if (materia.obrigatoriaTrancada()) {
			DTp++;
		}
		if (materia.optativaTrancada()) {
			DTb++;
		}
	}

	DC = historico.size();
	constante = 1 - (((0.6 * DTb) + (0.4 * DTp)) / DC);
	//-----------------------------------------

	for (MateriaCursada materia : historico){
		Peso_mencao = materia.getPesoMencao();
		Periodo_disciplina = materia.getPeriodoCursado();
		Credito_disciplina = materia.getCreditos();

		disc = disc + (Periodo_disciplina * Peso_mencao * Credito_disciplina);
		disc2 = disc2 + (Credito_disciplina * Periodo_disciplina);
	}

	variavel = disc / disc2;
	*/
	
	return constante * variavel;
}