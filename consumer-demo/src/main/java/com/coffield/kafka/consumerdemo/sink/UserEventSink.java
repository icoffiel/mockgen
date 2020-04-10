package com.coffield.kafka.consumerdemo.sink;

import com.coffield.kafka.consumerdemo.bindings.UserBinding;
import com.coffield.kafka.consumerdemo.domain.UserEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.*;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserEventSink {

    @StreamListener
    public void process(@Input(UserBinding.USERS_IN) KStream<String, UserEvent> events) {
        events
                .map((key, value) -> new KeyValue<>(value.getId(), 0))
                .groupByKey(Grouped.with(Serdes.Long(), Serdes.Integer()))
                .count(Materialized.as(UserBinding.USERS_COUNT_TABLE))
                .toStream()
                .foreach((key, value) -> log.info("key: " + key + " value: " + value));
    }
}
