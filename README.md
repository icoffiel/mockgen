# Mockgen

Contains the following sub projects:

- [consumer-demo](./consumer-demo/README.md)

## Send mock data to topic

Use the following to send mock data to a kafka topic:

`cat MOCK_DATA.json | kafkacat -b localhost:9092 -t users -P`

`MOCK_DATA.json` was created using [Mockaroo](https://www.mockaroo.com/).