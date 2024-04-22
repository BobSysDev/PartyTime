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
    while(true){
      try{
        Thread.sleep(4000);
      }catch(InterruptedException e){
        e.printStackTrace();
      }

      door.acquireReadAccess();

      //Counting the valuables
      ArrayList<String> valuables = lock.getTreasure();
      int totalValue = 0;
      Map<String, Integer> counts = new HashMap<>();

      for(String valuable : valuables){
        totalValue += PRICES.get(valuable.toString());
        if(!counts.containsKey(valuable.toString())){
          counts.put(valuable.toString(), 1);
        }
        else{
          counts.replace(valuable.toString(), counts.get(valuable.toString()) + 1);
        }
      }

      StringBuilder toReturn = new StringBuilder(
          "Accountant has finished counting the valuables in the vault. The result: ");
      counts.forEach((name, value) -> {
        toReturn.append("\n").append(name).append(" : ").append(value);});
      toReturn.append("\nTotal value of all valuables: ").append(totalValue);
      logger.log(toReturn.toString());

      try{
        Thread.sleep(1000);
      }catch(InterruptedException e){
        e.printStackTrace();
      }

      door.releaseReadAccess();
    }
  }
}
