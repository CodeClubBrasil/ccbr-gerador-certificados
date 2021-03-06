FROM adoptopenjdk/openjdk11-openj9
RUN groupadd -r codeclub && useradd -r -g codeclub codeclub
ENV CODECLUB_HOME "/opt/codeclub"
RUN mkdir -p "${CODECLUB_HOME}" && \
    chown -R codeclub:codeclub "${CODECLUB_HOME}"
ADD --chown=codeclub:codeclub certificate-generator-web/target/certificate-generator-web.jar "${CODECLUB_HOME}"
WORKDIR $CODECLUB_HOME
EXPOSE 8080
USER codeclub
CMD ["java","-Xmx256M","-jar", "certificate-generator-web.jar"]