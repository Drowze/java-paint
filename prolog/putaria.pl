nasceu(jose,m,eleazar,ana).
nasceu(sebastiao,m,eleazar,ana).
nasceu(neusa,f,zair,maria).
nasceu(paulo,m,zair,maria).
nasceu(andre,m,jose,neusa).
nasceu(zeluis,m,jose,neusa).
nasceu(marcoantonio,m,sebastiao,zulma).
nasceu(marcioaugusto,m,sebastiao,zulma).
nasceu(tieni,f,sebastiao,nilce).
nasceu(marcel,m,paulo,marialuiza).
nasceu(marcelo,m,paulo,marialuiza).
nasceu(marcioluis,m,paulo,marialuiza).
nasceu(hugo,m,andre,lilia).
morreu(zulma).
casou(eleazar, ana).
casou(zair, maria).
casou(jose,neusa).
casou(sebastiao,zulma).
casou(sebastiao,nilce).
casou(andre,lilia).
separou(andre,lilia).
pai(P,F):- nasceu(F,_,P,_).
mae(M,F):-nasceu(F,_,_,M).
genitor(G,F):-mae(G,F).
genitor(G,F):-pai(G,F).
avo(A,N):-pai(A,G),genitor(G,N).
