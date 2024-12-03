# Winter Supplement Test Rules Engine
Implement the project and tests according to the requirements.
For now, if the input JSON is invalid (e.g., missing fields, extra keys, or values exceeding the allowed range such as numberOfChildren being out of the int range), 
output's numeric fields will be set to 0.0, and boolean be set to false, string be set to "invalid-input".

## How to Run on Local Machine
### 0. Clone Repo
```bash
   git clone https://github.com/bi9potato/supplement-rules-engine.git
   cd supplement-rules-engine
```

### 1. Set up development environment
   1. **Java** 8 or higher (tested using Java 1.8)
   2. **Maven** 3.x (tested using Maven 3.9.9)

### 2. Update config properties  
**Note**:  Please **repackage** and **restart** the app when you update properties.
- In `src/main/resources/config.properties`
   1. Update `topicId`  to the lasted **`MQTT topic ID`** shown on the web app. 
   2. Update `mqtt.url` to the correct MQTT broker URL
   3. Update `mqtt.input.base` and `mqtt.output.base` to the correct input and output topic prefix
   4. Updating `mqtt.client.id.base` may be **optional**, because `App.java` appends it with UUID and currentTimeMillis to make unique  

### 3. Run the app
   **Note**: Make sure MQTT broker is running, and all properties set up properly.
  #### Method 1: Using Command Line
   **Note**:  Please **repackage** and **restart** the app when you update properties.
1. Navigate to the project root directory
2. Run the following commands:
   ```bash
   mvn compile
   mvn package
   ```
3. Run the generated JAR file. Replace <generated-jar-file> with the name of the .jar file generated under target folder (e.g., supplement-rules-engine-1.0-SNAPSHOT-shaded.jar):
    ```bash
     java -jar target/<generated-jar-file-name>
    ```
#### Method 2: Using IDE
**Note**:  Please **restart** the app when you update properties.
   1. Run the app in IDE (IDEA, Eclipse etc.) by executing the `App.java` in `src/main/java/com/ryan/rulesengine/`

### 4. Shutdown
   1. Enter Ctrl+c or terminate app in IDE to shutdown app.


## Test
**Note**: `IntegrationTest.java` is temporarily disabled. Because it depends on MQTT broker, which may fail due to network latency, high broker load etc., and prevent jar creation. You can uncomment @Disabled in IntegrationTest.java or run the test manually.
1. Install Maven (tested using Maven 3.9.9)
2. Execute the command in project's root directory:
   ```bash
     mvn test
     ```
   It will run all unit tests in `src/test/java`.

## Project struture
- `src/main/java`
    - `com.ryan.rulesengine`: 
    - `App.java`: The entry point of app
    - `consumer`: Contains `InputConsumer.java`, which listens to specific topic, invoke handler and producer, and works as a service layer. 
    - `eventbus`: Contains `EventBus.java`, which manage MQTT connection, subscribe and publish.
    - `handler`: Contains `SupplementHandler.java`, which handles computation logic.
    - `model`: Contains `InputData.java` and `OutputData.java`, which are the input and output data models.
    - `producer`: Contains `OutputProducer.java`, which publishes message to the output MQTT topic.
    - `util`: Contains `ConfigUtil.java` and `JsonUtil.java`, used for fetching config properties and Json conversion.

- `src/main/resources`: Contains the configuration file `config.properties`.

- `src/test/java`: Contains unit tests.



