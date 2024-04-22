import javax.swing.*;
import java.lang.invoke.VarHandle;
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
    public synchronized void addTreasure(ArrayList<Valuable> valuablez) {
        valuables.addAll(valuablez);
    }

    @Override
    public synchronized ArrayList<Valuable> getTreasure() {
        return valuables;
    }

    public synchronized ArrayList<Valuable> removeTreasure(){
        ArrayList<Valuable> removed = new ArrayList<>(valuables);
        valuables.clear();
        return removed;
    }
}