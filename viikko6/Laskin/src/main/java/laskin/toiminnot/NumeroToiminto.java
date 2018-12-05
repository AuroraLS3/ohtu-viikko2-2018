package laskin.toiminnot;

import javafx.scene.control.TextField;

import java.util.function.Consumer;

/**
 * TODO Class Javadoc comment
 *
 * @author Rsl1122
 */
public class NumeroToiminto implements Toiminto {

    private final TextField syotekentta;
    private final Consumer<Integer> integerConsumer;

    public NumeroToiminto(TextField syotekentta, Consumer<Integer> integerConsumer) {
        this.syotekentta = syotekentta;
        this.integerConsumer = integerConsumer;
    }

    @Override
    public void suorita() {
        integerConsumer.accept(Integer.parseInt(syotekentta.getText().trim()));
    }
}
