module.exports.MockUser = {
	matricula: '123456789',
	senha: '1234',
	nome: 'Emanuel B.',
	curso: 'Engenharia Mecatronica',
	periodo: 9,
	materias: [
		{nome:'Programacao Orientada a Objetos', codigo:116795, creditos:4},
		{nome:'Elementos de Automacao', codigo:170798, creditos:4},
		{nome:'Robotica Industrial', codigo:169641, creditos:4},
		{nome:'Higiene e Seguranca do Trabalho', codigo:168921, creditos:2},
		{nome:'Sistemas Digitais Integrados', codigo:117391, creditos:4},
		{nome:'Trabalho de Graduacao 1', codigo:167681, creditos:2}
	],
	historico: [
		{nome:'Calculo 1', codigo:113034, creditos:6, mencaoTag:'SS', obrigatoria:true, periodoTerminado:1},
		{nome:'Fisica 1', codigo:118001, creditos:4, mencaoTag:'MM', obrigatoria:true, periodoTerminado:1},
		{nome:'Fisica 1 Experimental', codigo:118010, creditos:2, mencaoTag:'MS', obrigatoria:true, periodoTerminado:1},
		{nome:'Computacao Basica', codigo:116301, creditos:6, mencaoTag:'MS', obrigatoria:true, periodoTerminado:1},
		{nome:'Quimica Geral Teorica', codigo:114626, creditos:4, mencaoTag:'MS', obrigatoria:true, periodoTerminado:1},
		{nome:'Quimica Experimental', codigo:114634, creditos:2, mencaoTag:'MS', obrigatoria:true, periodoTerminado:1},
		{nome:'Introducao a Engenharia Mecatronica', codigo:168891, creditos:2, mencaoTag:'MS', obrigatoria:false, periodoTerminado:1},

		{nome:'Calculo 2', codigo:113042, creditos:6, mencaoTag:'SS', obrigatoria:true, periodoTerminado:2},
		{nome:'Fisica 2', codigo:118028, creditos:4, mencaoTag:'MS', obrigatoria:true, periodoTerminado:2},
		{nome:'Fisica 2 Experimental', codigo:118036, creditos:4, mencaoTag:'MS', obrigatoria:true, periodoTerminado:2},
		{nome:'Desenho Mecanico Assistido por Computador 1', codigo:168874, creditos:6, mencaoTag:'MS', obrigatoria:true, periodoTerminado:2},
		{nome:'Probabilidade e Estatistica', codigo:115045, creditos:6, mencaoTag:'MS', obrigatoria:true, periodoTerminado:2},
		{nome:'Introducao a Algebra Linear', codigo:113093, creditos:4, mencaoTag:'MM', obrigatoria:true, periodoTerminado:2},

		{nome:'Calculo 3', codigo:113051, creditos:6, mencaoTag:'SS', obrigatoria:true, periodoTerminado:3},
		{nome:'Fisica 3', codigo:118044, creditos:4, mencaoTag:'MM', obrigatoria:true, periodoTerminado:3},
		{nome:'Fisica 3 Experimental', codigo:118052, creditos:4, mencaoTag:'MM', obrigatoria:true, periodoTerminado:3},
		{nome:'Estruturas de Dados', codigo:116034, creditos:4, mencaoTag:'MS', obrigatoria:true, periodoTerminado:3},
		{nome:'Mecanica 1', codigo:168769, creditos:4, mencaoTag:'SS', obrigatoria:true, periodoTerminado:3},

		{nome:'Variavel Complexa 1', codigo:113069, creditos:6, mencaoTag:'SS', obrigatoria:true, periodoTerminado:4},
		{nome:'Metodos Matematicos da Fisica 1', codigo:113522, creditos:6, mencaoTag:'SS', obrigatoria:false, periodoTerminado:4},
		{nome:'Mecanica dos Materiais 1', codigo:169510, creditos:4, mencaoTag:'MM', obrigatoria:true, periodoTerminado:4},
		{nome:'Mecanica 2', codigo:168777, creditos:4, mencaoTag:'MM', obrigatoria:true, periodoTerminado:4},
		{nome:'Circuitos Eletricos 1', codigo:167011, creditos:6, mencaoTag:'MS', obrigatoria:true, periodoTerminado:4},
		{nome:'Circuitos Digitais', codigo:116351, creditos:6, mencaoTag:'MS', obrigatoria:true, periodoTerminado:4},

		{nome:'Tecnologia de Fabricacao 1', codigo:168831, creditos:3, mencaoTag:'SS', obrigatoria:true, periodoTerminado:5},
		{nome:'Calculo Numerico', codigo:113417, creditos:4, mencaoTag:'SS', obrigatoria:false, periodoTerminado:5},
		{nome:'Introducao a Ciencia dos Materiais', codigo:0, creditos:3, mencaoTag:'MM', obrigatoria:true, periodoTerminado:5},
		{nome:'Circuitos Eletricos 2', codigo:167029, creditos:6, mencaoTag:'MS', obrigatoria:true, periodoTerminado:5},
		{nome:'Transporte de Calor e Massa', codigo:168840, creditos:4, mencaoTag:'MM', obrigatoria:true, periodoTerminado:5},
		{nome:'Conversao Eletromecanica de Energia', codigo:163627, creditos:6, mencaoTag:'MM', obrigatoria:true, periodoTerminado:5},

		{nome:'Analise Dinamica Linear', codigo:160041, creditos:6, mencaoTag:'MS', obrigatoria:true, periodoTerminado:6},
		{nome:'Organizacao e Arquitetura de Computadores', codigo:116394, creditos:4, mencaoTag:'SS', obrigatoria:true, periodoTerminado:6},
		{nome:'Ciencias do Ambiente', codigo:122408, creditos:2, mencaoTag:'MM', obrigatoria:true, periodoTerminado:6},
		{nome:'Tecnologias de Comando Numerico', codigo:164399, creditos:4, mencaoTag:'MS', obrigatoria:true, periodoTerminado:6},
		{nome:'Sistemas de Medicao', codigo:168742, creditos:3, mencaoTag:'MS', obrigatoria:true, periodoTerminado:6},
		{nome:'Dispositivos e Circuitos Eletronicos', codigo:170321, creditos:6, mencaoTag:'MS', obrigatoria:true, periodoTerminado:6},

		{nome:'Transmissao de Dados', codigo:116424, creditos:4, mencaoTag:'MM', obrigatoria:true, periodoTerminado:7},
		{nome:'Software Basico', codigo:116432, creditos:4, mencaoTag:'SS', obrigatoria:true, periodoTerminado:7},
		{nome:'Sistemas Integrados de Manufatura', codigo:168912, creditos:4, mencaoTag:'MS', obrigatoria:true, periodoTerminado:7},
		{nome:'Sistemas Hidraulicos e Pneumaticos', codigo:168238, creditos:4, mencaoTag:'MS',obrigatoria:true, periodoTerminado:7},
		{nome:'Eletronica de Potencia', codigo:169421, creditos:6, mencaoTag:'MS', obrigatoria:false, periodoTerminado:7},
		{nome:'Controle Dinamico', codigo:160032, creditos:6, mencaoTag:'MS', obrigatoria:true, periodoTerminado:7},

		{nome:'Processamento em Tempo Real', codigo:116599, creditos:4, mencaoTag:'MS', obrigatoria:true, periodoTerminado:8},
		{nome:'Organizacao Industrial', codigo:181315, creditos:4, mencaoTag:'MM', obrigatoria:true, periodoTerminado:8},
		{nome:'Controle Digital', codigo:164887, creditos:4, mencaoTag:'MS', obrigatoria:true, periodoTerminado:8},
		{nome:'Instrumentacao de Controle', codigo:167347, creditos:4, mencaoTag:'MS', obrigatoria:true, periodoTerminado:8},
		{nome:'Controle para Automacao', codigo:167657, creditos:4, mencaoTag:'MS', obrigatoria:true, periodoTerminado:8}
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