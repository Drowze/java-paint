import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;
import java.util.*;
import java.lang.Math;

public class Janela extends JFrame { // implements Cloneable

	private static final long serialVersionUID = 1L;

	private JButton btnPonto   = new JButton ("Ponto"),
                    btnLinha   = new JButton ("Linha"),
                    btnCirculo = new JButton ("Circulo"),
                    btnElipse  = new JButton ("Elipse"),
                    btnCores   = new JButton ("Cores"),
                    btnAbrir   = new JButton ("Abrir"),
                    btnSalvar  = new JButton ("Salvar"),
                    btnApagar  = new JButton ("Apagar"),
                    btnSair    = new JButton ("Sair");

    private MeuJPanel pnlDesenho = new MeuJPanel ();
    
    private JLabel statusBar1 = new JLabel ("Mensagem:"),
                   statusBar2 = new JLabel ("Coordenada:");

    boolean esperaPonto, 
            esperaInicioReta, esperaFimReta,
            esperaInicioCirculo, esperaFimCirculo,
            esperaInicioElipse, esperaFimElipse, esperaFim2Elipse;

    private Color corAtual = Color.black;
    private Ponto p1, p2;
    double raio, raio2;
    
    private Vector<Figura> figuras = new Vector<Figura>();

    public Janela () {
        super("Editor Gráfico");

        try {
            Image btnPontoImg = ImageIO.read(getClass().getResource("resources/ponto.jpg"));
            btnPonto.setIcon(new ImageIcon(btnPontoImg));
        }
        catch (IOException e) {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo ponto.jpg não foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }

        try {
            Image btnLinhaImg = ImageIO.read(getClass().getResource("resources/linha.jpg"));
            btnLinha.setIcon(new ImageIcon(btnLinhaImg));
        }
        catch (IOException e) {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo linha.jpg não foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }

        try {
            Image btnCirculoImg = ImageIO.read(getClass().getResource("resources/circulo.jpg"));
            btnCirculo.setIcon(new ImageIcon(btnCirculoImg));
        }
        catch (IOException e) {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo circulo.jpg não foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }

        try {
            Image btnElipseImg = ImageIO.read(getClass().getResource("resources/elipse.jpg"));
            btnElipse.setIcon(new ImageIcon(btnElipseImg));
        }
        catch (IOException e) {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo elipse.jpg não foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }

        try {
            Image btnCoresImg = ImageIO.read(getClass().getResource("resources/cores.jpg"));
            btnCores.setIcon(new ImageIcon(btnCoresImg));
        }
        catch (IOException e) {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo cores.jpg não foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }

        try {
            Image btnAbrirImg = ImageIO.read(getClass().getResource("resources/abrir.jpg"));
            btnAbrir.setIcon(new ImageIcon(btnAbrirImg));
        }
        catch (IOException e) {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo abrir.jpg não foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }

        try {
            Image btnSalvarImg = ImageIO.read(getClass().getResource("resources/salvar.jpg"));
            btnSalvar.setIcon(new ImageIcon(btnSalvarImg));
        }
        catch (IOException e) {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo salvar.jpg não foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }

        try {
            Image btnApagarImg = ImageIO.read(getClass().getResource("resources/apagar.jpg"));
            btnApagar.setIcon(new ImageIcon(btnApagarImg));
        }
        catch (IOException e) {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo apagar.jpg não foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }

        try {
            Image btnSairImg = ImageIO.read(getClass().getResource("resources/sair.jpg"));
            btnSair.setIcon(new ImageIcon(btnSairImg));
        }
        catch (IOException e) {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo sair.jpg não foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }

        btnPonto.addActionListener (new DesenhoDePonto());
        btnLinha.addActionListener (new DesenhoDeReta ());
        btnCirculo.addActionListener(new DesenhoDeCirculo());
        btnElipse.addActionListener(new DesenhoDeElipse());

        JPanel     pnlBotoes = new JPanel();
        FlowLayout flwBotoes = new FlowLayout(); 
        pnlBotoes.setLayout (flwBotoes);

        pnlBotoes.add (btnAbrir);
        pnlBotoes.add (btnSalvar);
        pnlBotoes.add (btnPonto);
        pnlBotoes.add (btnLinha);
        pnlBotoes.add (btnCirculo);
        pnlBotoes.add (btnElipse);
        pnlBotoes.add (btnCores);
        pnlBotoes.add (btnApagar);
        pnlBotoes.add (btnSair);

        JPanel     pnlStatus = new JPanel();
        GridLayout grdStatus = new GridLayout(1,2);
        pnlStatus.setLayout(grdStatus);

        pnlStatus.add(statusBar1);
        pnlStatus.add(statusBar2);

        Container cntForm = this.getContentPane();
        cntForm.setLayout (new BorderLayout());
        cntForm.add (pnlBotoes,  BorderLayout.NORTH);
        cntForm.add (pnlDesenho, BorderLayout.CENTER);
        cntForm.add (pnlStatus,  BorderLayout.SOUTH);
        
        this.addWindowListener (new FechamentoDeJanela());

        this.setSize (700,500);
        this.setVisible (true);
    }

