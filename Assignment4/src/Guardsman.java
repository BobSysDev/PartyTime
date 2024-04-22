import java.util.ArrayList;

public class Guardsman implements Door
{
  private TreasureRoom treasureRoom;
  private ReadTreasure readProxy;
  private WriteTreasure writeProxy;

  private int readers;
  private int writers;
  private int waitingWriters; //because of writer preference
  private ArrayList<Thread> allowedWriteAccess;
  private ArrayList<Thread> allowedReadAccess;

  public Guardsman(TreasureRoom treasureRoom)
  {
    this.treasureRoom = treasureRoom;
    this.readers = 0;
    this.writers = 0;
    this.waitingWriters = 0;
    this.allowedWriteAccess = new ArrayList<>();
    this.allowedReadAccess = new ArrayList<>();
    this.readProxy = new ReadProxy(treasureRoom,this);
    this.writeProxy = new WriteProxy(treasureRoom,this);
  }

  @Override public synchronized ReadTreasure acquireReadAccess()
  {
    while (writers > 0 || waitingWriters> 0){
      try{wait();}
      catch (InterruptedException e) {throw new RuntimeException(e);}
    }
    readers++;
    allowedReadAccess.add(Thread.currentThread());
    notifyAll();
    return readProxy;
  }

  @Override public synchronized void releaseReadAccess()
  {
    readers--;
    allowedReadAccess.remove(Thread.currentThread());
    if (readers==0){
      notifyAll();
    }
  }

  @Override public synchronized WriteTreasure acquireWriteAccess()
  {
    waitingWriters++;
    while (readers > 0 || writers > 0){
      try{wait();}
      catch (InterruptedException e) {throw new RuntimeException(e);}
    }
    waitingWriters--;
    writers++;
    allowedWriteAccess.add(Thread.currentThread());
    notifyAll();
    return writeProxy;
  }

  @Override public synchronized void releaseWriteAccess()
  {
    writers--;
    allowedWriteAccess.remove(Thread.currentThread());
    if (writers==0){
      notifyAll();
    }
  }

  public boolean hasAccess(Thread t){
    if (allowedWriteAccess.contains(t) || allowedReadAccess.contains(t)){
      return true;
    }
    else {
      return false;
    }
  }
}
