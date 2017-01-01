FROM java:8
MAINTAINER BARATH
EXPOSE 8083
ADD target/ZuulProxyServer-1.0.0.RELEASE.war zuulproxyserver.war
ENTRYPOINT ["java","-jar","zuulproxyserver.war"]
