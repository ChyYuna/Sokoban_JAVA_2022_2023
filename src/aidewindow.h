#ifndef AIDEWINDOW_H
#define AIDEWINDOW_H

#include <QDialog>
#include <QPainter>
#include <QMovie>
#include <QLabel>

namespace Ui {
class AideWindow;
}

/**
 * @file aidewindow.h
 * Fichier entete pour la classe AideWindow
 */

/**
 * @brief Classe <code>AideWindow</code> : permet l'affichage de la fenetre d'aide
 */
class AideWindow : public QDialog
{
    Q_OBJECT

public:
    explicit AideWindow(QWidget *parent = nullptr);
    ~AideWindow();

private slots:
    void on_bRetour_clicked();
    void paintEvent(QPaintEvent* e);

private:
    Ui::AideWindow *ui;
};

#endif // AIDEWINDOW_H
