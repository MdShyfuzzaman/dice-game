#!/bin/sh
export JAVA_OPTS=$(eval echo $JAVA_OPTS)

java $JAVA_OPTS -jar \
-Dspring.config.location=/usr/local/apps/dice-game/application.properties \
-Dlogging.config=file:/usr/local/apps/dice-game/logback-spring.xml \
/usr/local/apps/dice-game/*.jar \
-XX:+UseConcMarkSweepGC -XX:+CMSIncrementalMode