#ifndef OBJETMOBILE_H
#define OBJETMOBILE_H

#include "objet.h"
#include <QString>

/**
 * @file objetmobile.h
 * Fichier entete pour la classe ObjetMobile
 */

/**
 * @brief Classe <code>ObjetMobile</code> : permet de manipuler des objets mobiles
 */
class ObjetMobile: public Objet{
    private :
        QString img;

    public :
        ObjetMobile(int x=0, int y=0);
        void deplacerDe(int dx, int dy);
        QString getImg();
        void setImg(QString i);
};

#endif // OBJETMOBILE_H
