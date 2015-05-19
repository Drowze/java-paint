# java-paint

**Compilando em terminal:**  
```
javac *.java  
java -classpath . Editor  
```

---
O objetivo deste trabalho é implementar em Java um Editor Gráfico simples, semelhante ao Paint que acompanha o sistema operacional Windows. Ele deverá ser desenvolvido em duplas que o entregarão em fases, conforme especificado abaixo.
Para catalisar o desenvolvimento deste trabalho, fornecerei uma versão preliminar simplificada e funcional do programa desejado. Desta forma, seu trabalho consistirá de completar as funcionalidades já previstas no programa fornecido e agregar novas funcionalidades a ele.
Segue a especificação de cada fase, bem como do prazo de entrega.


**Fase I (entregar 28/abr):**  
a) Implementar coloração de contorno.  
b) Implementar coloração de interior, quando cabível.  
c) Implementar o desenho de círculo;  
d) Implementar o desenho de elipse.  
Para escolher cor, usar JColorChooser.  


**Fase II (entregar 5/mai):**  
a) Implementar o desenho de quadrado.  
b) Implementar o desenho de retângulo.  
c) Implementar o desenho de polígono.  
d) Implementar a escrita de texto.  
Tudo em consonância com o que já está implementado, ou seja, possibilitando colorir o contorno e o interior (só contorno no caso de texto), como os demais desenhos. Novos botões deverão ser colocados na janela para dar acesso a esses novos desenhos.  
Permitir a escolha da fonte, do tamanho da fonte, da cor e de efeitos que possam ser aplicados na fonte (user JFontChooser). Botões novos devem ser colocados na janela para dar acesso a esses novos recursos.  


**Fase III (entregar 12/mai):**  
a) Implementar o uso contínuo de ferramenta, ou seja, escolhida uma ferramenta, ela só deixaria de ser usada quando outra fosse escolhida.  
b) Implementar o desenho contínuo, ou seja, o desenho deve ir ocorrendo, crescendo ou diminuindo, à medida que o mouse é arrastado.  
c) Implementar salvamento de desenhos.  
d) Implementar carga de desenhos.  

Arquivos de desenho deverão ter uma extensão padrão que deverá ser escolhida por vocês. Salvamento e carga devem ser implementados dando ao usuário a oportunidade de escolher a pasta onde salvar (usar JFileChooser), bem como especificar o nome do arquivo. Apenas arquivos com a extensão padrão devem ser mostrados para carga. Caso se tente salvar um desenho em um arquivo já existente diferente daquele carregado ou sair do programa sem salvar, uma mensagem (usar JOptionPane.showConfirmDialog) deve ser dada confirmando a operação.  


**Fase IV (entregar 19/mai):**  
a) Implementar mudança de plano de desenho, aproximando-o ou afastando-o do primeiro plano.  
b) Implementar a movimentação de desenho, clicando no desenho a ser movimentado e arrastando-o para o local desejado.  
c) Implementar a deleção de desenho, clicando no desenho a ser apagado e pressionando, em seguida, a tecla DEL.  
