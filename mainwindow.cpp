#include "mainwindow.h"
#include "ui_mainwindow.h"
#include "optionswindow.h"
#include "aidewindow.h"

/**
 * @file mainwindow.cpp
 * Fichier cpp pour la classe MainWindow
 */

/**
 * @brief Constructeur gerant l'affichage du contenu de la fenetre principale
 * @param parent
 */

MainWindow::MainWindow(QWidget *parent)
    : QMainWindow(parent)
    , ui(new Ui::MainWindow)
{
    ui->setupUi(this);
    setWindowTitle("MonSokoban");
    part = new Partie;
    menu = true;
    pas=50; //correspond à la distance entre chaque centre d'objet (caisse, mur...)

    move(QGuiApplication::screens().at(0)->geometry().center() - frameGeometry().center()); //deplace la fenetre au centre de l'ecran
    this->setFixedSize(this->size()); //fixe la taille de la fenetre definitivement

    //Configuration du bouton Recommencer
    ui->bRecommencerNiv->setStyleSheet("QPushButton{font-size: 21px;font-family: Montserrat;font-weight: bold;color: rgb(255, 255, 255);background-color: rgb(220,20,60);border-radius: 10px;}");
    ui->bRecommencerNiv->setIcon(QIcon (":/images/Icones/restart.png"));
    ui->bRecommencerNiv->setIconSize(QSize (30,30));

    //Configuration du bouton Menu
    ui->bMenu->setStyleSheet("QPushButton{font-size: 22px;font-family: Montserrat;font-weight: bold;color: rgb(255, 255, 255);background-color: rgb(60,179,113);border-radius: 10px;}");
    ui->bMenu->setIcon(QIcon (":/images/Icones/menu.png"));
    ui->bMenu->setIconSize(QSize (30,30));

    //Configuration du bouton Configuration
    ui->bOptions->setStyleSheet("QPushButton{font-size: 20px;font-family: Montserrat;font-weight: bold;color: rgb(255, 255, 255);background-color: rgb(138,43,226);border-radius: 10px;}");
    ui->bOptions->setIcon(QIcon (":/images/Icones/options.png"));
    ui->bOptions->setIconSize(QSize (40,40));

    //Configuration du bouton Jouer
    ui->bJouer->setStyleSheet("QPushButton{font-size: 30px;font-family: Montserrat;font-weight: bold;color: rgb(255, 255, 255);background-color: rgb(251,177,13);border-radius: 10px;}");
    ui->bJouer->setIcon(QIcon (":/images/Icones/play.png"));
    ui->bJouer->setIconSize(QSize (40,40));

    //Configuration du bouton Aide
    ui->bAide->setStyleSheet("QPushButton{font-size: 20px;font-family: Montserrat;font-weight: bold;color: rgb(255, 255, 255);background-color: rgb(254,168,119);border-radius: 10px;}");
    ui->bAide->setIcon(QIcon (":/images/Icones/aide.png"));
    ui->bAide->setIconSize(QSize (20,20));

    //Lancement du premier niveau
    part->lancerNiveau(1);

    this->setFocus(Qt::TabFocusReason); //permet de "focus" les entrees clavier sur le deplacement du personnage et non sur les boutons
}

/**
 * @brief Destructeur
 */
MainWindow::~MainWindow()
{
    delete ui;
    delete part;
}

/**
 * @brief MainWindow::paintEvent (gere l'affichage des images)
 * @param e
 */
