(defun incluir (agenda lista)
	(cond
		((equal agenda 'nil) 
			(reverse (cons lista agenda))
		);caso agenda esteja vazia
		((equal (car(car agenda)) (car lista))
			(cond 	
				((comp_num (cdr(car agenda)) (cdr lista))
					agenda
				);caso comparar numero retornar true retorna a agenda
				('t (cons (concatenate 'cons (car agenda) (cdr lista)) (cdr agenda)))
			);caso contrario retorna a concatenização
		)
		('t (incluirauxiliar (cdr agenda) agenda lista))
	);caso contrario vai para função com fim da agenda e uma agenda sem alteração
)
(defun incluirauxiliar (agenda agendacompleta lista)
	(cond
		((equal agenda 'nil) 
			(reverse (cons lista agendacompleta))
		);caso agenda esteja vazia
		((equal (car(car agenda)) (car lista))
			(cond 	
				((comp_num (cdr(car agenda)) (cdr lista));envia o numero
					agendacompleta
				);caso comparar numero retornar true retorna a agendacompleta
				('t (append (concatenatudo agenda agendacompleta) (cons (concatenate 'cons (car agenda) (cdr lista)) (cdr agenda))))
			)
		)
		('t (incluirauxiliar (cdr agenda) agendacompleta lista))
	)
)
(defun comp_num (agenda numero);verifica se tem o numero na agenda
	(cond
		((equal agenda 'nil) 
			'nil
		);caso agenda esteja vazia
		((equal	(car agenda) (car numero)) 
			't
		);caso encontre o numero
		('t (comp_num (cdr agenda) numero))
	)
)
(defun concatenatudo (agenda agendacompleta)
	(cond
		((equal (caar (last agendacompleta)) (caar agenda)) 
			(butlast agendacompleta)
		)
		('t (concatenatudo agenda (butlast agendacompleta)))
	);pega tudo menos o ultimo
)
;-----------------------------------------------------------------------------------------

(defun Telefones(agenda nome)
	(cond
		((equal agenda 'nil) 
			'INEXISTENTE 
		);caso agenda esteja vazia
		((equal (caar agenda) nome) 
			(car agenda)
		);verifica se ja existe o nome informado
		('t (Telefones (cdr agenda) nome));recursao
	)
)
;-----------------------------------------------------------------------------------------

(defun excluir (agenda lista)
	(cond
		((equal agenda 'nil) 
			'INEXISTENTE
		);caso agenda esteja vazia avisa q nao existe
		((equal (car(car agenda)) (car lista))
			(cond 	
				((comp_num_exc (cdr(car agenda)) agenda (cdr lista)));caso comparar numero retornar true retorna a agenda
				('t 'INEXISTENTE)
			);caso contrario retorna a concatenização
		)
		('t 'INEXISTENTE)
	);caso contrario vai para função com fim da agenda e uma agenda sem alteração
)

(defun comp_num_exc (agenda agendacompleta lista);verifica se tem o numero na agenda e exclui
	(cond
		((equal agenda 'nil) 
			'nil
		);caso agenda esteja vazia
		((equal	(car agenda) (car lista)) 
;FALTA AKIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII
		);caso encontre o numero exclui
		('t (comp_num (cdr agenda) lista))
	)
)