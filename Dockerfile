FROM gradle AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle shadowJar --no-daemon --debug

FROM amazoncorretto:17-alpine
EXPOSE 8080:80
RUN mkdir /app
COPY --from=build /home/gradle/src/build/libs/*.jar /app/server.jar
ENTRYPOINT ["java","-jar","/app/server.jar", "server", "config.yml"]
