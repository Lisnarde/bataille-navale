# Rapport : Nos choix de conception

Notre application respectant strictement l'architecture MVC, 
elle est alors composée de trois packages principaux : model, view, controller.

Nous avons tenté de séparer les responsabilités et garder une extensibilité du jeu 
pour permettre l'ajout de nouveaux objets, vues et types d'adversaires simplement.



# 1. Le modèle

Le modèle représente la logique du jeu lui-même, il est totalement indépendant de l'interface vue par l'utilisateur.

## 1.1 La classe Game

Game est la façade du modèle : cela évite que les vues accèdent directement à des objets internes au modèle.
La logique du modèle se centralise alors au niveau de cette classe et réduira les dépendances.

Elle contient et gère les deux joueurs, le tour courant, vérifie la fin de partie, permet aux joueurs de réaliser leurs actions.
Elle stocke également les inventaires globaux d'armes et de pièges, pour permettre aux joueurs de les placer en début de partie, 
et le nombre de bateaux par types à placer.

Elle intègre aussi des **GameObserver** qui notifient sur des informations telles que le tour actuel, la fin de partie ou encore
la récupération ou l'utilisation d'une arme à usage unique d'un joueur. Ils permettent aux vues de pouvoir être au courant.

## 1.2 Player 

Player représente un joueur, qu'il soit humain ou ordinateur.

Un joueur possède une grille, un inventaire d'armes ainsi qu'une arme équipée. L'attaque est réalisée par l'arme équipée, c'est
un **Pattern Strategy** où chaque arme implémente son comportement de tir -> on peut donc ajouter des armes sans modifer Player

## 1.3 Grid

La grille représente l'espace de jeu d'un joueur, elle stocke ses dimensions ainsi qu'une liste d'objets Placeable.
C'est une grille implicite : elle ne stocke pas directement les cellules avec les objets dedans, ce sont les objets qui connaissent 
directement leurs positions. Cela réduit l'utilisation de mémoire, évite la redondance des informations, mais rend les algorithmes 
associés à la recherche d'une position un peu plus complexes.

Elle possède des observers **GridObserver** qui notifient sur des informations comme relatives à la grille, c'est-à-dire les tirs, placements
d'objets, pièges activés, un bateau coulé, l'absence de bateau encore en vie ou des infos relatives aux armes.

## 1.4 Cell

Cell est l'objet par défaut dès qu'on parle de position, il en effet un x et un y.

## 1.5 Les objets Placeable

Chaque objet plaçable peut connaitre son type, entre Ship, Trap, Weapon, IslandPart ou Impact. 
Ils connaissent les cellules qu'ils occupent.

### 1.5.a Ships

Ils sont définis par un type de bateau parmis _AircraftCarrier, Cruiser, Destroyer, Submarine et Torpedo_.
Ils sont représentés par un ensemble de **Cell** qui représentent sont espace occupé. Ainsi il n'y a pas besoin de stocker
une taille ou un axe de placement.

### 1.5.b Traps

Tous les pièges héritent de la classe abstraite **Trap**, chacun possède son comportement spécifique avec la méthode execTrap.
Son activation est automatique lors d'un tir. C'est un design qui simplifie l'ajout de nouveaux pièges.

Ainsi, **BlackHole** et **Tornado** héritent de **Trap**, ces deux sont alors plaçables sur la grille et activables. 
**BlackHole** est le seul qui fonctionne réellement, **Tornado** est utilisable mais n'a pas d'effet à son activation (par manque de temps).

### 1.5.c Weapons

Toutes les armes héritent de la calsse abstraite **Weapon**, chacun possède son comportement spécifique avec la méthode execShoot.
Ce **Pattern Strategy** simplifie l'ajout de nouvelles armes, tout comme les pièges.

**Bomb** et **Sonar** héritent de **Weapon** et son totalement utilisables et fonctionnels.
Certaines armes comme le Sonar peuvent interagir avec les observers pour donner des informations aux vues.

### 1.5.d IslandPart

