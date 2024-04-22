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
      //log.log(Thread.currentThread().getName() + " is mining...");
      try
      {
        Thread.sleep(5000);
      }
      catch (InterruptedException e)
      {
        throw new RuntimeException(e);
      }
      String valuable = mine.mine();
      log.log(Thread.currentThread().getName() + " mined " + valuable + "!");
      //log.log(Thread.currentThread().getName() + " is depositing " + valuable + "...");
      try
      {
        Thread.sleep(1000);
      }
      catch (InterruptedException e)
      {
        throw new RuntimeException(e);
      }
      lock.put(valuable);
      log.log(
          Thread.currentThread().getName() + " deposited " + valuable + ".");
    }
  }
}
