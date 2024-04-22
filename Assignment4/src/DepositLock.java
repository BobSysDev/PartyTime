public interface DepositLock {
    void put();
    void take();
    int size();
}
