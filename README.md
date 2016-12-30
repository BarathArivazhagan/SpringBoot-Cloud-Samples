# SpringBoot-Cloud-Samples
This repository contains samples for SpringBoot cloud projects which includes Eureka- NetFlix , Eureka service discovery , Zuul Edge proxy, Circuit breaker , Hystrix  and other interesting features of spring cloud services


Guide :

#Eureka Server - Service Registration Server 

1. Add @EnableEurekaServer 
2. Define the following properties in application properties 

eureka:<br />
  __instance:   <br />
    hostname: ${eureka.server.hostname:localhost} <br />
  client:<br />
    register-with-eureka: false <br />
    fetch-registry: false<br />
    

Run the app --> Go to http://localhost:8081 


# Eureka Client Demo APP -- Demo application to register with Eureka server 


1. Add @EnableDiscoveryClient to qualify app as a client to eureka server
2. properties : 

server: <br />
  port: 8085 <br />
  context-path: /client  <br />

eureka: <br />
  client:<br />
    register-with-eureka: true<br />
    fetch-registry: true<br />
    serviceUrl:<br />
      defaultZone:  http://${eureka.server.hostname:localhost}:${eureka.server.port:8081}/eureka<br />
      or
      defaultZone: http://localhost:8081/eureka <br />
  #instance:<br />
    #prefer-ip-address: true // depends whether ip address should be prefered for service registration<br /> 
    

Since we have added context root for the application: 

Run the app and test it : http://localhost:8085/client 


#Zuul Proxy -- API gateway


1. Add @EnableZuulProxy 
2. Properties : 


spring:<br />
  application:<br />
      name: zuulproxy<br />

server:<br />
  port: ${zuul.server.port:8083}<br />

#### eureka service properties 
eureka:<br />
  client:<br />
    serviceUrl:<br />
      defaultZone:  http://${eureka.server.hostname:localhost}:${eureka.server.port:8081}/eureka<br />
    register-with-eureka: true<br />
    fetch-registry: true<br />
  #instance:<br />
    #prefer-ip-address: true<br />

//zuul routes 
// Example :<br />

// zuul:<br />
//  routes:<br />
//      routename(can be any name):<br />
//       path: << path to match >><br />
//        url: << url to be forwarded >><br />
//        id: application name registered with the eureka server<br />
//        strip-prefix: true/false <br />

zuul:<br />
  routes:<br />
    app1:<br />
      path: /client/*<br />
      url: http://localhost:8085/client/<br />
      strip-prefix: true<br />
      
 
 Run the app and test by going through http://localhost:8083/ 
 
 
 
 Finally , start the applications in order 
#### EurekaServer  -- http://localhost:8081/
#### EurekaClientDemoApp -- http://localhost:8085/
#### ZuulProxyServer -- http://localhost:8083/

Zuul will act as reverse proxy server/ API gateway 

Test the client app through zuul by testing it with

http://localhost:8083/client

