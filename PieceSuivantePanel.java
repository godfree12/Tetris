import javax.swing.*;
import java.awt.*;

public class PieceSuivantePanel extends JPanel {

    private PieceTetris pieceSuivante;

    public PieceSuivantePanel(PieceTetris pieceSuivante) {
        this.pieceSuivante = pieceSuivante;
        setPreferredSize(new Dimension(100, 200)); // Ajustez la taille selon vos besoins
    }

    
   
    /*@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int cellSize = 20; // Ajustez la taille selon vos besoins
        int[][] pieceForme = pieceSuivante.getForme();

        // D�terminer les dimensions de la grille et de la pi�ce suivante
        int gridWidth = 15;  // Remplacez par la largeur r�elle de votre grille
        int gridHeight = 20; // Remplacez par la hauteur r�elle de votre grille

        int pieceWidth = pieceForme.length;
        int pieceHeight = pieceForme[0].length;

        // Calculer la position x pour aligner la pi�ce suivante � droite de la grille
        int x = gridWidth * cellSize + 10; // Ajustez la marge � droite selon vos besoins

        // Calculer la position y pour centrer la pi�ce suivante verticalement
        int y = (gridHeight * cellSize - pieceHeight * cellSize) / 2;

        for (int i = 0; i < pieceWidth; i++) {
            for (int j = 0; j < pieceHeight; j++) {
                // Assurez-vous que les indices restent dans les limites du tableau
                if (i < pieceForme.length && j < pieceForme[0].length && pieceForme[i][j] != 0) {
                    g.setColor(Color.GREEN);
                    g.fillRect(x + i * cellSize, y + j * cellSize, cellSize, cellSize);
                }
            }
        }
    }*/


    public void setPieceSuivante(PieceTetris pieceSuivante) {
        this.pieceSuivante = pieceSuivante;
        repaint(); // Redessine la pi�ce suivante lorsqu'elle change
    }
}
