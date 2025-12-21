# Guide d'installation et d'utilisation

Le projet contient uniquement du code source en Java, il faut ainsi le compiler pour pouvoir l'exécuter.

## Prérequis

- Java JDK 17 (ou supérieur)
- Un terminal (Linux / macOS / Windows PowerShell)
- Aucun IDE requis (IntelliJ n’est pas nécessaire pour compiler et lancer le projet)

Vérifier que Java est installé :
```bash
java --version
javac --version
```

## Cloner le dépôt
```bash
git clone https://git.unistra.fr/isnard-dumangin/a31-bataille-navale.git
cd a31-bataille-navale
```

## Compiler

Compilation récursive dans le dossier out :

- Linux :
```bash
javac -d out $(find src -name "*.java")
```
- Windows PowerShell :
```bash
javac -d out (Get-ChildItem -Recurse src -Filter *.java).FullName
```

## Lancer l'application

```bash
java -cp out Main
```