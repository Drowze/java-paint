import java.awt.*;
import java.awt.event.*;
import static java.awt.event.KeyEvent.VK_DELETE;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;
import java.util.*;
import java.lang.*;

public class Janela extends JFrame { // implements Cloneable

    private static final long serialVersionUID = 1L;

    private JButton btnPonto   = new JButton ("Ponto"),
                    btnLinha     = new JButton ("Linha"),
                    btnCirculo   = new JButton ("Circulo"),
                    btnElipse    = new JButton ("Elipse"),                
                    btnQuadrado  = new JButton ("Quadrado"),//--------------------------------------------
                    btnRetangulo = new JButton ("Retangulo"),//--------------------------------------------
                    btnPoligono  = new JButton ("Poligono"),//-----------------------------------
                    btnEscrita   = new JButton ("Escrita"),//----------------------------------
                    btnCores     = new JButton ("Contorno"),
                    btnPreen     = new JButton ("Preenchimento"),
                    btnAbrir     = new JButton ("Abrir"),
                    btnSalvar    = new JButton ("Salvar"),

                    btnApagar    = new JButton ("Apagar"),
                    btnSair      = new JButton ("Sair"),
    
                    btnSelect    = new JButton ("Selecionar"),
                    btnMover     = new JButton ("Mover"),
                    btnUp        = new JButton ("Pra Cima"),
                    btnDown      = new JButton ("Pra Baixo");

    private MeuJPanel pnlDesenho = new MeuJPanel ();
    
    private JLabel statusBar1 = new JLabel ("Mensagem:"),
                   statusBar2 = new JLabel ("Coordenada:");

    boolean esperaSelect, esperaMover, esperaUp, esperaDown,
            aberto,
            esperaPonto, 
            esperaInicioReta, esperaFimReta, desenhandoReta,
            esperaInicioCirculo, esperaFimCirculo, desenhandoCirculo,
            esperaInicioElipse, esperaFimElipse, desenhandoElipse,
            esperaInicioQuadrado, esperaFimQuadrado, desenhandoQuadrado,
            esperaInicioRetangulo, esperaFimRetangulo, desenhandoRetangulo,
            esperaInicioPoligono, esperaFimPoligono, desenhandoPoligono;

    private Color corAtual = Color.black;
    private Color corAtualPreen = new Color(0,0,0,0);
    private Ponto p0, p1, p2;
    //double raio, raio2;
    int x[] = new int[90];
    int y[] = new int[90];
    int xDragged, yDragged;
    int selecionado;
    
    private Vector<Figura> figuras = new Vector<Figura>();
    private Vector<Figura> aux = new Vector<Figura>();

