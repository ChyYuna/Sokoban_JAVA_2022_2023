package sokoban_code;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;
import java.awt.Cursor;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.Dimension;
import javax.swing.ButtonGroup;



// Comment: Try to resolve issue with background switching
public class InputFrame extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private final Partie m_partie = new Partie();
	private JPanel contentPane;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private GraphicPlateau GameContentPane;
	private JButton btnPlay = new JButton();
	private JButton btnHelp= new JButton();
	private JButton btnTools = new JButton();
	private JButton btnHome = new JButton();
	private JButton btnRetry = new JButton();
	private MyKeyEvent inputHandler = new MyKeyEvent(new Partie());

	
	
	
	//private JLabel back=new JLabel(background_menu);

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
		
		//adjust size+ add to Button ActionListener
		sizingButton(); 
	    ActionListenerButton();

	}

	private void sizingButton() {
		ImageIcon background_menu=new ImageIcon("menu.png");
	    ImageIcon icon_play = new ImageIcon("Play_Button.png");
	    ImageIcon icon_help = new ImageIcon("Help_button.png");
		ImageIcon icon_tools = new ImageIcon("Tools_Button.png");
		ImageIcon icon_retry = new ImageIcon("Tools_Button.png"); // à créer comme bouton
		ImageIcon icon_menu = new ImageIcon("Tools_Button.png"); // à créer comme bouton
		ImageIcon background_game = new ImageIcon("background.png");
		//set size button
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
		
		
		btnPlay = new JButton(icon_play);
		buttonGroup.add(btnPlay);
		btnPlay.setBounds(468, 300, 153, 62);
		contentPane.add(btnPlay);
		
		//Help
		btnHelp= new JButton(icon_help);
		btnHelp.setBounds(889, 300, 153, 62);
		btnHelp.setContentAreaFilled(false);
		btnHelp.setBorderPainted(false);
		contentPane.add(btnHelp);

		//Tools
		btnTools = new JButton(icon_tools);
		btnTools.setBounds(889, 228, 153, 62);
		contentPane.add(btnTools);
		
		//home
		btnHome = new JButton(icon_menu);
		btnHome.setBounds(889, 156, 153, 62);
		contentPane.add(btnHome);
		
		//Retry
		btnRetry = new JButton(icon_retry);
		btnRetry.setBounds(889, 84, 153, 62);
		contentPane.add(btnRetry);
		
		
		//background home
		//back.setBounds(0,0,1074,446); 
		//contentPane.add(back);
	}
	
	private void ActionListenerButton() {
		btnHelp.addActionListener(this:: btnPushHelpListener);
		btnTools.addActionListener(this::btnPushToolsListener);
		btnHome.addActionListener(this::btnPushHomeListener);
		btnPlay.addActionListener(this::btnPushPlayListener);
		//set Cursor
		btnPlay.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnHelp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnTools.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRetry.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnHome.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
	}
	
private void btnPushHelpListener(ActionEvent event) {
		JFrame newframe = new JFrame("JOptionPane showMessageDialog example");
        JOptionPane.showMessageDialog(newframe,"A basic JOptionPane message dialog");
	}
	
	private void btnPushToolsListener(ActionEvent event) {
		JFrame newframe = new JFrame("JOptionPane showMessageDialog example");
        JOptionPane.showMessageDialog(newframe,"A basic JOptionPane message dialog");
	}
	
	private void btnPushHomeListener(ActionEvent event) {
		//Supprimer la fenetre jeu quand on clique dessus
	}
	
	private void btnPushPlayListener(ActionEvent event) {
		m_partie.lancerNiveau(1);
		contentPane.setVisible(false);
		GameContentPane = new GraphicPlateau(m_partie,String.valueOf(1));
		inputHandler = new MyKeyEvent(m_partie);;
		setContentPane(GameContentPane);		
		GameContentPane.setFocusable(true);
		GameContentPane.requestFocusInWindow();
		GameContentPane.addKeyListener(inputHandler);
		repaint();

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