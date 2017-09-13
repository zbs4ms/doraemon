
FROM maven:3.3.3

ADD pom.xml  /tmp/build/

RUN cd /tmp/build && mvn -q dependency:resolve

ADD src /tmp/build/src

RUN cd /tmp/build && mvn -Dmaven.test.skip=true package \
    && mv target/*.jar /app.jar 

VOLUME /tmp
EXPOSE 9090
ENTRYPOINT ["java","-jar","/app.jar"]
