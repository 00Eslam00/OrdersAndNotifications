package com.FCAI.OrdersAndNotifications.Utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class OrderIDSerializer extends JsonSerializer<Integer> {
    @Override
    public void serialize(Integer value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        // Check if the current order is part of an orderList
        if (MyThreadLocalHolder.isInOrderList()) {
            // Don't serialize orderID
            gen.writeNull();
        } else {
            // Serialize orderID
            gen.writeNumber(value);
        }
    }
}