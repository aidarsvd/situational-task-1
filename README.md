# Fin Tech Innovators

## YouTube video demonstration
https://youtu.be/mnssFyXrnK8


The application for processing the payment between users.


## General information
#### Language / Framework
- Java Oracle OpenJDK 17
- Spring Boot 3.2.2

#### DBMS
- PostgreSQL


## The application usage
API Specification Documentation: _/swagger-ui/index.html_

### Authentication 
#### Sign-Up process:
For profile creation we must provide the necessary information:

POST _/trusted/auth/sign-up_
```json
{
  "username": "string",
  "password": "password",
  "name": "name"
}
```

#### Sign-In process:
For signing in to the system we should provide following endpoint with username and password and we will get the jwt tokens:

POST _/trusted/auth/sign-in_
```json
{
    "username": "username",
    "password": "password"
}
```

### Profile Management
#### Create an account:
For account creation we have to provide the requisite that we want to use

POST _/user/create-account_

-H Authorization: Bearer-Token <jwt-token> 

```json
{
    "requisite":"0705040643"
}
```

#### Get profile information:
For getting information about profile and account, we have to fetch it

GET _user/profile_

-H Authorization: Bearer-Token <jwt-token>

### Payment process
#### Make the transaction:
For proceed the payment we have to send following payload to endpoint:

POST _payment/proceed-payment_

-H Authorization: Bearer-Token <jwt-token> 

```json
{
    "account_id": 2,
    "recipient_requisite": "0705040644",
    "amount": 200
}
```