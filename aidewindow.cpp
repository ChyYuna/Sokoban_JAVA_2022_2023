#include "aidewindow.h"
#include "ui_aidewindow.h"

/**
 * @file aidewindow.cpp
 * Fichier cpp pour la classe AideWindow
 */

/**
 * @brief Constructeur gerant l'affichage du contenu de la fenetre d'aide
 * @param parent
 */
AideWindow::AideWindow(QWidget *parent) :
    QDialog(parent),
    ui(new Ui::AideWindow)
{
    ui->setupUi(this);
    setWindowTitle("Comment jouer ?");
    this->setFixedSize(this->size()); //fixe la taille de la fenetre definitivement

    //Configuration du bouton Retour
    ui->bRetour->setStyleSheet("QPushButton{font-size: 14px;font-family: Arial}");
    QIcon menu(":/images/Icones/retour.png");
    ui->bRetour->setIcon(menu);
    ui->bRetour->setIconSize(QSize (15,15));

    //GIF montrant comment jouer
    QMovie *movie=new QMovie(":/images/aideJeu.gif");
    // Play GIF
    QLabel *label=new QLabel(this);
    //label->setGeometry(0,0,350,400);
    label->setMovie(movie);
    movie->start();

    this->setFocus(Qt::MouseFocusReason);
}

/**
 * @brief Destructeur
 */
AideWindow::~AideWindow()
{
    delete ui;
}

/**
 * @brief AideWindow::paintEvent
 * @param e
 */
void AideWindow::paintEvent(QPaintEvent* e) {
     QWidget::paintEvent(e);
     QPainter painter(this);

     painter.drawPixmap(425,240,120,70,QPixmap(":/images/fleches.png"));
}

/**
 * @brief Ferme la fenetre d'aide si on clique sur Retour
 */
void AideWindow::on_bRetour_clicked()
{
    this->close();
}
