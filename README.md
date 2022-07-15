# Spring Boot with MongoDB Assignment project
A Java / MongoDB / Spring Boot application 
to show basic REST API functionalities, proper design patterns and CREATE / READ operations on a resource.

# Requirements
For building and running the application you'll need:
* [JDK 1.8](https://www.oracle.com/java/technologies/downloads/#java8)
* [Maven 3](https://maven.apache.org/)


# Getting started
There are several ways to run a Spring Boot application on your local machine. One way is to execute the main method in 
the com.example.demo.DemoApplication class from your IDE.  

Next, open up [Postman](https://www.postman.com/downloads/) and make a POST request to:  

Url:  
`http://localhost:8080/api/v1/students`    

Body:  
`
{
"name": "John",
"age": 60,
"gpa": 4.0
}
`

After adding a new student, try a GET request:  

Url:  
`http://localhost:8080/api/v1/students`  
or  
`http://localhost:8080/api/v1/students/{id}`

# Useful resources
A popular design pattern widely adopted by the Spring Boot community is the use of a Controller-Service-Repository.
This [article](https://tom-collings.medium.com/controller-service-repository-16e29a4684e5) was useful for my understanding of Spring Boot which provided not only the how, but also the why.


