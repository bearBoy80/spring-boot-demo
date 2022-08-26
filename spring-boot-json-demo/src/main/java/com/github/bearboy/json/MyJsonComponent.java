package com.github.bearboy.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.*;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

/**
 * @author jt.gui
 */
@JsonComponent
public class MyJsonComponent {

    public static class Serializer extends JsonSerializer<User> {

        @Override
        public void serialize(User value, JsonGenerator jgen, SerializerProvider serializers) throws IOException {
            jgen.writeStartObject();
            jgen.writeStringField("name", value.getName());
            jgen.writeNumberField("age", value.getAge());
            jgen.writeEndObject();
        }
    }

    public static class Deserializer extends JsonDeserializer<User> {

        @Override
        public User deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException {
            ObjectCodec codec = jsonParser.getCodec();
            JsonNode tree = codec.readTree(jsonParser);
            String name = tree.get("name").textValue();
            int age = tree.get("age").intValue();
            return new User(name, age);
        }

    }

}