# PantryInsight (Android)
This is a part of my broader smart pantry project Rikōtodana. The project is currently in the prototype stage.
This is the Android smartphone app that accesses the REST-API of Rikōtodana and the associated MQTT broker.
The pantry's inventory is accessed through the REST-API while the readings of the temperature and humidity sensor are provided to the app through MQTT.
You need to set up the respective MQTT Broker and change the MQTT credentials and MQTT broker address in the source code. Then you can compile it and install it on your Android phone.
The MQTT credentials are stored in the package `com.example.rikotodanactrl.DepInj` in the file `ModuleMaker.kt` while the MQTT broker address is stored in the `com.example.rikotodanactrl.core` package in the file `MqttInfo.kt`.
The address of the REST-API is stored in the same package in the file `HttpInfo.kt`.

### Security warning
Since this is still a prototype, many security measures are missing! Transport encryption for both the REST-API and the MQTT messages have not been implemented yet. Authentication and authorization techniques for the REST-API are also not implemented. TL;DR: This is a prototype without any security (yet).