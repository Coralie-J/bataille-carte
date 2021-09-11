import java.util.ArrayList;

public class Joueur {

    private ArrayList<Carte> cartes;
    private int points;

    public Joueur(ArrayList<Carte> cartes){
        this.points = 0;
        this.cartes = new ArrayList<Carte>(cartes);
    }

    public int getPoints(){
        return this.points;
    }

    public Carte tireCarte(int i){
        return this.cartes.get(i);
    }

    public void ajouteCarte(Carte c, Joueur j){
        this.cartes.add(c);
        this.points += c.getValeur();
        j.cartes.remove(c);
    }

    public int getNbCartes(){
        return this.cartes.size();
    }

}
