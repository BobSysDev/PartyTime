public interface Door {

    void acquireReadAccess();
    void releaseReadAccess();
    void acquireWriteAccess();
    void releaseWriteAccess();

}
