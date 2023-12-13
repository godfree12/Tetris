import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.io.FileWriter;
import java.io.IOException;


public class TetrisUI extends JFrame implements KeyListener,Observer {

    private static final int GRID_WIDTH = 20;
    private static final int GRID_HEIGHT = 20;
    private PieceTetris pieceCourante;
    private int[][] grid;
    private TetrisGrid tetrisGrid; // Dï¿½clarez la variable tetrisGrid ici
    private int nb_messagefin;
    private int nb_partie;


    /*private PieceTetris pieceCourante; // Piï¿½ce en cours
    private PieceTetris prochainePiece; // Prochaine piï¿½ce*/
    
     // Panneau pour afficher la prochaine piï¿½ce
    
    
    private JLabel scoreLabel;
    private JLabel pauseLabel;
    private JLabel reglesLabel;  // Utilisation d'un JLabel pour afficher le texte

    
    private Map<Integer, ToucheAction> actionsTouche = new HashMap<>(); // pour la gestion des touches
    
    Observer vueGrille;
    
    
    
    public TetrisUI() {
        nb_messagefin=0;
        nb_partie=0;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Tetris");
        setSize(900, 800);// taille de la fenetre
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        
        grid = new int[GRID_WIDTH][GRID_HEIGHT];
        tetrisGrid = new TetrisGrid(GRID_WIDTH, GRID_HEIGHT,grid);
        tetrisGrid.initialiser();
        //add(tetrisGrid);
        this.pieceCourante = tetrisGrid.getpieceCourante(); 
        
        VueTetrisGrid vueTetrisGrid = new VueTetrisGrid(tetrisGrid);
        vueGrille = vueTetrisGrid;
        add(vueTetrisGrid, BorderLayout.CENTER);
        
        
        PieceSuivantePanel pieceSuivantePanel_ = new PieceSuivantePanel(tetrisGrid.getNEXTPieceCourante());
        add(pieceSuivantePanel_, BorderLayout.EAST);
        
       
        
     // Initialisation du label de score
        scoreLabel = new JLabel("Score: " + tetrisGrid.getscore());
        add(scoreLabel, BorderLayout.SOUTH);          
        
    // Initialisation du label de jeu en pause
        pauseLabel = new JLabel("Prochaine PiÃ¨ce                                                                                                  Jeu en cours");
        add(pauseLabel, BorderLayout.NORTH);
        
        //Initialisation du label de regle du jeu
        
        reglesLabel = new JLabel("<html>" +
                "Liste de touche pour jouer: <br>" +
                "DÃ©placement Ã  gauche: Q ou â†� <br>" +
                "DÃ©placement Ã  droite: D ou â†’ <br>" +
                "DÃ©placement vers le bas: S ou â†“ <br>" +
                "Rotation +90Â°: E<br>" +
                "Rotation -90Â°: A<br>" +
                "Pause: Touche Espace<br>" +
                "Rejouer quand la partie est finie: R " +
                "</html>");

        add(reglesLabel, BorderLayout.EAST);
        
        addKeyListener(this);
        setFocusable(true);
        
        
        
        
        //init des events touche : 
        actionsTouche.put(KeyEvent.VK_LEFT, new ToucheGauche());
        actionsTouche.put(KeyEvent.VK_RIGHT, new ToucheDroite());
        actionsTouche.put(KeyEvent.VK_DOWN, new ToucheBas());
        actionsTouche.put(KeyEvent.VK_Q, new ToucheQ());
        actionsTouche.put(KeyEvent.VK_D, new ToucheD());
        actionsTouche.put(KeyEvent.VK_A, new ToucheA());
        actionsTouche.put(KeyEvent.VK_E, new ToucheE());
        actionsTouche.put(KeyEvent.VK_S, new ToucheS());
        actionsTouche.put(KeyEvent.VK_SPACE, new ToucheSpace());
        actionsTouche.put(KeyEvent.VK_X, new ToucheX());
        
        
        
       
        
    }
   

    

    
    /*private PieceTetris genererNouvellePiece() {
    	PieceTetris nouvellePiece = new PieceTetris(); // Exemple simple : gï¿½nï¿½rer une piï¿½ce alï¿½atoire
        System.out.println("Nouvelle piï¿½ce gï¿½nï¿½rï¿½e");
        // Logique pour gï¿½nï¿½rer une nouvelle piï¿½ce (ï¿½ adapter en fonction de votre implï¿½mentation)
        return nouvellePiece; // Exemple simple : gï¿½nï¿½rer une piï¿½ce alï¿½atoire
    }*/
    
    
    static long lastTime = System.currentTimeMillis();
    public void update(Observable o, Object arg) { // rafraichissement de la vue
        
        SwingUtilities.invokeLater(new Runnable() {
            //@Override
            public void run() {
                vueGrille.update(o, arg);
                
                lastTime = System.currentTimeMillis();
                
                updateScoreLabel(tetrisGrid);
                pieceCourante = tetrisGrid.getpieceCourante();
                
                mess_findejeu();
            }
        });

    }
    
    
    /*private class NextPiecePanel extends JPanel {

        private PieceTetris nextPiece;

        public NextPiecePanel(PieceTetris nextPiece) {
            this.nextPiece = nextPiece;
            setPreferredSize(new Dimension(100, 200));
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            int cellSize = 20;

            // Dessiner la prochaine piï¿½ce dans le panneau
            for (int i = 0; i < nextPiece.getForme().length; i++) {
                for (int j = 0; j < nextPiece.getForme()[0].length; j++) {
                    if (nextPiece.getForme()[i][j] != 0) {
                        g.setColor(Color.YELLOW); // Vous pouvez dï¿½finir une couleur en fonction de la valeur dans la piï¿½ce
                        g.fillRect(i * cellSize, j * cellSize, cellSize, cellSize);
                    }
                }
            }
        }
    }*/

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
        
        
        // Logique pour traiter les touches du clavier (gauche, droite, bas, rotation, etc.)
        // Mettre ï¿½ jour la grille en consï¿½quence
    	int keyCode = e.getKeyCode();

