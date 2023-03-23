package es.ull.utils.rest.serialization;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import es.ull.utils.rest.error.ApiError;
import es.ull.utils.rest.error.ApiSubError;
import es.ull.utils.rest.exception.UllException;

public abstract class UllJsonDeserializer<T> extends JsonDeserializer<T> {
    
    protected Optional<List<ApiSubError>> validate(JsonNode json) {
        List<ApiSubError> errors = new ArrayList<>();
        return (errors.isEmpty()) ? Optional.empty() : Optional.of(errors);
    }

    @Override
    public final T deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        final JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        final Optional<List<ApiSubError>> errors = this.validate(node);
        if (errors.isPresent()) {
            throw new UllException()
                    .setMessage(ApiError.MESSAGE_VALIDATION_ERRORS)
                    .setSubErrors(errors.get());
        }
        return this.parse(node);
    }

    protected abstract T parse(JsonNode node);
}
