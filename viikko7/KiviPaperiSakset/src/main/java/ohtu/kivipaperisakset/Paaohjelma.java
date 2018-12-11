package ohtu.kivipaperisakset;

import ohtu.kivipaperisakset.io.IO;
import ohtu.kivipaperisakset.io.SystemIO;
import ohtu.kivipaperisakset.peli.KPSFactory;
import ohtu.kivipaperisakset.peli.KiviPaperiSakset;

import java.util.Optional;

public class Paaohjelma {

    public static void main(String[] args) {
        while (true) {
            IO io = new SystemIO();

            io.println("\nValitse pelataanko"
                    + "\n (a) ihmistä vastaan "
                    + "\n (b) tekoälyä vastaan"
                    + "\n (c) parannettua tekoälyä vastaan"
                    + "\nmuilla valinnoilla lopetataan");

            String pelimuoto = io.input();

            Optional<KiviPaperiSakset> rakennettuPeli = KPSFactory.create()
                    .io(io)
                    .pelimuoto(pelimuoto)
                    .build();

            rakennettuPeli.ifPresent(peli -> {
                System.out.println("peli loppuu kun pelaaja antaa virheellisen siirron eli jonkun muun kuin k, p tai s");
                peli.pelaa();
            });

            if (!rakennettuPeli.isPresent()) {
                break;
            }
        }
    }
}
