package es.ull.utils.rest.error;

public class ApiSubErrorMessage extends ApiSubError {

    private String message;

    public ApiSubErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public ApiSubErrorMessage setMessage(String message) {
        this.message = message;
        return this;
    }

    @Override
    public String toString() {
        return "ApiSubErrorMessage ["
                + "message=" + message
                + "]";
    }
}
