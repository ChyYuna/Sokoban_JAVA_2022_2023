package sokoban_code;

import java.io.*;
import java.util.Scanner;

public class Partie {
	//Attributs
	private int m_niv=1, m_nbCaisses=1;
	private Personnage m_perso;
	private Caisse[] m_c;
	private Plateau m_plat;
	
	//Constructeurs 
	/**
     * Constructeur par defaut 
	 */
	public Partie(){
	    m_c = new Caisse[m_nbCaisses];
	    for (int i=0; i<m_nbCaisses; i++){
	        m_c[i] = new Caisse();
	    }
	}
	
	//Methodes
	/**
	 * Permet de lancer un niveau
	 * @param niv : entier contenant le numero du niveau a lancer
	 */
	public void lancerNiveau(int niv) {
		File fichier = new File("./niveau+codes/"+String.valueOf(niv)+".txt");// niv àà la place du 2
	    
	    int k=0; // L'entier k permet de numeroter les caisses et de les manipuler dans le tableau les contenant

	    try {
			Scanner scanner = new Scanner(fichier);
	 
	    	m_niv  = scanner.nextInt();
	    	m_nbCaisses  = scanner.nextInt();
	    	
	    	m_plat = new Plateau(1,2, scanner); // ne fonctionne pas. Essayer avec scanner à la place? 
	    	m_perso = new Personnage();
	        m_c = new Caisse[m_nbCaisses]; // En lancant un nouveau niveau, la liste m_c (contentant les caisses) doit etre construite en fonction du nombre de caisse m_nbCaisses du niveau
	        for (int i=0; i<m_nbCaisses; i++)
	             m_c[i] = new Caisse();
	        for (int i=0; i<m_plat.getLongueur(); i++){
	            for (int j=0; j<m_plat.getLargeur(); j++){
	                if (m_plat.getTab()[j][i].getType() == '@' || m_plat.getTab()[j][i].getType() == '+') {
	                    m_perso.setX(j);
	                    m_perso.setY(i);
	                    
	                    m_perso.setImg("Joueur/playerDown.png");
	                }
	                if (m_plat.getTab()[j][i].getType() == '$'){
	                    m_c[k].setX(j);
	                    m_c[k].setY(i);
	                    m_c[k].setImg("Caisse/caisse.png");
	                    k++;
	                }
	                if (m_plat.getTab()[j][i].getType() == '*'){
	                    m_c[k].setX(j);
	                    m_c[k].setY(i);
	                    m_c[k].setImg("Caisse/caisseSurCible.png");
	                    k++;
	                }
	            }
	        }
		    scanner.close();
	    }
	    catch (FileNotFoundException e) {
	        e.printStackTrace();
	    }
	}
	
	/**
	 * Donne acces au plateau actuel de la partie
	 * @return Objet de type Plateau
	 */
	public Plateau getPlateau() {return m_plat;}
	
	
	public char getPlateauElt(int i, int j) {return m_plat.getElt(i,j);}
	/**
	 * Donne acces aux caisses de la partie
	 * @return Vecteur contenant les caisses
	 */
	public Caisse[] getCaisses(){return m_c;}
	
	/**
	 * Donne acces au personnage de la partie
	 * @return Objet de type Personnage
	 */
	public Personnage getPerso(){return m_perso;}
	
	/**
	 * Donne acces au niveau actuel
	 * @return entier contenant le niveau actuel
	 */
	public int getNiv(){return m_niv;}
	
	/**
	 * Permet de modifier le numero du niveau actuel
	 * @param n : entier contenant le nouveau niveau
	 */
	public void setNiv(int n){m_niv=n;}
	
	/**
	 * Reinitialise le niveau actuel
	 */
	public void recommencerNiveau(){
	    lancerNiveau(m_niv);
	}
	
	/**
	 * Donne acces au nombre de caisse du niveau actuel
	 * @return entier contenant le nombre de caisse
	 */
	public int getNbCaisses(){return m_nbCaisses;}

	/**
	 * Permet de modifier le nombre de caisse
	 * @param nbCaisses : entier contenant le nouveau nombre de caisses
	 */
	public void setNbCaisses(int nbCaisses){m_nbCaisses=nbCaisses;}
	
	/**
	 * Permet de savoir si une case contient une caisse
	 * @param x : entier contenant la coordonnee x de la case visee
	 * @param y : entier contenant la coordonnee y de la case visee
	 * @return entier contenant le numero de la caisse si la case visee la contient et -1 sinon
	 */
	public int estCaisse(int x, int y){
	    for(int i=0; i<m_nbCaisses; i++){
	        if(m_c[i].getX()==x && m_c[i].getY()==y)
	            return i;
	    }
	    return -1;
	}
	
	/**
	 * Permet de savoir si une case est vide
	 * @param x : entier contenant la coordonnee x de la case visee
	 * @param y : entier contenant la coordonnee y de la case visee
	 * @return True si la case visee est vide et False sinon
	 */
	public boolean estVide(int x, int y) {
		System.out.println("Dans EstVide, j'appelle x = " + String.valueOf(x) + " y = " + String.valueOf(y));
		System.out.println("Dans m_plateau, getTab()[x= " + String.valueOf(x) + "][y= " + String.valueOf(y) + "] a pour coordonnées X = "
				+String.valueOf(m_plat.getTab()[x][y].getX()) + " Y = " + String.valueOf(m_plat.getTab()[x][y].getY()));
	    return (m_plat.getTab()[x][y].getType() != '#' && estCaisse(x,y)==-1);
	}
	
	/**
	 * Permet de savoir si une caisse donnee est sur une cible
	 * @param c : objet de type caisse
	 * @return True si la caisse est sur une cible et False sinon
	 */
	public boolean caisseSurCible(Caisse c){
	    char type = m_plat.getTab()[c.getX()][c.getY()].getType();
	    if(type=='.' || type=='+' || type=='*'){
	    	c.setImg("Caisse/caisseSurCible.png");
	        return true;
	     }
	     else {
	    	c.setImg("Caisse/caisse.png");
	        return false;
	    }
	}
	
	//Renvoie vrai si le niveau est termine et faux sinon
	/**
	 * Permet de savoir si un niveau est termine
	 * @return True si le niveau est finit (chaque cible contient une caisse) et False sinon
	 */
	public boolean victoire(){
	    int k=0;
	    for(int i=0; i<m_nbCaisses; i++){
	        if(caisseSurCible(m_c[i]))
	            k++;
	    }
	    return(k==m_nbCaisses);
	}
}
