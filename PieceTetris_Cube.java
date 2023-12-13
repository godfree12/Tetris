/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Hugo
 */
public class PieceTetris_Cube extends PieceTetris{
    
    public PieceTetris_Cube(int i){
        super();
        Coul couleur_ = new Coul_Jaune();
        couleur_.modif_couleur();// modif la valeur rgb lié a la piece
        this.setCoul(couleur_);// modif la couleur dans la classe superieur PieceTetris
        
        int[][] tab =new int[][]{{1, 1}, {1, 1}};
        this.setforme(tab);
    }
    
    
    
    /*@Override
    public void rotation_piece(int sens_rotation){
    }*/
    
}
