public class ValuablesMiner implements Runnable
{
  private DepositLock lock;
  private Mine mine;
  public ValuablesMiner(DepositLock lock, Mine mine){
    this.lock = lock;
    this.mine = mine;
  }
  @Override public void run()
  {

  }
}
