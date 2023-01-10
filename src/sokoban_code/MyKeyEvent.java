package sokoban_code;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
//import java.awt.event.KeyListener;

public class MyKeyEvent extends KeyAdapter{
	private Partie partie_aff = new Partie();
	
	public MyKeyEvent(Partie m_partie) {
		partie_aff = m_partie;
	}
	
	public void setMyKeyEvent(Partie m_partie) {
		partie_aff = m_partie;

	}
	@Override
	public void keyPressed(KeyEvent e) {
		int numCaisse;
		int x_perso = partie_aff.getPerso().getX();
		int y_perso = partie_aff.getPerso().getY();
		System.out.println("Perso : " + String.valueOf(x_perso) + " " + String.valueOf(y_perso));
		// Récupération de la touche appuyée
	    int keyCode = e.getKeyCode();
	    System.out.println(keyCode);
	    if (keyCode == KeyEvent.VK_UP) {
	      // Touche haut appuyée
	    	numCaisse = partie_aff.estCaisse(x_perso,  y_perso-1);
	    	if(partie_aff.estVide(x_perso,y_perso-1)){
	    		partie_aff.getPerso().deplacerDe(0, -1);
	    	}else if ((numCaisse!=-1) & (partie_aff.estVide(x_perso, y_perso-2))){
	    		partie_aff.getCaisses()[numCaisse].deplacerDe(0,-1);
	    		partie_aff.getPerso().deplacerDe(0,-1);
	    	}
	    	partie_aff.getPerso().setImg("Joueur/playerUp.png");

	    	
	    } else if (keyCode == KeyEvent.VK_DOWN) {
	      // Touche bas appuyée
	    	numCaisse = partie_aff.estCaisse(x_perso,  y_perso+1);
	    	if(partie_aff.estVide(x_perso,y_perso+1)){
	    		partie_aff.getPerso().deplacerDe(0, 1);
	    	}else if ((numCaisse!=-1) & (partie_aff.estVide(x_perso, y_perso+2))){
	    		partie_aff.getCaisses()[numCaisse].deplacerDe(0,1);
	    		partie_aff.getPerso().deplacerDe(0,1);
	    	}
	    	partie_aff.getPerso().setImg("Joueur/playerDown.png");

	    } else if (keyCode == KeyEvent.VK_LEFT) {
	      // Touche gauche appuyée
	    	numCaisse = partie_aff.estCaisse(x_perso-1,  y_perso);
	    	if(partie_aff.estVide(x_perso-1,y_perso)){
	    		partie_aff.getPerso().deplacerDe(-1, 0);
	    	}else if ((numCaisse!=-1) & (partie_aff.estVide(x_perso-2, y_perso))){
	    		partie_aff.getCaisses()[numCaisse].deplacerDe(-1,0);
	    		partie_aff.getPerso().deplacerDe(-1,0);
	    	}
	    	partie_aff.getPerso().setImg("Joueur/playerLeft.png");

	    } else if (keyCode == KeyEvent.VK_RIGHT) {
	      // Touche droite appuyée
	    	numCaisse = partie_aff.estCaisse(x_perso+1,  y_perso);
	    	if(partie_aff.estVide(x_perso+1,y_perso)){
	    		partie_aff.getPerso().deplacerDe(1, 0);
	    	}else if ((numCaisse!=-1) & (partie_aff.estVide(x_perso+2, y_perso))){
	    		partie_aff.getCaisses()[numCaisse].deplacerDe(1,0);
	    		partie_aff.getPerso().deplacerDe(1,0);
	    	}
	    	partie_aff.getPerso().setImg("Joueur/playerRight.png");
	    }
	    
	    //test : on cherche à verifier que la caisse et la marque sont sur le même plan
	    /**
	    System.out.println("(MKE) Caisse0 X = " + String.valueOf(partie_aff.getCaisses()[0].getX()) + " Y = " + String.valueOf(partie_aff.getCaisses()[0].getY()));
	    for (int x=0; x<partie_aff.getPlateau().getLargeur();x++) {
			for(int y=0; y<partie_aff.getPlateau().getLongueur(); y++) {
			    if (partie_aff.getPlateauElt(x,y) == '.') {
					System.out.println("Marque: X = " + String.valueOf(x) + " Y =" + String.valueOf(y));
			    }
			}
	    }
	    //fin de la paranthèse
	     
	     */
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
