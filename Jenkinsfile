pipeline {
    agent any

    triggers {
        githubPush()   
    }

    stages {

        stage('Cloner le projet') { steps { git url: 'https://github.com/hind0074/Gestion-fil-d-attente-.git', branch: 'develop' } }

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

    post {
        success {
            echo 'Build et analyse terminés avec succès !'
        }
        failure {
            echo 'Échec du build ou des tests.'
        }
    }
}
