# docker/Dockerfile.lambda-native (PROD)
FROM quay.io/quarkus/ubi9-quarkus-mandrel-builder-image:jdk-21 AS build
ENV MAVEN_OPTS="-Dmaven.repo.local=/app/.m2/repository"
WORKDIR /app

COPY --chown=1001:0 --chmod=0755 mvnw ./
COPY --chown=1001:0 .mvn .mvn
COPY --chown=1001:0 pom.xml ./

RUN ./mvnw -B -q dependency:go-offline
COPY src src
RUN ./mvnw -B -DskipTests -Pnative package

FROM public.ecr.aws/lambda/provided:al2023
# binario nativo como bootstrap
COPY --from=build /app/target/*-runner /var/runtime/bootstrap
RUN chmod 755 /var/runtime/bootstrap
# en Lambda, ejecuta tu runtime
ENTRYPOINT ["/var/runtime/bootstrap"]
