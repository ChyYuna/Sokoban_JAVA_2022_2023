package sokoban_code;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
public abstract class ObjetMobile extends Objet{
	//Attributs
	private String img_txt;
	private Image img;
	
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
	
	/**
	 * Donne acces a l'image de l'objet mobile dans l'etat
	 * @return String contenant l'image de l'objet mobile
	 */
	public Image getImg(){return img;}
	
	/**
	 * Permet de modifier l'image de l'objet mobile
	 * @param i : String contenant la nouvelle image de l'objet mobile
	 */
	public void setImg(String i){
		img_txt = i;
		try {
			img = ImageIO.read(new File(img_txt));
		}
		 catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}

	}
}
