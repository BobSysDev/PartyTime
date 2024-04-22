public interface DepositLock {
    void put(String valuable);
    String take();
    int size();
}
