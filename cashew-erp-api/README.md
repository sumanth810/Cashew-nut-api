# Cashew ERP System #
Cashew ERP is a RESTful API application built with Spring Boot for managing batch processing data and secure user authentication. This application uses an in-memory H2 database for easy setup and testing.

# Features:

(I) Secure authentication using JWT. Passwords hashed using BCrypt.

(II) Create and retrieve batch processing data entries. Validations to ensure correct data input.

(III) Pre-configured admin user.

(IV) Cross-Origin Resource Sharing (CORS) support for frontend integration.

(V) H2 Database - In-memory mode for quick testing and easy setup. Supports optional file-based persistence for storing data.


# Setup Instructions
1. Clone the repository.
2. Create a MySQL database named cashew_db.
3. Update application.properties with your database credentials.
4. Generate a JWT secret key using SecretKeyGenerator.java and add it to application.properties.
5. Run the application:

        # bash
        Copy code
        mvn spring-boot:run
    or use the VS Code to run.
6. Access the API at http://localhost:8080.

Testing
    
    1. Use Postman or similar tools to test endpoints.
    
    2. Default Admin Credentials:
        
        Email: admin@cashew.com
        
        Password: admin123

# H2 Database Console
Accessing the Console

        URL: http://localhost:8080/h2-console
        
        JDBC URL: jdbc:h2:mem:koyya (or your custom file path if using file-based mode)

        Username: sa
        
        Password: password
        
The H2 database console provides a web-based interface for running SQL queries and inspecting database tables.

# Tech Stack
1. Backend: Java 11, Spring Boot
2. Database: H2 (In-memory or File-based)
3. ORM: Hibernate (Spring Data JPA)
4. Security: Spring Security, JWT
5. Build Tool: Maven
6. Logging: SLF4J with LogbackL
