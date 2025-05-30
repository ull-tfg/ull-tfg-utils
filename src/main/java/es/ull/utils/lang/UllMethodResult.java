package es.ull.utils.lang;

public class UllMethodResult<T1, T2> {

    private T1 result;
    private T2 error;

    public UllMethodResult(T1 result) {
        this(result, null);
    }

    public UllMethodResult(T1 result, T2 error) {
        this.result = result;
        this.error = error;
    }

    public T1 getResult() {
        return this.result;
    }

    public T2 getError() {
        return this.error;
    }

    public boolean hasError() {
        return this.error != null;
    }

    public static <T1, T2> UllMethodResult<T1, T2> of(T1 result, T2 error) {
        return new UllMethodResult<T1, T2>(result, error);
    }

    public static <T1, T2> UllMethodResult<T1, T2> of(T1 result) {
        return new UllMethodResult<T1, T2>(result, null);
    }

    public String toString() {
        return String.format(
                "UllMethodResult={result='%s',error='%s'}",
                this.result, this.error);
    }
}
