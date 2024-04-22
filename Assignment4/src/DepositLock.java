import java.util.ArrayList;

public interface DepositLock {
    void put(String valuable);
    ArrayList<String> take();
    int size();
}
