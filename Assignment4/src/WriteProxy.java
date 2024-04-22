import java.util.ArrayList;

public class WriteProxy implements WriteTreasure {
    private TreasureRoom treasureRoom;
    private Guardsman guardsman;


    public WriteProxy(TreasureRoom treasureRoom, Guardsman guardsman) {
        this.treasureRoom = treasureRoom;
        this.guardsman = guardsman;
    }

    @Override
    public void addTreasure(String valuable) {
        if (guardsman.hasAccess(Thread.currentThread())){
            treasureRoom.addTreasure(valuable);
        } else {
            throw new IllegalStateException("Access is denied after release has been called");
        }
    }

    @Override
    public void addTreasure(ArrayList<String> valuablez) {
        if (guardsman.hasAccess(Thread.currentThread())){
            treasureRoom.addTreasure(valuablez);
        } else {
            throw new IllegalStateException("Access is denied after release has been called");
        }
    }

    @Override
    public ArrayList<String> getTreasure() {
        if (guardsman.hasAccess(Thread.currentThread())) {
            return this.treasureRoom.getTreasure();
        }
        throw new IllegalStateException("Access is denied after release has been called");
    }

    @Override public ArrayList<String> removeTreasure(){
        if (guardsman.hasAccess(Thread.currentThread())) {
            return this.treasureRoom.removeTreasure();
        }
        throw new IllegalStateException("Access is denied after release has been called");
    }
}