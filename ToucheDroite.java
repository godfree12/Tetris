/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Hugo
 */
public class ToucheDroite implements ToucheAction {//permet de deplacer la piece d'une case à droite
    @Override
    public void executerAction(PieceTetris piece, int gridWidth, int gridHeight, TetrisGrid tetrisgrid_) {
        piece.deplacerDroite(gridWidth, tetrisgrid_);
    }
}
