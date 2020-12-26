package algo;

public class Pair implements Comparable<Pair> {
    char character;
    int value;

    public Pair(char character, int value) {
        this.character = character;
        this.value = value;
    }

    @Override
    public int compareTo(Pair o) {
        int cmp = -Integer.compare(this.value, o.value);
        if (cmp == 0) {
            return -Integer.compare((int)this.character, (int)this.character);
        } else {
            return cmp;
        }
    }

    @Override
    public String toString() {
        return "Pair{" +
                "character=" + character +
                ", value=" + value +
                '}';
    }
}
