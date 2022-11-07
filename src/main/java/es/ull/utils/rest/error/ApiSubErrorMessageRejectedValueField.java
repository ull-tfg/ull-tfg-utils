package es.ull.utils.rest.error;

public class ApiSubErrorMessageRejectedValueField extends ApiSubErrorMessage {

    private Object rejectedValue;
    private String field;

    public ApiSubErrorMessageRejectedValueField(String message, String field) {
        this(message, null, field);
    }

    public ApiSubErrorMessageRejectedValueField(String message, Object rejectedValue, String field) {
        super(message);
        this.rejectedValue = rejectedValue;
        this.field = field;
    }

    public String getField() {
        return this.field;
    }

    public boolean hasRejectedValue() {
        return this.rejectedValue != null;
    }

    public Object getRejectedValue() {
        return this.rejectedValue;
    }

    public ApiSubErrorMessageRejectedValueField setField(String field) {
        this.field = field;
        return this;
    }

    public ApiSubErrorMessageRejectedValueField setRejectedValue(String rejectedValue) {
        this.rejectedValue = rejectedValue;
        return this;
    }

    @Override
    public String toString() {
        String string = "";
        string += "ApiSubErrorMessageRejectedValueField [";
        string += "message=" + this.getMessage() + ", ";
        if (this.hasRejectedValue()) {
            string += "rejectedValue=" + this.getRejectedValue().toString() + ", ";
        }
        string += "field=" + this.getField();
        string += "]";
        return string;
    }
}
