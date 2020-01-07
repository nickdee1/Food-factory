package foodchain.parties;
import java.util.Iterator;

/**
 * Iterator for parties in chai
 */
public class PartiesIterator implements Iterator {

    /**
     * Current party is iterated
     */
    private AbstractParty current;

    /**
     * Constructs party iterator
     * @param current the current party to be iterated from
     */
    public PartiesIterator(AbstractParty current) {
        this.current = current;
    }

    /**
     * Checks if next party exists
     * @return boolean value if next party exists
     */
    public boolean hasNext() {
        return current.getNextParty() != null;
    }

    /**
     * Get next party
     * @return next party or null if does not exist
     */
    public AbstractParty next() {
        if (hasNext()) {
            current = current.getNextParty();
            return current;
        }
        return null;
    }

    public void remove() { }
}
