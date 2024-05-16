plugins {
    java
    id("org.springframework.boot") version "3.2.4"
    id("io.spring.dependency-management") version "1.1.4"
}

group = "com.crocostaud"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-data-rest")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-oauth2-resource-server")
    compileOnly("org.projectlombok:lombok")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")

    implementation("org.postgresql:postgresql:42.7.3")
    implementation("mysql:mysql-connector-java:8.0.33")

    implementation("org.springframework.boot:spring-boot-starter-security")

//    implementation("org.springframework.security:spring-security-core:6.2.1")
//    implementation("org.springframework.security:spring-security-web:6.2.1")
//    implementation("org.springframework.security:spring-security-config:6.2.1")


    implementation("io.jsonwebtoken:jjwt:0.12.5")
    // https://mvnrepository.com/artifact/com.auth0/java-jwt
    implementation("com.auth0:java-jwt:4.4.0")


}

tasks.withType<Test> {
    useJUnitPlatform()
}
