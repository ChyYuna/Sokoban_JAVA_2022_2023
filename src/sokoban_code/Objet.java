package sokoban_code;

/**
 * Classe absraite permettant de dessiner le plateau de jeu
 */
public abstract class Objet {
	// Attribut
	private int m_x, m_y;

	// Constructeurs
	/**
	 * Constructeur par defaut
	 */
	public Objet() {
	}

	/**
	 * Construit l'objet selon ces coordonnees x et y
	 * 
	 * @param x entier contenant la coordonnee x de l'objet
	 * @param y entier contenant la coordonnee y de l'objet
	 */
	public Objet(int x, int y) {
		m_x = x;
		m_y = y;
	}

	// Methodes
	/**
	 * Permet d'acceder a la coordonnee x de l'objet
	 * 
	 * @return entier m_x
	 */
	public int getX() {
		return m_x;
	}

	/**
	 * Permet d'acceder a la coordonnee y de l'objet
	 * 
	 * @return entier m_y
	 */
	public int getY() {
		return m_y;
	}

	/**
	 * Permet de modifier la coordonnee x de l'objet
	 * 
	 * @param newX entier contenant la nouvelle coordonnee x de l'objet
	 * 
	 */
	public void setX(int newX) {
		m_x = newX;
	}

	/**
	 * Permet de modifier la coordonnee y de l'objet
	 * 
	 * @param newY entier contenant la nouvelle coordonnee y de l'objet
	 * 
	 */
	public void setY(int newY) {
		m_y = newY;
	}

}
