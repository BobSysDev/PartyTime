public interface Door {

    ReadTreasure acquireReadAccess();
    void releaseReadAccess();
    WriteTreasure acquireWriteAccess();
    void releaseWriteAccess();

}