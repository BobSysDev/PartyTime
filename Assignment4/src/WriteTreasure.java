import java.util.ArrayList;

public interface WriteTreasure {
  public void addTreasure(Valuable valuable);
  public void addTreasure(ArrayList<Valuable> valuablez);
  ArrayList<Valuable> getTreasure();
}
