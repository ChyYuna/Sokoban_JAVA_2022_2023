#ifndef CONFIGURATION_H
#define CONFIGURATION_H

#include <map>
#include <QString>
#include <fstream>
#include <iostream>

using namespace std;

/**
 * @file configuration.h
 * Fichier entete pour la classe Configuration
 */

/**
 * @brief Clase <code>Configuration</code> : permet de manipuler une configuration
 */
class Configuration
{
private :
    QString *m_listeCodes;
    int m_nbNiveau;
    int m_niveauALancer;

public:
    Configuration();
    Configuration(const Configuration &c);
    ~Configuration();
    QString* getListeCodes();
    int getNbNiv();
    int getNivAlancer();
    void setNivALancer(int niv);
};

#endif // CONFIGURATION_H
