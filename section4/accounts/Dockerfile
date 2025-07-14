#Start with a base image containing Java runtime
FROM openjdk:17-jdk-slim

# Add the application's jar to the imag
COPY target/accounts-1.0.1-SNAPSHOT.jar accounts-1.0.1-SNAPSHOT.jar


# MAINTAINER instruction is deprecated in favor of using label
# MAINTAINER eazybytes.com

#Information around who maintains the image
LABEL "org.opencontainers.image.authors"="venelindavidov"
#MAINTAINER venelindavidov



# execute the application
ENTRYPOINT ["java", "-jar", "accounts-1.0.1-SNAPSHOT.jar"]







#COMMANDS DOCKER
# command for docker image -  docker build . -t venelindavidov/accounts:s4 --- build the image                       (docker build. -t venelindavidov/accounts )
# docker run -p 8080:8080 accounts ---- single container                                                   (docker run -p 8080:8080 venelindavidov/accounts:s4)
# docker run -d -p 8080:8080 accounts ---- many containers can run the same image



#Build Docker and run
# BuildPacking    docker run -d -p 8090:8090 venelindavidov/loans:s4
# Google jib      docker run -d -p 9000:9000 venelindavidov/card:s4