# eclipselink-weaving

## A Spring boot based REST app with embedded H2 DB.

### Quick Facts:
- Maven Project - Supports standard lifecylce phases.
- Project uses spring-data-jpa with tomcat-jdbc for connection pooling and eclipselink as JPA provider
- Project uses embedded H2 DB and tables/data are created/loaded from data.sql file.
- Standard maven phases apply. 
- Project uses java17

### Build & Deployment
```declarative
# compile 
mvn clean compile

# local development
mvn spring-boot:run

# create jar
mvn package

# Run from jar 
java -jar target/eclipselink-weaving-0.0.1-SNAPSHOT.jar
```

- Application should be accessible at localhost:8080 
- You can access h2-console at localhost:8080/h2-console

### Running Tests
```declarative
mvn test
```


