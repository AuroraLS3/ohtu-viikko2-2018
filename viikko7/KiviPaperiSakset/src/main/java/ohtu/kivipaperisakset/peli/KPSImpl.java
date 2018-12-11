package ohtu.kivipaperisakset.peli;

import ohtu.kivipaperisakset.io.IO;
import ohtu.kivipaperisakset.peli.pelaaja.Pelaaja;

public class KPSImpl implements KiviPaperiSakset {

    private final Tuomari tuomari;
    private final IO io;
    private final Pelaaja pelaaja1;
    private final Pelaaja pelaaja2;

    KPSImpl(Tuomari tuomari, IO io, Pelaaja pelaaja1, Pelaaja pelaaja2) {
        this.tuomari = tuomari;
        this.io = io;
        this.pelaaja1 = pelaaja1;
        this.pelaaja2 = pelaaja2;
    }

    public void pelaa() {
        while (true) {
            String ekanSiirto = valitseSiirto(pelaaja1);
            String tokanSiirto = valitseSiirto(pelaaja2);

            boolean okSiirto = tuomari.onOKSiirto(ekanSiirto) && tuomari.onOKSiirto(tokanSiirto);
            if (!okSiirto) {
                break;
            }

            pelaaSiirto(ekanSiirto, tokanSiirto);
        }

        io.println("Kiitos!");
        io.println(tuomari);
    }

    private String valitseSiirto(Pelaaja pelaaja1) {
        String ekanSiirto = pelaaja1.annaSiirto();
        io.println(pelaaja1.getName() + " valitsi: " + ekanSiirto);
        return ekanSiirto;
    }

    private void pelaaSiirto(String ekanSiirto, String tokanSiirto) {
        pelaaja2.asetaSiirto(ekanSiirto);
        pelaaja1.asetaSiirto(tokanSiirto);

        tuomari.kirjaaSiirto(ekanSiirto, tokanSiirto);
        io.println(tuomari);
        io.println();
    }
}