    public Janela () {
        super("Editor Gráfico");
        
        try {
            Image btnDownImg = ImageIO.read(getClass().getResource("resources/down.png"));
            btnDown.setIcon(new ImageIcon(btnDownImg));
        }
        catch (IOException e) {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo down.png não foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }
        
        try {
            Image btnUpImg = ImageIO.read(getClass().getResource("resources/up.png"));
            btnUp.setIcon(new ImageIcon(btnUpImg));
        }
        catch (IOException e) {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo up.png não foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }

        try {
            Image btnMoverImg = ImageIO.read(getClass().getResource("resources/mover.png"));
            btnMover.setIcon(new ImageIcon(btnMoverImg));
        }
        catch (IOException e) {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo mover.png não foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }
        
        try {
            Image btnSelectImg = ImageIO.read(getClass().getResource("resources/selecionar.png"));
            btnSelect.setIcon(new ImageIcon(btnSelectImg));
        }
        catch (IOException e) {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo selecionar.png não foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }
        
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
        try {//----------------------------------------------------------------------------------------------------------
            Image btnQuadradoImg = ImageIO.read(getClass().getResource("resources/quadrado.jpg"));
            btnQuadrado.setIcon(new ImageIcon(btnQuadradoImg));
        }
        catch (IOException e) {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo quadrado.jpg não foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }
        try {
            Image btnRetanguloImg = ImageIO.read(getClass().getResource("resources/retangulo.jpg"));
            btnRetangulo.setIcon(new ImageIcon(btnRetanguloImg));
        }
        catch (IOException e) {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo retangulo.jpg não foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }//----------------------------------------------------------------------------------------------------------

        try {
            Image btnPoligonoImg = ImageIO.read(getClass().getResource("resources/poligono.jpg"));
            btnPoligono.setIcon(new ImageIcon(btnPoligonoImg));
        }
        catch (IOException e) {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo poligono.jpg não foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }
        try {
            Image btnEscritaImg = ImageIO.read(getClass().getResource("resources/escrita.jpg"));
            btnEscrita.setIcon(new ImageIcon(btnEscritaImg));
        }
        catch (IOException e) {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo escrita.jpg não foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }//----------------------------------------------------------------------------------------------------
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
            Image btnPreenImg = ImageIO.read(getClass().getResource("resources/preenchimento.png"));
            btnPreen.setIcon(new ImageIcon(btnPreenImg));
        }
        catch (IOException e) {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo preenchimento.jpg não foi encontrado",
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
        
        btnAbrir.addActionListener(new Abrir());
        btnSalvar.addActionListener(new Salvar());
        btnPonto.addActionListener (new DesenhoDePonto());
        btnLinha.addActionListener (new DesenhoDeReta ());
        btnCirculo.addActionListener(new DesenhoDeCirculo());
        btnElipse.addActionListener(new DesenhoDeElipse());
        btnQuadrado.addActionListener(new DesenhoDeQuadrado());//-------------------------------
        btnRetangulo.addActionListener(new DesenhoDeRetangulo());//-------------------------------
        btnPoligono.addActionListener(new DesenhoDePoligono());
        //btnEscrita.addActionListener(new CaixaDeEscrita());
        btnCores.addActionListener(new EscolhaCorContorno());
        btnPreen.addActionListener(new EscolhaCorPreenchimento());
        btnSair.addActionListener (new ParedeDeJanela());
        
        btnSelect.addActionListener(new SelecionarImagem());
        btnMover.addActionListener(new SelecionarImagem());
        btnUp.addActionListener(new Up ());
        btnDown.addActionListener(new Down ());

        JPanel     pnlBotoes = new JPanel();
        GridBagConstraints flwBotoes = new GridBagConstraints(); 
        
        pnlBotoes.setLayout (new GridBagLayout());
        
        flwBotoes.fill = GridBagConstraints.HORIZONTAL;
        flwBotoes.gridy = 0;
        pnlBotoes.add (btnAbrir, flwBotoes);
        pnlBotoes.add (btnSalvar, flwBotoes);
        pnlBotoes.add (btnSair, flwBotoes);
        
        flwBotoes.gridy = 1;
        pnlBotoes.add (btnPonto, flwBotoes);
        pnlBotoes.add (btnLinha, flwBotoes);
        pnlBotoes.add (btnCirculo, flwBotoes);
        pnlBotoes.add (btnElipse, flwBotoes);
        pnlBotoes.add (btnQuadrado, flwBotoes);
        pnlBotoes.add (btnRetangulo, flwBotoes);
        pnlBotoes.add (btnPoligono, flwBotoes);
        
        flwBotoes.gridy = 2;
        pnlBotoes.add (btnSelect, flwBotoes);
        pnlBotoes.add (btnMover, flwBotoes);
        pnlBotoes.add (btnUp, flwBotoes);
        pnlBotoes.add (btnDown, flwBotoes);
        pnlBotoes.add (btnEscrita, flwBotoes);
        pnlBotoes.add (btnCores, flwBotoes);
        pnlBotoes.add (btnPreen, flwBotoes);
        //pnlBotoes.add (btnApagar, flwBotoes2);
        

        JPanel     pnlStatus = new JPanel();
        GridBagConstraints grdStatus = new GridBagConstraints();
        pnlStatus.setLayout(new GridBagLayout());

        grdStatus.gridy = 0;
        //grdStatus.weightx = 1.0;
        pnlStatus.add(statusBar1, grdStatus);
        
        //grdStatus.weightx = 0;
        grdStatus.gridy = 1;
        pnlStatus.add(statusBar2, grdStatus);

        Container cntForm = this.getContentPane();
        cntForm.setLayout (new BorderLayout());
        cntForm.add (pnlBotoes,  BorderLayout.NORTH);
        cntForm.add (pnlDesenho, BorderLayout.CENTER);
        cntForm.add (pnlStatus,  BorderLayout.SOUTH);
        
        this.addWindowListener (new FechamentoDeJanela());

        this.setSize (1024,768);
        this.setVisible (true);
    }

