/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Hugo
 */
public class ToucheA implements ToucheAction {//permet de rotate la piece vers la droite
    @Override
    public void executerAction(PieceTetris piece, int gridWidth, int gridHeight, TetrisGrid tetrisgrid_) {
        piece.rotationPieceMOINS90(gridWidth,gridWidth, tetrisgrid_ );
    }
}