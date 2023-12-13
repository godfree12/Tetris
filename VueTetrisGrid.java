import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Observable;
import java.util.Observer;

public class VueTetrisGrid extends Canvas implements Observer {
    private TetrisGrid tetrisGrid;
    private static final int TAILLE = 16;
    private BufferStrategy bufferStrategy;

    public VueTetrisGrid(TetrisGrid tetrisGrid) {
        this.tetrisGrid = tetrisGrid;
        Dimension dim = new Dimension(TAILLE * tetrisGrid.getgridWidth(), TAILLE * tetrisGrid.getgridHeight());
        setPreferredSize(dim);
    }

    private void createBufferStrategyIfNecessary() {
        if (bufferStrategy == null) {
            createBufferStrategy(2);
            bufferStrategy = getBufferStrategy();
        }
    }

    @Override
    public void paint(Graphics g) {
    	
        
        super.paint(g);
        createBufferStrategyIfNecessary();
        setBackground(Color.BLACK);

        int cellSize = 30;

        // Dessiner la grille en fonction du tableau bidimensionnel
        for (int i = 0; i < tetrisGrid.getgridWidth(); i++) {
            for (int j = 0; j < tetrisGrid.getgridHeight(); j++) {
                if (tetrisGrid.getgrid()[i][j] != 0) {
                    //Coul couleur_ = Coul.random_couleurs(); // mode epileptie
                    Coul_Pourpre couleur_ = new Coul_Pourpre() ; 
                    couleur_.modif_couleur();
                    Color c_ = new Color(couleur_.get_r(), couleur_.get_g(), couleur_.get_b());
                    g.setColor(c_);
                    //g.setColor(Color.RED); fonction de base sans la modif classe Coul
                    
                    g.fillRect(j * cellSize, i * cellSize, cellSize, cellSize);
                } else {
                    g.setColor(Color.GRAY);
                    g.drawRect(j * cellSize, i * cellSize, cellSize, cellSize);
                }
            }
        }

        // Dessiner la pièce courante
        dessin_piece(tetrisGrid.getpieceCourante(), g, cellSize );
        
        
        // Dessiner la pièce suivante
        dessin_piece(tetrisGrid.getNEXTPieceCourante(), g, cellSize );
    }

    @Override
    public void update(Observable o, Object arg) {
        if (bufferStrategy != null) {
            paint(bufferStrategy.getDrawGraphics());
            bufferStrategy.show();
        }
    }
    
    
    
    public void dessin_piece(PieceTetris p,Graphics g,int cellSize ){//tetrisGrid.getNEXTPieceCourante()
        // Dessiner la pièce 
        int[][] pieceForm_ = p.getForme();
        int pieceX_ = p.getX();
        int pieceY_ = p.getY();

        for (int i = 0; i < pieceForm_.length; i++) {
            for (int j = 0; j < pieceForm_[0].length; j++) {
                if (pieceForm_[i][j] != 0) {
                    int cellX = pieceX_ + i;
                    int cellY = pieceY_ + j;

                    if (cellX >= 0 && cellX < tetrisGrid.getgridWidth() && cellY >= 0 && cellY < tetrisGrid.getgridHeight()) {
                        Color c = new Color(p.getCoul().get_r(), p.getCoul().get_g(), p.getCoul().get_b());
                        g.setColor(c);
                        g.fillRect(cellX * cellSize, cellY * cellSize, cellSize, cellSize);
                    }
                }
            }
        }
    }
}