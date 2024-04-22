public interface Door {

    ReadTreasure acquireRead();
    WriteTreasure acquireWrite();
    void releaseRead(ReadTreasure readTreasure);
    void releaseWrite(WriteTreasure writeTreasure);

    void putTreasure(Treasure treasure);
    Treasure getTreasure();
    void toAccount();

}
