pipeline {
    agent any

    tools {
        maven 'Maven_3.8.12' // configuré dans Jenkins
    }

    stages {
        stage('Cloner le repo') {
            steps {
                git 'https://github.com/hind0074/Gestion-fil-d-attente-.git'
            }
        }

        stage('Compiler le projet') {
            steps {
                sh 'mvn clean compile'
            }
        }

        stage('Tests unitaires') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Générer le package') {
            steps {
                sh 'mvn package'
            }
        }

        stage('Analyse SonarQube') {
            steps {
                withSonarQubeEnv('SonarQube') {
                    sh 'mvn sonar:sonar'
                }
            }
        }
    }
}
