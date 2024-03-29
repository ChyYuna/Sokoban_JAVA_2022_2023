package sokoban_code;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Classe permettant de gerer les evenements clavier
 */
public class MyKeyEvent extends KeyAdapter {
	private Partie partie_aff = new Partie();

	/**
	 * Constructeur
	 */
	public MyKeyEvent(Partie m_partie) {
		partie_aff = m_partie;
	}

	/**
	 * Methode permettant de gerer les evenements clavier
	 */
	public void setMyKeyEvent(Partie m_partie) {
		partie_aff = m_partie;

	}

	@Override
	public void keyPressed(KeyEvent e) {
		int numCaisse;
		int x_perso = partie_aff.getPerso().getX();
		int y_perso = partie_aff.getPerso().getY();
		// Recuperation de la touche appuyee
		int keyCode = e.getKeyCode();
		if (keyCode == KeyEvent.VK_UP) {
			// Touche haut appuyee
			numCaisse = partie_aff.estCaisse(x_perso, y_perso - 1);
			if (partie_aff.estVide(x_perso, y_perso - 1)) {
				partie_aff.getPerso().deplacerDe(0, -1);
			} else if ((numCaisse != -1) & (partie_aff.estVide(x_perso, y_perso - 2))) {
				partie_aff.getCaisses()[numCaisse].deplacerDe(0, -1);
				partie_aff.getPerso().deplacerDe(0, -1);
			}
			partie_aff.getPerso().setImg("Joueur/playerUp.png");

		} else if (keyCode == KeyEvent.VK_DOWN) {
			// Touche bas appuyee
			numCaisse = partie_aff.estCaisse(x_perso, y_perso + 1);
			if (partie_aff.estVide(x_perso, y_perso + 1)) {
				partie_aff.getPerso().deplacerDe(0, 1);
			} else if ((numCaisse != -1) & (partie_aff.estVide(x_perso, y_perso + 2))) {
				partie_aff.getCaisses()[numCaisse].deplacerDe(0, 1);
				partie_aff.getPerso().deplacerDe(0, 1);
			}
			partie_aff.getPerso().setImg("Joueur/playerDown.png");

		} else if (keyCode == KeyEvent.VK_LEFT) {
			// Touche gauche appuyee
			numCaisse = partie_aff.estCaisse(x_perso - 1, y_perso);
			if (partie_aff.estVide(x_perso - 1, y_perso)) {
				partie_aff.getPerso().deplacerDe(-1, 0);
			} else if ((numCaisse != -1) & (partie_aff.estVide(x_perso - 2, y_perso))) {
				partie_aff.getCaisses()[numCaisse].deplacerDe(-1, 0);
				partie_aff.getPerso().deplacerDe(-1, 0);
			}
			partie_aff.getPerso().setImg("Joueur/playerLeft.png");

		} else if (keyCode == KeyEvent.VK_RIGHT) {
			// Touche droite appuyee
			numCaisse = partie_aff.estCaisse(x_perso + 1, y_perso);
			if (partie_aff.estVide(x_perso + 1, y_perso)) {
				partie_aff.getPerso().deplacerDe(1, 0);
			} else if ((numCaisse != -1) & (partie_aff.estVide(x_perso + 2, y_perso))) {
				partie_aff.getCaisses()[numCaisse].deplacerDe(1, 0);
				partie_aff.getPerso().deplacerDe(1, 0);
			}
			partie_aff.getPerso().setImg("Joueur/playerRight.png");
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