void MainWindow::paintEvent(QPaintEvent* e) {
     QWidget::paintEvent(e);
     QPainter painter(this);
     char type;

     if (menu) //on affiche le menu
     {
        painter.drawPixmap(0,0,1310,580,QPixmap(":/images/menu.png"));

        //Sur le menu, on rend invisible les boutons Menu et Recommencer et visible le bouton Jouer
        ui->bRecommencerNiv->setVisible(false);
        ui->bMenu->setVisible(false);
        ui->bJouer->setVisible(true);
        ui->line->setVisible(false);
     }

     else {
         painter.drawPixmap(0,0,1000,580,QPixmap(":/images/background.png"));

         //Sur l'interface de jeu, on rend invisible le bouton Jouer et visible les boutons Menu et Recommencer
         ui->bJouer->setVisible(false);
         ui->bRecommencerNiv->setVisible(true);
         ui->bMenu->setVisible(true);
         ui->line->setVisible(true);

         //Affichage du plateau du niveau charge
         for (int i=0; i<part->getPlateau()->getLargeur(); i++){
             for (int j=0; j<part->getPlateau()->getLongueur(); j++){
                 type = part->getPlateau()->getTab()[i][j].getType();
                 if ( type == '#'){
                     painter.drawPixmap((j+part->getPlateau()->getX())*pas,(i+part->getPlateau()->getY())*pas,50,50,QPixmap(":/images/Autres/vide.png"));
                     painter.drawPixmap((j+part->getPlateau()->getX())*pas,(i+part->getPlateau()->getY())*pas,50,50,QPixmap(":/images/Autres/mur.png"));
                 }
                 else if (type == '.' or type== '*' or type == '+'){
                     painter.drawPixmap((j+part->getPlateau()->getX())*pas,(i+part->getPlateau()->getY())*pas,50,50,QPixmap(":/images/Autres/vide.png"));
                     painter.drawPixmap((j+part->getPlateau()->getX())*pas,(i+part->getPlateau()->getY())*pas,50,50,QPixmap(":/images/Autres/cible.png"));
                 }
                 else
                     painter.drawPixmap((j+part->getPlateau()->getX())*pas,(i+part->getPlateau()->getY())*pas,50,50,QPixmap(":/images/Autres/vide.png"));
             }
         }

         //Affichage des caises et du personnage du niveau charge
         for (int i=0; i<part->getNbCaisses(); i++)
             painter.drawPixmap((part->getCaisses()[i].getX()+ part->getPlateau()->getX())*pas,(part->getCaisses()[i].getY()+ part->getPlateau()->getY())*pas,50,50,QPixmap(part->getCaisses()[i].getImg()));

         painter.drawPixmap((part->getPerso()->getX() + part->getPlateau()->getX())*pas,(part->getPerso()->getY()+ part->getPlateau()->getY())*pas,50,50,QPixmap(part->getPerso()->getImg()));

        //Affichage du numero du niveau actuel
         painter.setPen(QPen(Qt::darkGreen,1));
         painter.setFont(QFont("Molot",38));
         painter.drawText(10,50,QString("Niveau "+QString::number(part->getNiv())));

         //Affichage du code d'acces du niveau
         painter.setPen(QPen(Qt::black,1));
         painter.setFont(QFont("Arial",15,QFont::Bold));
         painter.drawText(1030,80,QString("Code du niveau : " + part->getConfig()->getListeCodes()[part->getNiv()-1]));
     }
}

/**
 * @brief MainWindow::keyPressEvent (gere les entrees claviers)
 * @param event
 */
void MainWindow::keyPressEvent (QKeyEvent * event) {
    int numCaisse;
    int x_perso = part->getPerso()->getX();
    int y_perso = part->getPerso()->getY();

    switch(event->key()){
     case Qt::Key_Up :
        numCaisse = part->estCaisse(x_perso,y_perso-1);
        if (part->estVide(x_perso,y_perso-1)) //Si la case en haut du personnage est vide, on le deplace
            part->getPerso()->deplacerDe(0,-1);
        else if (numCaisse!=-1 and part->estVide(x_perso,y_perso-2)) //Sinon si la case en haut du personnage contient une caisse et que la case d'apres est vide, on deplace les deux
        {
            part->getCaisses()[numCaisse].deplacerDe(0,-1);
            part->getPerso()->deplacerDe(0,-1);
        }
        part->getPerso()->setImg(":/images/Joueur/playerUp.png");  //On affiche le personnage dans la direction du deplacement effectue
        break;

     case Qt::Key_Left :
        numCaisse = part->estCaisse(x_perso-1,y_perso);
        if (part->estVide(x_perso-1,y_perso)) //Si la case a gauche du personnage est vide, on le deplace
            part->getPerso()->deplacerDe(-1,0);
        else if (numCaisse!=-1 and part->estVide(x_perso-2,y_perso)) //Sinon si la case a gauche du personnage contient une caisse et que la case d'apres est vide, on deplace les deux
        {
            part->getCaisses()[numCaisse].deplacerDe(-1,0);
            part->getPerso()->deplacerDe(-1,0);
        }
        part->getPerso()->setImg(":/images/Joueur/playerLeft.png");  //On affiche le personnage dans la direction du deplacement effectue
        break;

     case Qt::Key_Down :
        numCaisse = part->estCaisse(x_perso,y_perso+1);
        if (part->estVide(x_perso,y_perso + 1))  //Si la case en bas du personnage est vide, on le deplace
            part->getPerso()->deplacerDe(0,1);
        else if (numCaisse!=-1 and part->estVide(x_perso,y_perso+2))  //Sinon si la case en bas du personnage contient une caisse et que la case d'apres est vide, on deplace les deux
        {
            part->getCaisses()[numCaisse].deplacerDe(0,1);
            part->getPerso()->deplacerDe(0,1);
        }
        part->getPerso()->setImg(":/images/Joueur/playerDown.png");  //On affiche le personnage dans la direction du deplacement effectue
        break;

     case Qt::Key_Right :
        numCaisse = part->estCaisse(x_perso+1,y_perso); //Si la case a droite du personnage est vide, on le deplace
        if (part->estVide(x_perso + 1,y_perso))
            part->getPerso()->deplacerDe(1,0);
        else if (numCaisse!=-1 and part->estVide(x_perso+2,y_perso))  //Sinon si la case a droite du personnage contient une caisse et que la case d'apres est vide, on deplace les deux
        {
            part->getCaisses()[numCaisse].deplacerDe(1,0);
            part->getPerso()->deplacerDe(1,0);
        }
        part->getPerso()->setImg(":/images/Joueur/playerRight.png");  //On affiche le personnage dans la direction du deplacement effectue
        break;

     //Supplements : raccourci des boutons via le clavier
     case Qt::Key_R : this->on_bRecommencerNiv_clicked();
        break;
     case Qt::Key_O : this->on_bOptions_clicked();
        break;
     case Qt::Key_A : this->on_bAide_clicked();
        break;
     }

     bool finNiv = part->victoire();
     this->repaint();

     if (part->getNiv() == part->getConfig()->getNbNiv() && finNiv) //Si le dernier niveau est termine, on affiche le message special
         this->messageFinal();

     if (finNiv && part->getNiv() != part->getConfig()->getNbNiv())  //Si un niveau quelconque est termine, on affiche le message de fin de niveau classique
         this->messageVictoire();
}

