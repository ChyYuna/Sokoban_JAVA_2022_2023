package sokoban_code;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.GridLayout;

import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.sql.*;
import java.text.AttributedCharacterIterator;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JDialog;
import javax.swing.table.*;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;

import java.awt.event.ActionEvent;
import java.awt.Dimension;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;



// Comment: Try to resolve issue with background switching
public class InputFrame extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private final Partie m_partie = new Partie();
	private JPanel contentPane;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private GraphicPlateau GameContentPane;
	private JButton btnPlay = new JButton();
	private JButton btnScore = new JButton();
	private JButton btnHelp= new JButton();
	private JButton btnTools = new JButton();
	private JButton btnHome = new JButton();
	private JButton btnRetry = new JButton();
	private MyKeyEvent inputHandler = new MyKeyEvent(new Partie());
	private int stage = 1;
	private String name;
	private int highscore = 0;
	public static Thread GameThread;
	public boolean lancerPartie = false;
	public int  etatGameProcess = 0;
	private Image background_menu;
	private Container firstframe;

	//private JLabel back=new JLabel(background_menu);
	public Connection conn;	
	
	public Connection connection(){
		Connection conn = null;	
		try {
			//Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:Sokoban.sqlite");
			System.out.println("Opened database successfully");
		}
		catch (SQLException ex) {
	        ex.printStackTrace();
	    }
		return conn;
	}
	

	/*public ResultSet LectureBBD() throws SQLException{
		conn = connection();
		ResultSet r = conn.createStatement().executeQuery("SELECT * FROM Score");
		while(r.next()) {System.out.println(r.getString("Nom"));}
		conn.close();
		return r;
	}*/

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		InputFrame frame = new InputFrame();
		frame.StartThread();
		frame.setPreferredSize(new Dimension(483, 1088)); //1310,483
		frame.setVisible(true);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				//System.out.println("Test");
//				while(GameThread!=null) {
					
