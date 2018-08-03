FROM openjdk:alpine

WORKDIR /app

ADD target/service-payment-server-0.0.1-SNAPSHOT.jar /app

EXPOSE 8080

ENV JAVA_OPTS=""
ENV JAVA_CONTAINER_OPTS="-XX:+UnlockExperimentalVMOptions -XX:+UseCGroupMemoryLimitForHeap -XshowSettings:vm -Djava.security.egd=file:/dev/./urandom"

ENTRYPOINT [ "sh", "-c", "java -server $JAVA_OPTS $JAVA_CONTAINER_OPTS -jar /app/service-payment-server-0.0.1-SNAPSHOT.jar" ]
