package sokoban_code;

import java.io.*;
import java.util.Scanner;

public class Plateau extends Objet{
	//Attributs
    private int m_largeur=5, m_longueur=5;
    private ObjetImmobile[][] tab;
    
	//Constructeurs 
	/**
	 * Constructeur par defaut 
	 */
	public Plateau() {
		super(); 
		tab = new ObjetImmobile[m_longueur][m_largeur];
		for (int i=0; i<m_largeur; i++){
	        for (int j=0; j<m_longueur; j++){
	            tab[i][j] = new ObjetImmobile(i,j,'_');
	        }
	    }
	}
	
	/**
	 * Constructeur (cree le plateau de chaque niveau)
	 * @param x : entier contenant la coordonnee x du plateau
	 * @param y : entier contenant la coordonnee y du plateau
	 * @param largeur : entier contenant la largeur du plateau
	 * @param longueur : entier contenant la longueur du plateau
	 */
	public Plateau(int x, int y, int largeur, int longueur) {
		super(x,y); 
		m_largeur = largeur;
		m_longueur = longueur;
		tab = new ObjetImmobile[m_longueur][m_largeur];
		for (int i=0; i<m_largeur; i++){
	        for (int j=0; j<m_longueur; j++){
	            tab[i][j] = new ObjetImmobile(i,j,'_');
	        }
	    }
	}
	
	/**
	 * Constructeur supplementaire
	 * @param x : entier contenant la coordonnee x du plateau
	 * @param y : entier contenant la coordonnee y du plateau
	 * @param fichier : ifstream du fichier contenant les elements du plateau du niveau en cours
	 */
	public Plateau(int x, int y, Scanner scanner){
	super(x,y); 
		//DrawImage(i,j) -> i = deplacement vers la droite || j = déplacement vers le bas
		// donc ici, i in [0 : m_largeur-1] || j in [0 : m_longeur-1]
		// Mais la lecture du fichier se fait par "longueur" ,donc on doit parcourir ainsi
		m_longueur = scanner.nextInt();
		m_largeur = scanner.nextInt();
	    tab = new ObjetImmobile[m_largeur][m_longueur];
		for (int i=0; i<m_longueur; i++){
			String line_type = scanner.next();
			for (int j=0; j<m_largeur; j++){
				tab[j][i] = new ObjetImmobile(j,i,line_type.charAt(j)); // x , y, comme sur un plan (ne pas penser matriciellement) 
			}
			//System.out.println(line_type);
		}
		//
		//catch (FileNotFoundException e) {
	    //    e.printStackTrace();
	    //}
	}
	//Methodes
	/**
	 * Donne acces aux elements du plateau (contenus dans un tableau d'ObjetImmobile)
	 * @return Objet de type ObjetImmobile**
	 */
	public ObjetImmobile[][] getTab(){return tab;}
	
	public char getElt(int i, int j) {return tab[i][j].getType();} 
	/**
	 * Donne acces a la largeur du plateau actuel
	 * @return entier contenant la largeur du plateau
	 */
	public int getLargeur(){return m_largeur;}
	
	/**
	 * Donne acces a la longueur du plateau actuel
	 * @return entier contenant la largeur du plateau
	 */
	public int getLongueur(){return m_longueur;}
	
	/**
	 * Permet de modifier le tableau contenant les elements du plateau
	 * @param T : ObjetImmobile** contenant le nouveau tableau
	 */
	public void setTab(ObjetImmobile[][] T){tab = T;}

	/**
	 * Permet de modifier la largeur du plateau
	 * @param largeur : entier contenant la nouvelle largeur
	 */
	public void setLargeur(int largeur){m_largeur = largeur;}

	/**
	 * Permet de modifier la longueur du plateau
	 * @param largeur : entier contenant la nouvelle longueur
	 */
	public void setLongueur(int longueur){m_longueur = longueur;}
}
