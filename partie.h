#ifndef PARTIE_H
#define PARTIE_H

#include "plateau.h"
#include "personnage.h"
#include "caisse.h"
#include "configuration.h"
#include <iostream>
#include <cstring>

using namespace std;

/**
 * @file partie.h
 * Fichier entete pour la classe Partie
 */

/**
 * @brief Classe <code>Partie</code> : permet de manipuler des parties
 */
class Partie
{
private:
    int m_niv;
    int m_nbCaisses;
    Personnage* m_perso;
    Caisse* m_c;
    Plateau* m_plat;
    Configuration* config;

public:
    Partie();
    Partie(const Partie &part);
    ~Partie();
    Plateau* getPlateau() const;
    Configuration* getConfig();
    Caisse* getCaisses();
    Personnage* getPerso();
    void lancerNiveau(int niv);
    bool estVide(int x, int y) ;
    void recommencerNiveau();
    bool victoire();
    int getNiv() const;
    void setNiv(int n);
    int getNbCaisses()const;
    void setNbCaisses(int nbCaisses);
    int estCaisse(int x, int y);
    bool caisseSurCible(Caisse &c);
};

#endif // PARTIE_H
