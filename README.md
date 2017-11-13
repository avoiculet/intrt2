# Solution
To run the solution run docker-compose up in the root of the project.

The producer on startup, starts sending 1 message every 5 seconds to a rabbit mq queue (using Spring AMQP).

The consumer reads these messages using Spring AMQP 

Both projects have been developed using SpringBoot.

To see the total and the grouping of the items, check http://localhost:4001 , after running docker-compose up

An improvement on this solution is to extend the logback config to use the logstash encoder, so that one may push the logs to say an ELK stack to better analyse them.




