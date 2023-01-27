package sokoban_code;

import java.awt.EventQueue;
import java.awt.GridLayout;

import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.sql.*;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JDialog;
import javax.swing.table.*;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
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
		sizingButton(); 
	    ActionListenerButton();

	}
	public void StartThread() {
		GameThread = new Thread();
		GameThread.start();
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
		
		btnScore = new JButton(icon_play);
		buttonGroup.add(btnScore);
		btnScore.setBounds(889, 84, 153, 62);
		contentPane.add(btnScore);
		
		btnPlay = new JButton(icon_play);
		buttonGroup.add(btnPlay);
		btnPlay.setBounds(468, 300, 153, 62);
		contentPane.add(btnPlay);
		
		//Help
		btnHelp= new JButton(icon_help);
		btnHelp.setBounds(889, 300, 153, 62);
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
		btnHelp.setContentAreaFilled(false);
		btnHelp.setBorderPainted(false);
		btnRetry.setVisible(false);	
		btnRetry.setEnabled(false);
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
		btnScore.addActionListener(this::btnPushScoreListener);
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

	}
	
	@SuppressWarnings("rawtypes")
	private void btnPushPlayListener(ActionEvent level) {
		name = JOptionPane.showInputDialog("Pour enregistrer votre score, veuillez entrer votre nom (unknow par defaut)");
        if (name.isEmpty()) {name = "Unknow";}
        
		lancerPartie = true;
		new SwingWorker() {
			protected Object doInBackground() throws Exception {
				// TODO Auto-generated method stub
				while(lancerPartie==true) {
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
					while(!GameContentPane.isFinNiv()){
						System.out.println("Information recue");
						btnRetry.setVisible(true);		
						btnRetry.setEnabled(true);
					}
					//JFrame NiveauReussi = new JFrame();
					highscore += 10*stage; 
					//stage++;
					repaint();
					revalidate();
					
					
				}
				return null;
			}
		}.execute();
		
		/**
		m_partie.lancerNiveau(stage);
		contentPane.setVisible(false);
		//frame for level
		GameContentPane = new GraphicPlateau(m_partie,String.valueOf(stage));
		//key for the game
		inputHandler = new MyKeyEvent(m_partie);;
		setContentPane(GameContentPane);		
		GameContentPane.setFocusable(true);
		GameContentPane.requestFocusInWindow();
		GameContentPane.addKeyListener(inputHandler);
		
		new SwingWorker() {
			@Override
			protected Object doInBackground() throws Exception {
				// TODO Auto-generated method stub
				while(true) {
				while(!GameContentPane.isFinNiv()){
					System.out.println("Information recue");
					stage++;
				}
				return null;
			}
		}.execute();
	    repaint();
		*/
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