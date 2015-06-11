/* CURSOS */
%forma: curso(Código do curso, nome_do_curso)
curso(1,egenharia_de_computacao).
curso(2,medicina).
curso(3,letras).
%curso(4,geografia).


/* materias */
%forma: materia(Código da matéria, nome_da_matéria)

% MATERIAS DE ENGENHARIA DE COMPUTACAO
materia(1,geometria_analitica,4).
materia(2,calculo_I,4). 
materia(3,algoritmos_de_programacao_para_computadores_1,4).
materia(4,introducao_a_programacao,4).
materia(5,introducao_a_computacao,2).

% MATERIAS DE MEDICINA
materia(6,anatomia,6).
materia(7,farmacologia,4).
materia(8,cardiologia,6).
materia(9,obstetricia,4).

% MATERIAS DE LINGUAS
materia(10,lingua_inglesa_1,4).
materia(11,lingua_portuguesa_1,6).
materia(12,educacao_cultura_e_sociedade,2).
materia(13,compreensao_e_producao_de_textos,4).

/* CURRICULO */
%forma: curriculo(Código do curso, [Código da materia, Código da materia, ...])
curriculo(1,[1,2,3,4,5]).
curriculo(2,[6,7,8,9]).
curriculo(3,[10,11,12,13]).

/* ALUNOS */
%forma: aluno(RA, Código do aluno)
aluno(001,rafael).
aluno(002,joao).
aluno(003,jose).
aluno(004,maria).
aluno(005,joaquim).
aluno(006,josevaldo).
aluno(007,josney).

/* CURSA */
%forma: cursa(RA, Código do curso)
cursa(001,1). %rafael cursa EC
cursa(002,2). %joao cursa medicina
cursa(003,3). %jose cursa letras
cursa(004,3). %maria cursa letras
cursa(005,2). %joaquim medicina
cursa(006,1). %josevaldo cursa EC
cursa(007,1). %josney cursa EC

/* HISTORICO */
%forma: historico(RA, [materia (Código, Semestre, Ano, Nota, Freq), materia(...)...])
historico(001,[materia(1,1,2008,4,0.75),materia(1,2,2008,5,0.90)])