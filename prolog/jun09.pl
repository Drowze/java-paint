%ANOTAÇÕES DA AULA DE HOJE:

%irmaoTotal(irmao,deQuemEleEhIrmao)
irmaoTotal(I,X):-nasceu(I,m,P,M),nasceu(X,_,P,M),I\==X.
%irmaoPPai(irmao,deQuemEleEhIrmao)
irmaoPPai(I,X):-nasceu(I,m,P,M1),nasceu(x,_,P,M2),M1\==M2.
%irmaoPMae(irmao,deQuemEleEhIrmao)
irmaoPMae(I,X):-nasceu(I,m,P1,M),nasceu(x,_,P2,M),P1\==P2.
%meioIrmao(irmao,deQuemEleEhIrmao)
meioIrmao(I,X):-irmaoPPai(I,X).
meioIrmao(I,X):-irmaoPMae(I,X).
%irmao(irmao,deQuemEleEhIrmao)
irmao(I,X):-irmaoTotal(I,X).
irmao(I,X):-meioIrmao(I,X).
---------------
?- irmao(daniel,tiago).
yes

?- irmao(tiago,daniel).
yes

?- irmao(daniel,QUEM).
QUEM=tiago ;
no

?- irmao(Quem,daniel).
QUEM=tiago ;
no

?- irmao(QUEM,fulano).
?- irmao(fulano,QUEM).
---------------

% \== @> @< @>= @=<
% fatorial(nroDoQualSeQuerCalcularFatorial,fatorialDele)
fatorial(0,1).
fatorial(1,1).
fatorial(X,F):-X@>1,PX is X-1,fatorial(PX,FP),F is X*FP.
--------------
?- fatorial(5,QTO).
QTO=120.
---------------

%qtosElems(umaLista,quantidadeDeElementosDela)
qtosElems([],0).
qtosElems([P|R],Q):-qtosElems(R,QR),Q is QR+1.
--------------
?- qtosElems([2,5,8,9],QTOS).
QTOS=4.
--------------

%maiorNota(listaDeNotas,nomeDeQuemTirouAMaiorNota)
%listaDeNotas eh lista de functors com a forma
%nota(nomeDeQuemTirouAquelaNota,ValorDaNotaDele)
maiorNota([nota(NOME,_)],NOME).
maiorNota([nota(NOME1,VALOR1),nota(NOME2,VALOR2)|R],NOME):-VALOR1@>VALOR2,maiorNota([nota(NOME1,VALOR1)|R],NOME).
maiorNota([nota(NOME1,VALOR1),nota(NOME2,VALOR2)|R],NOME):-VALOR1@=<VALOR2,maiorNota([nota(NOME2,VALOR2)|R],NOME).
--------------
?- maiorNota([nota(andre,10),nota(goes,6),nota(marcelo,8)],QUEM).
QUEM=andre