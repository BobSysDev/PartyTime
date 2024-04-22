import javax.sound.midi.Soundbank;
import java.io.IOException;

public class Logger
{
  private static Logger instance;
  private static Object lock = new Object();

  private String miner1output;
  private String miner2output;
  private String kingUpdate;
  private String transporterUpdate;
  private String accountantShit;

  private Logger(){
    miner1output = "Waiting for news from the mine...";
    miner2output = "Waiting for news from the mine...";
    kingUpdate = "Wating for the King to wake up...";
    transporterUpdate = "Trying to find a trustworthy transporter...";
    accountantShit = "Accountant is learning how to count...";
    displayLog();
  }

  public static Logger getInstance()
  {
    if(instance == null){
      synchronized (lock){
        if(instance == null){
          instance = new Logger();
        }
      }
    }

    return instance;
  }

  public synchronized void displayLog(){
    System.out.println("\n\n\n\n\n\n\n\n");
    System.out.println("PartyTime \n============================================");
    System.out.println("Miner one status: " + miner1output);
    System.out.println("Miner two status: " + miner2output);
    System.out.println("Transporter status: " + transporterUpdate);
    System.out.println("============================================");
    System.out.println("Party status: " + kingUpdate);
    System.out.println("============================================");
    System.out.println("Accountant's report: ");
    System.out.println(accountantShit);
  }

  public synchronized void updateMiner(int number, String value){
    if(number == 1){
      miner1output = value;
    }
    else{
      miner2output = value;
    }
    displayLog();
  }

  public synchronized void updateTransporter(String value){
    transporterUpdate = value;
    displayLog();
  }

  public synchronized void updateKing(String value){
    kingUpdate = value;
    displayLog();
  }

  public synchronized void updateAccountantData(String value){
    accountantShit = value;
    displayLog();
  }
}
