package com.tools.rental.util;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import io.jsonwebtoken.io.IOException;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

public class InstantSerializer extends StdSerializer<Instant> {

	public InstantSerializer() {
        super(Instant.class);
    }

    @Override
    public void serialize(
        @Nullable final Instant value,
        @Nonnull final JsonGenerator jsonGenerator,
        @Nonnull final SerializerProvider serializerProvider
    ) throws IOException, java.io.IOException {
        if (value != null) {
            final DateTimeFormatter formatter = DateTimeFormatter
                .ofPattern("yyyy/MM/dd'T'HH:mm:ss.SSS'Z'")
                .withZone(ZoneId.systemDefault());
            final String text = formatter.format(value);
            jsonGenerator.writeString(text);
        }
    }

}
