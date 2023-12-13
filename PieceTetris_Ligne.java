/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Hugo
 */
public class PieceTetris_Ligne extends PieceTetris {
    
    public PieceTetris_Ligne(int i){
        super();
        Coul couleur_ = new Coul_Cyan();
        couleur_.modif_couleur();
        this.setCoul(couleur_);
        
        int[][] tab =new int[][]{{1, 1, 1, 1}}; // Ligne horizontale
        this.setforme(tab);
    }
    
    
    
    
   
    
    
    
    /*@Override
    public void rotation_piece(int sens_rotation){
        
        CaseSimple[] c = get_Block().clone();
        boolean b= false;
        
        int y1 = get_CaseSimple(0).gety();
        int y2 = get_CaseSimple(1).gety();
        if (sens_rotation>=0){
            if(y1==y2){
                get_CaseSimple(0).modif_coord( this.get_CaseSimple(0).getx()+2, this.get_CaseSimple(0).gety()+1);
                get_CaseSimple(1).modif_coord( get_CaseSimple(0).getx(), get_CaseSimple(0).gety()-1);
                get_CaseSimple(2).modif_coord( get_CaseSimple(0).getx(), get_CaseSimple(0).gety()-2);
                get_CaseSimple(3).modif_coord( get_CaseSimple(0).getx(), get_CaseSimple(0).gety()-3);
                
                for(int i =0; i<getTaille();i++){
                    if(!get_CaseSimple(i).getGrilleSimple().validationCase(get_CaseSimple(i))){
                        b= true;
                    }
                }
            }
            else{
                get_CaseSimple(0).modif_coord( get_CaseSimple(0).getx()-1, get_CaseSimple(0).gety()-1);
                this.apparition_piece();
                
                for(int i =0; i<getTaille();i++){
                    if(!get_CaseSimple(i).getGrilleSimple().validationCase(get_CaseSimple(i))){
                        b= true;
                    }
                }
            }
        }     
                
        else {
            if(y1==y2){
                this.get_CaseSimple(0).modif_coord( this.get_CaseSimple(0).getx()+1, this.get_CaseSimple(0).gety()+1);
                get_CaseSimple(1).modif_coord( get_CaseSimple(0).getx(), get_CaseSimple(0).gety()-1);
                get_CaseSimple(2).modif_coord( get_CaseSimple(0).getx(), get_CaseSimple(0).gety()-2);
                get_CaseSimple(3).modif_coord( get_CaseSimple(0).getx(), get_CaseSimple(0).gety()-3);
                
                for(int i =0; i<getTaille();i++){
                    if(!get_CaseSimple(i).getGrilleSimple().validationCase(get_CaseSimple(i))){
                        b= true;
                    }
                }
            }
            else{
                get_CaseSimple(0).modif_coord( get_CaseSimple(0).getx()-2, get_CaseSimple(0).gety()-1);
                this.apparition_piece();
                
                for(int i =0; i<getTaille();i++){
                    if(!get_CaseSimple(i).getGrilleSimple().validationCase(get_CaseSimple(i))){
                        b= true;
                    }
                }
            }
        }
        if(b){
            this.set_Block(c);
        }

    }*/
}
