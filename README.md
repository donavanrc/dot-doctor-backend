# .Doctor - Backend (Java/Spring Framework)

###  Working in progress...

The <strong>".Doctor"</strong> project was designed with an essentially <strong>educational purpose</strong>. This application simulates an online medical appointment scheduling system, where doctors and patients can register. Users can easily schedule appointments with healthcare professionals from different specialties, such as <strong>Cardiology, Dermatology, Orthopedics and Gynecology</strong>.

This repository refers to the server part, which consists of a <strong>Rest API</strong>. This interface can be consumed by a web or mobile client, providing efficient and flexible integration with the application.

### Appointment business rules

* The clinic's opening hours are <strong>Monday to Saturday</strong>, from <strong>7am to 7pm</strong>.
* Each appointment has a <strong>fixed</strong> duration of <strong>1 hour</strong>.
* Appointments must be scheduled at least <strong>30 minutes in advance</strong>.
* Scheduling appointments with <strong>inactive</strong> patients in the system is <strong>not permitted</strong>.
* appointments with <strong>inactive</strong> doctors in the system is <strong>not permitted</strong>.
* Scheduling more than one appointment on the same day for the same patient is <strong>prohibited</strong>.
* It is <strong>not permitted</strong> to schedule an appointment with a doctor who already has another appointment scheduled on the same date and time.
* Choosing a doctor is <strong>optional</strong>. In this case, the system must randomly select a doctor available on the date and time entered.
* It is <strong>mandatory</strong> to provide medical specialty information if the <strong>doctor is not specified</strong>.

### API Documentation

The application's API is fully documented through the SpringDoc library, which includes the embedded Swagger UI.

To consult the documentation, simply open your browser and access the following link: `http://localhost:8080/swagger-ui/index.html`.

### Technologies

* Java
* Spring Boot
* Spring Framework
* Spring Security
* Sprint Data JPA
* Sprint Validation
* Lombok
* MySQL
* Migration (SQL script versions)

### Design Patterns

* DTO (Data Transfer Object) Pattern
* Repository Pattern
* Dependency Injection
* Strategy

### SOLID

In this project, I apply at least three <strong>SOLID principles</strong>:

<strong>Single Responsibility Principle:</strong> Each validation class is responsible for executing only a single business rule. This promotes cohesion and facilitates maintenance, as each component has a clear and specific responsibility.

<strong>Open-Closed Principle:</strong> In AppointmentService, the class is closed to modifications, which means that changes to the existing code are not required to add new functionality. At the same time, the class is open for extension, as you can add new validators by simply creating a new validation class and implementing the IAppointmentValidator interface. This favors the extensibility of the system, maintaining the stability of the existing parts.

<strong>Dependency Inversion Principle:</strong> The AppointmentService class depends only on the IAppointmentValidator interface and not on the concrete classes that implement the business rules. This dependency inversion allows for greater flexibility, as specific implementations can be swapped without affecting the main class. Furthermore, it facilitates the application of unit tests, as it is possible to easily create mocks or test implementations for the interface.

These <strong>SOLID principles</strong> provide a more <strong>robust and flexible architecture</strong>, making it <strong>easier to maintain, extend, and test code over time</strong>.

# About

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.2.0/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.2.0/maven-plugin/reference/html/#build-image)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/3.2.0/reference/htmlsingle/index.html#using.devtools)
* [Spring Web](https://docs.spring.io/spring-boot/docs/3.2.0/reference/htmlsingle/index.html#web)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)

