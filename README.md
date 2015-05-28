# LevelsBeyondAPI
 build project using mvn clean install
 
 Add WAR using Tomcat Manager or copy to <TOMCAT ROOT>/webapps folder
 
Add a note:  
POST /LevelsBeyondAPI/api/notes HTTP/1.1
Host: localhost:8089
Content-Type: application/json
Cache-Control: no-cache

{"body":"A Note test 3"}

I had issues with posting JSON using curl on cygwin.  Postman REST Client and SOAP UI worked fine

Get a note by id
$ curl -i -H "Accept: application/json" -X GET http://<host>:<port>/LevelsBeyondAPI/api/notes/1

Get all notes
$ curl -i -H "Accept: application/json" -X GET http://<host>:<port>/LevelsBeyondAPI/api/notes

Query for a note by text
$ curl -i -H "Accept: application/json" -X GET http://<host>:<post>/LevelsBeyondAPI/api/notes?query=test
