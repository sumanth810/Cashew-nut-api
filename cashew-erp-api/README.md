# Cashew ERP API #

This repository contains code, configuration and other assets of Cashew Nut Processing Stages.


# Cashew ERP System #

Cashew ERP is a RESTful API for managing cashew processing data with secure authentication and batch data management.

# Features:

(I) Secure authentication using JWT.

(II) Batch data management for cashew intake and output tracking.

(III) Pre-configured admin user.

(IV) CORS support for frontend integration.


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
# Tech Stack
1. Java 11
2. Spring Boot
3. Hibernate
4. MySQL
