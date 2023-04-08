# External Dependencies

## Installation of Dependencies

Before you can build and deploy the application, you will need to install the following dependencies on your virtual machine:  

### **Java**  
To install Java, run the following commands:  

```bash
sudo apt-get update  
sudo apt-get install openjdk-11-jdk  
```

### **Maven**  
To install maven, run the following commands:
```bash
sudo apt install maven
```
Once the command has finished executing, confirm its installation by running:
```bash
maven --v
```

### **Node.js and Angular CLI**  
To install Node.js and the Angular CLI, run the following commands:  

```bash
sudo apt-get update  
sudo apt-get install nodejs  
npm install -g @angular/cli
```

After installation, verify Node.js, npm and Angular are installed by running:

```bash
node -v  
npm -v  
ng version 
``` 

### **NVM**  
NVM is used to handle multiple versions of Node.js. To install NVM, run the following commands:  

```bash
sudo apt-get update  
sudo apt-get install build-essential libssl-dev  
curl -o- https://raw.githubusercontent.com/nvm-sh/nvm/v0.39.1/install.sh | bash  
```

After installation, verify that NVM is installed by running:  

```bash
nvm --version
```

### **GitLab Runner**  
To install your own GitLab Runner instance, follow the official documentation: https://docs.gitlab.com/runner/install/  
A private GitLab runner is recommended to ensure the security and reliability of your CI/CD pipeline.
By using a private runner, you can have more control over the environment and resources used for your builds.  
After installation, verify runner is installed by running:  

```bash
gitlab-runner --version  
```

### **Docker**  
To host your Spring Boot app using Docker, you will need to have Docker installed on your virtual machine.

Here are the steps to install Docker on Ubuntu:

* Install the necessary packages to allow apt to use a repository over HTTPS:

```bash
sudo apt-get install -y apt-transport-https ca-certificates curl software-properties-common
```

* Add the GPG key for the official Docker repository:

```bash
curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -
```

* Add the Docker repository to apt:  

```bash
sudo add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/ubuntu $(lsb_release -cs) stable"  
sudo apt-get update
```

* Install the latest version of Docker:  

```bash
sudo apt-get install -y docker-ce
```

* To host your Angular app , you will need to have apache installed on your VM. Install Apache by running the following command:  

```bash
sudo apt-get install apache2
```

### **Set up Variables**  
To set up variables for your repository in your CI/CD pipeline, go to your repository settings, then CI/CD, then variables. Now add the following variables:
```bash
DEPLOY_DIR – Type: Variable – Directory to store pipeline generated artifacts
DEPLOY_HOST – Type: Variable - Hostname
DEPLOY_USER – Type: Variable - Username
DEPLOY_SSH_KEY – Type: File – Use generated Private key
```
### **Set Up SSH Registration**  
In deployment stage, we need a SSH key to access the VM. To set up SSH, run the following commands on your VM:
```bash
ssh-keygen -t ecdsa -f ~/.ssh/<<key-name>>
cd .ssh
cat <<key-name>>.pub >> ~/.ssh/authorized_keys
eval "$(ssh-agent -s)"
ssh-add <<absolute_path_of_priv_key>>
cat <<key-name>>
```
Now, copy this private key and add it to your repository’s ```env``` variable for SSH key.  

[**Go back to README.md**](../README.md)