    private class MeuJPanel extends    JPanel 
                            implements MouseListener,
                                       KeyListener,
                                       MouseMotionListener 
    {
        
        private static final long serialVersionUID = 1L;

	public MeuJPanel() {
            super();

            this.addMouseListener       (this);
            this.addMouseMotionListener (this);
            this.addKeyListener         (this);
        }

        public void paint (Graphics g) {
            for (int i=0 ; i<figuras.size(); i++)
                figuras.get(i).torneSeVisivel(g);
        }
        
        public void mousePressed (MouseEvent e) {
            if (esperaPonto) {
                figuras.add (new Ponto (e.getX(), e.getY(), corAtual));
                figuras.get(figuras.size()-1).torneSeVisivel(pnlDesenho.getGraphics());
                //esperaPonto = false;
            }
            else
                if (esperaInicioReta) {
                    esperaInicioReta = false;
                    desenhandoReta = true;
                    
                    p1 = new Ponto (e.getX(), e.getY(), corAtual);
                    
                    statusBar1.setText("Mensagem: solte o mouse no ponto final da reta");    
                }
                else
                    if(esperaInicioCirculo){
                        esperaInicioCirculo = false;
                        desenhandoCirculo = true;

                        p1 = new Ponto (e.getX(), e.getY(), corAtual);

                        statusBar1.setText("Mensagem: solte o mouse o ponto final do circulo");
                    }
                    else
                        if(esperaInicioElipse){
                            esperaInicioElipse = false;
                            desenhandoElipse = true;

                            p1 = new Ponto (e.getX(), e.getY(), corAtual);

                            statusBar1.setText("Mensagem: solte o mouse no ponto final da elipse");
                        }
                        else
                            if (esperaInicioQuadrado) {
                                esperaInicioQuadrado = false;
                                desenhandoQuadrado = true;

                                p1 = new Ponto (e.getX(), e.getY(), corAtual);
                                x[0] = p1.getX();
                                y[0] = p1.getY();

                                statusBar1.setText("Mensagem: solte o mouse no ponto final do quadrado");    
                            }
                            else//---------------------------------------------------
                                if (esperaInicioRetangulo) {
                                    esperaInicioRetangulo = false;
                                    desenhandoRetangulo = true;

                                    p1 = new Ponto (e.getX(), e.getY(), corAtual);
                                    x[0] = p1.getX();
                                    y[0] = p1.getY();

                                    statusBar1.setText("Mensagem: solte o mouse no ponto final do retangulo");    
                                }
                                else//---------------------------------------------------
                                    if (esperaInicioPoligono) {
                                        esperaInicioPoligono = false;
                                        //desenhandoPoligono = true;

                                        p1 = new Ponto (e.getX(), e.getY(), corAtual);

                                        statusBar1.setText("Mensagem: clique o ponto final do poligono");    
                                    }
                                    else
                                        if (esperaFimPoligono) {
                                            esperaFimPoligono = false;

                                            figuras.add (new Linha(p1.getX(), p1.getY(), e.getX(), e.getY(), corAtual));
                                            figuras.get(figuras.size()-1).torneSeVisivel(pnlDesenho.getGraphics());

                                            statusBar1.setText("Mensagem:");    
                                        }//-------------------------------------------------------
        }
        
        public void mouseReleased (MouseEvent e) {
            if (esperaFimReta) {
                esperaInicioReta = true;
                esperaFimReta = false;
                desenhandoReta = false;
                RepintaTela();

                figuras.add (new Linha(p1.getX(), p1.getY(), e.getX(), e.getY(), corAtual));
                figuras.get(figuras.size()-1).torneSeVisivel(pnlDesenho.getGraphics());

                statusBar1.setText("Mensagem:");    
            }
            else
                if (esperaFimCirculo){
                    esperaInicioCirculo = true;
                    esperaFimCirculo = false;
                    desenhandoCirculo = false;
                    RepintaTela();
                    
                    figuras.add(new Circulo(p1.getX(), p1.getY(), e.getX(), e.getY(), corAtual, corAtualPreen ));
                    figuras.get(figuras.size()-1).torneSeVisivel(pnlDesenho.getGraphics());
                    
                    statusBar1.setText("Mensagem:");   
                }
                else
                    if(esperaFimElipse){
                        esperaFimElipse = false;
                        desenhandoElipse = false;
                        esperaInicioElipse = true;
                        RepintaTela();

                        figuras.add(new Elipse(p1.getX(), p1.getY(), e.getX(), e.getY(), corAtual, corAtualPreen));
                        figuras.get(figuras.size()-1).torneSeVisivel(pnlDesenho.getGraphics());

                        statusBar1.setText("Mensagem:");
                    }
                    else
                        if (esperaFimQuadrado) {
                            esperaFimQuadrado = false;
                            desenhandoQuadrado = false;
                            esperaInicioQuadrado = true;
                            RepintaTela();

                            figuras.add (new Quadrado(x, y, corAtual, corAtualPreen));
                            figuras.get(figuras.size()-1).torneSeVisivel(pnlDesenho.getGraphics());

                            statusBar1.setText("Mensagem:");    
                        }
                        else
                            if (esperaFimRetangulo) {
                                esperaFimRetangulo = false;
                                desenhandoRetangulo = false;
                                esperaInicioRetangulo = true;
                                RepintaTela();

                                figuras.add (new Retangulo(x, y, corAtual, corAtualPreen));
                                figuras.get(figuras.size()-1).torneSeVisivel(pnlDesenho.getGraphics());

                                statusBar1.setText("Mensagem:");
                            }
        }

        public void mouseClicked (MouseEvent e) {
            pnlDesenho.setFocusable(true);
            pnlDesenho.requestFocusInWindow();
            if(esperaSelect){
                for(int i = figuras.size()-1; i >= 0; i--){
                    p1 = new Ponto(e.getX(), e.getY());
                    if(figuras.elementAt(i).cliquePertence(p1.getX(), p1.getY()) == true){
                        selecionado = i;
                        System.out.println("Selecionada");   
                        i = -1;
                    }
                }
                //esperaSelect = false;
            }
            
        }
        
        public void mouseEntered (MouseEvent e) {
        }

        public void mouseExited (MouseEvent e) {
        }
        
        public void mouseDragged(MouseEvent e) {
            if (desenhandoReta) {
                RepintaTela();
                esperaFimReta = true;
                Graphics g = pnlDesenho.getGraphics();
                
                g.drawLine(e.getX(), e.getY(), p1.getX(), p1.getY());
                
            }
            else
                if(desenhandoCirculo){
                    esperaFimCirculo = true;
                    RepintaTela();

                    Circulo bola = new Circulo(p1.getX(), p1.getY(), e.getX(), e.getY(), corAtual, corAtualPreen );
                
                    bola.torneSeVisivel(pnlDesenho.getGraphics());
                }
                else
                    if (desenhandoElipse){
                        esperaFimElipse = true;
                        RepintaTela();

                        Elipse ovo = new Elipse(p1.getX(), p1.getY(), e.getX(), e.getY(), corAtual, corAtualPreen );

                        ovo.torneSeVisivel(pnlDesenho.getGraphics());
                    }
                    else
                        if (desenhandoQuadrado){
                            esperaFimQuadrado = true;
                                    
                            x[1] = e.getX();
                            y[1] = e.getY();
                            Quadrado quadradoDeDois = new Quadrado(x, y, corAtual, corAtualPreen);
                            
                            quadradoDeDois.torneSeVisivel(pnlDesenho.getGraphics());
                            RepintaTela();
                        }
                        else
                            if (desenhandoRetangulo) {
                                esperaFimRetangulo = true;

                                x[1] = e.getX();
                                y[1] = e.getY();
                                Retangulo quadradoDiferente = new Retangulo (x, y, corAtual, corAtualPreen);

                                quadradoDiferente.torneSeVisivel(pnlDesenho.getGraphics());
                                RepintaTela();
                            }
        }

        public void mouseMoved(MouseEvent e) {
            statusBar2.setText("Coordenada: "+e.getX()+","+e.getY());
        }

        public void keyTyped(KeyEvent ke) {
           //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        public void keyPressed(KeyEvent ke) {
            if(esperaSelect == true && ke.getKeyCode() == VK_DELETE){
              figuras.remove(selecionado);
              esperaSelect = false;
              RepintaTela();
           }
        }

        public void keyReleased(KeyEvent ke) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }

