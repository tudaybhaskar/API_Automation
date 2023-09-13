package core;

public enum StatusCode {
    SUCCESS(200, "Request is Successful"),
    FORBIDDEN(403, "Not allowed to perform operation"),
    BAD_REQUEST(400, "Request is BAD"),
    UNAUTHORIZED(401,"Not Authorized to access"),
    NOT_FOUND(400,"Resource is not found"),
    CREATED(201,"A Resource is created"),
    NO_CONTENT(204, "No Content to send in the Response body");

    public final int code ;

    public final String message;
    StatusCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
