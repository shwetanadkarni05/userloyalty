# User Loyalty

This project implements a basic loyalty transaction system.

## Getting Started

This project is implemented using mysql database and jersey / java for the REST Api.
### Prerequisites
You will need
* java runtime environment (I used java version "1.8.0_102")
* mysql server (I used mysql version 5.6.27)

### Installing
#### To set up the database
* Create user / password in the mysql database and give it all the permissions.
* The user and password I have used are in the LocalDatabaseConnection.properties file. Please replace these with the user and password you have created.
* On either the command line or through a tool like MySQL Workbench connect to mysql server using your user.
* Run the sql script, UserLoyalty-InitialSetup.sql, included at the base of project folder. This should set up the tables and stored procedures required to run the service.

#### To set up the code
* The project uses maven, to maintain external dependencies.
* Once you have cloned the repository, open the project in IDE of your choice.
* Using IntelliJ as an example, choose File -> New -> Project from Existing Sources and navigate to the cloned folder.
* Choose this and go through the entire dialog.
* Once you see the project folder in the Project Explorer, right click on the project folder and select Open Module Settings. This opens a dialog box.
* Select Artifacts -> + -> Web Application : exploded -> From Modules -> userloyalty.
* Select Artifacts -> + -> Web Application : archive -> For 'userloyalty war : exploded'.
* Apply your changes and exit Module Settings dialog.
* To create your run configuration, Select Run -> Edit Configurations… -> “+” -> Tomcat Server -> Local. Name it whatever you prefer.
* Choose the Deployment tab, clicking “+”, and choosing Artifact… Select the userloyalty artifact listed then apply and save your changes.
* You can launch the service by clicking the Play button.

## What It Does Right Now
### Operations supported
#### Creating a User
* This is a POST request. It accepts a JSON Request body with parameters as shown below
* curl -X POST \
    http://localhost:8080/1.0/user/register/ \
    -H 'content-type: application/json' \
    -d '{
  	"email":"<email address: optional>"
  	,"firstName":"<first name : required>"
  	,"lastName":"<last name : required>"
  }'

```
Example Call : curl -X POST \
                 http://localhost:8080/1.0/user/register/ \
                 -H 'content-type: application/json' \
                 -d '{
               		"email":"woof@gmail.com"
               		,"firstName":"Bones"
               		,"lastName":"Co"
               	}'
```

#### Add and deduct points from a user by creating a Transfer
* This is a POST request. It accepts a JSON Request body with parameters as shown below
* curl -X POST \
    http://localhost:8080/1.0/transfer/ \
    -H 'content-type: application/json' \
    -d '{
  	"userId":"<user id : required>"
  	,"amount":<an integer value : required>
  }'
```
Example Call : curl -X POST \
  				http://localhost:8080/1.0/transfer/ \
  				-H 'content-type: application/json' \
  				-d '{
					"userId":"1"
					,"amount":-30
				}'
```

#### List all Transfers for a user
* This is a GET request. It accepts a user id parameter in the url as shown below
* http://localhost:8080/1.0/transfer/user?id=<user id : required>
```
Example Call : http://localhost:8080/1.0/transfer/user?id=1
```