import java.awt.*;
import java.util.*;

public class Poligono extends Figura {
    protected int xis[] = new int[99];
    protected int yis[] = new int[99];
    protected int qtd;
	
    public Poligono (int x[], int y[], int qtd) {
        this (x, y, qtd, Color.BLACK, Color.WHITE);
    }
	
    public Poligono (int x[], int y[], int qtd, Color cor) {
        this (x, y, qtd, cor, Color.WHITE);
    }
    
    public Poligono (int x[], int y[], int qtd, Color cor, Color preen) {
        super(cor,preen);
        this.qtd = qtd;
        
        for(int i = 0; i < this.qtd; i++){
            this.xis[i] = x[i];
            this.yis[i] = y[i];
        }
    }

    public Poligono (String q) {
        StringTokenizer quebrador = new StringTokenizer(q,":");

        quebrador.nextToken();

        int   x   = Integer.parseInt(quebrador.nextToken());
        int   y   = Integer.parseInt(quebrador.nextToken());

        int   lado   = Integer.parseInt(quebrador.nextToken());

        Color cor = new Color (Integer.parseInt(quebrador.nextToken()),  // R
                               Integer.parseInt(quebrador.nextToken()),  // G
                               Integer.parseInt(quebrador.nextToken())); // B

//        this.centro = new Ponto (x,y,cor);
//        this.raio   = r;
//        this.cor    = cor;
        //aqui vamos ter que pegar também a cor do preenchimento
    }

    public void setP0 (int x, int y) {
        this.xis[0] = x;
        this.yis[0] = y;
    }

//    public void setLado (int lado) {
//        this.lado = lado;
//    }
    
//    public int getLado () {
//        return this.lado;
//    }

    public Ponto getP0 () {
        Ponto P0 = new Ponto(this.xis[0], this.yis[0]);
        return P0;
    }

    public void torneSeVisivel (Graphics g) {
        g.setColor (this.preen);
        g.fillPolygon (xis, yis, qtd);
        g.setColor (this.cor);
        g.drawPolygon (xis, yis, qtd);
    }

    public String toString() {
        return "q:" +
               this.xis[0] +
               ":" +
               this.xis[2] +
               ":" +
               //this.lado +
               ":" +
               this.getCor().getRed() +
               ":" +
               this.getCor().getGreen() +
               ":" +
               this.getCor().getBlue() + 
                ":" +
               this.getPreen().getRed() + //daqui pra baixo pra salvar a cor do preenchimento
                ":" +
               this.getPreen().getGreen() + //sujeito a erros lol
                ":" +
               this.getPreen().getBlue();
    }
    
    public boolean cliquePertence (int x, int y){
        return false;
    }
}
