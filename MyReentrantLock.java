import java.util.concurrent.atomic.AtomicBoolean;

public class MyReentrantLock implements Lock{

    private AtomicBoolean isLocked;
    private Thread owner = null;

    public MyReentrantLock() {
        this.isLocked = new AtomicBoolean(false);
    }

    @Override
    public void close(){
        this.isLocked.set(true);
        this.owner = Thread.currentThread();
    }
    @Override
    public void acquire(){
        Thread thread = Thread.currentThread();
             if (thread == this.owner && this.owner !=null) {
                 while (this.isLocked.get()) {
                    //find a way to make it wait
                 }
                 this.isLocked.compareAndSet(false, true);
                 this.owner = thread;
             }
    }

    @Override
    public boolean tryAcquire(){
        boolean check = false;
        try {
            Thread thread = Thread.currentThread();
                check = this.isLocked.compareAndSet(false, true);
                if (check) this.owner = thread;
            } finally {
            return check;
        }
    }

    @Override
    public void release(){
        if (this.isLocked.get() == false || Thread.currentThread() != owner) {
            throw new IllegalReleaseAttempt();
        }
        this.isLocked.compareAndSet(true, false);
    }
}
