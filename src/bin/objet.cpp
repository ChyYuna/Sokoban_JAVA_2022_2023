#include "objet.h"

/**
 * @file objet.cpp
 * Fichier cpp pour la classe Objet
 */

/**
 * @brief Constructeur
 * @param x : entier contenant la coordonnee x initiale de l'objet
 * @param y : entier contenant la coordonnee y initiale de l'objet
 */
Objet::Objet(int x, int y){
    m_x = x;
    m_y = y;
}

/**
 * @brief Donne acces a la coordonnee x de l'objet
 * @return entier contenant la coordonnee x de l'objet
 */
int Objet::getX(){
    return m_x;
}

/**
 * @brief Donne acces a la coordonnee y de l'objet
 * @return entier contenant la coordonnee y de l'objet
 */
int Objet::getY(){
    return m_y;
}

/**
 * @brief Permet de modifier la coordonnee x de l'objet
 * @param newX : entier contenant la nouvelle coordonnee x
 */
void Objet::setX(int newX){
    m_x = newX;
}

/**
 * @brief Permet de modifier la coordonnee y de l'objet
 * @param newY : entier contenant la nouvelle coordonnee y
 */
void Objet::setY(int newY){
    m_y = newY;
}
