public class Carte {

    public static String[] COULEURS = {"coeur", "carreau", "pic", "trèfle"};
    public static int[] VALEURS = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};

    private int valeur;
    private String couleur;

    public Carte(int val, String couleur){
        this.couleur = couleur;
        this.valeur = val;
    }

    public int getValeur(){
      return this.valeur;
    }

    public String getCouleur(){
      return this.couleur;
    }

    public int compare(Carte c){
      if (c.valeur == this.valeur) return 0;
      else if (c.valeur > this.valeur) return -1;
      else return 1;
    }

}
