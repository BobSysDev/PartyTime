import java.util.ArrayList;

public class TreasureRoom implements WriteTreasure, ReadTreasure{

    private ArrayList<String> valuables;

    public TreasureRoom() {
        this.valuables = new ArrayList<>();
    }

    @Override
    public synchronized void addTreasure(String valuable) {
        valuables.add(valuable);
    }

    @Override
    public synchronized void addTreasure(ArrayList<String> valuablez) {
        valuables.addAll(valuablez);
    }

    @Override
    public synchronized ArrayList<String> getTreasure() {
        return valuables;
    }
    
    public synchronized ArrayList<String> removeTreasure(){
        ArrayList<String> removed = new ArrayList<>(valuables);
        valuables.clear();
        return removed;
    }
}