FROM openjdk:11-jdk-slim

# Set the locale
ENV TZ=America/Sao_Paulo
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

ENV LANG en_US.UTF-8
ENV LANGUAGE en_US:en
ENV LC_ALL en_US.UTF-8

RUN mkdir -p /var/log/score-api/diagnostics/
RUN mkdir -p /var/log/score-api/old/

ADD @project.build.finalName@.jar /score-api/score-api.jar
ADD score-api.conf /score-api/score-api.conf

RUN chmod a+x /score-api/score-api.jar

RUN apt update && apt install rsyslog curl -y
RUN echo '*.info;mail.none;authpriv.*;cron.none @spobrservlog' >> /etc/rsyslog.conf

ENTRYPOINT ["/score-api/score-api.jar"]
