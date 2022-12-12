package sokoban_code;

import java.io.*;
import java.util.Scanner;

public class Configuration {
	//Attributs
	private String[] m_listeCodes;
    private int m_nbNiveau, m_niveauALancer;	
    
  //Constructeurs  	
  	/**
  	 * Constructeur (lit le nombre de niveaux du jeu et la liste des codes des niveaux)
  	 */
  	public Configuration(){
  	    m_niveauALancer = -1; //Par default, le niveau a lancer est -1, c'est a dire que rien ne se lance
  	    File fichier = new File("codes.txt");

	    try {
			Scanner scanner = new Scanner(fichier);
			m_nbNiveau = scanner.nextInt();
	    	m_listeCodes = new String[m_nbNiveau];
	    	
	    	for (int i=0; i<m_nbNiveau; i++) //On remplie notre liste avec les codes contenus dans le fichier
	    	{
	    		m_listeCodes[i] = scanner.nextLine();
	    	}
		    scanner.close();
	    }
	    catch (FileNotFoundException e) {
	        e.printStackTrace();
	    }
  	}
  	
  //Methodes
  	/**
  	 * Donne acces a la liste des code des niveaux du jeu
  	 * @return vecteur contenant les codes
  	 */
  	public String[] getListeCodes(){
  	    return m_listeCodes;
  	}
  	
  	/**
  	 * Donne acces au nombre de niveaux du jeu
  	 * @return entier contenant le nombre de niveaux
  	 */
  	public int getNbNiv(){return m_nbNiveau;}

  	/**
  	 * Donne acces au niveau que le jeu doit lancer
  	 * @return entier contenant le niveau a lancer
  	 */
  	public int getNivAlancer(){return m_niveauALancer;}

  	/**
  	 * Permet de modifier le niveau que le jeu doit lancer
  	 * @param niv : entier contenant le nouveau niveau a lancer
  	 */
  	public void setNivALancer(int niv){m_niveauALancer = niv;}
}