    private class DesenhoDePonto implements ActionListener {
        public void actionPerformed (ActionEvent e) {
            esperaDown = false;
            esperaUp = false;
            esperaMover = false;
            esperaSelect = false;
            
            esperaPonto      = true;
            esperaInicioReta = false;
            esperaFimReta    = false;
            esperaInicioCirculo = false;
            esperaFimCirculo = false;
            esperaInicioElipse = false;
            esperaFimElipse = false;
            esperaInicioQuadrado = false;
            esperaFimQuadrado = false;
            esperaInicioRetangulo = false;
            esperaFimRetangulo = false;
            esperaInicioPoligono = false;
            esperaFimPoligono = false;

            statusBar1.setText("Mensagem: clique o local do ponto desejado");
        }
    }

    private class DesenhoDeReta implements ActionListener {
        public void actionPerformed (ActionEvent e) {
            esperaDown = false;
            esperaUp = false;
            esperaMover = false;
            esperaSelect = false;
            
            esperaPonto      = false;
            esperaInicioReta = true;
            esperaFimReta    = false;
            esperaInicioCirculo = false;
            esperaFimCirculo = false;
            esperaInicioElipse = false;
            esperaFimElipse = false;
            esperaInicioQuadrado = false;
            esperaFimQuadrado = false;
            esperaInicioRetangulo = false;
            esperaFimRetangulo = false;
            esperaInicioPoligono = false;
            esperaFimPoligono = false;

            statusBar1.setText("Mensagem: clique o ponto inicial da reta");
        }
    }
    
