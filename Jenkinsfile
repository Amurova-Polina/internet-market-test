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
            description: 'Choose project branch'
        )
        choice(
            name: 'BROWSER',
            choices: ['chrome', 'firefox'],
            description: 'Choose browser'
        )
        string(
             name: 'TAG',
             defaultValue: '',
             description: 'Tags of the tests to run (leave it empty for everyone)'
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
                    url: 'https://github.com/Amurova-Polina/internet-market-test.git'
            }
        }

        stage('Build & Test') {
            steps {
                script {
                    def tagOption = params.TAG?.trim() ? "-Dgroups=\"${params.TAG}\"" : ""
                    withMaven(maven: 'maven-3.9.11') {
                        sh "mvn clean test -Dbrowser=${params.BROWSER} ${tagOption}"
                    }
                }
            }
        }
    }

    post {
        always {
            allure([
                results: [[path: 'target/allure-results']]
            ])
            junit 'target/surefire-reports/*.xml'
        }
        success {
            echo '=========== Success! ==========='
        }
        failure {
            echo '=========== Failure :( ==========='
        }
    }
}
