package uas.nabiilahputriafiifah.api

import retrofit2.Call
import retrofit2.http.GET
import uas.nabiilahputriafiifah.model.Locations

interface LocationApi {

    @GET("locations")
    fun getLocations(): Call<Locations>
}