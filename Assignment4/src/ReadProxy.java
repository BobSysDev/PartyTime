import java.util.ArrayList;

public class ReadProxy implements ReadTreasure{

    private TreasureRoom treasureRoom;
    private Guardsman guardsman;

    public ReadProxy(TreasureRoom treasureRoom, Guardsman guardsman) {
        this.treasureRoom = treasureRoom;
        this.guardsman = guardsman;
    }

    @Override public ArrayList<String> getTreasure()
    {
        if (guardsman.hasAccess(Thread.currentThread())){
            return treasureRoom.getTreasure();
        }
        else {
        throw new IllegalStateException("Access is denied after release has been called");
        }
    }
}
