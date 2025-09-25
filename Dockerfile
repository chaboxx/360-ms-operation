# docker/Dockerfile.lambda-native (PROD)
FROM quay.io/quarkus/ubi9-quarkus-mandrel-builder-image:jdk-21 AS build
ENV MAVEN_OPTS="-Dmaven.repo.local=/app/.m2/repository"
WORKDIR /app
COPY mvnw ./
COPY .mvn .mvn
COPY pom.xml ./
RUN chmod +x mvnw
RUN ./mvnw -B -q dependency:go-offline
COPY src src
RUN ./mvnw -B -DskipTests -Pnative package

FROM public.ecr.aws/lambda/provided:al2023
# binario nativo como bootstrap
COPY --from=build /app/target/*-runner /var/runtime/bootstrap
RUN chmod 755 /var/runtime/bootstrap
# en Lambda, ejecuta tu runtime
ENTRYPOINT ["/var/runtime/bootstrap"]