    private class MeuJPanel extends    JPanel 
                            implements MouseListener,
                                       MouseMotionListener 
    {
	
        private static final long serialVersionUID = 1L;

	public MeuJPanel() {
            super();

            this.addMouseListener       (this);
            this.addMouseMotionListener (this);
        }

        public void paint (Graphics g) {
            for (int i=0 ; i<figuras.size(); i++)
                figuras.get(i).torneSeVisivel(g);
        }
        
        public void mousePressed (MouseEvent e) {
            if (esperaPonto) {
                figuras.add (new Ponto (e.getX(), e.getY(), corAtual));
                figuras.get(figuras.size()-1).torneSeVisivel(pnlDesenho.getGraphics());
                esperaPonto = false;
            }
            else
                if (esperaInicioReta) {
                    esperaInicioReta = false;
                    esperaFimReta = true;
                    
                    p1 = new Ponto (e.getX(), e.getY(), corAtual);
                    
                    statusBar1.setText("Mensagem: clique o ponto final da reta");    
                }
                else
                    if (esperaFimReta) {
                        esperaFimReta = false;
                        
                        figuras.add (new Linha(p1.getX(), p1.getY(), e.getX(), e.getY(), corAtual));
                        figuras.get(figuras.size()-1).torneSeVisivel(pnlDesenho.getGraphics());
                        
                        statusBar1.setText("Mensagem:");    
                    }
                    else
                        if(esperaInicioCirculo){
                            esperaInicioCirculo = false;
                            esperaFimCirculo = true;
                            
                            p1 = new Ponto (e.getX(), e.getY(), corAtual);
                            
                            statusBar1.setText("Mensagem: clique o ponto final do raio do circulo");
                        }
                        else
                            if(esperaFimCirculo){
                                esperaFimCirculo = false;
                                
                                raio = Math.sqrt( Math.pow(p1.getX() - e.getX(),2) + Math.pow(p1.getY() - e.getY(),2) );
                                figuras.add(new Circulo(p1.getX(), p1.getY(), (int)raio, corAtual ));
                                figuras.get(figuras.size()-1).torneSeVisivel(pnlDesenho.getGraphics());
                                
                                statusBar1.setText("Mensagem:");
                            }
                            else
                                if(esperaInicioElipse){
                                    esperaInicioElipse = false;
                                    esperaFimElipse = true;
                                    
                                    p1 = new Ponto (e.getX(), e.getY(), corAtual);
                                    
                                    statusBar1.setText("Mensagem: clique o ponto final do raio horizontal");
                                }
                                else
                                    if(esperaFimElipse){
                                        esperaFimElipse = false;
                                        esperaFim2Elipse = true;
                                        
                                        raio = Math.sqrt( Math.pow(p1.getX() - e.getX(),2) + Math.pow(p1.getY() - e.getY(),2) );
                                        
                                        statusBar1.setText("Mensagem: clique o ponto final do raio vertical");
                                    }
                                    else
                                        if(esperaFim2Elipse){
                                            esperaFim2Elipse = false;
                                            
                                            raio2 = Math.sqrt( Math.pow(p1.getX() - e.getX(),2) + Math.pow(p1.getY() - e.getY(),2) );
                                            figuras.add(new Elipse(p1.getX(), p1.getY(), (int)raio, (int)raio2, corAtual));
                                            figuras.get(figuras.size()-1).torneSeVisivel(pnlDesenho.getGraphics());
                                            
                                            statusBar1.setText("Mensagem:");
                                        }
        }
        
        public void mouseReleased (MouseEvent e) {
        }
        
        public void mouseClicked (MouseEvent e) {
        }
        
        public void mouseEntered (MouseEvent e) {
        }

        public void mouseExited (MouseEvent e) {
        }
        
        public void mouseDragged(MouseEvent e) {
        }

        public void mouseMoved(MouseEvent e) {
            statusBar2.setText("Coordenada: "+e.getX()+","+e.getY());
        }
    }

    private class DesenhoDePonto implements ActionListener {
        public void actionPerformed (ActionEvent e) {
            esperaPonto      = true;
            esperaInicioReta = false;
            esperaFimReta    = false;
            esperaInicioCirculo = false;
            esperaFimCirculo = false;
            esperaInicioElipse = false;
            esperaFimElipse = false;
            esperaFim2Elipse = false;

            statusBar1.setText("Mensagem: clique o local do ponto desejado");
        }
    }

    private class DesenhoDeReta implements ActionListener {
        public void actionPerformed (ActionEvent e) {
            esperaPonto      = false;
            esperaInicioReta = true;
            esperaFimReta    = false;
            esperaInicioCirculo = false;
            esperaFimCirculo = false;
            esperaInicioElipse = false;
            esperaFimElipse = false;
            esperaFim2Elipse = false;

            statusBar1.setText("Mensagem: clique o ponto inicial da reta");
        }
    }
    
    private class DesenhoDeCirculo implements ActionListener {
        public void actionPerformed (ActionEvent e) {
            esperaPonto      = false;
            esperaInicioReta = false;
            esperaFimReta    = false;
            esperaInicioCirculo = true;
            esperaFimCirculo = false;
            esperaInicioElipse = false;
            esperaFimElipse = false;
            esperaFim2Elipse = false;

            statusBar1.setText("Mensagem: clique o ponto central do circulo");
        }
    }
    private class DesenhoDeElipse implements ActionListener {
        public void actionPerformed (ActionEvent e) {
            esperaPonto      = false;
            esperaInicioReta = false;
            esperaFimReta    = false;
            esperaInicioCirculo = false;
            esperaFimCirculo = false;
            esperaInicioElipse = true;
            esperaFimElipse = false;
            esperaFim2Elipse = false;

            statusBar1.setText("Mensagem: clique o ponto central da Elipse");
        }
    }

    private class FechamentoDeJanela extends WindowAdapter {
        public void windowClosing (WindowEvent e) {
            System.exit(0);
        }
    }

  //public Object  clone    ();
  //public         Janela   (Janela modelo);
  //public boolean equals   (Object obj);
  //public int     hashCode ();
  //public String  toString ();
}