    private class DesenhoDeCirculo implements ActionListener {
        public void actionPerformed (ActionEvent e) {
            esperaDown = false;
            esperaUp = false;
            esperaMover = false;
            esperaSelect = false;
            
            esperaPonto      = false;
            esperaInicioReta = false;
            esperaFimReta    = false;
            esperaInicioCirculo = true;
            esperaFimCirculo = false;
            esperaInicioElipse = false;
            esperaFimElipse = false;
            esperaInicioQuadrado = false;
            esperaFimQuadrado = false;
            esperaInicioRetangulo = false;
            esperaFimRetangulo = false;
            esperaInicioPoligono = false;
            esperaFimPoligono = false;

            statusBar1.setText("Mensagem: clique o ponto central do circulo");
        }
    }
    
    private class DesenhoDeElipse implements ActionListener {
        public void actionPerformed (ActionEvent e) {
            esperaDown = false;
            esperaUp = false;
            esperaMover = false;
            esperaSelect = false;
            
            esperaPonto      = false;
            esperaInicioReta = false;
            esperaFimReta    = false;
            esperaInicioCirculo = false;
            esperaFimCirculo = false;
            esperaInicioElipse = true;
            esperaFimElipse = false;
            esperaInicioQuadrado = false;
            esperaFimQuadrado = false;
            esperaInicioRetangulo = false;
            esperaFimRetangulo = false;         
            esperaInicioPoligono = false;
            esperaFimPoligono = false;

            statusBar1.setText("Mensagem: clique o ponto central da Elipse");
        }
    }
    private class DesenhoDeQuadrado implements ActionListener {//----------------------------------------------------------------------------
        public void actionPerformed (ActionEvent e) {
            esperaDown = false;
            esperaUp = false;
            esperaMover = false;
            esperaSelect = false;
            
            esperaPonto      = false;
            esperaInicioReta = false;
            esperaFimReta    = false;
            esperaInicioCirculo = false;
            esperaFimCirculo = false;
            esperaInicioElipse = false;
            esperaFimElipse = false;
            esperaInicioQuadrado = true;
            esperaFimQuadrado = false;
            esperaInicioRetangulo = false;
            esperaFimRetangulo = false;     
            esperaInicioPoligono = false;
            esperaFimPoligono = false;
            

            statusBar1.setText("Mensagem: clique o ponto central da quadrado");
        }
    }
    private class DesenhoDeRetangulo implements ActionListener {
        public void actionPerformed (ActionEvent e) {
            esperaDown = false;
            esperaUp = false;
            esperaMover = false;
            esperaSelect = false;
            
            esperaPonto      = false;
            esperaInicioReta = false;
            esperaFimReta    = false;
            esperaInicioCirculo = false;
            esperaFimCirculo = false;
            esperaInicioElipse = false;
            esperaFimElipse = false;
            esperaInicioQuadrado = false;
            esperaFimQuadrado = false;
            esperaInicioRetangulo = true;
            esperaFimRetangulo = false; 
            esperaInicioPoligono = false;
            esperaFimPoligono = false;

            statusBar1.setText("Mensagem: clique o ponto central da retangulo");
        }
    }//----------------------------------------------------------------------------
     private class DesenhoDePoligono implements ActionListener {
        public void actionPerformed (ActionEvent e) {
            esperaDown = false;
            esperaUp = false;
            esperaMover = false;
            esperaSelect = false;
            
            esperaPonto      = false;
            esperaInicioReta = false;
            esperaFimReta    = false;
            esperaInicioCirculo = false;
            esperaFimCirculo = false;
            esperaInicioElipse = false;
            esperaFimElipse = false;
            esperaInicioQuadrado = false;
            esperaFimQuadrado = false;
            esperaInicioRetangulo = false;
            esperaFimRetangulo = false; 
            esperaInicioPoligono = true;
            esperaFimPoligono = false;

            statusBar1.setText("Mensagem: clique o ponto central da retangulo");
        }
    }//----------------------------------------------------------------------------
     
