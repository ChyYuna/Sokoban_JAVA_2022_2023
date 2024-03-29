package sokoban_code;

/**
 * Classe representant un personnage
 * 
 * @version 1.0
 * @see ObjetMobile
 */
public class Personnage extends ObjetMobile {
	// Constructeurs
	/**
	 * Constructeur par defaut
	 */
	public Personnage() {
	}

	/**
	 * Construit l'objet personnage selon les coordonnees x et y
	 * 
	 * @param x entier contenant la coordonnee x de l'objet
	 * @param y entier contenant la coordonnee y de l'objet
	 */
	public Personnage(int x, int y) {
		super(x, y);
		setImg(":/images/Joueur/playerDown.png");
	}
}
