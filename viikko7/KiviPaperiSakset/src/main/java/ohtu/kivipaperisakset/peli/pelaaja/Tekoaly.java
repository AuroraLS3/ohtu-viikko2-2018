package ohtu.kivipaperisakset.peli.pelaaja;

public class Tekoaly implements Pelaaja {

    private int siirronNumero;

    public Tekoaly() {
        siirronNumero = 0;
    }

    public String annaSiirto() {
        siirronNumero++;
        siirronNumero = siirronNumero % 3;

        return valitseSiirto();
    }

    private String valitseSiirto() {
        if (siirronNumero == 0) {
            return "k";
        } else if (siirronNumero == 1) {
            return "p";
        } else {
            return "s";
        }
    }

    public void asetaSiirto(String ekanSiirto) {
        // ei tehdä mitään 
    }

    @Override
    public String getName() {
        return "Tietokone";
    }
}
