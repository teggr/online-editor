FROM openjdk:8-jdk

RUN apt-get update && apt-get install -y \
  graphviz

ENV GRAPHVIZ_DOT /usr/bin/dot

COPY target/online-editor-0.0.1-SNAPSHOT.jar /usr/share/online-editor-0.0.1-SNAPSHOT.jar

EXPOSE 8383

CMD ["java", "-XX:+UnlockExperimentalVMOptions", "-XX:+UseCGroupMemoryLimitForHeap", "-jar", "/usr/share/online-editor-0.0.1-SNAPSHOT.jar"]