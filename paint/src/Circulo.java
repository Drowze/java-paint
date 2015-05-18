import java.awt.*;
import java.util.*;

public class Circulo extends Figura {
    protected int centrox, centroy, xis1, yis1, xis0, yis0;
    protected int   diametro;
	
    public Circulo (int x, int y, int x1, int y1, int r) {
        this (x, y, x1, y1, Color.BLACK, Color.WHITE);
    }
	
    public Circulo (int x, int y, int x1, int y1, Color cor) {
        this (x, y, x1, y1, cor, Color.WHITE);
        this.xis1 = x1;
        this.yis1 = y1;
    }
    
    public Circulo (int x, int y, int x1, int y1, Color cor, Color preen) {
        super(cor,preen);
        this.xis0 = x;
        this.yis0 = y;
        this.xis1 = x1;
        this.yis1 = y1;
        
        
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
        this.preen = preen;
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
    
    public int getRaio () {
        return this.diametro/2;
    }
    
    public int getDiametro () {
        return this.diametro;
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
               this.xis0 +
               ":" +
               this.yis0 +
               ":" +
               this.xis1 +
               ":" +
               this.yis1 +
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
    
    public boolean cliquePertence (int x, int y){
        int raioAux;
        if(Math.abs(x - centrox) > Math.abs(y - centroy))
            raioAux = (int)Math.abs(x - centrox);
        else
            raioAux = (int)Math.abs(y - centroy);
        
        if(raioAux < (this.diametro / 2))
            return true;
        else
            return false;
            
    }
            
}
