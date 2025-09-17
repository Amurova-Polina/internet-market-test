pipeline {
    agent { label 'honor' }

    tools {
        maven 'maven-3.9.11'
        jdk 'jdk-17'
    }

    parameters {
        choice(
            name: 'GIT_BRANCH',
            choices: ['dev', 'master'],
            description: 'Ветка для сборки'
        )
        choice(
            name: 'BROWSER',
            choices: ['chrome', 'firefox'],
            description: 'Браузер для запуска тестов'
        )
    }

    stages {
        stage('Cleanup') {
            steps {
                cleanWs()
            }
        }

        stage('Checkout') {
            steps {
                git branch: "${params.GIT_BRANCH}",
                    url: 'git@github.com:Amurova-Polina/internet-market-test.git'
            }
        }

        stage('Build & Test') {
            steps {
                sh "mvn clean compile test -Dbrowser=${params.BROWSER}"
            }
        }

        stage('Allure Report') {
            steps {
                allure([
                    results: [[path: 'target/allure-results']]
                ])
            }
        }
    }

post {
    always {
        junit 'target/surefire-reports/*.xml'
    }
    success {
        echo '===========Победа-победа!==========='
    }
    failure {
        echo '===========Ну ничего страшного. Пау-пау-пау-пау==========='
    }
}
}
