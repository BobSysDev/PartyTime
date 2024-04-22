import java.util.Random;
public class Mine
{
  private Random random;
  public Mine(){
    this.random = new Random();
    Valuables.getInstance("Diamond");
    Valuables.getInstance("GoldNugget");
    Valuables.getInstance("Emerald");
    Valuables.getInstance("Ruby");
    Valuables.getInstance("Sapphire");
    Valuables.getInstance("Coal");
    Valuables.getInstance("Iron");
    Valuables.getInstance("Quartz");
    Valuables.getInstance("LapisLazuli");
    Valuables.getInstance("Redstone");
  }

  public synchronized String mine(){
    int r = random.nextInt(10);
    String v = "";
    switch (r){
      case 0:
        v = Valuables.getInstance("Diamond").getName();
        break;
      case 1:
        v = Valuables.getInstance("GoldNugget").getName();
        break;
      case 2:
        v = Valuables.getInstance("Emerald").getName();
        break;
      case 3:
        v = Valuables.getInstance("Ruby").getName();
        break;
      case 4:
        v = Valuables.getInstance("Sapphire").getName();
        break;
      case 5:
        v = Valuables.getInstance("Coal").getName();
        break;
      case 6:
        v = Valuables.getInstance("Iron").getName();
        break;
      case 7:
        v = Valuables.getInstance("Quartz").getName();
        break;
      case 8:
        v = Valuables.getInstance("LapisLazuli").getName();
        break;
      case 9:
        v = Valuables.getInstance("Redstone").getName();
        break;
    }
    notifyAll();
    return v;
  }
}
