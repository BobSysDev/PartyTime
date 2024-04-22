import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class TreasureRoom implements WriteTreasure, ReadTreasure{

    private ArrayList<Valuable> valuables;

    public TreasureRoom() {
        this.valuables = new ArrayList<>();


    }

    @Override
    public synchronized void addTreasure(Valuable valuable) {
        valuables.add(valuable);
    }

    @Override
    public synchronized Valuable getTreasure() {
        if (valuables.size() > 0) {
            return valuables.remove(0);
        }
        return null;
    }
}