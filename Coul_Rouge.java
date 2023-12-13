/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Hugo
 */
public class Coul_Rouge extends Coul{
    public Coul_Rouge(){
        super();
    }
    
    @Override
    public void modif_couleur(){
        this.set_rgb(0,255);
        this.set_rgb(1,0);
        this.set_rgb(2,0);
    }
}
