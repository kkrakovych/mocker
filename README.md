# Mocker

The application uses WireMock library to start a server for mocking REST API.

- MockerRulesConfig class contains sample rules. First one is simple, and second one is a scenario with three steps.
- MockerServerConfig class starts server and applies rules.

Actually the rules can be uploaded from a database, file, or any source you'd like to use.