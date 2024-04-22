public class WriteProxy implements WriteTreasure {
    private final TreasureRoom treasureRoom;
    private boolean isReleased;

    public WriteProxy(TreasureRoom treasureRoom) {
        this.treasureRoom = treasureRoom;
        this.isReleased = false;
    }

    @Override
    public void addTreasure(Valuable valuable) {
        if (!isReleased) {
            this.treasureRoom.addTreasure(valuable);
        } else {
            throw new IllegalStateException("Access is denied after release has been called");
        }
    }

    public Valuable getTreasure() {
        if (!isReleased) {
            return this.treasureRoom.getTreasure();
        }
        throw new IllegalStateException("Access is denied after release has been called");
    }

    public void release() {
        this.isReleased = true;
    }
}