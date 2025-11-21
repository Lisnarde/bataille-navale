# Choix de conception

## La position

Chaque objet plaçable sur la grille d'un joueur peut accéder à sa position grâce aux cases sur lesquelles il est placé, c'est-à-dire la classe **Cell**. Un objet connait alors directement sa position.

Sur chaque cellule *cell* on peut vérifier si elle a déjà été touchée, on peut aussi définir la cellule comme **hit** (touchée). On peut, bien évidemment aussi récupérer ou 
définir sa position.

## Les objets plaçables

Il existe actuellement 5 types d'objets plaçables, les bateaux '*ship*', les pièges '*trap*', les tirs réalisés par les joueurs '*impact*', 
l'île qui sera placée sur la grille '*islandpart*' et les armes disposées sur l'île '*weapon*'.

Chaque objet peut donc récupérer sa position ou ses positions grâce à la méthode **getCells**. Elle renvoie un tableau de **Cell** car un bateau peut être défini sur plusieurs cases.

## La grille

La classe **Grid** contient un tableau d'objets **Placeable** qui pour rappel connaissent chacun leur position. Ainsi, pour ne pas surcharger la mémoire et pour simplifier le fonctionnement (pas besoin de tenir à jour les positions des deux côtés), la grille n'a pas besoin de stocker ces positions.

## Player

La classe **Player** sert à représenter un joueur, qu'il soit humain ou machine. 
Chacun stocke sa grille avec ses bateaux, pièges, impact de l'adversaires etc...

Un joueur stocke aussi toutes les armes qu'il possède : celles qu'il a récupérées sur le mode île, sinon il les a toutes.
On stocke également l'arme actuellement équipée pour l'utiliser directement au tir : utilisation d'un patern **strategy method**

## MVC & Observer

Bien sûr, un Modèle Vue Controller sera utilisé pour le projet, pour l'organisation du code, la practicité d'ajout et de modification de fonctionnalités, et le changement de type de vue facile (terminal, graphique) 

Pour mettre à jour la vue automatiquement, des observers seront utilisés.