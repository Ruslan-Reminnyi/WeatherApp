package com.mycompany.myapplication.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mycompany.myapplication.R
import com.mycompany.myapplication.data.WeatherInfo

class WeatherAdapter internal constructor(context: Context, weatherList: List<WeatherInfo>) : RecyclerView.Adapter<WeatherAdapter.ViewHolder>() {

    private val weatherList: List<WeatherInfo>
    private val inflater: LayoutInflater
    private var mClickListener: ItemClickListener? = null
    private val context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.recyclerview_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val weatherOfDay = weatherList[position]

        holder.textViewDate.text = weatherOfDay.forecastValidDate
        holder.textViewTemperature.text = weatherOfDay.averageTemperature.toString()
        holder.textViewPressure.text = weatherOfDay.averagePressure
    }

    override fun getItemCount(): Int {
        return weatherList.size
    }

    inner class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        var textViewDate: TextView
        var textViewTemperature: TextView
        var textViewOrderTemperatureAmount: TextView
        var textViewPressure: TextView
        var textViewPressureAmount: TextView
        var relativeLayout: RelativeLayout

        override fun onClick(view: View) {
            if (mClickListener != null) mClickListener!!.onItemClick(view, adapterPosition)
        }

        init {
            relativeLayout = itemView.findViewById(R.id.recycler_coordinator_manager)
            textViewDate = itemView.findViewById(R.id.recyclerview_text_view_date)
            textViewTemperature = itemView.findViewById(R.id.recyclerview_text_view_temperature)
            textViewOrderTemperatureAmount = itemView.findViewById(R.id.recyclerview_text_view_temperature_amount)
            textViewPressure = itemView.findViewById(R.id.recyclerview_text_view_pressure)
            textViewPressureAmount = itemView.findViewById(R.id.recyclerview_text_view_pressure_amount)
            relativeLayout.setOnClickListener(this)
            itemView.setOnClickListener(this)
        }

    }

    override fun getItemId(i: Int): Long {
        return i.toLong()
    }

    fun setClickListener(itemClickListener: ItemClickListener?) {
        mClickListener = itemClickListener
    }

    interface ItemClickListener {
        fun onItemClick(view: View?, position: Int)
    }

    init {
        inflater = LayoutInflater.from(context)
        this.weatherList = weatherList
        this.context = context
    }

}