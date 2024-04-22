public class Deposit
{
    private QueueADT<Valuable> valuables;
    private int valuableCountWhenToDeliver;
    private int valuablesInDeliveries;
    private int depositDeliverySize;

    public Deposit(int depositSize, int cookieCountWhenToBake,
                     int depositDeliverySize)
    {
        System.out.println("depositSize"+ depositSize);
        this.valuables = new ArrayQueue<>(depositSize);
        for (int i=0; i<depositSize; i++)
        {
            valuables.enqueue(new Valuable("diamond"));
        }
        this.valuableCountWhenToDeliver = cookieCountWhenToBake;
        this.depositDeliverySize = depositDeliverySize;
        this.valuablesInDeliveries = 0;
    }

    public synchronized void startBaking()
    {
        while (valuables.size() > valuableCountWhenToDeliver)
        {
            try
            {
                System.out.println("Waiting to deliver: "
                        + Thread.currentThread().getName() + " count=" + valuables.size());
                wait();
            }
            catch (InterruptedException e)
            {
                //
            }
        }
        System.out.println("Started delivery: " + Thread.currentThread().getName()
                + " count=" + valuables.size());
        this.valuablesInDeliveries = depositDeliverySize;
    }

    public synchronized int deliveredValuables()
    {
        if (valuablesInDeliveries <= 0)
        {
            System.out.println("No valuables in the deliveries: "
                    + Thread.currentThread().getName() + " count=" + valuables.size());
            return 0;
        }
        this.valuablesInDeliveries = 0;
        for (int i=0; i<depositDeliverySize; i++)
        {
            valuables.enqueue(new Valuable("diamond"));
        }
        System.out.println("Finished delivering: " + Thread.currentThread().getName()
                + " count=" + valuables.size());
        notifyAll();
        return depositDeliverySize;
    }

    public synchronized void take()
    {
        while (valuables.isEmpty())
        {
            try
            {
                System.out.println("Waiting to take: "
                        + Thread.currentThread().getName() + " count=" + valuables);
                wait();
            }
            catch (InterruptedException e)
            {
                //
            }
        }
        Valuable valuable = valuables.dequeue();
        notify();
        System.out.println("Taking " + valuable + ": " + Thread.currentThread().getName()
                + " count=" + valuables.size());
    }

}
