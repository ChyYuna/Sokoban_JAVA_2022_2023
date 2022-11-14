#ifndef MAINWINDOW_H
#define MAINWINDOW_H

#include <QMainWindow>
#include <QPainter>
#include <QKeyEvent>
#include <QMessageBox>
#include <QDesktopWidget>
#include <QScreen>
#include <QDialogButtonBox>
#include <string>
#include "partie.h"

QT_BEGIN_NAMESPACE
namespace Ui { class MainWindow; }
QT_END_NAMESPACE

/**
 * @file mainwindow.h
 * Fichier entete pour la classe MainWindow
 */

/**
 * @brief Classe <code>MainWindow</code> : permet l'affichage de la fenetre principale
 */
class MainWindow : public QMainWindow
{
    Q_OBJECT

private :
    QMessageBox msgBox, msgBox2;
    Partie *part;
    int pas;
    bool menu;

public:
    MainWindow(QWidget *parent = 0);
    ~MainWindow();

private:
    Ui::MainWindow *ui;

private slots :
    void paintEvent(QPaintEvent* e);
    void keyPressEvent (QKeyEvent * event);
    void messageVictoire();
    void messageFinal();
    void on_bRecommencerNiv_clicked();
    void on_bMenu_clicked();
    void on_bOptions_clicked();
    void on_bJouer_clicked();
    void on_bAide_clicked();
};
#endif // MAINWINDOW_H
