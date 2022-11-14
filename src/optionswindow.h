#ifndef OPTIONSWINDOW_H
#define OPTIONSWINDOW_H

#include <QDialog>
#include "configuration.h"
#include <QMessageBox>
#include <QPainter>

namespace Ui {
class OptionsWindow;
}

/**
 * @file optionswindow.h
 * Fichier entete pour la classe OptionsWindow
 */

/**
 * @brief Classe <code>OptionsWindow</code> : permet l'affichage de la fenetre des options (configuration)
 */
class OptionsWindow : public QDialog
{
    Q_OBJECT

private :
    Configuration *config;
    QMessageBox msgBox;
    bool codeFaux;

public:
    explicit OptionsWindow(QWidget *parent = nullptr);
    ~OptionsWindow();
    int trouve(QString code);
    Configuration* getConfig();

private slots:
    void on_bRetour_clicked();
    void on_bValider_clicked();
    void paintEvent(QPaintEvent* e);

private:
    Ui::OptionsWindow *ui;
};

#endif // OPTIONSWINDOWS_H
