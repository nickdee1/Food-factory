package foodchain.parties;

import foodchain.parties.AbstractParty;

import java.util.Iterator;

public class PartiesIterator implements Iterator {

    private AbstractParty current;

    public PartiesIterator(AbstractParty current) {
        this.current = current;
    }

    public boolean hasNext() {
        return current.getNextParty() != null;
    }

    public AbstractParty next() {
        if (hasNext()) {
            current = current.getNextParty();
            return current;
        }
        return null;
    }

    public void remove() {

    }
}
