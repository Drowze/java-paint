import java.awt.*;
import java.util.*;
 
public class Poligono extends Figura
{
    protected int xis[] = new int[99];
    protected int yis[] = new int[99];
   
    protected int vert;
   
    protected int       x[]={12, 120, 300};
    protected int       y[]={30, 300, 50};
       
    public Poligono (int x[], int y[], int n)
    {
        this (x, y, n, Color.BLACK, Color.white);
    }
       
    public Poligono (int x[], int y[], int n, Color cor, Color cor2)
    {
        super (cor, cor2);
       
        for(int i = 0; i < n; i++){
            xis[i] = x[i];
            yis[i] = y[i];
        }
       
        vert = n;
 
    }
 
    public Poligono (String s)
    {
        StringTokenizer quebrador = new StringTokenizer(s,":");
 
        quebrador.nextToken();
 
        int   x   = Integer.parseInt(quebrador.nextToken());
        int   y   = Integer.parseInt(quebrador.nextToken());
 
        int   w   = Integer.parseInt(quebrador.nextToken());
        int   h   = Integer.parseInt(quebrador.nextToken());
       
        //auxiliares
 
        Color cor = new Color (Integer.parseInt(quebrador.nextToken()),  // R
                               Integer.parseInt(quebrador.nextToken()),  // G
                               Integer.parseInt(quebrador.nextToken())); // B
         
        Color cor2 = new Color (Integer.parseInt(quebrador.nextToken()),  // R
                                                Integer.parseInt(quebrador.nextToken()),  // G
                                                Integer.parseInt(quebrador.nextToken())); // B
        this.cor      = cor;
        this.preen     = cor2;
       
    }
 
    public void torneSeVisivel (Graphics g)
    {
            g.setColor(this.preen);
            g.fillPolygon(xis, yis, vert);
            g.setColor (this.cor);
            g.drawPolygon(xis, yis, vert);
 
    }
 
    public String toString()
    {
        String ponto = "";
        for(int i = 0; i < vert; i++){
            ponto = ponto + xis[i] + ":" + yis[i] + ":";
        }
        return "g:" +
                vert +
                 ":" +
                ponto +
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
    
    public boolean cliquePertence(int x, int y){
    	int maxX=0, minX=9999, maxY=0, minY=9999;
    	for(int i = 0; i < vert; i++){
    		if(xis[i] > maxX){
    			maxX = xis[i];
    		}
    		if(yis[i] > maxY){
    			maxY = yis[i];
    		}
    		if(xis[i] < minX){
    			minX = xis[i];
    		}
    		if(yis[i] < minY){
    			minY = yis[i];
    		}
    	}
    	
    	if((x>minX && x<maxX) && (y>minY && y<maxY)){
    		return true;
    	}
    	else{
    		return false;
    	}
    }
    
    public void move(int x, int y){
        if(x > this.xis[0]){
            for(int i=0;i<=this.vert;i++){
                this.xis[i] = this.xis[i]+8;
            }
        }
        if(y > this.yis[0]){
            for(int i=0;i<=this.vert;i++){
                this.yis[i] = this.yis[i]+8;
            }
        }
        if(x < this.xis[0]){
            for(int i=0;i<=this.vert;i++){
                this.xis[i] = this.xis[i]-8;
            }
        }
        if(y < this.yis[0]){
            for(int i=0;i<=this.vert;i++){
                this.yis[i] = this.yis[i]-8;
            }
        }
    }
}