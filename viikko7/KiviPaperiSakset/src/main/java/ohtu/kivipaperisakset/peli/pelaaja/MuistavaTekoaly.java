
package ohtu.kivipaperisakset.peli.pelaaja;

public class MuistavaTekoaly extends Tekoaly {
    private String[] muisti;
    private int vapaaMuistiIndeksi;

    public MuistavaTekoaly(int muistinKoko) {
        muisti = new String[muistinKoko];
        vapaaMuistiIndeksi = 0;
    }

    public void asetaSiirto(String siirto) {
        // jos muisti täyttyy, unohdetaan viimeinen alkio
        if (vapaaMuistiIndeksi == muisti.length) {
            for (int i = 1; i < muisti.length; i++) {
                muisti[i - 1] = muisti[i];
            }

            vapaaMuistiIndeksi--;
        }

        muisti[vapaaMuistiIndeksi] = siirto;
        vapaaMuistiIndeksi++;
    }


    public String annaSiirto() {
        if (vapaaMuistiIndeksi == 0 || vapaaMuistiIndeksi == 1) {
            return "k";
        }

        String viimeisinSiirto = muisti[vapaaMuistiIndeksi - 1];

        return haeMuistinMukaan(viimeisinSiirto);
    }

    private String haeMuistinMukaan(String viimeisinSiirto) {
        int kivia = 0;
        int papereita = 0;
        int saksia = 0;

        for (int i = 0; i < vapaaMuistiIndeksi - 1; i++) {
            if (viimeisinSiirto.equals(muisti[i])) {
                String seuraava = muisti[i + 1];

                if ("k".equals(seuraava)) {
                    kivia++;
                } else if ("p".equals(seuraava)) {
                    papereita++;
                } else {
                    saksia++;
                }
            }
        }
        return valitseSiirto(kivia, papereita, saksia);


    }

    private String valitseSiirto(int kivia, int papereita, int saksia) {
        // Tehdään siirron valinta esimerkiksi seuraavasti;
        // - jos kiviä eniten, annetaan aina paperi
        // - jos papereita eniten, annetaan aina sakset
        // muulloin annetaan aina kivi
        boolean enitenKivia = kivia > papereita && kivia > saksia;
        boolean enitenPapereita = papereita > kivia && papereita > saksia;

        if (enitenKivia) {
            return "p";
        } else if (enitenPapereita) {
            return "s";
        } else {
            return "k";
        }
    }
}