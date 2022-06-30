import java.util.concurrent.atomic.AtomicBoolean;

/**
 * MyReentrantLock objects attributes:
 * counter - the number of times the lock has been locked minus the number of times the lock has been unlocked
 * isLocked - Atomic boolean that says if the lock is locked or not
 * owner - the thread that locked the lock
 */
public class MyReentrantLock implements Lock{

    private int counter=0;
    private AtomicBoolean isLocked;
    private Thread owner = null;

    public MyReentrantLock() {
        this.isLocked = new AtomicBoolean(false);
    }

    /**
     * function that releases the lock while in a try with resources loop
     */
    @Override
    public void close(){
        release();
    }

    /**
     * a function that tries to acquire and lock the lock until it succeeds
     * @throws IllegalReleaseAttempt when the thread is interrupted during sleep
     */
    @Override
    public void acquire(){
        while (!tryAcquire()){
            try {
                Thread.sleep(1);
            } catch (InterruptedException e){}
        }
    }

    /**
     * a function that tries to acquire and lock. If it can't acquire it- it returns false, else returns true
     */
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
    /**
     * a function that releases the lock.
     * @throws IllegalReleaseAttempt when cannot release
     */
    @Override
    public void release() {
        if (!this.isLocked.get() || Thread.currentThread() != owner || this.counter < 1) {
           throw new IllegalReleaseAttempt();
        }
        if (--this.counter == 0) {
            this.isLocked = new AtomicBoolean();
            this.owner = null;
        }
    }
}
