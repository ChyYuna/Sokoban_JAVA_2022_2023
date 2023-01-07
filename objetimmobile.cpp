#include "objetimmobile.h"

/**
 * @file objetimmobile.cpp
 * Fichier cpp pour la classe ObjetImmobile
 */

/**
 * @brief Constructeur
 * @param x : entier contenant la coordonnee x initiale de l'objet immobile
 * @param y : entier contenant la coordonnee y initiale de l'objet immobile
 * @param t : caractere contenant le type de l'objet immobile
 */
ObjetImmobile::ObjetImmobile(int x, int y, char t):Objet(x,y)
{
    m_type=t;
}

/**
 * @brief Donne acces au type de l'objet immobile ("#" : mur, "." : cible, "_" : vide)
 * @return caractere contenant le type de l'objet immobile
 */
char ObjetImmobile::getType(){
    return m_type;
}

/**
 * @brief Permet de modifier le type de l'objet immobile
 * @param type : caractere contenant le nouveau type de l'objet immobile
 */
void ObjetImmobile::setType(char type){
    m_type=type;
}
