package sokoban_code;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class GraphicPlateau extends JComponent{

	private static final long serialVersionUID = -8596357816364035609L;
	private Image background_game;
	private Image mur_img;
	private Image vide_img;
	public Partie partie_aff;
	public GraphicPlateau(Partie m_partie) {
		partie_aff = m_partie;
	}


	@Override
	protected void paintComponent(Graphics graphics) {
		// TODO Auto-generated method stub
		super.paintComponent(graphics);

		try {
			background_game = ImageIO.read(new File("background.png"));
			mur_img = ImageIO.read(new File("Autres/mur.png"));
			vide_img = ImageIO.read(new File("Autres/Vide.png"));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		background_game = ResizeGround(background_game, 1074,446);
		graphics.drawImage(background_game,0,0,this);
		for (int i=0; i<partie_aff.getPlateau().getLongueur();i++) {

			for(int j=0; j<partie_aff.getPlateau().getLargeur(); j++) {
				graphics.drawImage(vide_img, 100+64*i,64*j, this);
				if (partie_aff.getPlateauElt(i,j) == '#') {
					graphics.drawImage(mur_img, 100+64*i, 64*j, this);
				}
			}
		}
		for (int i=0; i<partie_aff.getNbCaisses();i++) {
			graphics.drawImage(partie_aff.getCaisses()[i].getImg(), 100+64*partie_aff.getCaisses()[i].getX(),
					64*partie_aff.getCaisses()[i].getY(), this);
		}

		graphics.drawImage(partie_aff.getPerso().getImg(), 100+64*partie_aff.getPerso().getX(), 
				64*partie_aff.getPerso().getY(), this);
	}


	private Image ResizeGround(Image img, int w, int h) {
		Image newimg = img.getScaledInstance(w, h,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		return newimg;  // transform it back
	}

}