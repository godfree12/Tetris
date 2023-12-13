/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Hugo
 */
public class ToucheE implements ToucheAction {
    @Override
    public void executerAction(PieceTetris piece, int gridWidth, int gridHeight, TetrisGrid tetrisgrid_) {//permet de rotate la piece vers la gauche
        piece.rotationPiecePLUS90(gridWidth,gridWidth, tetrisgrid_ );
    }
}
