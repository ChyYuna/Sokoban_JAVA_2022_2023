package sokoban_code;

public class ObjetImmobile extends Objet{
	//Attributs
		private char m_type;
		
	//Constructeurs 
	/**
	 * Constructeur par defaut 
	 */
	public ObjetImmobile() {}
	
	/**
	 * Construit un objet immobile selon les coordonnees x et y
	 * @param x entier contenant la coordonnee x de l'objet
	 * @param y entier contenant la coordonnee y de l'objet
	 * @param t caractere contenant le type de l'objet immobile
	 */	
	public ObjetImmobile(int x, int y, char type) {
		super(x,y);
		m_type = type;
	}
	
	//Methodes
	/**
	 * Donne acces au type de l'objet immobile ("#" : mur, "." : cible, "_" : vide)
	 * @return caractere contenant le type de l'objet immobile
	 */
	public char getType() {return m_type;}
	
	/**
	 * Permet de modifier le type de l'objet immobile
	 * @param type : caractere contenant le nouveau type de l'objet immobile
	 */
	public void setType(char type){m_type=type;}
}
