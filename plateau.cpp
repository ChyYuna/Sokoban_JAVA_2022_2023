#include "plateau.h"

/**
 * @file plateau.cpp
 * Fichier cpp pour la classe Plateau
 */

/**
 * @brief Constructeur (creer le plateau de chaque niveau)
 * @param x : entier contenant la coordonnee x du plateau
 * @param y : entier contenant la coordonnee y du plateau
 * @param la : entier contenant la largeur du plateau
 * @param lo : entier contenant la longueur du plateau
 */
Plateau::Plateau(int x, int y, int la, int lo):Objet(x,y)
{
    m_la=la;
    m_lo=lo;
    tab = new ObjetImmobile*[m_la];
    for (int i=0; i<m_la; i++){
        tab[i] = new ObjetImmobile[m_lo];
        for (int j=0; j<m_lo; j++){
            tab[i][j] = ObjetImmobile(i,j,'_');
        }
    }
}

/**
 * @brief Constructeur de recopie
 * @param x : entier contenant la coordonnee x du plateau
 * @param y : entier contenant la coordonnee y du plateau
 * @param p : objet de type Plateau a recopier
 */
Plateau::Plateau(int x, int y, const Plateau &p):Objet(x,y)
{
    m_la=p.m_la;
    m_lo=p.m_lo;
    tab = new ObjetImmobile*[p.m_la];
    for (int i=0; i<p.m_la; i++){
        tab[i] = new ObjetImmobile[p.m_lo];
        for (int j=0; j<p.m_lo; j++){
            tab[i][j] = ObjetImmobile(i,j,p.tab[i][j].getType());
        }
    }
}

/**
 * @brief Constructeur supplementaire
 * @param x : entier contenant la coordonnee x du plateau
 * @param y : entier contenant la coordonnee y du plateau
 * @param fichier : ifstream du fichier contenant les elements du plateau du niveau en cours
 */
Plateau::Plateau(int x, int y, ifstream &fichier):Objet(x,y)
{
    char type;
    fichier >> m_la >> m_lo;

    tab = new ObjetImmobile*[m_la];
    for (int i=0; i<m_la; i++){
        tab[i] = new ObjetImmobile[m_lo];
        for (int j=0; j<m_lo; j++){
            fichier >> type;
            tab[i][j].setType(type);
           }
    }
}

/**
 * @brief Destructeur
 */
Plateau::~Plateau(){
    for (int i=0; i<m_la; i++)
        delete[] tab[i];
}

/**
 * @brief Donne acces aux elements du plateau (contenus dans un tableau d'ObjetImmobile)
 * @return Objet de type ObjetImmobile**
 */
ObjetImmobile** Plateau::getTab() const{
    return tab;
}

/**
 * @brief Donne acces a la largeur du plateau actuel
 * @return entier contenant la largeur du plateau
 */
int Plateau::getLargeur(){
    return m_la;
}

/**
 * @brief Donne acces a la longueur du plateau actuel
 * @return entier contenant la longueur du plateau
 */
int Plateau::getLongueur(){
    return m_lo;
}

/**
 * @brief Permet de modifier le tableau contenant les elements du plateau
 * @param T : ObjetImmobile** contenant le nouveau tableau
 */
void Plateau::setTab(ObjetImmobile** T){
    tab = T;
}

/**
 * @brief Permet de modifier la largeur du plateau
 * @param largeur : entier contenant la nouvelle largeur
 */
void Plateau::setLargeur(int largeur){
    m_la = largeur;
}

/**
 * @brief Permet de modifier la longueur du plateau
 * @param largeur : entier contenant la nouvelle longueur
 */
void Plateau::setLongueur(int longueur){
    m_lo = longueur;
}
