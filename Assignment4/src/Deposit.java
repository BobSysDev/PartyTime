import utility.collection.ArrayList;

public class Deposit implements DepositLock {

    private ArrayList<String> valuables;

    public Deposit() {
        this.valuables = new ArrayList<String>();
    }

    @Override
    public void put(String valuable) {
        valuables.add(valuable);
    }

    @Override
    public String take() {

        if (!valuables.isEmpty()) {
            return valuables.remove(0);
        } else {return null;}

    }

    @Override
    public int size() {
        return valuables.size();
    }
}