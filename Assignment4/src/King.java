import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class King implements Runnable
{
  private WriteTreasure lock;
  private Door door;
  private Logger logger;

  private static final int PARTY_PRICE = 69;

  public King(Door door){
    this.door = door;
    lock = null;
    logger = Logger.getInstance();
  }

  @Override public void run()
  {
    while(true){
      try{
        Thread.sleep(2000);
      }catch(InterruptedException e){
        e.printStackTrace();
      }

      lock = door.acquireWriteAccess();
      ArrayList<String> valuables = new ArrayList<>(lock.removeTreasure());

      int totalValue = 0;

      for(String valuable : valuables)
      {
        totalValue += Accountant.PRICES.get(valuable);
      }

      if(totalValue <= PARTY_PRICE){
        lock.addTreasure(valuables);
        System.out.println("Not enough moneyz... (｡•́︿•̀｡)");
      }
      else{
        int valueGathered = 0;
        int initialValuablesSize = valuables.size();
        for(int i = 0; i<initialValuablesSize; i++){
          valueGathered += Accountant.PRICES.get(valuables.remove(0));
          if(valueGathered >= PARTY_PRICE){
            break;
          }
        }
        logger.log("PARTY TIME!!! ( ﾉ･o･ )ﾉ");
      }

      try{
        Thread.sleep(10000);
      }catch(InterruptedException e){
        e.printStackTrace();
      }

      door.releaseWriteAccess();
      lock = null;
    }
  }
}
