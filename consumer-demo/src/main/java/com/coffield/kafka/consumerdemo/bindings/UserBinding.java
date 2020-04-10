package com.coffield.kafka.consumerdemo.bindings;

import com.coffield.kafka.consumerdemo.domain.UserEvent;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.cloud.stream.annotation.Input;

public interface UserBinding {
    String USERS_IN = "users_in";
    String USERS_COUNT_TABLE = "users_count_table";

    @Input(USERS_IN)
    KStream<String, UserEvent> usersProcessor();
}