//				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public InputFrame() {
		
		//adjust size+ add to Button ActionListener
		contentPane = new JPanel() {
			private Image background_menu;
		    @Override
		    protected void paintComponent(Graphics g) {
		        super.paintComponent(g);
		        
		        try {
		        	background_menu = ImageIO.read(new File("menu.png"));
			        g.drawImage(background_menu, 0, 0, getWidth(), getHeight(), this);
		        }
		        catch (IOException e) {
		        	// TODO Auto-generated catch block
		        	e.printStackTrace();
		        };
		    }
		};   
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1088, 483);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		firstframe = getContentPane();
		DisplayButton(firstframe, "menu");  //getContentPane(), "menu"
	    ActionListenerButton();

	}
	public void StartThread() {
		GameThread = new Thread();
		GameThread.start();
	}
	@SuppressWarnings("serial")
	private void DisplayButton(Container cPane, String mode) {
		ImageIcon icon_play = new ImageIcon("Icones/Play_Button.png");
	    ImageIcon icon_help = new ImageIcon("Icones/Help_button.png");
		ImageIcon icon_tools = new ImageIcon("Icones/Tools_Button.png");
		ImageIcon icon_retry = new ImageIcon("Icones/Restart_Button.png"); // à créer comme bouton
		ImageIcon icon_menu = new ImageIcon("Icones/Home_Button.png"); // à créer comme bouton
		ImageIcon icon_score = new ImageIcon("Icones/Score_Button.png");
		
		icon_play = ResizeButton(icon_play,153,64);
		icon_help = ResizeButton(icon_help,153,64);
		icon_tools = ResizeButton(icon_tools,153,64);
		icon_retry = ResizeButton(icon_retry,153,64);
		icon_menu = ResizeButton(icon_menu,153,64);
		icon_score = ResizeButton(icon_score, 153, 64);
		switch (mode) {
		case "menu":
			
			btnPlay = new JButton(icon_play);
			buttonGroup.add(btnPlay);
			btnPlay.setBounds(468, 300, 153, 62);
			cPane.add(btnPlay);
			
			//Help
			btnHelp= new JButton(icon_help);
			btnHelp.setBounds(889, 300, 153, 62);
			cPane.add(btnHelp);

			//Tools
			btnTools = new JButton(icon_tools);
			btnTools.setBounds(889, 228, 153, 62);
			cPane.add(btnTools);
			
			//
			btnScore = new JButton(icon_score);
			btnScore.setBounds(889, 156, 153, 62);

			buttonGroup.add(btnScore);
			cPane.add(btnScore);
			

		case "partie":

			btnScore = new JButton(icon_score);
			btnScore.setBounds(889, 156, 153, 62);
			buttonGroup.add(btnScore);
			cPane.add(btnScore);
			
			//home			
			btnRetry = new JButton(icon_retry);
			btnRetry.setBounds(889, 300, 153, 62);
			cPane.add(btnRetry);
			
			btnHome = new JButton(icon_menu);
			btnHome.setBounds(889, 228, 153, 62);
			cPane.add(btnHome);

			
		}
		
	}

	
	private void ActionListenerButton() {
		btnHelp.addActionListener(this:: btnPushHelpListener);
		btnTools.addActionListener(this::btnPushToolsListener);
		btnHome.addActionListener(this::btnPushHomeListener);
		btnPlay.addActionListener(this::btnPushPlayListener);
		btnScore.addActionListener(this::btnPushScoreListener);
		btnRetry.addActionListener(this::btnPushRetryListener);
		
		//set Cursor
		btnScore.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnPlay.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnHelp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnTools.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRetry.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnHome.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
	}
	
	private void btnPushScoreListener(ActionEvent event) {
		try {
			JDialog DDB = new JDialog();
			DDB.setTitle("Table des scores");
			
			conn = connection();
			ResultSet r = conn.createStatement().executeQuery("SELECT * FROM Score");
			String[] columnNames = {"Nom", "Highscore"};
			
			DefaultTableModel model = new DefaultTableModel(columnNames, 10);
			while(r.next()) {
				String n = r.getString("Nom");
				int s = r.getInt("Highscore");
				model.insertRow(0,new Object[]{n,s});
			}
			
			JTable table = new JTable(model);
			table.setEnabled(false);
			JScrollPane scroll = new JScrollPane(table);
			DDB.add(scroll);

			DDB.setSize(400,300);
			DDB.setModal(true);
			DDB.setVisible(true);
			
			conn.close();
			
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
		
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
		name = JOptionPane.showInputDialog("Pour enregistrer votre score, veuillez entrer votre nom (unknow par defaut)");
        if (name.isEmpty()) {name = "Unknow";}
        //TODO 
        if (GameContentPane != null) {
        	GameContentPane.setVisible(false);
        	GameContentPane.removeKeyListener(inputHandler);
			contentPane.remove(GameContentPane);

        }
        contentPane.removeAll();
        lancerPartie = false; 
		setContentPane(contentPane);
		DisplayButton(contentPane, "menu");
		contentPane.setFocusable(true);
		contentPane.setVisible(true);
		//firstframe.setVisible(true);
	    ActionListenerButton();
        repaint();
        revalidate();
	}
	
	@SuppressWarnings("rawtypes")
	private void btnPushPlayListener(ActionEvent level) {
		name = JOptionPane.showInputDialog("Pour enregistrer votre score, veuillez entrer votre nom (unknow par defaut)");
        if (name.isEmpty()) {name = "Unknow";}
        
		lancerPartie = true;
		new SwingWorker() {
			protected Object doInBackground() throws Exception {
				while(lancerPartie==true && stage <11) {
					switch(etatGameProcess) {
					case 0:
						//TODO Génération de la frame (niveau)
						m_partie.lancerNiveau(stage);
						contentPane.setVisible(false);
						//frame for level
						GameContentPane = new GraphicPlateau(m_partie,String.valueOf(stage));
						//key for the game
						inputHandler = new MyKeyEvent(m_partie);
						setContentPane(GameContentPane);		
						GameContentPane.setFocusable(true);
						GameContentPane.requestFocusInWindow();
						GameContentPane.addKeyListener(inputHandler);
						DisplayButton(getContentPane(), "partie"); 

					    ActionListenerButton();
						setEtatGame(1); 
						repaint();
						revalidate();
						break;
					case 1: 
						if (GameContentPane.isFinNiv() == true) {
							System.out.println("Niv Terminé");
							setEtatGame(2);
							stage++;
						}
						else {
							System.out.println("Info reçue");
						}break;
					case 2 : 
						//détruit le niveau et la configuration
						GameContentPane.setVisible(false);
						GameContentPane.setFocusable(false);
						GameContentPane.removeKeyListener(inputHandler);
						contentPane.remove(GameContentPane);
						stage++;
						repaint();
						revalidate();
						setEtatGame(0);
						break;	
					}
				}return null;
			}
		}.execute();
		
	}
	
	private void btnPushRetryListener(ActionEvent event) {
		setEtatGame(0);
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
	public void setEtatGame(int v) {
		etatGameProcess = v;
	}
}