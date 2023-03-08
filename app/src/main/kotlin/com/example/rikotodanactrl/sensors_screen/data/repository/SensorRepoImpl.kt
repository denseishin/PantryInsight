package com.example.rikotodanactrl.sensors_screen.data.repository
import com.example.rikotodanactrl.core.MqttInfo
import com.example.rikotodanactrl.sensors_screen.domain.repository.SensorRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.eclipse.paho.client.mqttv3.*

class SensorRepoImpl(private val client: IMqttAsyncClient, private val options: MqttConnectOptions): SensorRepo {
    private val listener: MqttMsgListener = MqttMsgListener()
    private val _tempState = MutableStateFlow("N/A")
    override val tempState = _tempState.asStateFlow()
    private val _humidState = MutableStateFlow("N/A")
    override val humidState = _humidState.asStateFlow()

    override fun connect() {
        client.connect(options, null, object: IMqttActionListener {
            override fun onSuccess(asyncActionToken: IMqttToken?) {
                sub()
            }

            override fun onFailure(asyncActionToken: IMqttToken?, exception: Throwable?) {
                println("connect failed!")
            }
        })
    }

    override fun disconnect() {
        client.disconnect()
    }

    override fun sub() {
        client.subscribe(MqttInfo.HumidityTopic, 0, listener)
        client.subscribe(MqttInfo.TemperatureTopic, 0, listener)
    }

    override suspend fun cbOnTempMsg(): Unit {
        listener.tempState.collect {
            _tempState.emit(String.format("%.1f",it)+"°C")
        }
    }

    override suspend fun cbOnHumidMsg(): Unit {
        listener.humidState.collect {
            _humidState.emit(String.format("%.1f",it)+"%")
        }
    }
}

class MqttMsgListener(): IMqttMessageListener {
    private val _tempState = MutableStateFlow(0f)
    val tempState = _tempState.asStateFlow()
    private val _humidState = MutableStateFlow(0f)
    val humidState = _humidState.asStateFlow()
    override fun messageArrived(topic: String?, message: MqttMessage?) {
        //println("message")
        if (message != null)
        {
            val sensorval = String(message.payload).toFloat()
            if (topic != null)
            {
                when(topic)
                {
                    MqttInfo.HumidityTopic -> _humidState.value = sensorval
                    MqttInfo.TemperatureTopic -> _tempState.value = sensorval
                }
            }
        }
    }
}