#include "personnage.h"

/**
 * @file personnage.cpp
 * Fichier cpp pour la classe Personnage
 */

/**
 * @brief Constructeur
 * @param x : entier contenant la coordonnee x initiale du personnage
 * @param y : entier contenant la coordonnee y initiale du personnage
 */
Personnage::Personnage(int x, int y):ObjetMobile(x,y){
    this->setImg(":/images/Joueur/playerDown.png");
}
