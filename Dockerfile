FROM ghcr.io/identicum/centos7-java11-maven:latest as builder
WORKDIR /app
COPY src ./src
COPY pom.xml .
RUN mvn install -DskipTests

# FROM ghcr.io/identicum/centos7-java11:latest
# WORKDIR /app
# COPY --from=builder /app/target/*.jar ./app.jar
# ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","./app.jar"]

FROM ghcr.io/identicum/centos7-java11-tomcat:latest
COPY --from=builder /app/target/*.war /tmp/demoapp.war
RUN yum -y install unzip \
 && rm -rf /usr/local/tomcat/webapps/ROOT/* \
 && unzip -qq /tmp/demoapp.war -d /usr/local/tomcat/webapps/ROOT \
 && rm -f /tmp/demoapp.war
