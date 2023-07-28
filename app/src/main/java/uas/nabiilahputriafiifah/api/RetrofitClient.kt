package uas.nabiilahputriafiifah.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private val ENDPOINT_API = "https://location-review.000webhostapp.com/api/noauth/"


    val instance: LocationApi by lazy{
        val api = Retrofit.Builder()
            .baseUrl(ENDPOINT_API)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api.create(LocationApi::class.java)
    }
}
