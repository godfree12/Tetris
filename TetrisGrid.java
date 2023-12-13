

import javax.swing.*;
import java.awt.*;
import java.util.Observable;

public class TetrisGrid extends Observable implements Runnable{

    private int gridWidth;
    private int gridHeight;
    private PieceTetris pieceCourante;
    private PieceTetris NEXTpieceCourante;
    private int[][] grid;
    private int score = 0; // Ajout de la variable de score
    private int meilleurs_score =0;
    
    private boolean pause;
    private boolean findejeu;

    public TetrisGrid(int width, int height,int[][] grid) {
        this.gridWidth = width;
        this.gridHeight = height;
        this.pieceCourante = pieceCourante;
        this.grid = grid;
        
        this.pause = false;
        this.findejeu = false;
        
        System.out.println("pieceCourante = "+ pieceCourante);
        
        PieceTetris pieceCourante_ = PieceTetris.genererFormeAleatoire();// G�n�rer la premi�re pi�ce
        PieceTetris prochainePiece_ = PieceTetris.genererFormeAleatoire();// G�n�rer la prochaine pi�ce
        
        this.pieceCourante = pieceCourante_;
        this.NEXTpieceCourante = prochainePiece_;
        
        nouvellePiecedansPartie();
        
        new OrdonnanceurSimple(this).start(); // pour changer le temps de pause, garder la référence de l'ordonnanceur 
    }
   
    
    // permet de remettre a zero l'etat du plateau
    public void initialiser() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = 0;
            }
        }
    }

    public int supprimerLigne() {
        int nb_ligne_detruite =0;
        int score_supp;
        for (int i = grid.length - 1; i >= 0; i--) {
            boolean ligneComplete = true;

            for (int j = 0; j < grid[i].length; j++) {
            	// cette condtion permet de verifier si toute la ligne est agee a 1 ou pas
                if (grid[i][j] != 1) {
                    ligneComplete = false;
                    break;  // Sortir de la boucle si au moins une case n'est pas égale à 1
                    
                }
            }

            if (ligneComplete) {
                nb_ligne_detruite=nb_ligne_detruite+1;
                // Supprimer la ligne en la remplaçant par la ligne du dessus (s'il en existe une)
                for (int k = i; k >0 ; k--) {
                    for (int l = 0; l < grid[k].length; l++) {
                        grid[k][l] = grid[k - 1][l];
                    }
                }
                // Remettre à zéro la première ligne
                for (int l = 0; l < grid[0].length; l++) {
                    grid[0][l] = 0;
                }
                
            }
        }
        switch(nb_ligne_detruite){
            case 1: 
                score_supp =40;
                break;
            case 2: 
                score_supp =100;
                break;
            case 3: 
                score_supp =300;
                break;
            case 4: 
                score_supp =1200;
                break;
            default: 
                score_supp =0;
        }
        return score_supp;
    }

    public void updateScore(int i) {
        score=score+i;
    }
    
    public int getscore(){
        return this.score;
    }
    
    
    private void updateGrid() {
    	if (this.getpieceCourante() != null) {
    	System.out.println("getx = "+ this.getpieceCourante().getX());
        System.out.println("gety = "+ this.getpieceCourante().getY());
        this.setPieceCoordinates(this.getpieceCourante().getX(), this.getpieceCourante().getY());
        // Logique pour mettre � jour la grille en fonction du mouvement de la pi�ce
        //tetrisGrid.repaint(); // Repaint la grille pour refl�ter les changements
    	}else {
    		System.out.println("piece Courante is null");
    	}
    }
    
    
    private void nouvellePiecedansPartie( ) {
        // Placer la premi�re pi�ce au milieu de la grille (ajustez ces valeurs en fonction de votre conception)
        int startX = (gridWidth - this.getpieceCourante().getForme().length) / 2;
        int startY = 2;
        this.getpieceCourante().setPosition(startX, startY);
        System.out.println("Apr�s l'initialisation de la pi�ce");

        updateGrid();

    }
    
    
    public IntroductionWindow fin_de_partie(){
    	return new IntroductionWindow();
    }
    
    
    
    
    
    
    
    
    
    public void run() {// boucle logique du jeu 
        
        if (!this.pause&&!this.findejeu){
            if (!pieceCourante.getdeplacable()){
                System.out.print("Fin piece ");
                ajouterPiece();
                int score_ =supprimerLigne();
                updateScore(score_);
                if(score== max_score()){
                    meilleurs_score= score;
                }
                this.pieceCourante= this.NEXTpieceCourante;
                nouvellePiecedansPartie();
                this.NEXTpieceCourante = PieceTetris.genererFormeAleatoire();
                if(!pieceCourante.test_plateau(this,this.pieceCourante.getForme(),0)){//mettre le message de fin de jeu
                    System.out.println("fin de jeu//////////////////////////////////////////////////////////////////////////");
                    this.findejeu = true;
                    
                    Coul_Pourpre couleur_ = new Coul_Pourpre() ; 
                    couleur_.modif_couleur();
                    pieceCourante.setCoul(couleur_);
                    NEXTpieceCourante.setCoul(couleur_);
                    
                    
   
                }
                

            }
            else{
                pieceCourante.deplacerBas(gridHeight, this);//si ca bloque -> deplacable devient false
            }
        }
        
        print_tab(grid);
        setChanged(); // setChanged() + notifyObservers() : notification de la vue pour le rafraichissement
        notifyObservers();
    }
    
    
    
    
    
    
