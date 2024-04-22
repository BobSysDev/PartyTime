import java.lang.invoke.StringConcatFactory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

public class Accountant implements Runnable
{
  private ReadTreasure lock;
  private Door door;
  private Logger logger;
  public static final Map<String, Integer> PRICES = new HashMap<String, Integer>() {{
    put("Diamond", 10);
    put("Emerald", 9);
    put("GoldNugget", 8);
    put("Sapphire", 7);
    put("Ruby", 6);
    put("LapisLazuli", 5);
    put("Redstone", 4);
    put("Quartz", 3);
    put("Iron", 2);
    put("Coal", 1);
  }};

  public Accountant(Door door){
    this.door = door;
    lock = null;
    logger = Logger.getInstance();
  }

  @Override public void run(){
    Map<String, Integer> counts = new HashMap<>();
    int totalValue = 0;

    while(true){
      try{
        Thread.sleep(1000);
      }catch(InterruptedException e){
        e.printStackTrace();
      }

      lock = door.acquireReadAccess();

      //Counting the valuables
      ArrayList<String> valuables = lock.getTreasure();
      totalValue = 0;
      counts.clear();

      for(String valuable : valuables){
        totalValue += PRICES.get(valuable);
        if(!counts.containsKey(valuable)){
          counts.put(valuable, 1);
        }
        else{
          counts.replace(valuable, counts.get(valuable) + 1);
        }
      }

      StringBuilder toReturn = new StringBuilder();
      counts.forEach((name, value) -> {
        toReturn.append(name).append(" : ").append(value).append("\n");});
      toReturn.append("Total value of all valuables: ").append(totalValue);
      logger.updateAccountantData(toReturn.toString());

      try{
        Thread.sleep(500);
      }catch(InterruptedException e){
        e.printStackTrace();
      }

      lock = null;
      door.releaseReadAccess();
    }
  }
}
