;car 1 elmt a esquerda 
;cdr 1 elemento mais a direita
;atom 

(defun negativo (X)
    (cond   ((equal X 0) 'nil)
            ('t(neg X X))
    )
)

(defun neg 	(X Y)
    (cond   ((equal X 0)'t)
            ((equal Y 0)'nil)
            ('t(neg(1+ X)(1- Y)))
    )
)

(defun soma (X Y)    
    (cond   ((equal Y 0)X)	  
            ((negativo Y)(soma(1- X)(1+ Y)))
            ('t(soma(1+ X)(1- Y)))
    )
)

(defun sub (X Y)
    (cond   ((equal Y 0) X)
            ((negativo Y)(sub(1+ X)(1+ Y)))
            ('t(sub(1- X)(1- Y)))
    )
)

(defun NE (X Y)
    (cond   ((equal X Y) 't)
            ('t 'nil)
    )
)

(defun GT (X Y)
    (cond   ((equal X Y) 'nil)
            ((

    )
)

;NE = diferente 
;GT = maior 
;GE = maior ou igual
;LT = menor
;LE = menor ou igual