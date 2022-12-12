#ifndef OBJETIMMOBILE_H
#define OBJETIMMOBILE_H

#include "objet.h"

/**
 * @file objetimmobile.h
 * Fichier entete pour la classe ObjetImmobile
 */

/**
 * @brief Classe <code>ObjetImmobile</code> : permet de manipuler des objets immobiles
 */
class ObjetImmobile : public Objet
{
private:
    char m_type;

public:
    ObjetImmobile(int x=0, int y=0, char t='_');
    char getType();
    void setType(char type);
};

#endif // OBJETIMMOBILE_H
