#include "objetmobile.h"

/**
 * @file objetmobile.cpp
 * Fichier cpp pour la classe ObjetMobile
 */

/**
 * @brief Constructeur
 * @param x : entier contenant la coordonnee x initiale de l'objet mobile
 * @param y : entier contenant la coordonnee y initiale de l'objet mobile
 */
ObjetMobile::ObjetMobile(int x, int y):Objet(x,y){
}

/**
 * @brief Deplace l'objet mobile (personnage ou caisse) de +dx selon x et +dy selon y
 * @param dx : entier contenant le deplacement selon x
 * @param dy : entier contenant le deplacement selon y
 */
void ObjetMobile::deplacerDe(int dx,int dy){
    this->setX(this->getX() + dx);
    this->setY(this->getY() + dy);
}

/**
 * @brief Donne acces a l'image de l'objet mobile dans l'etat
 * @return QString contenant l'image de l'objet mobile
 */
QString ObjetMobile:: getImg(){
    return img;
}

/**
 * @brief Permet de modifier l'image de l'objet mobile
 * @param i : QString contenant la nouvelle image de l'objet mobile
 */
void ObjetMobile:: setImg(QString i){
    img = i;
}

