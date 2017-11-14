# Solution
To run the solution please use the script: runDockerCompose located in the root of the project, on a Unix OS.

The producer on startup, starts sending 1 message every 5 seconds to a RabbitMQ queue (using Spring AMQP).

The consumer reads these messages using Spring AMQP 

Both projects have been developed using SpringBoot.

To see the total and the grouping of the items, check http://localhost:4001

An improvement to this solution is to extend the logback config to use the logstash encoder, so that one may push the logs to say an ELK stack to better analyse them.




