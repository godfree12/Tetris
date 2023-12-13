

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PieceTetris {

    private int[][] forme;
    
    private int x;
    private int y;
    private Coul couleurs;
    private boolean deplacable;

    // Constructeur par d�faut
    public PieceTetris() {
        // Exemple : g�n�ration al�atoire d'une pi�ce (� adapter)
        //this.forme = genererFormeAleatoire();
        this.x = 1;
        this.y = 0;
        this.deplacable = true;
    }
    
   

    // M�thode pour g�n�rer une forme de pi�ce al�atoire (exemple)
    public static PieceTetris genererFormeAleatoire() {
        List<Class<? extends PieceTetris>> PieceTetris_Class = new ArrayList<>();
        
        PieceTetris_Class.add(PieceTetris_T.class);
        PieceTetris_Class.add(PieceTetris_L_G.class);
        PieceTetris_Class.add(PieceTetris_L_D.class);
        PieceTetris_Class.add(PieceTetris_S_G.class);
        PieceTetris_Class.add(PieceTetris_S_D.class);
        PieceTetris_Class.add(PieceTetris_Ligne.class);
        PieceTetris_Class.add(PieceTetris_Cube.class);
        
        Random random = new Random();
        int formeAleatoire = random.nextInt(PieceTetris_Class.size()); // Vous pouvez ajuster ce nombre en fonction du nombre total de formes
        
        try {
            Class<? extends PieceTetris> selectedClass = PieceTetris_Class.get(formeAleatoire);
            Constructor<? extends PieceTetris> constructor = selectedClass.getDeclaredConstructor(int.class);
            
            return constructor.newInstance(4);
        }catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }
    }     
           
            
		
    
    public void rotationPiecePLUS90(int gridWidth, int gridHeight, TetrisGrid tetrisgrid_) {
    int rows = forme.length;
    int cols = forme[0].length;

    int[][] rotatedPiece = new int[cols][rows];

    for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {
            rotatedPiece[j][rows - 1 - i] = forme[i][j];
        }
    }

    // Vérifier si la nouvelle position est en dehors des limites de la grille
    int nouvelleX = x;
    int nouvelleY = y;

    if (nouvelleX < 0) {
        nouvelleX = 0;
    } else if (nouvelleX + rotatedPiece.length > gridWidth) {
        nouvelleX = gridWidth - rotatedPiece.length;
    }

    if (nouvelleY + rotatedPiece[0].length > gridHeight) {
        nouvelleY = gridHeight - rotatedPiece[0].length;
    }

    // Appliquer la rotation et l'ajustement de position
    forme = rotatedPiece;
    x = nouvelleX;
    y = nouvelleY;

    System.out.println("Rotation effectuée");
}

    public void rotationPieceMOINS90(int gridWidth, int gridHeight, TetrisGrid tetrisgrid_) {
        int rows = forme.length;
        int cols = forme[0].length;

        int[][] rotatedPiece = new int[cols][rows];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                rotatedPiece[j][i] = forme[i][cols - 1 - j];
            }
        }

        // Vérifier si la nouvelle position est en dehors des limites de la grille
        int nouvelleX = 0; // Pour cet exemple, nous fixons x à 0
        int nouvelleY = 0; // Pour cet exemple, nous fixons y à 0

        if (nouvelleY + rotatedPiece[0].length > gridHeight) {
            nouvelleY = gridHeight - rotatedPiece[0].length;
        }

        // Appliquer la rotation et l'ajustement de position
        if (test_plateau(tetrisgrid_, rotatedPiece,0)){
            //print_tab(forme);
            forme = rotatedPiece;
            //print_tab(forme);
        }
        
        

        System.out.println("Rotation effectuée");
    }
    
    
    public void print_tab(int[][] tab){
         for (int[] row : tab) {
            for (int value : row) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
    
    }

    public boolean test_plateau(TetrisGrid tetrisgrid_, int[][] tab, int p){
        
        int hauteurPiece = tab.length;
        int largeurPiece = tab[0].length;
        boolean b = true;
        for (int i = 0; i < hauteurPiece; i++) {
            for (int j = 0; j < largeurPiece; j++) {             // p sert au deplacement lateraux et evite de se mettre dans les parois
                if (tetrisgrid_.getgrid()[y + j+1][x + i+ p] != 0 && tab[i][j] != 0) {// -1 -> gauche, +1 droite et 0 sinon (rotation et en bas)
                    b=false;
                    break;
                }
            }
        }
        return b;
    }
    
    public void deplacerGauche(int gridWidth, TetrisGrid tetrisgrid_) {// check case du tab et si dans grille
    	System.out.println("D�placer � gauche");
    	if (x > 0&& test_plateau(tetrisgrid_,this.forme,-1)) {
            x--; // D�placer la pi�ce � gauche si elle n'est pas d�j� � la limite gauche
        }// D�placer la pi�ce d'une case vers la gauche
    }

    public void deplacerDroite(int gridWidth, TetrisGrid tetrisgrid_) {// check case du tab et si dans grille
    	System.out.println("D�placer � droite");
    	if (x + forme.length < gridWidth&& test_plateau(tetrisgrid_,this.forme,+1)) {
            x++; // D�placer la pi�ce � droite si elle n'est pas d�j� � la limite droite
        }
    }

    public void deplacerBas(int gridHeight, TetrisGrid tetrisgrid_) {// check case du tab et si dans grille
    	System.out.println("D�placer en bas");
    	if (y + forme[0].length < gridHeight && test_plateau(tetrisgrid_,this.forme,0)) {
            y++; // D�placer la pi�ce vers le bas si elle n'est pas d�j� � la limite inf�rieure
        }
        else {
            this.deplacable = false;
        }
         System.out.println(this.getY() ); 
    }
    
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Getters et setters pour les propri�t�s de la pi�ce (forme, position, etc.)
    public int[][] getForme() {
        return forme;
    }

    public int getX() {
    	
        return x;
    }

    public int getY() {
    	
        return y;
    }
    public void decalage() {
    	System.out.println(x);
    }
    
      public Coul getCoul() {
        return couleurs;
    }

    public void setCoul(Coul c){
        this.couleurs = c;
    }
    
      public int[][] getforme() {
        return forme;
    }

    public void setforme(int[][] tab){
        this.forme = tab;
    }
     public boolean getdeplacable() {
        return deplacable;
    }

    public void setdeplacable(boolean b){
        this.deplacable =b;
    }
    // Autres m�thodes en fonction des besoins de votre jeu Tetris
}
