FROM maven:3.8.6-openjdk-18

WORKDIR /usr/src/app

ADD ./ ./

ENTRYPOINT ["java","-jar","target/blog-0.0.1-SNAPSHOT.jar"]