     private class SelecionarImagem implements ActionListener{
        public void actionPerformed(ActionEvent e){
            esperaDown = false;
            esperaUp = false;
            esperaMover = false;
            esperaSelect = true;
            
            esperaPonto      = false;
            esperaInicioReta = false;
            esperaFimReta    = false;
            esperaInicioCirculo = false;
            esperaFimCirculo = false;
            esperaInicioElipse = false;
            esperaFimElipse = false;
            esperaInicioQuadrado = false;
            esperaFimQuadrado = false;
            esperaInicioRetangulo = false;
            esperaFimRetangulo = false; 
            esperaInicioPoligono = false;
            esperaFimPoligono = false;

            statusBar1.setText("Mensagem: clique para selecionar");
        }
    }
     
     private class MoverImagem implements ActionListener{
        public void actionPerformed(ActionEvent e){
            esperaDown = false;
            esperaUp = false;
            esperaMover = true;
            esperaSelect = esperaSelect; // no mods
            
            esperaPonto      = false;
            esperaInicioReta = false;
            esperaFimReta    = false;
            esperaInicioCirculo = false;
            esperaFimCirculo = false;
            esperaInicioElipse = false;
            esperaFimElipse = false;
            esperaInicioQuadrado = false;
            esperaFimQuadrado = false;
            esperaInicioRetangulo = false;
            esperaFimRetangulo = false; 
            esperaInicioPoligono = false;
            esperaFimPoligono = false;

            statusBar1.setText("Mensagem: clique para selecionar");
        }
    }
     
     private class Up implements ActionListener{
         public void actionPerformed(ActionEvent e){
             if (esperaSelect){
                try{
                   aux.removeAllElements();
                   aux.addAll(figuras);
                   figuras.set(selecionado, aux.elementAt(selecionado+1));
                   figuras.set(selecionado+1, aux.elementAt(selecionado));
                   selecionado++;
                   RepintaTela();
                } catch(Exception er){ }
             } else 
                 statusBar1.setText("Mensagem: Nenhuma imagem selecionada");
         }
     }
     
     private class Down implements ActionListener{
         public void actionPerformed(ActionEvent e){
             if (esperaSelect){
                try{
                   aux.removeAllElements();
                   aux.addAll(figuras);
                   figuras.set(selecionado, aux.elementAt(selecionado-1));
                   figuras.set(selecionado-1, aux.elementAt(selecionado));
                   selecionado--;
                   RepintaTela();
                }catch(Exception er){  }
             } else 
                 statusBar1.setText("Mensagem: Nenhuma imagem selecionada");
         }
     }
    
    private class EscolhaCorContorno implements ActionListener {
        public void actionPerformed (ActionEvent e) {
            JColorChooser javacor = new JColorChooser();
            Color corContorno = javacor.showDialog(btnCores, "Selecione a cor", Color.yellow);
            corAtual = corContorno;
        }
    }
    
    private class EscolhaCorPreenchimento implements ActionListener {
        public void actionPerformed (ActionEvent e) {
            JColorChooser javacor = new JColorChooser();
            Color corPreen = javacor.showDialog(btnCores, "Selecione a cor", Color.yellow);
            corAtualPreen = corPreen;
        }
    }

    private class FechamentoDeJanela extends WindowAdapter {
        public void windowClosing (WindowEvent e) {
            
            	String Texto=null; 
	String SaidaSalva=("Salvando"); 
	String SaidaSemSalvar=("Saindo sem salvar"); 
	Texto = JOptionPane.showInputDialog("Deseja salvar? (sim/nao)");
	if(Texto.equals("Sim")==true || Texto.equals("sim")==true){
            JOptionPane.showMessageDialog(null, SaidaSalva);
            String string1 = new String(SaidaSalva);
            System.out.println(string1);

            JFileChooser j= new JFileChooser();

            int returnVal = j.showSaveDialog(Janela.this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                try {
                    FileWriter fw = new FileWriter(j.getSelectedFile()+".paint");
                    for(int k = 0; k<figuras.size(); k++){
                        fw.write(figuras.elementAt(k).toString());
                        fw.write("\n");
                    }
                    fw.close();
                } catch (Exception ex) {
                     ex.printStackTrace();
                }
            }
            System.exit(0);
		
	}
	else{
		JOptionPane.showMessageDialog(null, SaidaSemSalvar);
		String string1 = new String(SaidaSemSalvar);
		System.out.println(string1);
                System.exit(0);		
	}
            
        }
    }
    
