
FROM centos:latest
MAINTAINER zhida

ADD jdk-8u151-linux-x64.tar.gz  /usr/local/lib/

ADD phdl-gateway-1.0-SNAPSHOT.jar  /app.jar


ENV JAVA_HOME /usr/local/lib/jdk1.8.0_151
ENV JRE_HOME $JAVA_HOME/jre  
ENV CLASSPATH .:$JAVA_HOME/lib:$JRE_HOME/lib  
ENV PATH $PATH:$JAVA_HOME/bin


RUN /bin/cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime \
  && echo 'Asia/Shanghai' >/etc/timezone 


ENV LANG en_US.utf8


ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]