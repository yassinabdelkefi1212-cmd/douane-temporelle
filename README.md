# Douane Temporelle (Time Travel Customs)

Mini projet (DS 1) – Programmation Mobile – 3ème BI – FSEGT

Application Android (Java) simulant un poste de douane pour voyageurs du temps.

## Les 3 activités
1. **MainActivity** : formulaire (nom, année de naissance, année de destination, motif via Spinner) + bouton « Inspecter le profil » (Intent explicite).
2. **VerificationActivity** : « passeport » (LinearLayout horizontal : image 0.3 + RelativeLayout 0.7), affiche nom, âge temporel, motif ; boutons Approuver / Refuser qui envoient le verdict (booléen) et le nom.
3. **ResultActivity** : fond vert (approuvé) ou rouge (refusé), message personnalisé, bouton « Nouveau voyageur » (retour à MainActivity en fermant les activités précédentes).

## Contraintes respectées
- Clés des `putExtra` préfixées par les 4 derniers chiffres de la carte étudiant, construites par la méthode statique `Cles.cle(matricule, nom)` (pas de clé en dur).
- Bouton « Approuver » désactivé au chargement si motif = « Modification du passé » ET (année destination − année naissance) est un nombre premier.
- Tous les IDs contiennent les initiales (`_cf`).
- Code commenté en arabe.

## Avant le rendu
- Modifier `Cles.MATRICULE` (4 derniers chiffres de votre carte).
- Si vos initiales ne sont pas `cf`, remplacer le suffixe `_cf` des IDs.
- Ouvrir le dossier dans Android Studio, puis pousser sur GitHub avec au moins autant de commits que de jours de travail.