    private void RepintaTela(){
        
        try {
            Thread.sleep(10);                 //1000 milliseconds is one second.
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        pnlDesenho.resize(pnlDesenho.getHeight()+1, pnlDesenho.getWidth()+1);
        pnlDesenho.resize(pnlDesenho.getHeight()-1, pnlDesenho.getWidth()-1);
    }
    
    private class Salvar implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
                JFileChooser j= new JFileChooser();
                
               
                int returnVal = j.showSaveDialog(Janela.this);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                try {
                    FileWriter fw = new FileWriter(j.getSelectedFile()+".paint");
                    for(int k = 0; k<figuras.size(); k++){
                        fw.write(figuras.elementAt(k).toString());
                        fw.write("\n");
                    }
                    fw.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
               
        }
    }
    
    private class ParedeDeJanela implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            
            String Texto=null; 
            String SaidaSalva=("Salvando"); 
            String SaidaSemSalvar=("Saindo sem salvar"); 
            Texto = JOptionPane.showInputDialog("Deseja salvar? (sim/nao)");
            if(Texto.equals("Sim")==true || Texto.equals("sim")==true){
                JOptionPane.showMessageDialog(null, SaidaSalva);
                String string1 = new String(SaidaSalva);
                System.out.println(string1);

                JFileChooser j= new JFileChooser();

                int returnVal = j.showSaveDialog(Janela.this);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    try {
                        FileWriter fw = new FileWriter(j.getSelectedFile()+".paint");
                        for(int k = 0; k<figuras.size(); k++){
                            fw.write(figuras.elementAt(k).toString());
                            fw.write("\n");
                        }
                        fw.close();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            System.exit(0);
		
	}
	else{
		JOptionPane.showMessageDialog(null, SaidaSemSalvar);
		String string1 = new String(SaidaSemSalvar);
		System.out.println(string1);
                System.exit(0);		
	}
        }
    }
    
    private class Abrir implements ActionListener{
        public void actionPerformed(ActionEvent e){
            String p = "p";
            String l = "l";
            String c = "c";
            String el = "e";
            String r = "r";
            String q = "q";
            //String t = "t";
            aberto = false;
            JFileChooser j= new JFileChooser();
            int returnVal = j.showOpenDialog(Janela.this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
               try {
                   Scanner scanner = new Scanner(new FileReader(j.getSelectedFile())).useDelimiter("\\s*:\\s*|\\s*\n\\s*");
                   figuras.clear();
                   RepintaTela();
                   while (scanner.hasNext()) {
                       String tipo = scanner.next();
                       System.out.println("inicio");
                       System.out.println(tipo);
                       if(p.equals(tipo)){
                           System.out.println("entrou ponto");
                           figuras.add (new Ponto (Integer.parseInt(scanner.next()), 
                                                   Integer.parseInt(scanner.next()), 
                                                   new Color(Integer.parseInt(scanner.next()), 
                                                        Integer.parseInt(scanner.next()), 
                                                        Integer.parseInt(scanner.next())))
                                        );
                           figuras.get(figuras.size()-1).torneSeVisivel(pnlDesenho.getGraphics());
                           scanner.next();
                       }
                       if(l.equals(tipo)){
                           System.out.println("entrou linha");
                           figuras.add (new Linha(Integer.parseInt(scanner.next()), 
                                            Integer.parseInt(scanner.next()), 
                                            Integer.parseInt(scanner.next()), 
                                            Integer.parseInt(scanner.next()), 
                                            new Color(Integer.parseInt(scanner.next()), 
                                                    Integer.parseInt(scanner.next()), 
                                                    Integer.parseInt(scanner.next())))
                                        );
                           figuras.get(figuras.size()-1).torneSeVisivel(pnlDesenho.getGraphics());
                           scanner.next();
                       }
                       if(c.equals(tipo)){
                           System.out.println("entrou circ");
                           figuras.add(new Circulo(Integer.parseInt(scanner.next()), 
                                   Integer.parseInt(scanner.next()), 
                                   Integer.parseInt(scanner.next()), 
                                   Integer.parseInt(scanner.next()), 
                                   new Color(Integer.parseInt(scanner.next()), 
                                           Integer.parseInt(scanner.next()), 
                                           Integer.parseInt(scanner.next())), 
                                   new Color(Integer.parseInt(scanner.next()), 
                                           Integer.parseInt(scanner.next()), 
                                           Integer.parseInt(scanner.next()),
                                           Integer.parseInt(scanner.next())))
                                       );
                           figuras.get(figuras.size()-1).torneSeVisivel(pnlDesenho.getGraphics());
                       }
                       if(el.equals(tipo)){
                           System.out.println("entrou elipse");
                           figuras.add (new Elipse(Integer.parseInt(scanner.next()), 
                                   Integer.parseInt(scanner.next()), 
                                   Integer.parseInt(scanner.next()), 
                                   Integer.parseInt(scanner.next()), 
                                   new Color(Integer.parseInt(scanner.next()), 
                                           Integer.parseInt(scanner.next()), 
                                           Integer.parseInt(scanner.next())), 
                                   new Color(Integer.parseInt(scanner.next()), 
                                           Integer.parseInt(scanner.next()), 
                                           Integer.parseInt(scanner.next()),
                                           Integer.parseInt(scanner.next())))
                                        );
                           figuras.get(figuras.size()-1).torneSeVisivel(pnlDesenho.getGraphics());
                       }
                       if(q.equals(tipo)){
                           System.out.println("entrou quadrado");
                           int x[] = new int[99], y[] = new int[99];
                           x[0] = Integer.parseInt(scanner.next());
                           y[0] = Integer.parseInt(scanner.next());
                           Integer.parseInt(scanner.next());
                           Integer.parseInt(scanner.next());
                           x[1] = Integer.parseInt(scanner.next());
                           y[1] = Integer.parseInt(scanner.next());
                           Integer.parseInt(scanner.next());
                           Integer.parseInt(scanner.next());
                           Integer.parseInt(scanner.next());
                           figuras.add (new Quadrado(x, y, 
                                   new Color(Integer.parseInt(scanner.next()), 
                                           Integer.parseInt(scanner.next()), 
                                           Integer.parseInt(scanner.next())), 
                                   new Color(Integer.parseInt(scanner.next()), 
                                           Integer.parseInt(scanner.next()), 
                                           Integer.parseInt(scanner.next()),
                                           Integer.parseInt(scanner.next()))));
                           figuras.get(figuras.size()-1).torneSeVisivel(pnlDesenho.getGraphics());
                       }
                       if(r.equals(tipo)){
                           System.out.println("entrou retangulo");
                           int x[] = new int[99], y[] = new int[99];
                           x[0] = Integer.parseInt(scanner.next());
                           x[1] = Integer.parseInt(scanner.next());
                           y[0] = Integer.parseInt(scanner.next());
                           y[1] = Integer.parseInt(scanner.next());
                           Integer.parseInt(scanner.next());
                           Integer.parseInt(scanner.next());
                           figuras.add (new Retangulo(x, y, 
                                   new Color(Integer.parseInt(scanner.next()), 
                                           Integer.parseInt(scanner.next()), 
                                           Integer.parseInt(scanner.next())), 
                                   new Color(Integer.parseInt(scanner.next()), 
                                           Integer.parseInt(scanner.next()), 
                                           Integer.parseInt(scanner.next()),
                                           Integer.parseInt(scanner.next())))
                                        );
                           figuras.get(figuras.size()-1).torneSeVisivel(pnlDesenho.getGraphics());
                       }
//                       if(t.equals(tipo)){
//                           System.out.println("entrou texto");
//                           int x = Integer.parseInt(scanner.next());
//                           int y = Integer.parseInt(scanner.next());
//                           String txt =  scanner.next();
//                           Color cor1 = new Color(Integer.parseInt(scanner.next()), Integer.parseInt(scanner.next()), Integer.parseInt(scanner.next()));
//                           Color cor2 = new Color(Integer.parseInt(scanner.next()), Integer.parseInt(scanner.next()), Integer.parseInt(scanner.next()));
//                           int size = Integer.parseInt(scanner.next());
//                           String family = scanner.next();
//                           int style = Integer.parseInt(scanner.next());
//                           Font fonte =  new Font (family, style, size);
//
//                           //figuras.add (new Texto(x, y, txt, cor1, cor2, fonte));
//                           figuras.get(figuras.size()-1).torneSeVisivel(pnlDesenho.getGraphics());
//                       }
                       System.out.println("fim");
                   }
               }catch (Exception ex) {
                   ex.printStackTrace();
               }
            }
        }
    }

  //public Object  clone    ();
  //public         Janela   (Janela modelo);
  //public boolean equals   (Object obj);
  //public int     hashCode ();
  //public String  toString ();
}
