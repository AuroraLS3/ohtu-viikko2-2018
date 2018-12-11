package ohtu.kivipaperisakset.peli;

import ohtu.kivipaperisakset.io.IO;
import ohtu.kivipaperisakset.peli.pelaaja.Ihminen;
import ohtu.kivipaperisakset.peli.pelaaja.Tekoaly;
import ohtu.kivipaperisakset.peli.pelaaja.MuistavaTekoaly;

import java.util.Optional;

public class KPSFactory {

    private IO io;

    private KiviPaperiSakset peli;

    private KPSFactory() {
    }

    public static KPSFactory create() {
        return new KPSFactory();
    }

    public KPSFactory io(IO io) {
        this.io = io;
        return this;
    }

    public KPSFactory pelimuoto(String peliMuoto) {
        if (io == null) {
            throw new IllegalStateException("IO:ta ei ole valittu");
        }


        Tuomari tuomari = new Tuomari();
        Ihminen pelaaja1 = new Ihminen("Pelaaja 1", io);

        char gameMode = peliMuoto.charAt(peliMuoto.length() - 1);
        if (gameMode == 'a') {
            peli = new KPSImpl(tuomari, io, pelaaja1, new Ihminen("Pelaaja 2", io));
        } else if (gameMode == 'b') {
            peli = new KPSImpl(tuomari, io, pelaaja1, new Tekoaly());
        } else if (gameMode == 'c') {
            peli = new KPSImpl(tuomari, io, pelaaja1, new MuistavaTekoaly(20));
        }
        return this;
    }

    public Optional<KiviPaperiSakset> build() {
        return Optional.ofNullable(peli);
    }
}
