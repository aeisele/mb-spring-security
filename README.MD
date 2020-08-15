# Spring Security OAuth Client / Resource Server Showcase

This project houses an example resource server and a matching client, both are spring boot web applications.

The only thing not included is an authentication server.

I used a locally running standalone [keycloak](https://www.keycloak.org/) for that.

## test urls

| App | Url | Purpose |
| --- | --- | ------- |
| Resource Server | http://localhost:8082/transactions | some test data (fake financial transactions) |
| Client | http://localhost:8081/remote | makes to call to the resource server's transactions endpoint |
| Client | http://localhost:8081/client | some debug client info fetched out of the currently active authentication | 
| Client | http://localhost:8081/local  | is not supposed to call the resource server therefore this endpoint should work without any login |

## local keycloak setup

 1. get the zip distribution and run it `standalone.bat` / `standalone.sh`
 1. go to `http://localhost:8080/auth`
 1. create an admin user
 1. create a new domain called _Dev_ (or any name you like)
 1. create a new client called _mb-client_ (or any name you like) that uses "openid-connect" protocol
 1. note down the client credentials (client-secret)
 1. add a new user with some basic user info and set a password for that user
 1. add a new client scope `read_transactions`
 1. add a mapper to this scope to provide a hardcoded jwt claim "account_no" with a dummy value
 1. add the new scope to the optional scopes available for the client `mb-client`