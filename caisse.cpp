#include "caisse.h"

/**
 * @file caisse.cpp
 * Fichier cpp pour la classe Caisse
 */

/**
 * @brief Constructeur
 * @param x : entier contenant la coordonnee x initiale de la caisse
 * @param y : entier contenant la coordonnee y initiale de la caisse
 */
Caisse::Caisse(int x, int y):ObjetMobile(x,y){
    this->setImg(":/images/Caisse/caisse.png");
}
