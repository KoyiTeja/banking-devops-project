pipeline {

    agent any

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

    }

    post {

        success {
            echo 'Banking application CI build successful'
        }

        failure {
            echo 'Banking application CI build failed'
        }

    }
}