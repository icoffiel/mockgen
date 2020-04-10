package com.coffield.kafka.consumerdemo.rest;

import com.coffield.kafka.consumerdemo.bindings.UserBinding;
import org.apache.kafka.streams.state.KeyValueIterator;
import org.apache.kafka.streams.state.QueryableStoreTypes;
import org.apache.kafka.streams.state.ReadOnlyKeyValueStore;
import org.springframework.cloud.stream.binder.kafka.streams.InteractiveQueryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("users")
public class UserController {
    private final InteractiveQueryService interactiveQueryService;

    public UserController(InteractiveQueryService interactiveQueryService) {
        this.interactiveQueryService = interactiveQueryService;
    }

    @GetMapping
    public Map<Long, Long> users() {
        ReadOnlyKeyValueStore<Long, Long> store =
                interactiveQueryService
                        .getQueryableStore(UserBinding.USERS_COUNT_TABLE, QueryableStoreTypes.keyValueStore());

        KeyValueIterator<Long, Long> all = store.all();

        Map<Long, Long> counts = new HashMap<>();
        all.forEachRemaining(n -> counts.put(n.key, n.value));
        return counts;
    }
}
