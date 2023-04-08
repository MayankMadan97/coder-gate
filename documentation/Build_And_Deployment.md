# Build and Deployment


## Building the Application

Once you have installed all the dependencies, you can proceed with building the application.

### **Backend Application**  
For the backend Spring Boot application, navigate to the project directory and run the following command to build the application:  

```java
mvn clean package
```

Or  

To build the backend Spring Boot application in your CI/CD pipeline, you can use the following steps:

* Create a new job in your GitLab CI/CD pipeline and define it as a ```build``` stage.
* Choose an appropriate Docker image to run the job. In this case, we can use the ```maven:latest``` image to build the Spring Boot application.
* In the script section of the job, navigate to the project directory using the ```cd``` command.
* Use the ```mvn clean``` package command to build the application.
* Define the dependencies of the job, which should include the previous stages of the pipeline (```code_quality```, ```test```, and ```build-frontend``` in this case).
* Define the artifacts section to specify which files should be stored as pipeline artifacts. In this case, we want to store the target folder of the Spring Boot project
* Finally, specify the runner tags that should be used to execute the job, in this case ```Group19Runner```.

These steps can be used to build the backend Spring Boot application in your CI/CD pipeline and store the build artifacts as pipeline artifacts.  

You will be able to see 
```java
coder-gate-0.0.1-SNAPSHOT.jar
``` 
in this path:
```bash
"/home/gitlab-runner/builds/applicationPath/target"
```

### **Frontend Application**  
For the frontend Angular application, navigate to the project directory and run the following commands to install the required dependencies:
```bash
npm install
```
Then, run the following command to build the application for production:

```bash
ng build --configuration=production
```
Or

To build the frontend Angular application in your CI/CD pipeline, you can use the following steps:

* Create a new job in your GitLab CI/CD pipeline and define it as a ```build``` stage.
* Choose an appropriate Docker image to run the job. In this case, we can use the ```node:latest``` image to build the Angular application.
* In the script section of the job, navigate to the project directory where the Angular application is located using the ```cd``` command.
* Use the ```npm install``` command to install the required dependencies for the Angular application.
* Use the ```ng build --configuration=production``` command to build the production version of the Angular application.
* Define the artifacts section to specify which files should be stored as pipeline artifacts. In this case, we want to store the dist folder of the Angular project.
* Finally, specify the runner tags that should be used to execute the job, in this case ```Group19Runner```.

These steps can be used to build the frontend Angular application in your CI/CD pipeline and store the build artifacts as pipeline artifacts.

You can see your ```dist``` folder in this path 
```bash
"/home/gitlab-runner/builds/applicationPath/frontend-application/dist"
```

## Deploying The Application

### **Deploying Backend**  
The following are the steps to deploy Spring Boot Application using Docker and CI/CD pipeline. Once docker is installed in virtual machine:  

**Step 1:**  Creating a Dockerfile
```dockerfile
FROM openjdk:11-jre-slim

WORKDIR /app

COPY coder-gate/target/coder-gate-0.0.1-SNAPSHOT.jar /app/coder-gate-0.0.1-SNAPSHOT.jar

EXPOSE 8080

CMD ["java", "-jar", "coder-gate-0.0.1-SNAPSHOT.jar"]
```

**Explanation:**

```FROM``` - specifies the base image for the Docker image we are building.  
```WORKDIR``` - sets the working directory for the Docker image.  
```COPY``` - copies the application jar file to the Docker image.  
```EXPOSE``` - exposes port 8080 for the Docker image.  
```CMD``` - specifies the command to run when the Docker container starts.

**Step 2:** Creating a CI/CD Pipeline

The next step is to create a CI/CD pipeline that will automate the deployment process.

* Set the Docker image to use for running the job to the latest version of Docker.
```yaml
image: docker:latest
```
* Specify the Docker service to use for running the job as ```docker:dind```, which allows the job to run Docker commands within a Docker container.
* Set the commands to be executed by the job.
* Change the permission of the SSH key file to ensure that only the owner can read, write, or execute the file.
* Connect to the server using SSH, and stops the running Docker container if it exists. The ```StrictHostKeyChecking``` option is set to no to disable host key checking, which is useful when connecting to a new host.
```dockerfile
docker stop container-name || true
```
* Remove the Docker container if it exists. The ```StrictHostKeyChecking``` option is set to no to disable host key checking.
```dockerfile
docker rm container-name
```
* Copy the built JAR file of the application to the server using SCP (Secure Copy). The ```StrictHostKeyChecking``` option is set to no to disable host key checking.
* Build a Docker image for the backend application on the server. It changes to the directory containing the Dockerfile, builds the image, and tags it as ```coder-gate-backend:latest```.
```dockerfile
docker build -t coder-gate-backend:latest
```
* Run the Docker container for the backend application on the server. It starts the container in detached mode (-d option), names it as coder-gate-backend, maps ```port 8080``` of the container to ```port 8080``` of the host (```-p``` option), and uses the ```coder-gate-backend``` image that was built earlier.
```dockerfile
docker run -d --name coder-gate-backend -p 8080:8080 coder-gate-backend
```
* Specify the runner that should execute the job as a runner tagged as ```Group19Runner```.

Now this will create a docker container with your jar file from application, now you check the container is running by:

```dockerfile
docker ps -a
```

If you go to your VM host with respective port you can see your backend code running.

You can check by creating a get or post request using applications such as Postman.

```NOTE: The port number must be same in application.properties, docker file AND docker run command```

### **Deploying Frontend**  
Once we build the front-end, we will get a ```dist``` file in our project's location. These are static files that needs to be hosted in the server. We implement it using Apache 2. For that we need to modify the configuration file that will be in your vm available at:
```bash
 "/etc/apache2/sites-available/000-default.conf"
 ```
Add the following lines to the config file:
```apache
<VirtualHost *:80>
    ServerAdmin webmaster@localhost
    DocumentRoot /var/www/html/my-angular-app/dist/coder-gate-ui/
    <Directory /var/www/html/my-angular-app/dist/coder-gate-ui/>
        Options Indexes FollowSymLinks
        AllowOverride All
        Require all granted
        RewriteEngine On
        RewriteBase /
        RewriteRule ^index\.html$ - [L]
        RewriteCond %{REQUEST_FILENAME} !-f
        RewriteCond %{REQUEST_FILENAME} !-d
        RewriteRule . /index.html [L]
    </Directory>
    ErrorLog ${APACHE_LOG_DIR}/error.log
    CustomLog ${APACHE_LOG_DIR}/access.log combined
</VirtualHost>
```

The deploy-frontend script is responsible for deploying a frontend application to a remote server. The script uses the alpine image to execute the deployment process and requires the following environment variables to be set:

* Set the necessary permissions for the SSH key with the command you did for backend application
* Remove the default ```index.html``` file from the remote server with the command  
```bash 
rm -f /var/www/html/index.html
```
* Copy the distribution files of the frontend application to the remote server with the command 
```
scp {build file location}
```
 to  
 ```bash
 /var/www/html/my-angular-app/
 ```
* Restart the apache2 service on the remote server with the command:
```bash
sudo -S sh -c 'sudo service apache2 restart'" < <(echo ${SUDO_PASSWORD}).
```
* Note you might need sudo access to restart
* Note set this file path  ```"/var/www/html/my-angular-app/"``` with permission  to 755 in your VM.

 

To check whether the frontend application has been successfully deployed, you can navigate to the URL of the of host server.

By following the steps outlined in this document, you should be able to build and deploy your web application using GitLab CI/CD.  

[**Go back to README.md**](../README.md)