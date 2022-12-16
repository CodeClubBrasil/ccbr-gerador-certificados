FROM adoptopenjdk/maven-openjdk11 as build
COPY . /build
WORKDIR /build
RUN mvn clean install

FROM adoptopenjdk/openjdk11-openj9
RUN groupadd -r codeclub && useradd -r -g codeclub codeclub
ENV CODECLUB_HOME "/opt/codeclub"
RUN mkdir -p "${CODECLUB_HOME}" && \
    chown -R codeclub:codeclub "${CODECLUB_HOME}"
COPY --from=build /build/certificate-generator-web/target/certificate-generator-web.jar ${CODECLUB_HOME}/
WORKDIR $CODECLUB_HOME
EXPOSE 8080
USER codeclub
CMD ["java","-Xmx256M","-jar", "certificate-generator-web.jar"]