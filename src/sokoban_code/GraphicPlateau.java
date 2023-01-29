package sokoban_code;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JComponent;

/**
 * Classe permettant de dessiner le plateau de jeu
 */
public class GraphicPlateau extends JComponent {

	private static final long serialVersionUID = -8596357816340035609L;
	private Image background_game;
	private Image mur_img;
	private Image vide_img;
	private Image marquee_img;
	public Partie partie_aff;
	private String level;
	private boolean finNiv;
	private String code;

	/**
	 * Constructeur
	 */
	public GraphicPlateau(Partie m_partie, String m_level, String m_code) {
		partie_aff = m_partie;
		level = m_level;
		code = m_code;
		setFinNiv(partie_aff.victoire());

	}

	/**
	 * Methode permettant de dessiner le plateau de jeu
	 */
	protected void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);

		try {
			background_game = ImageIO.read(new File("./Ground/background.png"));
			mur_img = ImageIO.read(new File("Autres/mur.png"));
			vide_img = ImageIO.read(new File("Autres/Vide.png"));
			marquee_img = ImageIO.read(new File("Autres/cible.png"));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		;
		background_game = ResizeImg(background_game, 1074, 446);
		mur_img = ResizeImg(mur_img, 40, 40);
		vide_img = ResizeImg(vide_img, 40, 40);
		marquee_img = ResizeImg(marquee_img, 40, 40);
		// background
		graphics.drawImage(background_game, 0, 0, this);

		// field
		for (int x = 0; x < partie_aff.getPlateau().getLargeur(); x++) {

			for (int y = 0; y < partie_aff.getPlateau().getLongueur(); y++) {
				graphics.drawImage(vide_img, 50 + 40 * x, 100 + 40 * y, this);
				if (partie_aff.getPlateauElt(x, y) == '#') {
					graphics.drawImage(mur_img, 50 + 40 * x, 100 + 40 * y, this);
				}
				if (partie_aff.getPlateauElt(x, y) == '.') {
					graphics.drawImage(marquee_img, 50 + 40 * x, 100 + 40 * y, this);
				}
				if (partie_aff.getPlateauElt(x, y) == '*') {
					graphics.drawImage(marquee_img, 50 + 40 * x, 100 + 40 * y, this);
				}
				if (partie_aff.getPlateauElt(x, y) == '+') {
					graphics.drawImage(marquee_img, 50 + 40 * x, 100 + 40 * y, this);
				}

			}
		}
		for (int i = 0; i < partie_aff.getNbCaisses(); i++) {
			graphics.drawImage(partie_aff.getCaisses()[i].getImg(), 50 + 40 * partie_aff.getCaisses()[i].getX(),
					100 + 40 * partie_aff.getCaisses()[i].getY(), this);
		}

		graphics.drawImage(partie_aff.getPerso().getImg(), 50 + 40 * partie_aff.getPerso().getX(),
				100 + 40 * partie_aff.getPerso().getY(), this);

		// text level
		Font fonte = new Font("TimesRoman ", Font.BOLD, 30);
		graphics.setFont(fonte);
		graphics.drawString("Niveau " + level, 50, 50);
		graphics.drawString("Code : " + code, 50, 80);
		setFinNiv(partie_aff.victoire());
		repaint();
	}

	/**
	 * Methode permettant de redimensionner une image
	 * 
	 * @param img
	 * @param w
	 * @param h
	 * @return
	 */
	private Image ResizeImg(Image img, int w, int h) {
		Image newimg = img.getScaledInstance(w, h, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
		return newimg; // transform it back
	}

	/**
	 * Methode permettant de savoir si le niveau est fini
	 * 
	 * @return finNiv
	 */
	public boolean isFinNiv() {
		return finNiv;
	}

	/**
	 * Methode permettant de modifier la valeur de finNiv
	 * 
	 * @param finNiv
	 */
	public void setFinNiv(boolean finNiv) {
		this.finNiv = finNiv;
	}

}