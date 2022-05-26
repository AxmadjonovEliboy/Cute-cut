package uz.pdp.cutecutapp.exception;

public class OrganizationNotActiveException extends RuntimeException {
    public OrganizationNotActiveException(String message) {
        super(message);
    }

    public OrganizationNotActiveException() {
        super("organization not active!!!");
    }
}
