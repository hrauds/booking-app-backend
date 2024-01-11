# Frontend Installation guide

### Login into virtual machine
- Open Command Prompt
- Type `ssh user@example.com`

## Dependencies

### Install NodeJS
`sudo apt install nodejs`

### Install NPM

`sudo apt install npm`

### Install NGINX
`sudo apt install nginx`

### Install Gitlab Runner
1. Use `curl -LJO "https://gitlab-runner-downloads.s3.amazonaws.com/latest/deb/gitlab-runner_amd64.deb"`
2. after that: `sudo dpkg -i gitlab-runner_amd64.deb`


## Setup

### Firstly setup gitlab runner
- Use `gitlab-runner register`
- Enter gitlab-ci coordinator URL `https://gitlab.cs.taltech.ee/`
- Enter gitlab-ci token `#######################`
- Enter description `frontend runner`
- Enter tags `tag1,tag2`
- Enter executor `shell`

### Add .gitlab-ci.yml file to your frontend repository
Get file content from here: https://pastebin.com/bsZrWjK1

### Configure NGINX server

- Create symlink from gitlab-runner frontend code to /var/www
    - `ln -s /home/gitlab-runner/front-deployment/ /var/www/front-deployment`
- Edit NGINX default config file
    - `cd /etc/nginx/sites-available`
    - `sudo nano default`
    - Change root location to `root /var/www/front-deployment;`
    - Change server name to `server_name pokecrypt.tech www.pokecrypt.tech;`
    - Add following parameters:
      -
      `location /api {
      proxy_pass http://localhost:8080/;
      }`
        -
      `location /api/monitor {
      proxy_pass http://localhost:3001/;
      rewrite  ^/api/monitor/(.*)  /$1 break;
      proxy_set_header   Host $host;
      }`
    - Change "location /" to:
        - `
          location / {
          index index.html index.htm;
          if (!-e $request_filename){
          rewrite ^(.*)$ /index.html break;
          }
          }
          `
        - Config file should look like this:
            - https://pastebin.com/8duAticc

### Install https
- Use `sudo apt-get install python3-certbot-nginx`
- Use `sudo certbot –-nginx`
    - Enter your email address.
    - Enter domain name: `pokecrypt.tech`
    - Redirect traffic to HTTPS, type `2`
- Edit NGINX default config file again
    - `cd /etc/nginx/sites-available`
    - `sudo nano default`
    - Make changes according to this template: https://pastebin.com/dPj3KS2e

# Backend Installlation guide

## Quickstart
- Install dependencies
- Git clone
- Run `mvn clean install && sudo docker-compose up -d`

## Dependencies

### Setup gitlab runner (optional)
- Use `gitlab-runner register`
- Enter gitlab-ci coordinator URL `https://gitlab.cs.taltech.ee/`
- Enter gitlab-ci token `#######################`
- Enter description `backend runner`
- Enter tags `tag1,tag2`
- Enter executor `shell`

### Install docker
- Update system `sudo apt-get update`
- Install docker `sudo apt install docker.io`
- Enable service `sudo systemctl enable --now docker`
- Confirm installation `docker --version`

### Install docker-compose
- Download `sudo curl -L "https://github.com/docker/compose/releases/download/1.29.2/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose`
- Make runnable `sudo chmod +x /usr/local/bin/docker-compose`
- Confirm installation `docker-compose --version`

### Install java
- `sudo apt install openjdk-17-jre-headless openjdk-17-jdk-headless`

### Running backend manually

#### Build
- Clone project `git clone https://gitlab.cs.taltech.ee/hrauds/iti0302-2023-backend.git`
- `cd [project folder]/`
- Run tests (optional) `mvn test`
- Build JAR `mvn clean package`

#### Run
- Run `sudo docker-compose up -d`
- Verify running `sudo docker-compose ps`

#### Stop
- Stop `sudo docker-compose stop`
- Stop only application, keep monitoring active `sudo docker-compose stop pokemon`

#### Remove
- Remove all unused docker images from filesystem `sudo docker system prune -a`
- `rm -rf [project folder]/`

## Monitoring (Grafana) setup
- Go to `http://localhost:3001`
- Default login/password is admin/admin

#### Adding data source
- Go to Donfiguration -> Data sources -> Add data source -> Prometheus
- URL is `prometheus:9090`
- Keep other settings default
- Save & test

#### Adding a dashboard
- Go to Dashboards -> Manage -> Import -> Import via [this](https://pastebin.com/3Bh1AMV6) json
- Or create a dashboard yourself
