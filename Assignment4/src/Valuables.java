import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Valuables
{
  private static Map<String, Valuables> map = new HashMap<>();
  private String name;

  private Valuables(String name)
  {
   this.name = name;
   //key = name of valuable
  }

  public static Valuables getInstance(String key)
  {
    Valuables instance = map.get(key);
    if (instance == null)
    {
      synchronized (map)
      {
        instance = map.get(key);
        if (instance == null)
        {
          instance = new Valuables(key);
          map.put(key, instance);
        }
      }
    }
    return instance;
  }

  public String getName()
  {
    return name;
  }
}
