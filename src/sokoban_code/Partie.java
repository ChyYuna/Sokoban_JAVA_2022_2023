package sokoban_code;

public class Partie {
	//Attributs
	private int m_niv=1, m_nbCaisses=1;
	private Personnage m_perso;
	private Caisse[] m_c;
	private Plateau m_plat;
	
	//Constructeurs 
	/**
     * Constructeur par defaut 
	 */
	public Partie(){
	    m_c = new Caisse[m_nbCaisses];
	    for (int i=0; i<m_nbCaisses; i++){
	        m_c[i] = new Caisse();
	    }
	}
	
	//Methodes
	/**
	 * Donne acces au plateau actuel de la partie
	 * @return Objet de type Plateau
	 */
	public Plateau getPlateau() {return m_plat;}
	
	/**
	 * Donne acces aux caisses de la partie
	 * @return Vecteur contenant les caisses
	 */
	public Caisse[] getCaisses(){return m_c;}
	
	/**
	 * Donne acces au personnage de la partie
	 * @return Objet de type Personnage
	 */
	public Personnage getPerso(){return m_perso;}
	
	/**
	 * Donne acces au niveau actuel
	 * @return entier contenant le niveau actuel
	 */
	public int getNiv(){return m_niv;}
	
	/**
	 * Permet de modifier le numero du niveau actuel
	 * @param n : entier contenant le nouveau niveau
	 */
	public void setNiv(int n){
	    m_niv=n;
	}
}
