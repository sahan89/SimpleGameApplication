pipeline {
    agent any
    tools { 
        maven 'M2_Home' 
        jdk 'Java_Home' 
    }
	
    environment {
    	registry = "sahan89/test_img"
    	registryCredential = 'dockerhub'
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
                echo "######### Testing Stage Done #########"
            }
        }
	     
//	stage('Building image Stage') {
 //     		steps{
//	      	sh 'docker version'
//        	script {          
          //docker.build registry + ":$BUILD_NUMBER"          
//	  echo "######### Building image Stage Done #########"	
 //       }
 //     }
 //   }
	     
	 stage ('Deployment Stage') {
            steps {
                 echo "######### Deployment Stage Done #########"
		 //sh 'docker cp jenkins:/var/jenkins_home/workspace/SimpleGamePipeline/target/simple-game-0.0.1-SNAPSHOT.war tomcat:/opt/tomcat/webapps/'
		 echo "xxxxxxxxxxxxx"
		 echo (pwd)
                 //sh 'mv /var/jenkins_home/workspace/SimpleGamePipeline/target/simple-game-0.0.1-SNAPSHOT.war /opt/tomcat/webapps/'
                 //bat 'cp /var/lib/jenkins/.m2/repository/HelloWorld/helloworld/1.0-SNAPSHOT/helloworld-1.0-SNAPSHOT.war /opt/apache-tomcat-9.0.21/webapps/helloworld.war'
                 //bat 'cp C:\\Windows\\System32\\config\\systemprofile\\.jenkins\\workspace\\HelloWorldPipeline\\target\\helloworld-0.0.1.war /opt/apache-tomcat-9.0.21/webapps/helloworld.war'
                 //bat 'target\\helloworld-0.0.1.war C:\\Program Files\\Apache\\Tomcat 9.0\\webapps\\'
            }
        }
    }
}