public void ajouterPiece() {
    int[][] forme = pieceCourante.getforme();
    int hauteurPiece = forme.length;
    int largeurPiece = forme[0].length;
    /*System.out.println(pieceCourante.getX() );
    System.out.println(largeurPiece );
    System.out.println(grid[0].length+1 );
    System.out.println(pieceCourante.getX() + largeurPiece <= grid[0].length+1);
    
    System.out.println("" );
    
    System.out.println(pieceCourante.getY() );
    System.out.println(hauteurPiece );
    System.out.println(grid.length+1 );
    System.out.println(pieceCourante.getY() + hauteurPiece <= grid.length+1);*/
    
    // Vérifier que la pièce peut être ajoutée aux coordonnées spécifiées
    if (pieceCourante.getX() >= 0 && pieceCourante.getX() + hauteurPiece <= grid[0].length+1 && pieceCourante.getY() >= 0 && pieceCourante.getY() +largeurPiece  <= grid.length+1) {
        for (int i = 0; i < hauteurPiece; i++) {
            for (int j = 0; j < largeurPiece; j++) {
                // Ajouter la valeur de la pièce au plateau
                grid[pieceCourante.getY() + j][pieceCourante.getX() + i] += forme[i][j];
            }
        }
    } else {
        System.out.println("La pièce ne peut pas être ajoutée aux coordonnées spécifiées.");
    }
}
    
    
    public void print_tab(int[][] tab){
         for (int[] row : tab) {
            for (int value : row) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
    
    }
    
    
    
    
    public void setmeilleurs_score(int p) {
        this.meilleurs_score = p;
    }
    
     public int getmeilleurs_score() {
        return meilleurs_score;
    }
        
    public void setNEXTPieceCourante(PieceTetris p) {
        this.NEXTpieceCourante = p;
    }
    
     public PieceTetris getNEXTPieceCourante() {
        return NEXTpieceCourante;
    }

    public void setPieceCoordinates(int x, int y) {
    	try {
            if (pieceCourante != null) {
            	
                pieceCourante.setX(x);
                pieceCourante.setY(y);
                System.out.println("Piece coordinates updated: x=" + x + ", y=" + y);
            } else {
            	System.out.println("Setting coordinates: x=" + x + ", y=" + y);
                System.out.println("pieceCourante is null");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }    
    	
    }
    
    public PieceTetris getpieceCourante() {
        return pieceCourante;
    }

    public void setpieceCourante(PieceTetris p){
        this.pieceCourante = p;
    }
    public int[][] getgrid() {
        return grid;
    }

    public void setgrid(int[][] g){
        this.grid =g;
    }
    
    public int getgridHeight() {
        return gridHeight;
    }

    public void setgridHeight(int i){
        this.gridHeight = i;
    }
    public int getgridWidth() {
        return gridWidth;
    }

    public void setgridWidth(int i){
        this.gridWidth =i;
    }
    
     public boolean getpause() {
        return pause;
    }

    public void setpause(boolean b){
        this.pause =b;
    }
      public boolean getfindejeu() {
        return findejeu;
    }

    public void setfindejeu(boolean b){
        this.findejeu =b;
    }
    
    public int max_score(){
        if (score<meilleurs_score){
            return meilleurs_score;
        }
        else {
            return score;
        }
    }
    
    
    public void reset(int width, int height){
        this.gridWidth = width;
        this.gridHeight = height;
        initialiser();
        this.pause = false;
        this.findejeu = false;
        
        System.out.println("pieceCourante = "+ pieceCourante);
        
        PieceTetris pieceCourante_ = PieceTetris.genererFormeAleatoire();// G�n�rer la premi�re pi�ce
        PieceTetris prochainePiece_ = PieceTetris.genererFormeAleatoire();// G�n�rer la prochaine pi�ce
        
        this.pieceCourante = pieceCourante_;
        this.NEXTpieceCourante = prochainePiece_;
        
        nouvellePiecedansPartie();
        meilleurs_score = max_score();
        score =0;
    }
}


