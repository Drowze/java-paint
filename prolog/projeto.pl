/* CURSOS */
%forma: curso(Código do curso, nome_do_curso)
curso(1,egenharia_de_computacao).
curso(2,medicina).
curso(3,letras).
%curso(4,geografia).


/* materias */
%forma: materia(Código da matéria, nome_da_matéria, créditos)

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
historico(001,[(1,1,2008,4.0,0.75),(4,2,2008,5.0,0.90)]).
historico(002,[(6,5,2009,8.0,0.82),(8,5,2009,9.5,1.00),(9,6,2009,7.0,0.75)]).
historico(003,[(10,2,2008,6.0,0.75),(11,1,2008,7.0,0.93),(12,3,2009,5.0,0.75),(4,3,2009,3.0,0.42)]).
historico(004,[(10,2,2008,9.0,0.93),(11,1,2008,8.5,0.82),(12,3,2009,9.5,1.00),(13,3,2009,8.5,0.93)]).
historico(005,[(6,5,2010,5.5,0.75),(7,6,2010,3.0,0.64),(8,5,2010,7.5,0.76),(9,6,2010,8.0,0.82)]).
historico(006,[(1,1,2011,6.5,0.82),(2,1,2008,4.5,0.92),(3,2,2008,6.0,1.00),(4,1,2008,8.0,0.82),(5,1,2008,7.5,0.76),(10,2,2008,9.5,0.82)]).
historico(007,[(1,1,2008,4.0,0.82),(2,1,2008,3.5,0.62),(4,2,2008,5.0,0.76)]).

/* LOGICA */
% forma: extra(RA, curso, QUAIS)
% extra(001,1,QUAIS) .
%% extra(RA,CC,QUAIS):-historico(RA,[materia(QUAIS,_,_,_,_)|R] .
%% extra(RA,CC,QUAIS):-historico(RA,[materia(QUAIS,_,_,_,_)])|historico(QUAIS|R]) .
%% extra(RA,CC,Q):-historico(RA,(Q,_,_,_,_),[(Q,_,_,_,_)|R]) .
extra(RA,CC):-historico(RA,(CC,_,_,_,_),[(CC,_,_,_,_)|R]) .
%extra(RA,CC):-historico(RA,[(CC,_,_,_,_)]).
%% extra(RA,CC,Q):-materia(Q,_,_,_,_).

%% extra(RA,CC,QUAIS):-historico(RA,[H|T],H,T) .
%% QUAIS=[]
%materia(CC,_,_)