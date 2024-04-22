public class Main
{
  public static void main(String[] args)
  {
    DepositLock depositLock = new Deposit();
    Mine mine = new Mine();
    ValuablesMiner miner = new ValuablesMiner(depositLock, mine);
    ValuablesMiner miner2 = new ValuablesMiner(depositLock, mine);
    TreasureRoom treasureRoom = new TreasureRoom();
    Door door = new Guardsman(treasureRoom);
    ValuableTransporter valuableTransporter = new ValuableTransporter(depositLock, door);
    King king = new King(door);
    Accountant accountant = new Accountant(door);

    Thread minerThread1 = new Thread(miner, "Miner 1");
    Thread minerThread2 = new Thread(miner2, "Miner 2");
    Thread transporterThread = new Thread(valuableTransporter, "Transporter");
    Thread kingThread = new Thread(king, "King");
    Thread accountantThread = new Thread(accountant, "Accountant");

    minerThread1.start();
    minerThread2.start();
    transporterThread.start();
    kingThread.start();
    accountantThread.start();
  }
}
