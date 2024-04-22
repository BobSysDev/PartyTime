import utility.collection.ArrayList;

public class Deposit implements DepositLock {

    private ArrayList<String> valuables;

    public Deposit() {
        this.valuables = new ArrayList<String>();
    }

    @Override
    public synchronized void put(String valuable) {
        valuables.add(valuable);
        notifyAll();
    }

    @Override
    public synchronized java.util.ArrayList<String> take() {
        while(size()<3){
            try{
                wait();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        java.util.ArrayList<String> valuablesToTake = new java.util.ArrayList<>();
        for (int i = 0; i < 3; i++)
        {
            valuablesToTake.add(valuables.remove(0));
        }
        return valuablesToTake;
    }

    @Override
    public synchronized int size() {
        return valuables.size();
    }
}