public class ValuablesMiner implements Runnable
{
  private DepositLock lock;
  private Mine mine;
  private Logger log;
  public ValuablesMiner(DepositLock lock, Mine mine){
    this.lock = lock;
    this.mine = mine;
    this.log = Logger.getInstance();
  }
  @Override public void run()
  {
    while(true)
    {
      try
      {
        Thread.sleep(5000);
      }
      catch (InterruptedException e)
      {
        throw new RuntimeException(e);
      }
      String valuable = mine.mine();
      log.updateMiner(Integer.parseInt(Thread.currentThread().getName().split(" ")[1])," mined " + valuable + "!");
      try
      {
        Thread.sleep(1000);
      }
      catch (InterruptedException e)
      {
        throw new RuntimeException(e);
      }
      lock.put(valuable);
      log.updateMiner(Integer.parseInt(Thread.currentThread().getName().split(" ")[1]),
          " deposited " + valuable + ".");
    }
  }
}
