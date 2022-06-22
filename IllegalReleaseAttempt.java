public class IllegalReleaseAttempt extends IllegalMonitorStateException{
    public IllegalReleaseAttempt() {}
    public IllegalReleaseAttempt(String s) { super(s);}
}
