import java.awt.*;
import java.util.*;

public class Elipse extends Figura {
    protected int centrox, centroy, xis0, yis0, xis1, yis1;
    protected int raiox, raioy;
	
    public Elipse (int x, int y, int x1, int y1) {
        this (x, y, x1, y1, Color.BLACK, Color.WHITE);
    }
    
    public Elipse (int x, int y, int x1, int y1, Color cor) {
        this (x, y, x1, y1, cor, Color.WHITE);
    }
    
    public Elipse (int x, int y, int x1, int y1, Color cor, Color preen) {
        super (cor,preen);
        xis0 = x;
        yis0 = y;
        xis1 = x1;
        yis1 = y1;
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

        int   raiox  = Integer.parseInt(quebrador.nextToken());
        int   raioy  = Integer.parseInt(quebrador.nextToken());

        Color cor = new Color (Integer.parseInt(quebrador.nextToken()),  // R
                               Integer.parseInt(quebrador.nextToken()),  // G
                               Integer.parseInt(quebrador.nextToken())); // B
        
        Color preen = new Color (Integer.parseInt(quebrador.nextToken()),  // R
                               Integer.parseInt(quebrador.nextToken()),  // G
                               Integer.parseInt(quebrador.nextToken()),  // B
                               Integer.parseInt(quebrador.nextToken())); // Alpha

        this.centrox = x;
        this.centroy = y;
        this.raiox  = raiox;
        this.raioy  = raioy;
        this.cor    = cor;
        this.preen = preen;
    }

    //setters
    public void setCentro (Ponto centro) {
        this.centrox = centro.getX();
        this.centroy = centro.getY();
    }
    
    public void setCentro (int x, int y) {
        this.centrox = x;
        this.centroy = y;
    }
    
    public void setCentroX (int x) {
        this.centrox = x;
    }
    
    public void setCentroY (int y) {
        this.centrox = y;
    }

    public void setRaioX (int raiox) {
        this.raiox = raiox;
    }

    public void setRaioY (int raioy) {
        this.raioy = raioy;
    }
    
    //getters
    public Ponto getCentro () {
        Ponto ponto = new Ponto(this.centrox, this.centroy, this.cor);
        return ponto;
    }

    public int getRaioX () {
        return this.raiox;
    }

    public int getRaioY () {
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
}
