import java.awt.*;
import java.util.*;

public class Quadrado extends Figura {
    protected int xis[] = new int[99];
    protected int yis[] = new int[99];
    
    protected int lado;
	
    public Quadrado (int x[], int y[]) {
        this (x, y, Color.BLACK, Color.WHITE);
    }
	
    public Quadrado (int x[], int y[], Color cor) {
        this (x, y, cor, Color.WHITE);
    }
    
    public Quadrado (int x[], int y[], Color cor, Color preen) {
        super(cor,preen);

        if(Math.abs(x[0] - x[1]) > Math.abs(y[0] - y[1]))
            this.lado = (int)(Math.abs(x[0] - x[1]));
        else 
            this.lado = (int)(Math.abs(y[0] - y[1]));
        
        this.xis[0] = x[0]; this.yis[0] = y[0]; //p0 do quadrado
        if(y[1] > y[0]){
            if(x[1] > x[0]){
                //1º quadrante
                this.xis[1] = x[0] + this.lado; this.yis[1] = y[0]; //p1
                this.xis[2] = x[0] + this.lado; this.yis[2] = y[0] + this.lado; //p2
                this.xis[3] = x[0];             this.yis[3] = y[0] + this.lado; //p3
            }
            else{
                //2º quadrante
                this.xis[1] = x[0];             this.yis[1] = y[0] + this.lado; //p1
                this.xis[2] = x[0] - this.lado; this.yis[2] = y[0] + this.lado; //p2
                this.xis[3] = x[0] - this.lado; this.yis[3] = y[0]; //p3
            }
        }
        else{
            if(x[1] < x[0]){
                //3º quadrante
                this.xis[1] = x[0] - this.lado; this.yis[1] = y[0]; //p1
                this.xis[2] = x[0] - this.lado; this.yis[2] = y[0] - this.lado; //p2
                this.xis[3] = x[0];             this.yis[3] = y[0] - this.lado; //p3
            }
            else{
                //4º quadrante
                this.xis[1] = x[0];             this.yis[1] = y[0] - this.lado; //p1
                this.xis[2] = x[0] + this.lado; this.yis[2] = y[0] - this.lado; //p2
                this.xis[3] = x[0] + this.lado; this.yis[3] = y[0]; //p3
            }
        }
    }

    public Quadrado (String q) {
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

    public void setLado (int lado) {
        this.lado = lado;
    }
    
    public int getLado () {
        return this.lado;
    }

    public Ponto getP0 () {
        Ponto P0 = new Ponto(this.xis[0], this.yis[0]);
        return P0;
    }

    public void torneSeVisivel (Graphics g) {
        g.setColor (this.preen);
        g.fillPolygon (xis, yis, 4);
        g.setColor (this.cor);
        g.drawPolygon (xis, yis, 4);
    }

    public String toString() {
        return "q:" +
               this.xis[0] +
               ":" +
               this.xis[2] +
               ":" +
               this.lado +
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
}
