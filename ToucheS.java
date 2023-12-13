/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Hugo
 */
public class ToucheS implements ToucheAction {//permet de descendre la piece d'une case plus bas
    @Override
    public void executerAction(PieceTetris piece, int gridWidth, int gridHeight, TetrisGrid tetrisgrid_) {
        piece.deplacerBas(gridHeight, tetrisgrid_);
    }
}
