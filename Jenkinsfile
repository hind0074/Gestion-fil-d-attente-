pipeline {
    agent any

    triggers {
        githubPush()
    }

    tools {
        maven 'Maven_3.8.12'  // doit correspondre au nom configuré dans Jenkins
    }

    stages {
        stage('Cloner le repo') {
            steps {
                git url: 'https://github.com/hind0074/Gestion-fil-d-attente-.git', branch: 'develop'
            }
        }

        stage('Compiler le projet') {
            steps {
                bat 'mvn clean compile'
            }
        }

       stage('Tests unitaires') {
    steps {
        bat 'mvn test jacoco:report'
    }
}


        stage('Générer le package') {
            steps {
                bat 'mvn package'
            }
        }

        stage('Analyse SonarQube') { steps { withSonarQubeEnv('SonarQubeServer') { bat 'mvn sonar:sonar' } } }
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
