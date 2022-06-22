import java.util.concurrent.atomic.AtomicBoolean;

public class MyReentrantLock implements Lock{
    private AtomicBoolean isLocked = new AtomicBoolean(false);
    @Override
    public void close(){
        this.isLocked.set(true);
    }
    @Override
    public void acquire(){

    }
    @Override
    public boolean tryAcquire(){
        return false;
    }
    @Override
    public void release(){

    }

}
