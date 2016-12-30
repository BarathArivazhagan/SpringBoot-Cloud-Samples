# SpringBoot-Cloud-Samples
This repository contains samples for SpringBoot cloud projects which includes Eureka- NetFlix , Eureka service discovery , Zuul Edge proxy, Circuit breaker , Hystrix  and other interesting features of spring cloud services


Guide :

#Eureka Server - Service Registration Server 

1. Add @EnableEurekaServer 
2. Define the following properties in application properties 

eureka:
  instance:   
    hostname: ${eureka.server.hostname:localhost} 
  client:
    register-with-eureka: false 
    fetch-registry: false
    

Run the app --> Go to http://localhost:8081 


# Eureka Client Demo APP -- Demo application to register with Eureka server 


1. Add @EnableDiscoveryClient to qualify app as a client to eureka server
2. properties : 

server:
  port: 8085
  context-path: /client

eureka:
  client:
    register-with-eureka: true
    fetch-registry: true
    serviceUrl:
      defaultZone:  http://${eureka.server.hostname:localhost}:${eureka.server.port:8081}/eureka
      or
      defaultZone: http://localhost:8081/eureka 
  #instance:
    #prefer-ip-address: true // depends whether ip address should be prefered for service registration 
    

Since we have added context root for the application: 

Run the app and test it : http://localhost:8085/client 


#Zuul Proxy -- API gateway


1. Add @EnableZuulProxy 
2. Properties : 


spring:
  application:
      name: zuulproxy

server:
  port: ${zuul.server.port:8083}

# eureka service properties 
eureka:
  client:
    serviceUrl:
      defaultZone:  http://${eureka.server.hostname:localhost}:${eureka.server.port:8081}/eureka
    register-with-eureka: true
    fetch-registry: true
  #instance:
    #prefer-ip-address: true

# zuul routes 
# Example :

# zuul:
#  routes:
#      routename(can be any name):
#        path: << path to match >>
#        url: << url to be forwarded >>
#        id: application name registered with the eureka server
#        strip-prefix: true/false 

zuul:
  routes:
    app1:
      path: /client/*
      url: http://localhost:8085/client/
      strip-prefix: true
      
 
 Run the app and test by going through http://localhost:8083/ 
 
 
 
 Finally , start the applications in order 
# EurekaServer  -- http://localhost:8081/
# EurekaClientDemoApp -- http://localhost:8085/
# ZuulProxyServer -- http://localhost:8083/

Zuul will act as reverse proxy server/ API gateway 

Test the client app through zuul by testing it with

http://localhost:8083/client

