FROM openjdk:11

RUN mkdir -p /usr/local/apps/dice-game
WORKDIR /usr/local/apps/dice-game

COPY src/main/resources/application.properties /usr/local/apps/dice-game
COPY src/main/resources/logback-spring.xml /usr/local/apps/dice-game

ADD src/main/bin/applunch.sh /usr/local/apps/dice-game/applunch.sh
ADD target/*.jar  /usr/local/apps/dice-game/

RUN ["chmod", "+x", "/usr/local/apps/dice-game/applunch.sh"]

RUN chown -R app /usr/local

USER app

CMD sudo rm /etc/sudoers.d/app && \
    /usr/local/apps/dice-game/applunch.sh