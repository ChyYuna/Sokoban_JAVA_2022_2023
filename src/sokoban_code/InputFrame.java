package sokoban_code;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.ButtonGroup;



// Comment: Try to resolve issue with background switching
public class InputFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Partie m_partie = new Partie();
	private JPanel contentPane;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private int stateWindow = 0; //0-> menu 1-> Game
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InputFrame frame = new InputFrame();
					frame.setPreferredSize(new Dimension(483, 1088)); //1310,483
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public InputFrame() {

		//import image button for Menu window
	    ImageIcon background_menu=new ImageIcon("menu.png");
		ImageIcon icon_play = new ImageIcon("Play_Button.png");
		ImageIcon icon_help = new ImageIcon("Help_button.png");
		ImageIcon icon_tools = new ImageIcon("Tools_Button.png");
		ImageIcon icon_retry = new ImageIcon("Tools_Button.png"); // à créer comme bouton
		ImageIcon icon_menu = new ImageIcon("Tools_Button.png"); // à créer comme bouton
		ImageIcon background_game = new ImageIcon("background.png");
		icon_play = ResizeButton(icon_play,153,62);
		icon_help = ResizeButton(icon_help,153,62);
		icon_tools = ResizeButton(icon_tools,153,62);
		icon_retry = ResizeButton(icon_retry,153,62);
		icon_menu = ResizeButton(icon_menu,153,62);
		background_menu = ResizeGround(background_menu, 1074,446);
		background_game = ResizeGround(background_game, 1074,446);

		//resize image for button
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1088, 483);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnPlay = new JButton(icon_play);
		buttonGroup.add(btnPlay);
		btnPlay.setBounds(468, 300, 153, 62);
		contentPane.add(btnPlay);
		
		//Help
		JButton btnHelp= new JButton(icon_help);
		btnHelp.setBounds(889, 300, 153, 62);
		btnHelp.setContentAreaFilled(false);
		btnHelp.setBorderPainted(false);
		contentPane.add(btnHelp);

		//Tools
		JButton btnConfigure = new JButton(icon_tools);
		btnConfigure.setBounds(889, 228, 153, 62);
		contentPane.add(btnConfigure);
		
		//home
		JButton btnHome = new JButton(icon_menu);
		btnHome.setBounds(889, 156, 153, 62);
		contentPane.add(btnHome);
		
		//Retry
		JButton btnRetry = new JButton(icon_retry);
		btnRetry.setBounds(889, 84, 153, 62);
		contentPane.add(btnRetry);
		
		
		//background home
		JLabel back=new JLabel(background_menu);
		//back.setLayout(null);
		back.setBounds(0,0,1074,446); 
		contentPane.add(back);
		//event button gestionnary
		class Listener extends JPanel implements ActionListener {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent event) {
				ImageIcon background_game = new ImageIcon("background.png");
				background_game = ResizeGround(background_game, 1074,446);
				JLabel back_game=new JLabel(background_game);
				//back.setLayout(null);
				back_game.setBounds(0,0,1074,446); 
				if (event.getSource() == btnHelp) {
					JFrame newframe = new JFrame("JOptionPane showMessageDialog example");
	                JOptionPane.showMessageDialog(newframe,"A basic JOptionPane message dialog");

				} else if (event.getSource() == btnPlay) {
					
					JFrame newframe = new JFrame("JOptionPane Play UwU example");
	                JOptionPane.showMessageDialog(newframe,"UwU");
	                contentPane.remove(back);
	                contentPane.add(back_game);
	                btnPlay.setVisible(false);
	                m_partie.lancerNiveau(1);       
	                int largeur = m_partie.getPlateau().getLargeur();
	                int longueur = m_partie.getPlateau().getLongueur();
	                for (int i=0; i<longueur; i++) {
	                	for(int j=0; i<largeur;j++) {
	                		char type = m_partie.getPlateauElt(i,j); //out of bond index 7 (pb)
	                		System.out.print(type);	                		
	                	}
	                }
	         
	                
				} else if (event.getSource()== btnHome){
					contentPane.remove(back_game);
					contentPane.add(back);
					btnPlay.setVisible(true);
				}
		       contentPane.repaint();
			}
		}
	
		
		//set Cursor
		btnPlay.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnHelp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnConfigure.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRetry.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnHome.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		//action with buttons 
		btnHelp.addActionListener(new Listener());
		btnPlay.addActionListener(new Listener());
		btnHome.addActionListener(new Listener());
	}

	
	public ImageIcon ResizeButton(ImageIcon icon, int w, int h) {
		Image image = icon.getImage(); // transform it 
		Image newimg = image.getScaledInstance(w, h,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		return new ImageIcon(newimg);  // transform it back
	}
	
	
	public ImageIcon ResizeGround(ImageIcon img, int w, int h) {
		Image image = img.getImage(); // transform it 
		Image newimg = image.getScaledInstance(w, h,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		return new ImageIcon(newimg);  // transform it back
	}
	
}
//
//Image image = icon_play.getImage(); // transform it 
//Image newimg = image.getScaledInstance(153, 62,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
//icon_play = new ImageIcon(newimg);  // transform it back