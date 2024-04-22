import java.util.ArrayList;

public class ReadProxy implements ReadTreasure, WriteTreasure{

    private final TreasureRoom treasureRoom;
    private boolean isReleased;

    public ReadProxy(TreasureRoom treasureRoom) {
        this.treasureRoom = treasureRoom;
        this.isReleased = false;
    }

    @Override public void addTreasure(String valuable)
    {
        if (!isReleased) {
            this.treasureRoom.addTreasure(valuable);
        } else {
            throw new IllegalStateException("Access is denied");
        }
    }

    @Override public void addTreasure(ArrayList<String> valuablez)
    {
        if (!isReleased) {
            this.treasureRoom.addTreasure(valuablez);
        } else {
            throw new IllegalStateException("Access is denied");
        }
    }

    @Override
    public ArrayList<String> getTreasure() {
        if (!isReleased){
            return this.treasureRoom.getTreasure();
        }
        throw new IllegalStateException("Access is denied");
    }

    @Override
    public void release() {
        this.isReleased = true;
    }
}
