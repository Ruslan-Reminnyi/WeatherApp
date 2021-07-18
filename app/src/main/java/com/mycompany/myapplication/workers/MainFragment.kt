package com.mycompany.myapplication.workers

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mycompany.myapplication.R
import com.mycompany.myapplication.adapters.WeatherAdapter
import com.mycompany.myapplication.data.WeatherInfo
import com.mycompany.myapplication.viewmodels.MainViewModel

class MainFragment : Fragment() {

    private val mainViewModel: MainViewModel by viewModels()
    private var textView: TextView? = null
    private var recyclerView: RecyclerView? = null
    private var adapter: WeatherAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_main, container, false)

        initGui(view)

        mainViewModel.weatherList.observe(this.viewLifecycleOwner, this::updateUI)

        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    private fun initGui(view: View?) {
        textView = view?.findViewById<TextView>(R.id.text_view_main_screen_title)
        recyclerView = requireActivity().findViewById(R.id.recycler_view_manager)
        recyclerView?.layoutManager = LinearLayoutManager(requireActivity())
        adapter = WeatherAdapter(requireActivity(), emptyList())
        adapter?.notifyDataSetChanged()
        recyclerView?.adapter = adapter
    }

    private fun updateUI(weatherList: ArrayList<WeatherInfo>) {
        adapter = WeatherAdapter(requireActivity(), weatherList)
        Log.i("updateUI", "${mainViewModel.weatherList.value?.get(0)?.averageTemperature}")
    }

    override fun onResume() {
        super.onResume()
        mainViewModel.getWeather()
    }

}