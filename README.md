# BitBucket

BitBucket creates Vert.x REST api that uses basic operations

##Usage

#returns hello:world
http://localhost:8080/hello/world

#creates a message
http://localhost:8080/create/message
with a body of the form 
{
    "message": "a value"
}

#returns all the messages for a specific uid
http://localhost:8080/messages

#Test

You can test the functionality of this project via postman
using the appropriate operation (get, post) with the correct classpath

#Sidenote

"16-char long" will be used in future to validate the request of each client 
