
;Função que verifica se lista L contem o elemento A
(defun ChecaSeContem (A L)
    (cond   ((atom L) 'nil )
            ((equal (car L) A) 'HEHE)
            ('t (ChecaSeContem A (cdr L)))
    )
)

;Função que "remove" elemento de lista
(defun RemoveAtmLista (A L)
    (cond   ((atom L) L )
            ((equal A (car L)) (RemoveAtmLista A (cdr L)))
            ('t (cons (car L) (RemoveAtmLista A (cdr L))))
    )
)

;Duas funções pra checar se o numero é negativo
(defun negativo (x)
    (cond   ((equal x 0) 'nil)
            ('t (negAux x x))
    )
)

;Duas funções pra checar se o numero é negativo
(defun negAux (A D)
    (cond   ((equal A 0) 't)
            ((equal D 0) 'nil)
            ('t (negAux (1+ A) (1- D)))
    )
)

;Função que retorna a soma/subt/mult/div de dois inteiros dados
;Função que retorna o valor absoluto


; OcorreAtEmLstAt
; RemoveAtEmLstAt



;'(nil) == (nil . nil)
;'() == 'nil
;'(()) == '(nil)

;; Loading file ex_prova.lsp ...
;; Loaded file ex_prova.lsp
; [1]> (ChecaSeContem 'nil '(nil))
; T
; [2]> (ChecaSeContem '(nil) '(nil))
; NIL
; [3]> (ChecaSeContem '() '(nil))
; T
; [4]> (ChecaSeContem '(()) '(nil))
; NIL
; [5]> (ChecaSeContem '(nil) '(nil))
; NIL
; [6]> (ChecaSeContem '() '(nil))
; T
; [7]> (ChecaSeContem 'nil '(nil))
; T
; [8]> (ChecaSeContem '(()) '(nil))
; NIL
; [9]> (ChecaSeContem 'nil '(a . nil))
; NIL
; [10]> (ChecaSeContem '(a) '(a . nil))
; NIL
; [11]> (ChecaSeContem 'a '(a . nil))
; T
; [12]> 

;[4]> (RemoveAtmLista 'a '(a . (b . (c . nil) ) ) )
;(B C)
