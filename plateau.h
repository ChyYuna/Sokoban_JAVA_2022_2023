#ifndef PLATEAU_H
#define PLATEAU_H

#include "objet.h"
#include "objetimmobile.h"
#include <fstream>
#include <iostream>
#include <cstring>

using namespace std;

/**
 * @file plateau.h
 * Fichier entete pour la classe Plateau
 */

/**
 * @brief Classe <code>Plateau</code> : permet de manipuler un plateau
 */
class Plateau: public Objet
{
private:
    ObjetImmobile** tab;
    int m_la;
    int m_lo;

public:
    Plateau(int x=0, int y=0, int la=5, int lo=5);
    Plateau(int x, int y, const Plateau &p);
    Plateau(int x, int y, ifstream &fichier);
    ~Plateau();
    ObjetImmobile** getTab() const;
    int getLargeur();
    int getLongueur();
    void setLargeur(int largeur);
    void setLongueur(int longueur);
    void setTab(ObjetImmobile** T);
};

#endif // PLATEAU_H
