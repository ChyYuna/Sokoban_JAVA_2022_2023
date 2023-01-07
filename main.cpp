#include "mainwindow.h"

#include <QApplication>

/**
 * @mainpage Sokoban Master Quest
 * @section intro_sec Bienvenue sur la page doxygen de notre jeu Sokoban
 * Vous trouverez parmi les onglets ci-dessus tous les elements necessaires a la comprehension de notre code.\n
 * Chaque classe ainsi que leurs fonctions/procedures y sont detaillees.\n
 * Les fichiers sources sont aussi mis a disposition.
 */

int main(int argc, char *argv[])
{
    QApplication a(argc, argv);
    MainWindow w;
    w.show();
    return a.exec();
}
