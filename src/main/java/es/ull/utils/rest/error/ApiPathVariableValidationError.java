package es.ull.utils.rest.error;

public class ApiPathVariableValidationError extends ApiSubErrorMessage {

    private String pathVariable;

    public ApiPathVariableValidationError(String message, String pathVariable) {
        super(message);
        this.pathVariable = pathVariable;
    }

    public String getPathVariable() {
        return this.pathVariable;
    }

    public ApiPathVariableValidationError setPathVariable(String pathVariable) {
        this.pathVariable = pathVariable;
        return this;
    }

    @Override
    public String toString() {
        return "ApiPathVariableValidationError ["
                + "message=" + this.getMessage()
                + "pathVariable=" + this.getPathVariable() + ", "
                + "]";
    }
}
