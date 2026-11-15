import java.io.IOException;
class ExamException extends Exception {
    ExamException(String message) { super(message); }
    ExamException(String message, Throwable cause) { super(message, cause); }
}
class SubmissionStorageException extends ExamException {
    SubmissionStorageException(String message, Throwable cause) { super(message, cause); }
}
class ExamWindowClosedException extends ExamException {
    ExamWindowClosedException(String message) { super(message); }
}
class StudentNotRegisteredException extends ExamException {
    StudentNotRegisteredException(String message) { super(message); }
}
class FileSizeLimitException extends ExamException {
    FileSizeLimitException(String message) { super(message); }
}
class Storage {
    void save(String fileName, long sizeKb) throws SubmissionStorageException {
        try {
            if (sizeKb > 500) throw new IOException("Disk quota exceeded");
            System.out.println("Saved " + fileName);
        } catch (IOException e) {
            throw new SubmissionStorageException("Could not store " + fileName, e);
        }
    }
}
class SubmissionService {
    private final Storage storage = new Storage();
    void submit(String fileName, long sizeKb, boolean registered, boolean windowOpen)
            throws ExamException {
        if (!windowOpen) throw new ExamWindowClosedException("Exam window has closed");
        if (!registered) throw new StudentNotRegisteredException("Student is not registered");
        if (sizeKb > 1000) throw new FileSizeLimitException("Answer file exceeds 1000 KB");
        storage.save(fileName, sizeKb);
    }
}
public class StorageDemo {
    public static void main(String[] args) {
        SubmissionService service = new SubmissionService();
        try {
            service.submit("answer01.pdf", 120, true, true);
            service.submit("answer02.pdf", 800, true, true);
        } catch (ExamException e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println("Cause: " + e.getCause().getMessage());
            System.out.println("Chained stack trace:");
            e.printStackTrace(System.out);
        }
        System.out.println("\\nAdditional validation scenarios:");
        test(service, "late.pdf", 100, true, false);
        test(service, "unregistered.pdf", 100, false, true);
        test(service, "large.pdf", 1500, true, true);
    }
    private static void test(SubmissionService s, String file, long size,
                             boolean registered, boolean open) {
        try { s.submit(file, size, registered, open); }
        catch (ExamException e) { System.out.println(file + ": " + e.getMessage()); }
    }
}