pipeline {

    agent any

    options {
        skipDefaultCheckout(true)
    }

    tools {
        maven 'Maven-3.9.16'
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                dir('banking-app') {
                    sh 'mvn clean package -DskipTests'
                }
            }
        }

        stage('Test') {
            steps {
                dir('banking-app') {
                    sh 'mvn test'
                }
            }
        }

        stage('Docker Build') {
            steps {
                sh 'docker build -t banking-app:1.0 -f docker/Dockerfile .'
            }
        }

        stage('Docker Login') {
            steps {
                withCredentials([
                    usernamePassword(
                        credentialsId: 'dockerhub-credentials',
                        usernameVariable: 'DOCKER_USERNAME',
                        passwordVariable: 'DOCKER_PASSWORD'
                    )
                ]) {
                    sh '''
                        echo "$DOCKER_PASSWORD" | docker login -u "$DOCKER_USERNAME" --password-stdin
                    '''
                }
            }
        }

        stage('Docker Tag') {
            steps {
                sh 'docker tag banking-app:1.0 tejakoyi/banking-app:1.0'
            }
        }

        stage('Docker Push') {
            steps {
                sh 'docker push tejakoyi/banking-app:1.0'
            }
        }

    }

    post {

        success {
            echo 'Banking application CI/CD build and Docker push successful'
        }

        failure {
            echo 'Banking application CI/CD build or Docker push failed'
        }

    }
}