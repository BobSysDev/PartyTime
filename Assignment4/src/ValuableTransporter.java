import java.util.ArrayList;

public class ValuableTransporter implements Runnable
{
  private DepositLock lock;
  private Door door;
  private ArrayList<String> valuables;
  private Logger log;

  public ValuableTransporter(DepositLock lock, Door door)
  {
    this.lock = lock;
    this.door = door;
    this.valuables = new ArrayList<>();
    this.log = Logger.getInstance();
  }

  @Override public void run()
  {
    while(true){
      try{
        valuables.addAll(lock.take());
        log.log(Thread.currentThread().getName()+" took stuff from deposit.");
      }
      catch (Exception e)
      {
        throw new RuntimeException(e);
      }

      try
      {
        Thread.sleep(1500);
      }
      catch (InterruptedException e)
      {
        throw new RuntimeException(e);
      }

      try{
        WriteTreasure writeTreasure = door.acquireWriteAccess();
        writeTreasure.addTreasure(valuables);
        door.releaseWriteAccess();
        log.log(Thread.currentThread().getName()+" put stuff into treasure.");
      }
      catch (Exception e)
      {
        throw new RuntimeException(e);
      }

    }

  }



}
