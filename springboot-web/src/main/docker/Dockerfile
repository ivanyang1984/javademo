FROM openjdk:8

WORKDIR /opt/apps

# 添加项目
ADD ./destName.jar /opt/apps/vphotobi.jar

RUN ln -snf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && echo Asia/Shanghai > /etc/timezone


EXPOSE 8080

CMD ["java","-jar","-Dspring.profiles.active=test","default.jar"]