        ToucheAction action = actionsTouche.get(keyCode);
        
        
        
        if (action != null && !tetrisGrid.getpause() && !tetrisGrid.getfindejeu()) {
            action.executerAction(pieceCourante, GRID_WIDTH, GRID_HEIGHT, tetrisGrid);          
            
        }
        //updateGrid(tetrisGrid);

        // Mettre ï¿½ jour la grille aprï¿½s le dï¿½placement ou la rotation
       if (keyCode == KeyEvent.VK_SPACE) {
           tetrisGrid.updateScore(10) ;
            tetrisGrid.setpause(!(tetrisGrid.getpause()));
            updatePauseLabel(tetrisGrid);
        }
       if (keyCode == KeyEvent.VK_R && tetrisGrid.getfindejeu() ) {
            resetGame();
        }
    	
    }
    public TetrisGrid getTetrisGrid() {
        return tetrisGrid;
    }

    public void setTetrisGrid(TetrisGrid t){
        this.tetrisGrid =t;
    }
    
    private void updateScoreLabel(TetrisGrid tetrisgrid_) {
        if (nb_partie >0){
            scoreLabel.setText("Score: " + tetrisgrid_.getscore()+"                                        Meilleur score:"+ tetrisgrid_.getmeilleurs_score());
        }
        else{
            scoreLabel.setText("Score: " + tetrisgrid_.getscore());
        }
    }
    
      private void updatePauseLabel(TetrisGrid tetrisgrid_) {
        if (tetrisgrid_.getpause()){
            pauseLabel.setText("Prochaine PiÃ¨ce                                        Jeu en pause");
        }
        else{
            pauseLabel.setText("Prochaine PiÃ¨ce                                        Jeu en cours");
        }
    }
      
      
    public void mess_findejeu(){
        if (tetrisGrid.getfindejeu()&&nb_messagefin<1) {
          // Affichez un message Ã  la fin de la partie avec le score
          nb_messagefin=1;
          JOptionPane.showMessageDialog(this, "La partie est terminÃ©e. Votre score est : " + tetrisGrid.getscore());
        }
    }
      
      
    public void resetGame() {
        // RÃ©initialisez tous les objets et Ã©tats nÃ©cessaires ici
        tetrisGrid.reset(GRID_WIDTH,GRID_HEIGHT);  // Ajoutez une mÃ©thode reset dans votre classe TetrisGrid
        pieceCourante = tetrisGrid.getpieceCourante();
        nb_messagefin=0;
        nb_partie=nb_partie+1;
        
        // Mettez Ã  jour les labels si nÃ©cessaire
        updateScoreLabel(tetrisGrid);
        updatePauseLabel(tetrisGrid);
    
    
    }

      
      
      
      
      
      
      

    @Override
    public void keyReleased(KeyEvent e) {
    }

    public void startGame() {
        SwingUtilities.invokeLater(new Runnable()  {
            public void run() {    
                TetrisUI tetrisUI = new TetrisUI();
                TetrisGrid t = tetrisUI.getTetrisGrid();
                tetrisUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);              
                t.addObserver(tetrisUI);
                tetrisUI.setVisible(true);
            }
        });
    }
    
    
    public void endGame(int score) {
        // Enregistrez le score dans le fichier texte
        saveScore(score);
        // Affichez une boîte de dialogue avec le score
        JOptionPane.showMessageDialog(this, "Game Over! Your score is: " + score);
    }

    private void saveScore(int score) {
        try (FileWriter writer = new FileWriter("C:/Users/ASUS/eclipse-workspace/Lyon_Projet_Java/src/scores.txt", true)) {
            writer.write(tetrisGrid.getscore() + System.lineSeparator());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
