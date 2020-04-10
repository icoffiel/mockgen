# Consumer Demo

## Kafka Consumer

The kafka consumer in this project consumes from a topic called `users`. It converts the stream to a count of users by ID, stores them in a materialized view, and logs them.

## Rest Endpoints

A Restful endpoint is exposed at `/users` that queries the Materialized view and returns it.