/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Hugo
 */
public class ToucheQ implements ToucheAction {//permet de deplacer la piece d'une case à gauche
    @Override
    public void executerAction(PieceTetris piece, int gridWidth, int gridHeight, TetrisGrid tetrisgrid_) {
        piece.deplacerGauche(gridWidth, tetrisgrid_);
    }
}
