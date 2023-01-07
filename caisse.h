#ifndef CAISSE_H
#define CAISSE_H

#include "objetmobile.h"

/**
 * @file caisse.h
 * Fichier entete pour la classe Caisse
 */

/**
 * @brief  Classe <code>Caisse</code> : permet de manipuler des caisses
 */
class Caisse: public ObjetMobile
{
public:
    Caisse(int x=0, int y=0);
};

#endif // CAISSE_H
