package laskin.toiminnot;

public class ComboToiminto implements Toiminto {

    private Toiminto[] toiminnot;

    public ComboToiminto(Toiminto... toiminnot) {
        this.toiminnot = toiminnot;
    }

    @Override
    public void suorita() {
        for (Toiminto toiminto : toiminnot) {
            toiminto.suorita();
        }

    }
}
