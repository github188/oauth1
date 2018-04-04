FROM openjdk:8-jdk
VOLUME "/mnt/logs"
USER root
ARG JAR_FILE
ARG UID

RUN groupadd -r appuser
RUN useradd -r -u $UID -g appuser appuser
#ADD gosu /usr/local/bin/gosu
RUN curl -sSL -o /usr/local/bin/gosu "https://github.com/tianon/gosu/releases/download/1.9/gosu-amd64"
RUN chown root:users /usr/local/bin/gosu && chmod +x /usr/local/bin/gosu && chmod +s /usr/local/bin/gosu

ADD $JAR_FILE app.jar
ADD entrypoint.sh /entrypoint.sh
ENTRYPOINT ["/entrypoint.sh"]