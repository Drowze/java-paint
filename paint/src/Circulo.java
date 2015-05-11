import java.awt.*;
import java.util.*;

public class Circulo extends Figura {
    protected int centrox, centroy;
    protected int   diametro;
	
    public Circulo (int x, int y, int x1, int y1, int r) {
        this (x, y, x1, y1, Color.BLACK, Color.WHITE);
    }
	
    public Circulo (int x, int y, int x1, int y1, Color cor) {
        this (x, y, x1, y1, cor, Color.WHITE);
    }
    
    public Circulo (int x, int y, int x1, int y1, Color cor, Color preen) {
        super(cor,preen);
        
        if(Math.abs(x - x1) > Math.abs(y - y1))
            this.diametro = (int)(Math.abs(x - x1));
        else 
            this.diametro = (int)(Math.abs(y - y1));
        
        
        if(y1 > y){
            if(x1 > x){
                //1º quadrante
                this.centrox = (x+diametro/2);
                this.centroy = (y+diametro/2);
            }
            else{
                //2º quadrante
                this.centrox = (x-diametro/2);
                this.centroy = (y+diametro/2);
            }
        }
        else{
            if(x1 < x){
                //3º quadrante
                this.centrox = (x-diametro/2);
                this.centroy = (y-diametro/2);
            }
            else{
                //4º quadrante
                this.centrox = (x+diametro/2);
                this.centroy = (y-diametro/2);
            }
        }
        
    }

    public Circulo (String s) {
        StringTokenizer quebrador = new StringTokenizer(s,":");

        quebrador.nextToken();

        int   x   = Integer.parseInt(quebrador.nextToken());
        int   y   = Integer.parseInt(quebrador.nextToken());

        int   d   = Integer.parseInt(quebrador.nextToken());

        Color cor = new Color (Integer.parseInt(quebrador.nextToken()),  // R
                               Integer.parseInt(quebrador.nextToken()),  // G
                               Integer.parseInt(quebrador.nextToken())); // B
        
        Color preen = new Color (Integer.parseInt(quebrador.nextToken()),  // R
                               Integer.parseInt(quebrador.nextToken()),  // G
                               Integer.parseInt(quebrador.nextToken()),  // B
                               Integer.parseInt(quebrador.nextToken())); // Alpha

        this.centrox = x;
        this.centroy = y;
        this.diametro  = d;
        this.cor    = cor;
        //aqui vamos ter que pegar também a cor do preenchimento
    }

    //setters
    public void setCentro (Ponto centro) {
        this.centrox = centro.getX();
        this.centroy = centro.getY();
    }
    
    public void setCentroX (int x) {
        this.centrox = x;
    }
    
    public void setCentroY (int y) {
        this.centroy = y;
    }

    public void setRaio (int r) {
        this.diametro = r*2;
    }
    
    public void setDiametro (int d) {
        this.diametro = d;
    }

    //getters
    public Ponto getCentro(){
        Ponto centro = new Ponto(this.centrox, this.centroy);
        return centro;
    }
    
    public int getCentroX () {
        return this.centrox;
    }
    
    public int getCentroY () {
        return this.centroy;
    }

    public void torneSeVisivel (Graphics g) {
        int upperLeftX = centrox-diametro/2;
        int upperLeftY = centroy-diametro/2;
        
        g.setColor (this.preen);
        g.fillOval (upperLeftX, upperLeftY, diametro, diametro);
        g.setColor (this.cor);
        g.drawOval (upperLeftX, upperLeftY, diametro, diametro);
        //g.drawLine (this.centrox, this.centroy, upperLeftX, upperLeftY);
    }

    public String toString() {
        return "c:" +
               this.centrox +
               ":" +
               this.centroy +
               ":" +
               this.diametro +
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
               this.getPreen().getBlue() +
                ":" +
               this.getPreen().getAlpha();
    }
}
