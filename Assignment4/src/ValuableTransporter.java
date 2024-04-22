import java.util.ArrayList;
import java.util.logging.Logger;

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

        @Override
        public void run()
        {
            while (lock.size()>=3)
            {
                for (int i = 0; i < 3; i++) {
                    try {Thread.sleep(300);}
                    catch (InterruptedException e) {throw new RuntimeException(e);}
                    String v = lock.take();
                    valuables.add(v);
                    log.log(Thread.currentThread().getName()+ " is taking " + v);

                }

                WriteTreasure treasure = door.acquireWriteAccess();
                for (int i = 0; i < 3; i++) {
                    try {Thread.sleep(2000);}
                    catch (InterruptedException e) {throw new RuntimeException(e);}
                    treasure.addTreasure(valuables.remove(i));
                    log.log(Thread.currentThread().getName()+ " delivered treasure ");
                }

            }

        }
        private void spendSomeTime(String what)
        {
            try
            {
                Thread.sleep(500);
            }
            catch (InterruptedException e)
            {
                // ...
            }
        }

    }
