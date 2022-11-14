#ifndef OBJET_H
#define OBJET_H

/**
 * @file objet.h
 * Fichier entete pour la classe Objet
 */

/**
 * @brief Classe <code>Objet</code> : permet de manipuler des objets propres au jeu
 */
class Objet{
    private :
        int m_x;
        int m_y;

    public :
        Objet(int x=0, int y=0);
        int getX();
        void setX(int newX);
        int getY();
        void setY(int newY);
};

#endif // OBJET_H
