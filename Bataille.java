import java.lang.Math;
import java.util.ArrayList;

public class Bataille {
    public static void main(String[] args) {

          /* Génération d'un paquet de 52 cartes et mélange aléatoire*/

          ArrayList<Carte> paquet_carte = new ArrayList<Carte>();

          for (int z=0; z < Carte.COULEURS.length; z++){
              for (int j = 0; j < Carte.VALEURS.length; j++){
                  Carte c = new Carte(Carte.VALEURS[j], Carte.COULEURS[z]);
                  paquet_carte.add(c);
              }
          }

          int taille_paquet = paquet_carte.size();

          for (int indice = 0; indice < taille_paquet; indice++){
              int indice_carte_tiree = (int)(Math.random() * taille_paquet);
              int indice_carte_inserer = (int)(Math.random() * taille_paquet);

              Carte swap = paquet_carte.get(indice_carte_inserer);
              Carte carte_tiree = paquet_carte.get(indice_carte_tiree);

              paquet_carte.set(indice_carte_inserer, carte_tiree);
              paquet_carte.set(indice_carte_tiree, swap);
          }

          // Distribution des cartes

          ArrayList<Carte> cartes_j1 = new ArrayList<Carte>();
          ArrayList<Carte> cartes_j2 = new ArrayList<Carte>();

          for (int i=0; i < taille_paquet; i++){
              if (i%2 == 0){
                  cartes_j1.add( (Carte) paquet_carte.get(i));
              } else {
                  cartes_j2.add( (Carte) paquet_carte.get(i));
              }
          }

          // Instanciation des joueurs

          Joueur j1 = new Joueur(cartes_j1);
          Joueur j2 = new Joueur(cartes_j2);

          // Début de la boucle de jeu

          int indice_carte_joueur1 = j1.getNbCartes() - 1;
          int indice_carte_joueur2 = j2.getNbCartes() - 1;
          int bataille;

          while (j1.getNbCartes() > 0 && j2.getNbCartes() > 0){

              System.out.println("Scores => Joueur 1 : " + j1.getPoints() + " | Joueur 2 : " + j2.getPoints());

              Carte carte_j1 = j1.tireCarte(indice_carte_joueur1);
              Carte carte_j2 = j2.tireCarte(indice_carte_joueur2);

              // Comparaison des cartes

              bataille = carte_j1.compare(carte_j2);

              if (bataille == -1){
                  j2.ajouteCarte(carte_j1, j1);
              } else if(bataille == 1){
                  j1.ajouteCarte(carte_j2, j2);
              } else {

                    // Stockage des cartes jouées

                    ArrayList<Carte> cartes_joues_depuis_egalite_j1 = new ArrayList<Carte>();
                    ArrayList<Carte> cartes_joues_depuis_egalite_j2 = new ArrayList<Carte>();

                    cartes_joues_depuis_egalite_j1.add(carte_j1);
                    cartes_joues_depuis_egalite_j2.add(carte_j2);

                    // Tant qu'il y a égalité, les joueurs tirent leurs cartes

                    while (bataille == 0){

                        if (indice_carte_joueur1 == 0)  indice_carte_joueur1 = j1.getNbCartes() - 1;
                        else indice_carte_joueur1--;

                        if (indice_carte_joueur2 == 0) indice_carte_joueur2 = j2.getNbCartes() - 1;
                        else indice_carte_joueur2--;

                        Carte relance_j1 = j1.tireCarte(indice_carte_joueur1);
                        Carte relance_j2 = j2.tireCarte(indice_carte_joueur2);

                        // Stockage des cartes jouées

                        cartes_joues_depuis_egalite_j1.add(relance_j1);
                        cartes_joues_depuis_egalite_j2.add(relance_j2);

                        bataille = relance_j1.compare(relance_j2);

                    }

                    // Ajout des cartes gagnées dans le paquet du gagnant

                    if (bataille == -1){

                        for (int c=0; c < cartes_joues_depuis_egalite_j1.size(); c++ ){
                            j2.ajouteCarte(cartes_joues_depuis_egalite_j1.get(c), j1);
                        }

                    } else if (bataille == 1){

                        for (int c=0; c<cartes_joues_depuis_egalite_j2.size(); c++){
                            j1.ajouteCarte(cartes_joues_depuis_egalite_j2.get(c), j2);
                        }
                    }
              }

              /*
                  Ici, on met à jour les indices des cartes que chacun des joueurs doit tirer.

                  On vérifie également que l'indice des cartes que les joueurs
                  vont tirer est inférieur aux nombre de cartes qu'ils possèdent puisque
                  le nombre de cartes que chacun possède peut varier grandement si il y a eu égalité.

              */

              if (indice_carte_joueur1 == 0 || indice_carte_joueur1 >= j1.getNbCartes()) indice_carte_joueur1 = j1.getNbCartes() - 1;
              else indice_carte_joueur1--;

              if (indice_carte_joueur2 == 0 || indice_carte_joueur2 >= j2.getNbCartes()) indice_carte_joueur2 = j2.getNbCartes() - 1;
              else indice_carte_joueur2--;

          }

          // Affiche le gagnant

          if (j1.getPoints() < j2.getPoints()){
              System.out.println("Le joueur 2 a gagné");
          } else {
              System.out.println("Le joueur 1 a gagné");
          }
    }

}
