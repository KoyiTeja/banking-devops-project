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

    }

    post {

        success {
            echo 'Banking application CI/CD build successful'
        }

        failure {
            echo 'Banking application CI/CD build failed'
        }

    }
}