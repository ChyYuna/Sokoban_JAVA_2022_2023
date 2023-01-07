#include "partie.h"

/**
 * @file partie.cpp
 * Fichier cpp pour la classe Partie
 */

/**
 * @brief Constructeur (forme une partie en creeant les elements necessaires pour jouer)
 */
Partie::Partie(){
    m_niv = 1;
    m_nbCaisses = 1;
    m_c = new Caisse[m_nbCaisses];
    for (int i=0; i<m_nbCaisses; i++){
        m_c[i] = Caisse();
    }
    m_plat = new Plateau;
    config = new Configuration;
    m_perso = new Personnage;
}

/**
 * @brief Constructeur de recopie
 * @param part : objet de type Partie a recopier
 */
Partie::Partie(const Partie &part){
    m_niv = part.m_niv;
    m_nbCaisses = part.m_nbCaisses;
    m_c = new Caisse[part.m_nbCaisses];
    for (int i=0; i<part.m_nbCaisses; i++){
        m_c[i] = Caisse();
    }
    m_plat = new Plateau(part.m_plat->getX(),part.m_plat->getY(),*part.m_plat);
    config = new Configuration(*part.config);
    m_perso = new Personnage;
    *m_perso = *part.m_perso;
}


/**
 * @brief Permet de lancer un niveau
 * @param niv : entier contenant le numero du niveau a lancer
 */
void Partie::lancerNiveau(int niv){
    ifstream fichier(to_string(niv)+".txt", ios::in);
    int k=0; // L'entier k permet de numeroter les caisses et de les manipuler dans le tableau les contenant

    if(!fichier.fail()){
        fichier >> m_niv >> m_nbCaisses;

        m_plat = new Plateau(1,2,fichier); //On construit le plateau via ce constructeur qui va lire la suite du fichier

        m_c = new Caisse[m_nbCaisses]; // En lancant un nouveau niveau, la liste m_c (contentant les caisses) doit etre construite en fonction du nombre de caisse m_nbCaisses du niveau
        for (int i=0; i<m_nbCaisses; i++)
             m_c[i] = Caisse();

        for (int i=0; i<m_plat->getLargeur(); i++){
            for (int j=0; j<m_plat->getLongueur(); j++){

                if (m_plat->getTab()[i][j].getType() == '@' or m_plat->getTab()[i][j].getType() == '+') {
                    m_perso->setX(j);
                    m_perso->setY(i);
                    m_perso->setImg(":/images/Joueur/playerDown.png");
                }
                if (m_plat->getTab()[i][j].getType() == '$'){
                    m_c[k].setX(j);
                    m_c[k].setY(i);
                    m_c[k].setImg(":/images/Caisse/caisse.png");
                    k++;
                }
                if (m_plat->getTab()[i][j].getType() == '*'){
                    m_c[k].setX(j);
                    m_c[k].setY(i);
                    m_c[k].setImg(":/images/Caisse/caisseSurCible.png");
                    k++;
                }
            }
        }
        fichier.close();
     }
     else
        cerr << "Impossible d'ouvrir le fichier !" << endl;
}
/**
 * @brief Donne acces au plateau actuel de la partie
 * @return Objet de type Plateau
 */
Plateau* Partie::getPlateau() const{
    return m_plat;
}

/**
 * @brief Donne acces a la configuration (liee aux options) de la partie
 * @return Objet de type Configuration
 */
Configuration* Partie::getConfig(){
    return config;
}

/**
 * @brief Donne acces aux caisses de la partie
 * @return Vecteur contenant les caisses
 */
Caisse* Partie::getCaisses(){
    return m_c;
}

/**
 * @brief Donne acces au personnage de la partie
 * @return Objet de type Personnage
 */
Personnage* Partie::getPerso(){
    return m_perso;
}

/**
 * @brief Donne acces au niveau actuel
 * @return entier contenant le niveau actuel
 */
int Partie::getNiv() const{
    return m_niv;
}

/**
 * @brief Permet de modifier le numero du niveau actuel
 * @param n : entier contenant le nouveau niveau
 */
void Partie::setNiv(int n){
    m_niv=n;
}

/**
 * @brief Reinitialise le niveau actuel
 */
void Partie::recommencerNiveau(){
    this->lancerNiveau(m_niv);
}

/**
 * @brief Donne acces au nombre de caisse du niveau actuel
 * @return entier contenant le nombre de caisse
 */
int Partie::getNbCaisses()const{
    return m_nbCaisses;
}

/**
 * @brief Permet de modifier le nombre de caisse
 * @param nbCaisses : entier contenant le nouveau nombre de caisses
 */
void Partie::setNbCaisses(int nbCaisses){
    m_nbCaisses=nbCaisses;
}

/**
 * @brief Permet de savoir si une case contient une caisse
 * @param x : entier contenant la coordonnee x de la case visee
 * @param y : entier contenant la coordonnee y de la case visee
 * @return entier contenant le numero de la caisse si la case visee la contient et -1 sinon
 */
int Partie::estCaisse(int x, int y){
    for(int i=0; i<m_nbCaisses; i++){
        if(m_c[i].getX()==x && m_c[i].getY()==y)
            return i;
    }
    return -1;
}

/**
 * @brief Permet de savoir si une case est vide
 * @param x : entier contenant la coordonnee x de la case visee
 * @param y : entier contenant la coordonnee y de la case visee
 * @return True si la case visee est vide et False sinon
 */
bool Partie::estVide(int x, int y) {
    return (m_plat->getTab()[y][x].getType() != '#' && estCaisse(x,y)==-1);
}

/**
 * @brief Permet de savoir si une caisse donnee est sur une cible
 * @param c : objet de type caisse
 * @return True si la caisse est sur une cible et False sinon
 */
bool Partie::caisseSurCible(Caisse &c){
    char type = m_plat->getTab()[c.getY()][c.getX()].getType();
    if(type=='.' or type=='+' or type=='*'){
        c.setImg(":/images/Caisse/caisseSurCible.png");
        return true;
     }
     else {
        c.setImg(":/images/Caisse/caisse.png");
        return false;
    }
}

//Renvoie vrai si le niveau est termine et faux sinon
/**
 * @brief Permet de savoir si un niveau est termine
 * @return True si le niveau est finit (chaque cible contient une caisse) et False sinon
 */
bool Partie::victoire(){
    int k=0;
    for(int i=0; i<m_nbCaisses; i++){
        if(this->caisseSurCible(m_c[i]))
            k++;
    }
    return(k==m_nbCaisses);
}

/**
 * @brief Destructeur
 */
Partie::~Partie(){
    delete m_plat;
    delete m_perso;
    delete[] m_c;
}
