#include "configuration.h"

/**
 * @file configuration.cpp
 * Fichier cpp pour la classe Configuration
 */

/**
 * @brief Constructeur (lit le nombre de niveaux du jeu et la liste des codes des niveaux)
 */
Configuration::Configuration()
{
    m_niveauALancer = -1; //Par default, le niveau a lancer est -1, c'est a dire que rien ne se lance
    ifstream fichier("codes.txt", ios::in);

    if(!fichier.fail()){
        string code;
        fichier >> m_nbNiveau;
        m_listeCodes = new QString[m_nbNiveau];

        for (int i=0; i<m_nbNiveau; i++) //On remplie notre liste avec les codes contenus dans le fichier
        {
             fichier >> code;
             m_listeCodes[i] = QString::fromStdString(code);
        }
        fichier.close();
     }
     else
        cerr << "Impossible d'ouvrir le fichier !" << endl;
}

/**
 * @brief Constructeur de recopie
 * @param c : objet de type Congiguration a recopier
 */

Configuration::Configuration(const Configuration &c){
    m_niveauALancer = c.m_niveauALancer;
    m_nbNiveau = c.m_nbNiveau;

    m_listeCodes = new QString[c.m_nbNiveau];
    for (int i=0; i<c.m_nbNiveau; i++)
         m_listeCodes[i] = c.m_listeCodes[i];
}

/**
 * @brief Destructeur
 */
Configuration::~Configuration(){
    delete[] m_listeCodes;
}

/**
 * @brief Donne acces a la liste des code des niveaux du jeu
 * @return vecteur contenant les codes
 */
QString* Configuration::getListeCodes(){
    return m_listeCodes;
}

/**
 * @brief Donne acces au nombre de niveaux du jeu
 * @return entier contenant le nombre de niveaux
 */
int Configuration::getNbNiv(){
    return m_nbNiveau;
}

/**
 * @brief Donne acces au niveau que le jeu doit lancer
 * @return entier contenant le niveau a lancer
 */
int Configuration::getNivAlancer(){
    return m_niveauALancer;
}

/**
 * @brief Permet de modifier le niveau que le jeu doit lancer
 * @param niv : entier contenant le nouveau niveau a lancer
 */
void Configuration::setNivALancer(int niv){
    m_niveauALancer = niv;
}
