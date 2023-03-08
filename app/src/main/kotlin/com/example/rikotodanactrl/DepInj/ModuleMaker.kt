package com.example.rikotodanactrl.DepInj
//import org.eclipse.paho.android.service.MqttAndroidClient
import android.content.Context
import com.example.rikotodanactrl.core.MqttInfo
import com.example.rikotodanactrl.inventory_screen.data.repository.ItemRepoImpl
import com.example.rikotodanactrl.inventory_screen.domain.converter.IModelToDataModelConverter
import com.example.rikotodanactrl.inventory_screen.domain.converter.ModelToDataModelConverter
import com.example.rikotodanactrl.inventory_screen.domain.repository.ItemRepo.ItemRepo
import com.example.rikotodanactrl.inventory_screen.domain.use_case.GetInventoryUC
import com.example.rikotodanactrl.inventory_screen.domain.use_case.GetItemUC
import com.example.rikotodanactrl.inventory_screen.domain.use_case.UpdateItemUC
import com.example.rikotodanactrl.sensors_screen.data.repository.SensorRepoImpl
import com.example.rikotodanactrl.sensors_screen.domain.repository.SensorRepo
import com.example.rikotodanactrl.sensors_screen.domain.use_case.GetSensorValuesUC
import com.example.rikotodanactrl.warnings_screen.data.repository.WarningRepoImpl
import com.example.rikotodanactrl.warnings_screen.domain.repository.WarningRepo
import com.example.rikotodanactrl.warnings_screen.domain.use_case.GetFoodWarningsUC
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import info.mqtt.android.service.MqttAndroidClient
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import org.eclipse.paho.client.mqttv3.IMqttAsyncClient
import org.eclipse.paho.client.mqttv3.MqttClient
import org.eclipse.paho.client.mqttv3.MqttConnectOptions
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class ModuleMaker {
    @Provides
    @Singleton
    fun makeHttpClient(): HttpClient {
        return HttpClient(Android) {
            install(JsonFeature) {
                serializer = KotlinxSerializer()
            }
        }
    }
    @Provides
    @Singleton
    fun makeInvApiRepo(client: HttpClient): ItemRepo {
        return ItemRepoImpl(client)
    }

    @Provides
    @Singleton
    fun makeFoodWarnApiRepo(client: HttpClient): WarningRepo {
        return WarningRepoImpl(client)
    }

    @Provides
    @Singleton
    fun makeMqttClient(@ApplicationContext appContext: Context): IMqttAsyncClient
    {
        val randoId: String = MqttClient.generateClientId()
        return MqttAndroidClient(appContext,MqttInfo.BaseUrl,randoId)
    }

    @Provides
    @Singleton
    fun getMQTTAuthParams(): MqttConnectOptions {
        val options = MqttConnectOptions()
        options.userName = "android"
        options.password = "supersafeandroidpassword".toCharArray()
        return options
    }

    @Provides
    @Singleton
    fun makeSensorValueRepo(client: IMqttAsyncClient, options: MqttConnectOptions) : SensorRepo
    {
        return SensorRepoImpl(client, options)
    }

    @Provides
    @Singleton
    fun makeSensorValUCs(repo: SensorRepo): GetSensorValuesUC
    {
        return GetSensorValuesUC(repo)
    }

    @Provides
    @Singleton
    fun makeModel2Dtos(): IModelToDataModelConverter
    {
        return ModelToDataModelConverter()
    }

    @Provides
    @Singleton
    fun makeUpdateItemUC(repo: ItemRepo): UpdateItemUC
    {
        return UpdateItemUC(repo)
    }

    @Provides
    @Singleton
    fun makeInvUC(repo: ItemRepo, conv: IModelToDataModelConverter): GetInventoryUC
    {
        return GetInventoryUC(repo,conv)
    }

    @Provides
    @Singleton
    fun makeItemUC(repo: ItemRepo, conv: IModelToDataModelConverter): GetItemUC
    {
        return GetItemUC(repo,conv)
    }

    @Provides
    @Singleton
    fun makeWarningUCs(repo: WarningRepo): GetFoodWarningsUC
    {
        return GetFoodWarningsUC(repo)
    }

}