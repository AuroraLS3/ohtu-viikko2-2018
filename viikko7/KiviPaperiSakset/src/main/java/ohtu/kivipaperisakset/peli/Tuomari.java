package ohtu.kivipaperisakset.peli;

/**
 * Tuomari pitää kirjaa ensimmäisen ja toisen pelaajan pisteistä sekä tasapelien määrästä.
  */
public class Tuomari {

    private int ekanPisteet;
    private int tokanPisteet;
    private int tasapelit;

    public Tuomari() {
        this.ekanPisteet = 0;
        this.tokanPisteet = 0;
        this.tasapelit = 0;
    }

    public void kirjaaSiirto(String ekanSiirto, String tokanSiirto) {
        if (onkoTasapeli(ekanSiirto, tokanSiirto)) {
            tasapelit++;
        } else if (voittaakoEka(ekanSiirto, tokanSiirto)) {
            ekanPisteet++;
        } else {
            tokanPisteet++;
        }
    }

    private boolean onkoTasapeli(String eka, String toka) {
        return eka.equals(toka);
    }

    private boolean voittaakoEka(String eka, String toka) {
        if (onKivi(eka)) {
            return onSakset(toka);
        } else if (onSakset(eka)) {
            return onPaperi(toka);
        } else {
            // Nyt ekalla on paperi.
            return onKivi(toka);
        }
    }

    private boolean onKivi(String siirto) {
        return "k".equals(siirto);
    }

    private boolean onSakset(String siirto) {
        return "s".equals(siirto);
    }

    private boolean onPaperi(String siirto) {
        return "p".equals(siirto);
    }

    public boolean onOKSiirto(String siirto) {
        return "k".equals(siirto) || "p".equals(siirto) || "s".equals(siirto);
    }

    public String toString() {
        return "Pelitilanne: " + ekanPisteet + " - " + tokanPisteet + "\n"
                + "Tasapelit: " + tasapelit;
    }
}