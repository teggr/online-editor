FROM openjdk:8-jdk

RUN apt-get update && apt-get install -y \
  graphviz

ENV GRAPHVIZ_DOT /usr/bin/dot

COPY target/online-editor.jar /usr/share/online-editor.jar

EXPOSE 8383

CMD ["java", "-XX:+UnlockExperimentalVMOptions", "-XX:+UseCGroupMemoryLimitForHeap", "-jar", "/usr/share/online-editor.jar"]