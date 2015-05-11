import java.awt.*;
import java.util.*;

public class Elipse extends Figura {
    protected int centrox, centroy;
    protected int raiox, raioy;
	
    public Elipse (int x, int y, int x1, int y1) {
        this (x, y, x1, y1, Color.BLACK, Color.WHITE);
    }
    
    public Elipse (int x, int y, int x1, int y1, Color cor) {
        this (x, y, x1, y1, cor, Color.WHITE);
    }
    
    public Elipse (int x, int y, int x1, int y1, Color cor, Color preen) {
        super (cor,preen);
        this.raiox = (int)Math.abs(x-x1)/2;
        this.raioy = (int)Math.abs(y-y1)/2;
        
        if(y1 > y){
            if(x1 > x){
                //1º quadrante
                this.centrox = (x+raiox);
                this.centroy = (y+raioy);
            }
            else{
                //2º quadrante
                this.centrox = (x-raiox);
                this.centroy = (y+raioy);
            }
        }
        else{
            if(x1 < x){
                //3º quadrante
                this.centrox = (x-raiox);
                this.centroy = (y-raioy);
            }
            else{
                //4º quadrante
                this.centrox = (x+raiox);
                this.centroy = (y-raioy);
            }
        }
    }

    public Elipse (String s) {
        StringTokenizer quebrador = new StringTokenizer(s,":");

        quebrador.nextToken();

        int   x   = Integer.parseInt(quebrador.nextToken());
        int   y   = Integer.parseInt(quebrador.nextToken());

        int   r1  = Integer.parseInt(quebrador.nextToken());
        int   r2  = Integer.parseInt(quebrador.nextToken());

        Color cor = new Color (Integer.parseInt(quebrador.nextToken()),  // R
                               Integer.parseInt(quebrador.nextToken()),  // G
                               Integer.parseInt(quebrador.nextToken())); // B

        this.centrox = x;
        this.centroy = y;
        this.raiox  = r1;
        this.raioy  = r2;
        this.cor    = cor;
        //aqui vamos ter que pegar também a cor do preenchimento
    }

//    public void setCentro (int x, int y) {
//        this.centro = new Ponto (x,y,this.getCor());
//    }

    public void setRaioX (int r1) {
        this.raiox = r1;
    }

    public void setRaioY (int r2) {
        this.raioy = r2;
    }

//    public Ponto getCentro () {
//        return this.centro;
//    }

    public int setRaioX () {
        return this.raiox;
    }

    public int setRaioY () {
        return this.raioy;
    }
    
    public void torneSeVisivel (Graphics g) {
        g.setColor (this.preen);
        g.fillOval (this.centrox-raiox, this.centroy-raioy, 2*raiox, 2*raioy);
        g.setColor (this.cor);
        g.drawOval (this.centrox-raiox, this.centroy-raioy, 2*raiox, 2*raioy);
    }

    public String toString() {
        return "e:" +
               this.centrox +
               ":" +
               this.centroy +
               ":" +
               this.raiox +
               ":" +
               this.raioy +
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
