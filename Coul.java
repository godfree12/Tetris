/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/**import Modele.GrilleSimple;
 *
 * @author Hugo
 */

abstract public class Coul {
    
    private int[] couleur_rgb;
    
    public Coul(){
        couleur_rgb = new int[3];//
        for (int j=0; j<3;j++){
            couleur_rgb[j]=0;
        }
    }
    public void set_rgb(int i, int n){
        this.couleur_rgb[i]= n;
    }
    
    public int get_r() {
        return couleur_rgb[0];
    }
    public int get_g() {
        return couleur_rgb[1];
    }
    public int get_b() {
        return couleur_rgb[2];
    }
    
    abstract void modif_couleur();
    
    public static Coul random_couleurs(){
         List<Class<? extends Coul>> Coul_Class = new ArrayList<>();
         int index_random;
         
        Coul_Class.add(Coul_Rouge.class);
        Coul_Class.add(Coul_Jaune.class);
        Coul_Class.add(Coul_Bleu.class);
        Coul_Class.add(Coul_Vert.class);
        Coul_Class.add(Coul_Cyan.class);
        Coul_Class.add(Coul_Magenta.class);
        Coul_Class.add(Coul_Orange.class);
        
        // générateur aléatoire pour choisir un élément au hasard dans la liste
        Random random = new Random();
        index_random = random.nextInt(Coul_Class.size());
         try {
            return Coul_Class.get(index_random).newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }
};
