package uas.nabiilahputriafiifah.adapter

import uas.nabiilahputriafiifah.activity.HomePageActivity

import android.annotation.SuppressLint
import android.content.Context
import android.location.Geocoder
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import uas.nabiilahputriafiifah.client_server.R
import uas.nabiilahputriafiifah.model.DetailLoc
import java.text.DecimalFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.*

class LocationAdapter(private val context: Context, private val list: ArrayList<DetailLoc>): ArrayAdapter<DetailLoc>(context, R.layout.item_list, list){
    var myLatAdapter: Double = 0.0
    var myLotAdapter: Double = 0.0
    @SuppressLint("SetTextI18n")

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater : LayoutInflater = LayoutInflater.from(context)
        val view : View = inflater.inflate(R.layout.item_list, null)

        var geocoder: Geocoder = Geocoder(context, Locale.getDefault())
        val locName : TextView = view.findViewById(R.id.locName)
//        val locDist : TextView = view.findViewById(R.id.locDist)

        var latitude = list[position].latitude.toDouble()
        var longitude = list[position].longitude.toDouble()
//        locName.text =
        var address = geocoder.getFromLocation(list[position].latitude.toDouble(), list[position].longitude.toDouble(), 1)
        Log.i("test", position.toString() + " " + address.toString())
        if (address != null) {
            if (address.isEmpty()){
                locName.text = "Location Not Found"
            }else{
                locName.text = "${address?.get(0)?.locality}, ${address?.get(0)?.countryName}"
            }
        }

        return view
    }

    fun setKosInfo(lat: Double, lot: Double) {
        myLatAdapter = lat
        myLotAdapter = lot
        notifyDataSetChanged()
    }

    private fun getDistance(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Double {

        val earthRad = 6371.0
        val dLat = Math.toRadians(lat2 - lat1)
        val dLon = Math.toRadians(lon2 - lon1)

        val a = sin(dLat / 2).pow(2) + cos(Math.toRadians(lat1)) *
                cos(Math.toRadians(lat2)) * sin(dLon / 2).pow(2)
        val c = 2 * atan2(sqrt(a), sqrt(1 - a))

        val distance = earthRad * c * 1000
        val decimalFormat = DecimalFormat("#")
        return decimalFormat.format(distance).toDouble()

    }

}
