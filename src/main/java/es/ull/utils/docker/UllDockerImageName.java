package es.ull.utils.docker;

import java.util.Objects;
import java.util.regex.Pattern;

import es.ull.utils.string.UllString;

public class UllDockerImageName {

    public static final String REGULAR_EXPRESSION = "^(?:(?=[^:\\/]{1,253})(?!-)[a-zA-Z0-9-]{1,63}(?<!-)(?:\\.(?!-)[a-zA-Z0-9-]{1,63}(?<!-))*(?::[0-9]{1,5})?/)?((?![._-])(?:[a-z0-9._-]*)(?<![._-])(?:/(?![._-])[a-z0-9._-]*(?<![._-]))*)(?::(?![.-])[a-zA-Z0-9_.-]{1,128})?$";
    public static final String ERROR_NOT_DEFINED = "Docker image name is not defined";
    public static final String ERROR_EMPTY = "Docker image name cannot be empty";
    public static final String ERROR_WRONG_FORMAT = "Docker image name has the following format and components: [HOST[:PORT_NUMBER]/]PATH. "
            +
            "HOST it the optional registry hostname specifies where the image is located. " +
            "PORT_NUMBER is the registry port number. " +
            "PATH consists of slash-separated components. Each component may contain lowercase letters, digits and separators. "
            +
            "More information can be found in https://docs.docker.com/engine/reference/commandline/image_tag/#description";
    /**
     * The value of the Docker image name.
     */
    private String value;

    /**
     * Constructor for a Docker image name.
     * 
     * @param value the value of the Docker image name
     */
    public UllDockerImageName(String value) {
        this.validate(value);
        this.value = UllString.simplify(value);
    }

    public UllDockerImageName(UllDockerImageName otherDockerImageName) {
        this.value = otherDockerImageName.value;
    }

    /**
     * Method to validate the constraints specific to the Docker image name.
     * 
     * @param value the value to validate
     */
    private void validate(String value) {
        if (value == null) {
            throw new NullPointerException(ERROR_NOT_DEFINED);
        }
        value = UllString.simplify(value);
        final int length = value.length();
        if (length == 0) {
            throw new IllegalArgumentException(ERROR_EMPTY);
        }
        if (!Pattern.compile(REGULAR_EXPRESSION).matcher(value).matches()) {
            throw new IllegalArgumentException(ERROR_WRONG_FORMAT);
        }
    }

    /**
     * Method to clone the Docker image name.
     * 
     * @return the cloned Docker image name
     */
    @Override
    public boolean equals(Object otherObject) {
        if (otherObject == this) {
            return true;
        }
        if (otherObject == null) {
            return false;
        }
        if (!(otherObject instanceof UllDockerImageName)) {
            return false;
        }
        final UllDockerImageName otherDockerImageName = (UllDockerImageName) otherObject;
        return (this.value.equals(otherDockerImageName.value));
    }

    /**
     * Method to compare two Docker image names.
     * 
     * @return the hash code of the Docker image name
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.value);
    }

    /**
     * Method to get the value of the Docker image name.
     * 
     * @return the value of the Docker image name
     */
    public String getValue() {
        return this.value;
    }

    /**
     * Method to get the string representation of the Docker image name.
     * 
     * @return the string representation of the Docker image name
     */
    public String toString() {
        return this.value;
    }
}
