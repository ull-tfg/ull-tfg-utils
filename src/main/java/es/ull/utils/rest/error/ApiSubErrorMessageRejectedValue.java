package es.ull.utils.rest.error;

public class ApiSubErrorMessageRejectedValue extends ApiSubErrorMessage {

    private String rejectedValue;

    public ApiSubErrorMessageRejectedValue(String message, String rejectedValue) {
        super(message);
        this.rejectedValue = rejectedValue;
    }

    public String getRejectedValue() {
        return this.rejectedValue;
    }

    public ApiSubErrorMessageRejectedValue setRejectedValue(String rejectedValue) {
        this.rejectedValue = rejectedValue;
        return this;
    }

    @Override
    public String toString() {
        return "ApiSubErrorMessageRejectedValue ["
                + "rejectedValue=" + this.getRejectedValue() + ", "
                + "message=" + this.getMessage()
                + "]";
    }
}
