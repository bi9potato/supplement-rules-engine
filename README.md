# Winter Supplement Test Rules Engine
Implement the project and tests according to the requirements.
For now, if the input JSON is invalid (e.g., missing fields, extra keys, or values exceeding the allowed range such as numberOfChildren being out of the int range), 
output's numeric fields will be set to 0.0, and boolean be set to false, string be set to "invalid-input".

## How to Run on Local Machine
### 1. Set up development environment
   1. Java 8 or higher (tested using Java 1.8)
   2. Maven 3.x (tested using Maven 3.9.9)
### 2. Update config properties  
- In `src/main/resources/config.properties` 
   1. Update `topicId`  to the lasted **`MQTT topic ID`** shown on the web app. Please **restart** app when you update this property.
   2. Update `mqtt.url` to the correct MQTT broker URL
   3. Update `mqtt.input.base` and `mqtt.output.base` to the correct input and output topic prefix
   4. Updating `mqtt.client.id.base` may be **not necessary**, because `App.java` appends it with UUID and currentTimeMillis to make unique  
### 3. Run the app
   **Note**: Make sure MQTT broker is running, and all properties set up properly.
   1. Run the app in IDE (IDEA, Eclipse etc.) by executing the `App.java` in `src/main/java/com/ryan/rulesengine/`
### 4. Now you can send data to MQTT broker!
### 5. Shutdown
   1. Enter Ctrl+c or terminate app in IDE to shutdown app.

## Test
**Note**: `IntegrationTest.java` may fail due to network latency, high broker load etc., because it depends on service to do end to end test.
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



