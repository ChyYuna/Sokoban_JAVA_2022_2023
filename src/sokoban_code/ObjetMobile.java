package sokoban_code;

public abstract class ObjetMobile extends Objet{
	
	//Constructeurs 
	/**
	 * Constructeur par defaut 
	 */
	public ObjetMobile() {}
	
	/**
	 * Construit un objet mobile selon les coordonnees x et y
	 * @param x entier contenant la coordonnee x de l'objet
	 * @param y entier contenant la coordonnee y de l'objet
	 */	
	public ObjetMobile(int x, int y) {
		super(x,y); 
	}
	
	//Methodes
	/**
	 * Deplace l'objet mobile (personnage ou caisse) de +dx selon x et +dy selon y
	 * @param dx entier contenant le deplacement x de l'objet
	 * @param dy entier contenant le deplacement y de l'objet
	 */	
	public void deplacerDe(int dx, int dy) {
		super.setX(super.getX() + dx);
		super.setY(super.getY() + dy);
	}
}
