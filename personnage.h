#ifndef PERSONNAGE_H
#define PERSONNAGE_H

#include "objetmobile.h"

/**
 * @file personnage.h
 * Fichier entete pour la classe Personnage
 */

/**
 * @brief  Classe <code>Personnage</code> : permet de manipuler un personnage
 */
class Personnage: public ObjetMobile{
    public :
        Personnage(int x=0, int y=0);   
};


#endif // PERSONNAGE_H
