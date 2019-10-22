pipeline {
    agent any
    tools { 
        maven 'M2_Home' 
        jdk 'Java_Home' 
    }
     stages {             
         stage ('Initialize') {
            steps {
                    echo "PATH = ${PATH}"
                    echo "M2_HOME = ${M2_HOME}" 
		    echo "######### Initialize Stage Done #########"					
            }
        }
         
        stage ('Checkout Stage') {
            steps {
                checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[url: 'https://github.com/sahan89/SimpleGameApplication.git']]])
                echo "######### Checkout Stage Done #########"
            }
        }
		
	stage ('Build Stage') {
	    steps {
		sh 'mvn clean install -DskipTests'
                echo "######### Build Stage Done #########"
            }
		}
		
	stage ('Testing Stage') {
            steps {
                sh 'mvn test'
                echo "######### Testing Stage Done #########"
            }
        }
    }
}
