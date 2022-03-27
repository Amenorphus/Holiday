Holiday webservice

Launching the service (JDK 1.8 and Maven required):
Access the root folder of the project and enter command:

mvn spring-boot:run


Example GET request with parameters:
http://localhost:8080/holiday?code1=PL&code2=DE&date=2022-12-28


Assumptions:
The used API did not require a personalized key, so none is provided in the code.
The task stated that a first holiday "after" the given date is to be returned, therefore providing exactly the holiday date will result in returning the next holiday in line.
