package sokoban_code;

/**
 * Classe permettant de dessiner le plateau de jeu
 */
public class Caisse extends ObjetMobile {
	// Constructeurs
	/**
	 * Constructeur par defaut
	 */
	public Caisse() {
	}

	/**
	 * Construit l'objet personnage selon les coordonnees x et y
	 * 
	 * @param x entier contenant la coordonnee x de l'objet
	 * @param y entier contenant la coordonnee y de l'objet
	 */
	public Caisse(int x, int y) {
		super(x, y);
		setImg(":/images/Caisse/caisse.png");
	}
}