C'est une partie d'île, c'est-à-dire une case unique. L'île est alors l'ensemble de ces parties d'île.
Une partie peut stocker au maximum un objet **Placeable**.

### 1.5.e Impact

Un impact sert uniquement à marquer une case, de telle sorte qu'on sache qu'on a déjà "interagit" avec cette case, pour éviter 
de pouvoir tirer dessus plusieurs fois.



# 2. Le Controller

Les controllers servent à la gestion des actions et la navigation entre les vues.

## 2.1 GameController

Il sert d'intermédiaire entre la vue, le modèle et le bot. Dès que quelque chose veut modifier ou manipuler le modèle (hors consultation), 
c'est par GameController qu'il doit passer.

Il convertit les demandes dans les bons types pour le modèle (simplifie les demandes), valide les actions et empêche les illégales.
C'est aussi lui qui déclenche les tours de l'IA.

Il garantit donc le respect du MVC.

## 2.2 NavigationController

C'est la classe qui gère le changement d'écrans, en passant par un **CardLayout** de Swing

Il permet de faire en sorte que les vues ne connaissent pas leurs voisines, mais demanderont simplement au NavigationController la prochaine vue.

## 2.3 Bot

Bot est une interface qui permet d'interchanger facilement différents types d'IA.
Il définit le comportement du placement des objets et du tir. On peut très facilement développer de nouvelles IA grâce
à ce **Pattern Strategy**.

Pour l'instant, il y a deux implémentations de bot. Un RandomBot qui joue totalement aléatoirement.
Il y a aussi un SmartBot qui joue aléatoirement jusqu'à toucher un bateau, après, il cherchera à le couler.



# 3. Les vues

Les vues sont représentées par les interfaces présentées aux utilisateurs. Ce sont elles qui affichent l'état de la partie, les actions jouées
et transmettent les demandes de l'utilisateur au controller. Elles peuvent également consulter le model pour afficher la partie.

## 3.1 GraphicalView

C'est la fenêtre principale : elle initialise toutes les vues, applique le **Theme**, puis délègue la navigation au **NavigationController**.

## 3.2 Les écrans

L'application est découpée en plusieurs écrans : ConfigScreen, PlacementScreen, GameScreen, EndScreen.

Chaque écran est indépendant des autres, il applique sa propre logique d'affichage. La majorité sont mis à jour via Observers.

Chacun peut utiliser des Panel réutilisables qui sont définis dans **components**.

## 3.3 GridPanel

Le GridPanel est réutilisé dans plusieurs contextes comme le placement, l'attaque et la réception de tirs.
Il figure ainsi sur PlacementScreen et GameScreen.

Son comportement est défini par un mode via l'énumération **GridMode**. 

## 3.4 Theme

La classe **Theme** définit des types d'affichages précis (pour les boutons par exemple) ou les polices d'écriture.
Elle permet que les affichages soient cohérents entre eux et éviter la redondance de code dans les méthodes pour dessiner l'interface.

Le theme est ainsi transmit de GraphicalView, jusqu'à absolument toutes les vues.

# Conclusion

Les évolutions depuis le premier rendu ont permis une meilleure architecture et une meilleure extensibilité 
(armes, pièges, IA, vues...)

Par manque de temps, nous n'avons pas pu implémenter le fonctionnement de **Tornado**, même s'il reste plaçable et utilisable.

Il manque également un bouton pour recommencer une nouvelle partie de zéro, mais il se serait résumer à appeler une méthode de 
réinitialisation de **Game**, notamment en appelant washGrid() qui est déjà implémenté dans **Grid**. 
Ensuite, il aurait fallu appeler la méthode showConfig() de NavigationController. La vraie difficulté aurait été 
la réinitialisation du modèle, car cela nécessite de penser à beaucoup de choses (toutes les informations stockées dans 
les attributs, ainsi que toutes les sous-classes -> pouvait mener à de nombreux bugs lors d'une seconde partie)

Nous avons également pû compléter la partie sur les vues qui était incomplète, car nous nous concentrions d'abord sur le modèle 
avant de réfléchir aux vues