/**
 * @brief Boite de dialogue affichant un message de victoire
 */
void MainWindow::messageVictoire(){
    msgBox.setWindowTitle("Bravo");
    msgBox.setText("Vous avez reussi le niveau " + QString::number(part->getNiv()));
    msgBox.setButtonText(QMessageBox::Ok,"Niveau suivant");
    msgBox.exec();

    part->lancerNiveau(part->getNiv()+1); //Suite au message, on lance le niveau suivant

    this->repaint();
}

/**
 * @brief Boite de dialogue affichant un message de victoire pour le dernier niveau
 */
void MainWindow::messageFinal(){
    msgBox.setWindowTitle("Felicitations");
    msgBox.setText("Vous avez termine le dernier niveau !!!\nToutes les caisses de l'entrepot sont rangees.");
    msgBox.setButtonText(QMessageBox::Ok,"Retour au menu");
    msgBox.exec();
    menu = true;
    this->repaint();
    this->setFocus(Qt::MouseFocusReason); //Sur le menu, on focus les entrees sur la souris
}

/**
 * @brief Reinitialise le niveau en cours si on clique sur Recommencer
 */
void MainWindow::on_bRecommencerNiv_clicked()
{
    part->recommencerNiveau();
    this->repaint();
    this->setFocus(Qt::TabFocusReason);
}

/**
 * @brief Ramene au menu principal si on clique sur Menu
 */
void MainWindow::on_bMenu_clicked()
{
    msgBox2.setWindowTitle("Retour au menu");
    msgBox2.setText("Cette action est irreversible !\nToute progression sera perdue.");  //On affiche un message de prevention en cas de miss-click
    msgBox2.setIcon(QMessageBox::Warning);
    msgBox2.setStandardButtons(QMessageBox::Yes);
    msgBox2.setDefaultButton(QMessageBox::Yes);
    msgBox2.addButton(QMessageBox::No);
    msgBox2.setButtonText(QMessageBox::Yes,"Continuer");
    msgBox2.setButtonText(QMessageBox::No,"Annuler");

    if(msgBox2.exec() == QMessageBox::Yes){
        menu = true;
        this->repaint();
    }
    this->setFocus(Qt::MouseFocusReason); //Sur le menu, on focus les entrees sur la souris
}

/**
 * @brief Ouvre la fenetre des options si on clique sur Configuration
 */
void MainWindow::on_bOptions_clicked()
{
    OptionsWindow f(this);
    f.exec();
    if (f.getConfig()->getNivAlancer() != -1) //Si le niveau a lancer de cette fenetre est different de -1, alors la fenetre principale doit lancer le niveau
    {
        part->lancerNiveau(f.getConfig()->getNivAlancer());
        menu = false;
        f.close();
    }
    this->repaint();
    this->setFocus(Qt::TabFocusReason);
}

/**
 * @brief Lance le jeu (niveau 1) si on clique sur Jouer
 */
void MainWindow::on_bJouer_clicked()
{
    menu = false;
    part->lancerNiveau(1);
    this->repaint();
    this->setFocus(Qt::TabFocusReason);
}

/**
 * @brief Ouvre la fenetre d'aide si on clique sur Aide
 */
void MainWindow::on_bAide_clicked()
{
    AideWindow f(this);
    f.exec();
    this->setFocus(Qt::TabFocusReason);
}
