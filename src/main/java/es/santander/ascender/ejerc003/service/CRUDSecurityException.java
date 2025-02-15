package es.santander.ascender.ejerc003.service;

public class CRUDSecurityException extends RuntimeException {

    private final CRUDOperation operation;
    private final Long id;

    public CRUDSecurityException(CRUDOperation operation, Long id) {
        this.operation = operation;
        this.id = id;
    }

    public CRUDSecurityException(String message, CRUDOperation operation, Long id) {
        super(message);
        this.operation = operation;
        this.id = id;
    }

    public CRUDOperation getOperation() {
        return operation;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "OperacionSecurityException [operation=" + operation + ", id=" + id + ", toString()=" + super.toString()
                + "]";
    }

}
