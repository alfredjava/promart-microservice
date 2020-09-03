FROM openjdk:8

ARG artifact_id
ARG artifact_version

ENV artifact ${artifact_id}-${artifact_version}.jar

# Create app directory
RUN mkdir -p /usr/src/app/properties/keyStore
WORKDIR /usr/src/app

# Install app dependencies
COPY target/${artifact} /usr/src/app/${artifact}

EXPOSE 8090

CMD ["sh", "-c", "java -jar ${artifact}"]
