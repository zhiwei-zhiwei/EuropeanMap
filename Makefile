run: compile

	java -classpath .:lib/* launch
compile: compileData compileBackEnd compileFrontEnd

test: TestSuite.class
	java -jar junit5.jar -cp .:fastjson-1.2.68.jar -scan-classpath	

TestSuite.class: TestSuite.java
	javac -classpath .:fastjson-1.2.68.jar:junit5.jar TestSuite.java

clean:
	$(RM) *.class
	$(RM) *~

compileData: WebReading.class City.class TwoCitiesUnit.class DataReading.class

compileData2: WebReader.class Data.class EMapCity.class

Data.class: Data.java

	javac Data.java

EMapCity.class: EMapCity.java

	javac EMapCity.java

data2: WebReader.class

	java -classpath .:jsoup-1.11.3.jar WebReader

WebReading.class: WebReading.java  

	javac -classpath .:lib/* WebReading.java

City.class: City.java

	javac City.java

TwoCitiesUnit.class: TwoCitiesUnit.java

	javac TwoCitiesUnit.java
DataReading.class: DataReading.java

	javac DataReading.java

compileBackEnd: GraphADT.class CS400Graph.class RoadSystem.class

compileFrontEnd: launch.class

launch.class: launch.java

	javac launch.java

GraphADT.class: GraphADT.java

	javac GraphADT.java 

CS400Graph.class: CS400Graph.java

	javac CS400Graph.java 

RoadSystem.class: RoadSystem.java

	javac RoadSystem.java

WebReader.class: WebReader.java

	javac -classpath .:jsoup-1.11.3.jar WebReader.java
