# Chutes and Ladders(Standalone application)
This application is standalone java application simulating Chutes and Ladders game.

### Assumptions
- This simulation doesn't have any GUI component.
- Only 2-4 players can play.
- Spinner is going to spin and give the value from 1-6.
- Chutes and Ladder configuration has to be given in the form of a json file referenced via **location.chutesandladder** property inside the application*.properties file in resources directory. 
A sample and explanation is given below:
```json
[
  {
    "configId": 1,
    "configuration": [
      {
        "id": 1,
        "type": "ladder",
        "startSquare": 1,
        "endSquare": 38
      },
      {
        "id": 2,
        "type": "chute",
        "startSquare": 99,
        "endSquare": 10
      }
    ]
  }
]
``` 

   *_It is essentially an array of multiple configurations. At the start of the game application picks up the configuration randomly out of all configurations that remains same during that game for all players. If you are not sure, please keep only one configuration as kept currently in the project.
Every configuration has id and within it is an array of construct depicting moveType i.e. either a chute or ladder. Below sample shows a chute and a ladder. Chute has startSquare greater than endsquare and for ladder it is opposite. Id for every chute and ladder should be unique. Type specifies whether it's a chute or ladder._*
-  Number of squares on the board can be changed by a property **square.total** in the application*.properties file. 
### Prerequisites
To build and compile maven, Java 8

### How to setup the project in IDE
``` git
git clone https://github.com/vipulbhale/standalone-chutes-and-ladder.git
```
 Import it as a maven project in Intellij or eclipse
 
### How to run tests
```bash
git clone https://github.com/vipulbhale/standalone-chutes-and-ladder.git
cd standalone-chutes-and-ladder
mvn clean verify

#in case sonar is there
mvn -Dsonar.host.url=<sonar.url> clean verify sonar:sonar package
```
Coverage report would be in target directory.

### How to run it 

- #### on mac or linux 
```bash
git clone https://github.com/vipulbhale/standalone-chutes-and-ladder.git
cd standalone-chutes-and-ladder  # parallel to pom.xml
mvn clean install
cd ~/.m2/repository/com/candidate/priceline/standalone-chutes-and-ladder/1.0-SNAPSHOT/
unzip standalone-chutes-and-ladder-1.0-SNAPSHOT-distribution.zip
cd standalone-chutes-and-ladder-1.0-SNAPSHOT/unix/bin
```
./run.sh args #**args are player names(2 to 4) separated by space** for e.g.

```bash
./run.sh JOHN JOE DAVID BOB
```
  To run it for specific environments(dev,qa, prod , etc. except **test**), please make sure application.**environmentName**.properties file exists in src/main/resources directory. Application is already bundled with properties files for dev, qa and prod. If environment is not given as system property default file i.e. application.properties will be picked up.
  
   Example of running it for a specific environment:

```bash
git clone https://github.com/vipulbhale/standalone-chutes-and-ladder.git
cd standalone-chutes-and-ladder  # parallel to pom.xml
mvn clean install
cd ~/.m2/repository/com/candidate/priceline/standalone-chutes-and-ladder/1.0-SNAPSHOT/
unzip standalone-chutes-and-ladder-1.0-SNAPSHOT-distribution.zip
cd standalone-chutes-and-ladder-1.0-SNAPSHOT/unix
java -Denvironment=<environmentname> -cp ./lib -jar ./standalone-chutesladders-1.0-SNAPSHOT.jar JOHN JOE PETER DAVID
```

- #### on windows 
```bash
git clone https://github.com/vipulbhale/standalone-chutes-and-ladder.git
cd standalone-chutes-and-ladder  # parallel to pom.xml
mvn clean package
cd target/
unzip standalone-chutes-and-ladder-1.0-SNAPSHOT-distribution.zip
cd standalone-chutes-and-ladder-1.0-SNAPSHOT/windows
```
java -cp lib -jar standalone-chutesladders-1.0-SNAPSHOT.jar args , **args are player names(2 to 4) separated by space** for e.g.

```bash
java -cp lib -jar standalone-chutesladders-1.0-SNAPSHOT.jar JOHN JOE PETER DAVID
```

  To run it for specific environments(dev,qa, prod , etc. except **test**), please make sure application.**environmentName**.properties file exists in src/main/resources directory. Application is already bundled with properties files for dev, qa and prod. If environment is not given as system property default file i.e. application.properties will be picked up.
  
   Example of running it for a specific environment:

```bash
git clone https://github.com/vipulbhale/standalone-chutes-and-ladder.git
cd standalone-chutes-and-ladder  # parallel to pom.xml
mvn clean package
cd target/
unzip standalone-chutes-and-ladder-1.0-SNAPSHOT-distribution.zip
cd standalone-chutes-and-ladder-1.0-SNAPSHOT/windows
java -Denvironment=<environmentname> -cp lib -jar standalone-chutesladders-1.0-SNAPSHOT.jar TOM DICK HARRY
```

### TODO
- Add tests for random pick up of chutes and ladder configuration from multiple configuration.
- There is an ignored test ApplicationProperties1Test.test_b_should_return_101_for_squares_total_getProperty because of issues in picking up System property even using custom rules when we run it. 
- Create a windows batch file for this app.
- Add Jenkinsfile to make it easy to deploy on Jenkins.
