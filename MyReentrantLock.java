import java.util.concurrent.atomic.AtomicBoolean;

public class MyReentrantLock implements Lock{

    private AtomicBoolean isLocked;
    private Thread owner;

    public MyReentrantLock() {
        this.isLocked = new AtomicBoolean(false);
        this.owner = Thread.currentThread();
    }

    @Override
    public void close(){
        this.isLocked.set(true);
    }
    @Override
    public void acquire(){
        Thread thread = Thread.currentThread();
             while(thread != owner && this.isLocked.get()) {
                 //need to find a way to wait without causing a Busy waiting
             }
             if (thread == owner) {this.isLocked.compareAndSet(false, true);}
    }

    @Override
    public boolean tryAcquire(){
        boolean check = false;
        try {
            check = this.isLocked.compareAndSet(false, true);
        } finally {
            return check;
        }
    }

    @Override
    public void release(){
        //the Thread.currentThread() != owner is causing errors for some reason
        if (this.isLocked.get() == false || Thread.currentThread() != owner) {
            throw new IllegalReleaseAttempt();
        }
        this.isLocked.compareAndSet(true, false);
    }
}
