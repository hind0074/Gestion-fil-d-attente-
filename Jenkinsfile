pipeline {
    agent any

    triggers {
        githubPush()   
    }

    stages {

        stage('Cloner le projet') { steps { git url: 'https://github.com/hind0074/Gestion-fil-d-attente-.git', branch: 'develop' } }

        stage('Compiler le projet') {
            steps {
                bat 'mvn clean compile'
            }
        }

        stage('Tests unitaires') {
            steps {
                bat 'mvn test'
            }
        }

        stage('Générer le package') {
            steps {
                bat 'mvn package'
            }
        }

        stage('Analyse SonarQube') {
            steps {
                withSonarQubeEnv('SonarQube') {
                    bat 'mvn sonar:sonar'
                }
            }
        }
        
    }

    post {
        success {
            echo 'Build et analyse terminés avec succès !'
        }
        failure {
            echo 'Échec du build ou des tests.'
        }
    }
}
