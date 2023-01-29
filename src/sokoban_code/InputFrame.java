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
import javax.swing.JTextArea;
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
import javax.swing.JCheckBox;
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
	private GraphicPlateau GameContentPane;
	private JButton btnPlay = new JButton();
	private JButton btnScore = new JButton();
	private JButton btnHelp= new JButton();
	private JButton btnLoad = new JButton();
	private JButton btnHome = new JButton();
	private JButton btnRetry = new JButton();
    private JCheckBox musicCheckBox = new JCheckBox();
    private Configuration configLevel = new Configuration();    
    
	private MyKeyEvent inputHandler = new MyKeyEvent(new Partie());
	private int stage = 1;
	private String name;
	private int highscore = 0;
	public static Thread GameThread;
	public boolean lancerPartie = false;
	public int  etatGameProcess = 0;
	private Container firstframe; // ? 
    private static MusicPlayer backgroundMusic; 
    private static MusicPlayer soundEffect;//?
    private static ImageIcon iconResult; //?
    
    
    

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
		        backgroundMusic = new MusicPlayer("sound/ambiance.wav");
		        //backgroundMusic.play();
		        //soundEffect = new MusicPlayer("path_to_sound_effect.wav");
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
	    
		iconResult = new ImageIcon("Icones/congratulation.png");
		iconResult = ResizeButton(iconResult, 40, 40);

	}
	public void StartThread() {
		GameThread = new Thread();
		GameThread.start();
	}
	@SuppressWarnings("serial")
	private void DisplayButton(Container cPane, String mode) {
		ImageIcon icon_play = new ImageIcon("Icones/Play_Button.png");
	    ImageIcon icon_help = new ImageIcon("Icones/Help_button.png");
		ImageIcon icon_load = new ImageIcon("Icones/Load_Button.png");
		ImageIcon icon_retry = new ImageIcon("Icones/Restart_Button.png"); // à créer comme bouton
		ImageIcon icon_menu = new ImageIcon("Icones/Home_Button.png"); // à créer comme bouton
		ImageIcon icon_score = new ImageIcon("Icones/Score_Button.png");
		ImageIcon icon_music_enabled = new ImageIcon("Icones/volume-max.png");
		ImageIcon icon_music_disabled = new ImageIcon("Icones/volume-x.png");
		icon_play = ResizeButton(icon_play,153,64);
		icon_help = ResizeButton(icon_help,153,64);
		icon_load = ResizeButton(icon_load,153,64);
		icon_retry = ResizeButton(icon_retry,153,64);
		icon_menu = ResizeButton(icon_menu,153,64);
		icon_score = ResizeButton(icon_score, 153, 64);
			
		btnPlay = new JButton(icon_play);
		btnPlay.setBounds(468, 300, 153, 62);
		cPane.add(btnPlay);
			
		btnHelp= new JButton(icon_help);
		btnHelp.setBounds(889, 300, 153, 62);
		cPane.add(btnHelp);
		
		btnLoad = new JButton(icon_load);
		btnLoad.setBounds(889, 228, 153, 62);
		cPane.add(btnLoad);
			
		btnScore = new JButton(icon_score);
		btnScore.setBounds(889, 156, 153, 62);
		cPane.add(btnScore);
		
		btnRetry = new JButton(icon_retry);
		btnRetry.setBounds(889, 300, 153, 62);
		cPane.add(btnRetry);
			
		btnHome = new JButton(icon_menu);
		btnHome.setBounds(889, 228, 153, 62);
		cPane.add(btnHome);
		
		musicCheckBox = new JCheckBox();
		musicCheckBox.setBounds(889, 50, 50, 50);
		if (backgroundMusic !=null) {
			if (backgroundMusic.state()=="play") {musicCheckBox.setIcon(icon_music_disabled);}
			else{musicCheckBox.setIcon(icon_music_disabled);}
		}else {musicCheckBox.setIcon(icon_music_disabled);}
        musicCheckBox.setOpaque(false);
        cPane.add(musicCheckBox);
		
		

		if (mode.equals("menu")){
			btnScore.setVisible(true);
			btnHelp.setVisible(true);
			btnPlay.setVisible(true);
			btnLoad.setVisible(true);
			btnRetry.setVisible(false);
			btnHome.setVisible(false);		
		}else if(mode.equals("partie")) {
			btnScore.setVisible(true);
			btnHelp.setVisible(false);
			btnPlay.setVisible(false);
			btnLoad.setVisible(false);
			btnRetry.setVisible(true);
			btnHome.setVisible(true);	
		}
	}

	
	private void ActionListenerButton() {
		btnHelp.addActionListener(this:: btnPushHelpListener);
		btnLoad.addActionListener(this::btnPushLoadListener);
		btnHome.addActionListener(this::btnPushHomeListener);
		btnPlay.addActionListener(this::btnPushPlayListener);
		btnScore.addActionListener(this::btnPushScoreListener);
		btnRetry.addActionListener(this::btnPushRetryListener);
		musicCheckBox.addActionListener(this::PushMusicListener);
		
		//set Cursor
		btnScore.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnPlay.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnHelp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLoad.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRetry.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnHome.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
	}
	
	private void PushMusicListener(ActionEvent event) {
		ImageIcon icon_music_enabled = new ImageIcon("Icones/volume-max.png");
		ImageIcon icon_music_disabled = new ImageIcon("Icones/volume-x.png");
		if (musicCheckBox.isSelected()) {
			//play music
			musicCheckBox.setIcon(icon_music_enabled);
			backgroundMusic.play();
		}
		else {
			musicCheckBox.setIcon(icon_music_disabled);
			backgroundMusic.stop();
		}
		if (lancerPartie) {GameContentPane.requestFocus();} 

	}
	
	
	private void btnPushScoreListener(ActionEvent event) {
		try {
			JDialog DDB = new JDialog();
			DDB.setTitle("Table des scores");
			
			conn = connection();
			ResultSet r = conn.createStatement().executeQuery("SELECT * FROM Score ORDER BY Highscore DESC LIMIT 5");
			String[] columnNames = {"Nom", "Highscore"};
			
			DefaultTableModel model = new DefaultTableModel(columnNames, 5);
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
			if (lancerPartie) {GameContentPane.requestFocus();} 
			
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	}
	
	public void MusicEnabled(Container cPane) {
	    JCheckBox musicCheckBox;
        musicCheckBox = new JCheckBox();
        musicCheckBox.setBounds(889, 10, 40, 40);
        musicCheckBox.setIcon(iconResult);
        cPane.add(musicCheckBox);
        JLabel musicLabel = new JLabel("Activate music:");
        cPane.add(musicLabel);
        cPane.setVisible(true);
	}
	
	private void btnPushHelpListener(ActionEvent event) {
		JDialog Help = new JDialog();
		Help.setTitle("Aide");
		JLabel label = new JLabel();
		ImageIcon icon = new ImageIcon("aide_Sokoban.png");
		Image img = icon.getImage();
		Image newImage = img.getScaledInstance(600, 400, Image.SCALE_SMOOTH);
		ImageIcon newIcon = new ImageIcon(newImage);
		label.setIcon(newIcon);
		Help.add(label);
		Help.setVisible(true);
		Help.setSize(600,440);
	}
	
	private void btnPushLoadListener(ActionEvent event) { // Pourquoi avoir crée Configuration? 
		//JFrame newframe = new JFrame("JOptionPane showMessageDialog example");
		//JOptionPane.showMessageDialog(newframe,"A basic JOptionPane message dialog");
		String StageCode = JOptionPane.showInputDialog("Veuillez saisir le mot de passe");
		stage = configLevel.getNivAlancer(StageCode);
	}
	
	private void btnPushHomeListener(ActionEvent event) {
        //TODO 
		int result = JOptionPane.showOptionDialog(null, "Etes-vous sur de vouloir quitter le jeu ? Vous allez perdre votre progression !", "Quitter le jeu", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);

		if (result == 0) { 
			EnregistrerScore();
	        if (GameContentPane != null) {
	        	GameContentPane.setVisible(false);
				GameContentPane.setFocusable(false); //?
	        	GameContentPane.removeKeyListener(inputHandler);
				contentPane.remove(GameContentPane);

	        }
			setEtatGame(0);

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
		else GameContentPane.requestFocus();
	}
	
	@SuppressWarnings("rawtypes")
	private void btnPushPlayListener(ActionEvent level) {
		name = JOptionPane.showInputDialog("Pour enregistrer votre score, veuillez entrer votre nom (unknow par defaut)");
        if (name.isEmpty()) {name = "Unknow";}
        
		lancerPartie = true;
		new SwingWorker() {
			protected Object doInBackground() throws Exception {
				while(lancerPartie && stage <11) {
					switch(etatGameProcess) {
					case 0:
						//TODO Génération de la frame (niveau)
						int continueB = 0; // 
						m_partie.lancerNiveau(stage);
						contentPane.setVisible(false);
						//frame for level
						GameContentPane = new GraphicPlateau(m_partie,String.valueOf(stage), configLevel.getListeCodes()[stage]);
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
							highscore += stage*10;
							//if lastLevel finished
							if (stage+1 >= 11) {
								JOptionPane.showMessageDialog(null,"Bravo, vous avez terminé Sokoban Master Quest !");
								continueB = 1;
							}
							else {
								//For each Level, give the possibility to leave / continue game
								continueB = JOptionPane.showOptionDialog(null, "Niveau Terminé! Continuez ?", "Niveau terminé", JOptionPane.YES_NO_OPTION,JOptionPane.PLAIN_MESSAGE, iconResult, null, null);
								System.out.print(continueB);
							}
							if (continueB == 1) {
								EnregistrerScore();
								stage = 1;
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
							else {
								stage++;

							}
						}
						else {
							System.out.println("");
						}break;
					case 2 : 
						//détruit le niveau et la configuration
						GameContentPane.setVisible(false);
						GameContentPane.setFocusable(false);
						GameContentPane.removeKeyListener(inputHandler);
						contentPane.remove(GameContentPane);
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
		
	private void EnregistrerScore(){
		try {				
			conn = connection();
			String query = "INSERT INTO Score VALUES(?,?)";
			PreparedStatement pst = conn.prepareStatement(query);
			pst.setString(1, name);
			pst.setInt(2, highscore);
			pst.execute();
			conn.close();
				
		} catch (SQLException ex) {
		    ex.printStackTrace();
		}
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