
package ohtu.intjoukkosovellus;

import java.util.Optional;
import java.util.function.Consumer;

public class IntJoukko {

    private final static int DEFAULT_INITIAL_CAPACITY = 5, DEFAULT_INCREMENT = 5;

    private int increment;
    private int[] storedNumbers;
    private int currentHead;

    public IntJoukko() {
        this(DEFAULT_INITIAL_CAPACITY, DEFAULT_INCREMENT);
    }

    public IntJoukko(int initialCapacity) {
        this(initialCapacity, DEFAULT_INCREMENT);
    }

    private int[] initializeArray(int size) {
        int[] intArray = new int[size];
        for (int i = 0; i < intArray.length; i++) {
            intArray[i] = 0;
        }
        return intArray;
    }


    public IntJoukko(int initialCapacity, int increment) {
        if (initialCapacity < 0) {
            throw new IndexOutOfBoundsException("Initial Capacity must be over 0.");//heitin vaan jotain :D
        }
        if (increment < 0) {
            throw new IndexOutOfBoundsException("Increment must be over 0.");//heitin vaan jotain :D
        }
        storedNumbers = initializeArray(initialCapacity);
        currentHead = 0;
        this.increment = increment;

    }

    public void add(int luku) {
        if (!contains(luku)) {
            storedNumbers[currentHead] = luku;
            currentHead++;
            if (currentHead == storedNumbers.length) {
                increaseSize();
            }
        }
    }

    private void increaseSize() {
        int[] previouslyStored = storedNumbers;
        copyArray(storedNumbers, previouslyStored);
        storedNumbers = new int[currentHead + increment];
        copyArray(previouslyStored, storedNumbers);
    }

    public boolean contains(int number) {
        return indexOf(number).isPresent();
    }

    private Optional<Integer> indexOf(int number) {
        for (int i = 0; i < currentHead; i++) {
            if (number == storedNumbers[i]) {
                return Optional.of(i);
            }
        }
        return Optional.empty();
    }

    public void remove(int luku) {
        Optional<Integer> foundAt = indexOf(luku);

        if (foundAt.isPresent()) {
            Integer atIndex = foundAt.get();
            storedNumbers[atIndex] = 0;
            moveAllLeftStartingFrom(atIndex);
            currentHead--;
        }
    }

    private void moveAllLeftStartingFrom(Integer index) {
        int helpVar;
        for (int i = index; i < currentHead - 1; i++) {
            helpVar = storedNumbers[i];
            storedNumbers[i] = storedNumbers[i + 1];
            storedNumbers[i + 1] = helpVar;
        }
    }

    public void addAll(IntJoukko from) {
        actUpon(from, this::add);
    }

    public void removeAll(IntJoukko toRemove) {
        actUpon(toRemove, this::remove);
    }

    public void actUpon(IntJoukko group, Consumer<Integer> action) {
        for (int number : group.toIntArray()) {
            action.accept(number);
        }
    }

    public int getCurrentHead() {
        return currentHead;
    }


    @Override
    public String toString() {
        if (currentHead == 0) {
            return "{}";
        } else if (currentHead == 1) {
            return "{" + storedNumbers[0] + "}";
        } else {
            return buildToString();
        }
    }

    private String buildToString() {
        StringBuilder builder = new StringBuilder("{");
        for (int i = 0; i < currentHead - 1; i++) {
            builder.append(storedNumbers[i]);
            builder.append(", ");
        }
        builder.append(storedNumbers[currentHead - 1]);
        builder.append("}");
        return builder.toString();
    }

    public int[] toIntArray() {
        int[] taulu = new int[currentHead];
        copyArray(storedNumbers, taulu);
        return taulu;
    }

    private static void copyArray(int[] from, int[] to) {
        System.arraycopy(from, 0, to, 0, Math.min(to.length, from.length));
    }

    private static IntJoukko copyOf(IntJoukko of) {
        IntJoukko copy = new IntJoukko(of.storedNumbers.length, of.increment);
        copy.currentHead = of.currentHead;
        copyArray(of.storedNumbers, copy.storedNumbers);
        return copy;
    }

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko result = copyOf(a);
        result.addAll(b);
        return result;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko erotusAB = erotus(a, b);
        IntJoukko erotusBA = erotus(b, a);
        IntJoukko negative = yhdiste(erotusAB, erotusBA);
        return erotus(yhdiste(a, b), negative);
    }

    public static IntJoukko erotus(IntJoukko a, IntJoukko b) {
        IntJoukko result = copyOf(a);
        result.removeAll(b);
        return result;
    }

}