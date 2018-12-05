package laskin;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventTarget;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import laskin.toiminnot.ComboToiminto;
import laskin.toiminnot.NumeroToiminto;
import laskin.toiminnot.Toiminto;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Tapahtumankuuntelija implements EventHandler {
    private TextField tuloskentta;
    private TextField syotekentta;

    private Button plus;
    private Button miinus;
    private Button nollaa;
    private Button undo;

    private Sovelluslogiikka sovellus;

    private Map<Button, Toiminto> komennot;
    private Stack<Integer> history;

    public Tapahtumankuuntelija(
            TextField tuloskentta, TextField syotekentta,
            Button plus, Button miinus, Button nollaa, Button undo
    ) {
        this.tuloskentta = tuloskentta;
        this.syotekentta = syotekentta;
        this.plus = plus;
        this.miinus = miinus;
        this.nollaa = nollaa;
        this.undo = undo;
        this.sovellus = new Sovelluslogiikka();

        history = new Stack<>();

        komennot = new HashMap<>();

        Toiminto historyStore = () -> history.push(sovellus.tulos());
        Toiminto historyReturn = () -> sovellus.setTulos(history.pop());

        komennot.put(plus, new ComboToiminto(new NumeroToiminto(syotekentta, sovellus::plus), historyStore));
        komennot.put(miinus, new ComboToiminto(new NumeroToiminto(syotekentta, sovellus::miinus), historyStore));
        komennot.put(nollaa, new ComboToiminto(sovellus::nollaa, history::clear));
        komennot.put(undo, historyReturn);
    }

    @Override
    public void handle(Event event) {
        EventTarget target = event.getTarget();
        if (!(target instanceof Button)) {
            return;
        }
        try {
            Toiminto komento = komennot.get(target);
            komento.suorita();
            asetaTulos();
        } catch (Exception e) {
        }
    }

    private void asetaTulos() {
        int laskunTulos = sovellus.tulos();

        syotekentta.setText("");
        tuloskentta.setText("" + laskunTulos);

        nollaa.disableProperty().set(laskunTulos == 0);
        undo.disableProperty().set(history.empty());
    }

}
