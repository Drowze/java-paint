/*
%ANOTAÇÕES DA AULA

%irmaoTotal(irmao,deQuemEleEhIrmao)
irmaoTotal(I,X):-nasceu(I,m,P,M)nasceu(X,_,P,M),I\==X.
%irmaoPPai(irmao,deQuemEleEhIrmao)
irmaoPPai(I,X):-nasceu(I, 
*/

% \== @> @< @>= @=<
%fatorial(nroDoQualSeQuerCalcularFatorial,fatorialDele)
fatorial(0,1).
fatorial(1,1).
fatorial(X,F):-X@>1,PX is X-1,fatorial(PX,FP),F is X*FP.