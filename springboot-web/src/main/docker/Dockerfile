FROM openjdk:8

WORKDIR /opt/apps

# 添加项目
ADD ./vphotobi.zip ./
RUN /usr/bin/unzip ./vphotobi.zip

EXPOSE 10000

CMD ["sh","-c","./bin/single.sh","start"]