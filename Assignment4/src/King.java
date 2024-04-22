import java.util.HashMap;
import java.util.Map;

public class King implements Runnable
{
  private ReadTreasure lock;
  private Door door;
  private Logger logger;
  private Map<String, Integer> prices;

  public King(Door door){
    this.door = door;
    lock = null;
    logger = Logger.getInstance();
    prices = Accountant.PRICES;
  }

  @Override public void run()
  {
    while(true)
  }
}
