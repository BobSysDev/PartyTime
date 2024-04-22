public class Guardsman implements Door{
    private TreasureRoom treasureRoom;
    private ReadTreasure readProxy;

    public Guardsman(TreasureRoom treasureRoom) {
        this.treasureRoom = treasureRoom;
    }


    @Override
    public ReadTreasure acquireReadAccess() {
        return this.readProxy;
    }

    @Override
    public void releaseReadAccess() {
        this.readProxy.release();
    }

    @Override
    public WriteTreasure acquireWriteAccess() {
        return this.treasureRoom;
    }

    @Override
    public void releaseWriteAccess() {

    }
}
