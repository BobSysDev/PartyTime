public class Guardsman implements Door{
    private TreasureRoom treasureRoom;

    public Guardsman(TreasureRoom treasureRoom) {
        this.treasureRoom = treasureRoom;
    }

    @Override
    public void acquireReadAccess() {

    }

    @Override
    public void releaseReadAccess() {

    }

    @Override
    public void acquireWriteAccess() {

    }

    @Override
    public void releaseWriteAccess() {

    }
}
