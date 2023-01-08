package sokoban_code;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class GraphicPlateau extends JComponent{

	private static final long serialVersionUID = -8596357816340035609L;
	private Image background_game;
	private Image mur_img;
	private Image vide_img;
	private Image marquee_img;
	public Partie partie_aff;
	private String level; 

	public GraphicPlateau(Partie m_partie, String m_level) {
		partie_aff = m_partie;
		level= m_level;

	} 


	protected void paintComponent(Graphics graphics) {
		// TODO Auto-generated method stub
		super.paintComponent(graphics);
		
		try {
			background_game = ImageIO.read(new File("background.png"));
			mur_img = ImageIO.read(new File("Autres/mur.png"));
			vide_img = ImageIO.read(new File("Autres/Vide.png"));
			marquee_img = ImageIO.read(new File("Autres/cible.png"));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		background_game = ResizeImg(background_game, 1074,446);
		mur_img =  ResizeImg(mur_img, 40, 40);
		vide_img = ResizeImg(vide_img, 40, 40);
		marquee_img = ResizeImg(marquee_img, 40,40);
		
		//background
		graphics.drawImage(background_game,0,0,this);
		
		// field
		for (int i=0; i<partie_aff.getPlateau().getLongueur();i++) {

			for(int j=0; j<partie_aff.getPlateau().getLargeur(); j++) {
				graphics.drawImage(vide_img, 50+40*j,100+40*i, this);
				if (partie_aff.getPlateauElt(i,j) == '#') {
					graphics.drawImage(mur_img, 50+40*j, 100+40*i, this);
				}
				if (partie_aff.getPlateauElt(i,j) == '.') {
					graphics.drawImage(marquee_img, 50+40*j, 100+40*i, this);
				}
			}
		} 
		for (int i=0; i<partie_aff.getNbCaisses();i++) {
			graphics.drawImage(partie_aff.getCaisses()[i].getImg(), 50+40*partie_aff.getCaisses()[i].getX(),
					100+40*partie_aff.getCaisses()[i].getY(), this);
		}

		graphics.drawImage(partie_aff.getPerso().getImg(), 50+40*partie_aff.getPerso().getX(), 
				100+40*partie_aff.getPerso().getY(), this);
		
		//text level
		Font fonte = new Font("TimesRoman ",Font.BOLD,30);
		graphics.setFont(fonte);
		graphics.drawString("Niveau " + level, 50,50);
		repaint();
	}


	private Image ResizeImg(Image img, int w, int h) {
		Image newimg = img.getScaledInstance(w, h,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		return newimg;  // transform it back
	}


	

}