/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Hugo
 */
public class PieceTetris_T extends PieceTetris{
    public PieceTetris_T(int i){
        super();
        Coul couleur_ = new Coul_Magenta();
        couleur_.modif_couleur();
        this.setCoul(couleur_);
        
        int[][] tab =new int[][]{{1, 1, 1}, {0, 1,0}};
        this.setforme(tab);
    }
    
    /*@Override
    public void rotation_piece(int sens_rotation){        //verif que new pose ok
        
        CaseSimple[] bloc2 = this.get_Block();
        boolean b = false;
        
        int min_x= this.min_x();
        int min_y= this.min_y();
        CaseSimple[][] Tab = new CaseSimple[3][3];
        for(int k =0; k<3;k++){
            for(int l=0; l<3;l++){
                Tab[k][l]=null;
            }
        }
        for(int p =0; p<this.getTaille();p++){
            Tab[this.get_CaseSimple(p).getx()-min_x][this.get_CaseSimple(p).gety()-min_y] =this.get_CaseSimple(p);
        }
        int x;
        int y;
        outerLoop:
        if (sens_rotation<=0){ // test si la ligne de 4 CaseSimple est verticale ou Horizontal
            for(int i =0; i<3;i++){
                for(int j =0; j<3;j++){
                    x= i;
                    y= j;
                    if(((i+j)%2==0)&&((i%2)==0)){
                        int tmp=y;
                        x=(x+2)%4;
                        y=x;
                        x= tmp;
                    }
                    else{
                        if ((i+j)%2==1){
                            int tmp = y;
                            
                            if (x==0){
                                x=(x+2);
                                
                            }
                            else{
                                x=(x+2);
                                x=x%2;
                            }
                            y=x;
                            x= tmp;
                        }
                    }
                    /*System.out.println("iteration : " + (j+3*i));
                    System.out.println("Valeur de i : " + i);
                    System.out.println("Valeur de j : " + j);
                    System.out.println("Valeur de x : " + x);
                    System.out.println("Valeur de y : " + y);
                    System.out.println("");*/
                    /*if(Tab[i][j]!=null){
                        if((get_CaseSimple(i).getGrilleSimple().validationDepla(min_x+x,min_y+y,get_CaseSimple(i)))){//get_CaseSimple(i) est a remplacer par un truc get_CaseSimple(i).getGrilleSimple().get_tab(new_x,new_y)
                            Tab[i][j].modif_coord(min_x+x,min_y+y);
                        }
                        else{
                            b= true;
                            break outerLoop ;
                        }
                    }
                    
                }
            }
        }
        else{
            for(int i =0; i<3;i++){
                for(int j =0; j<3;j++){
                    x= i;
                    y= j;
                    if(((i+j)%2==0)&&((i%2)==0)){
                        int tmp=x;
                        y=(y+2)%4;
                        x=y;
                        y= tmp;
                    }                    
                    else{
                        if ((i+j)%2==1){
                            int tmp = x;
                            
                            if (j==0){
                                y=(y+2);
                            }
                            else{
                                y=(y+2);
                                y=y%2;
                            }
                            x=y;
                            y= tmp;
                        }
                    }
                    /*System.out.println("iteration : " + (j+3*i));
                    System.out.println("Valeur de i : " + i);
                    System.out.println("Valeur de j : " + j);
                    System.out.println("Valeur de x : " + x);
                    System.out.println("Valeur de y : " + y);
                    System.out.println("");*/
                    /*if(Tab[i][j]!=null){
                        if((get_CaseSimple(i).getGrilleSimple().validationDepla(min_x+x,min_y+y,get_CaseSimple(i)))){//get_CaseSimple(i) est a remplacer par un truc get_CaseSimple(i).getGrilleSimple().get_tab(new_x,new_y)
                            Tab[i][j].modif_coord(min_x+x,min_y+y);
                        }
                        else{
                            b= true;
                            break outerLoop ;
                        }
                    }
                    
                }
            }
        }
        //System.out.println("iteration : " );
        //si la rotatio est impossible -> on est dans ce cas et on annule les modif
        if(b){
            this.set_Block(bloc2);
        }
    }*/
}
