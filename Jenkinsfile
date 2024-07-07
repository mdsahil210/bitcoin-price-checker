pipeline {
    agent any

    tools {
        maven 'Maven_3_9_8'
    }

    stages {
        stage('Build Maven') {
            steps {
                checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/mdsahil210/bitcoin-price-checker/']])
                sh 'cd backend; mvn clean install'
            }
        }
        stage("Build Docker Image") {
            steps {
                script {
                    sh 'cd backend; docker build -t mdsahil210/bitcoin-price-checker-backend .'
                    sh 'cd frontend; docker build -t mdsahil210/bitcoin-price-checker-frontend . '
                }
            }
        }
        stage("Push Image to Dockerhub") {
            steps {
                script {
                    withCredentials([string(credentialsId: 'dockerhub-pwd', variable: 'dockerhubpwd')]) {
                        sh 'docker login -u mdsahil210 -p ${dockerhubpwd}'
                    }
                    
                    sh 'docker push mdsahil210/bitcoin-price-checker-backend'
                    sh 'docker push mdsahil210/bitcoin-price-checker-frontend'
                }
            }
        }
    }
}
