    public class ValuableTransporter implements Runnable
    {
        private DepositLock lock;
        private Door door;

        public ValuableTransporter(DepositLock lock)
        {
            this.lock = lock;
        }

        @Override
        public void run()
        {
            while (true)
            {
                lock.take();
                System.out.println(
                        Thread.currentThread().getName() + " taking treasure ");
                spendSomeTime("TAKING TREASURE");

                door.putTreasure();
                spendSomeTime("DELIVERING TREASURE");
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
