package ohtu.kivipaperisakset.peli.pelaaja;

import ohtu.kivipaperisakset.io.IO;

public class Ihminen implements Pelaaja {

    private final String name;
    private final IO io;

    public Ihminen(String name, IO io) {
        this.name = name;
        this.io = io;
    }

    @Override
    public String annaSiirto() {
        io.print(name + ":n siirto: ");
        return io.input();
    }

    @Override
    public void asetaSiirto(String siirto) {
        // Ei tarvi muistaa
    }

    @Override
    public String getName() {
        return name;
    }
}
