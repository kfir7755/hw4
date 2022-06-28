import java.util.concurrent.atomic.AtomicBoolean;

public class MyReentrantLock implements Lock{


    private int counter=0;
    private AtomicBoolean isLocked;
    private Thread owner = null;

    public MyReentrantLock() {
        this.isLocked = new AtomicBoolean(false);
    }

    @Override
    public void close(){
        release();
    }
    @Override
    public void acquire(){
        while (!tryAcquire()){
            try {
                Thread.sleep(1);
            } catch (InterruptedException e){}
        }
    }

    @Override
    public boolean tryAcquire(){
        boolean check = false;
        if (this.isLocked.compareAndSet(false, true) || Thread.currentThread() == this.owner) {
            this.counter++;
            this.owner =  Thread.currentThread();
            check = true;
        }
        return check;
    }

    @Override
    public void release() {
        if (!this.isLocked.get() || Thread.currentThread() != owner || this.counter < 1) {
           throw new IllegalReleaseAttempt();
        }
//        if (!this.isLocked.get()) throw new IllegalReleaseAttempt("cant release an unlocked lock");
//        if (Thread.currentThread() != owner) throw new IllegalReleaseAttempt("cant release if you are not owner");
//        if (this.counter < 1) throw new IllegalReleaseAttempt("problem with count");
        if (--this.counter == 0) {
            this.isLocked = new AtomicBoolean();
            this.owner = null;
        }
    }
}
