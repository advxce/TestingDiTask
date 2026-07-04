package com.example.testingditask.liveCodding

import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.testingditask.R

class SearchFragment: Fragment() {

    private val handlerThread = HandlerThread("handlerThread").apply { start() }
    private val handler = Handler(handlerThread.looper)

    private val searchText = requireView().findViewById<EditText>(com.example.home.R.id.homeTextView)
    private val searchResultView = requireView().findViewById<TextView>(com.example.home.R.id.tvTitle)
    private val searchButton = requireView().findViewById<Button>(com.example.home.R.id.btnGoToHome)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(com.example.home.R.layout.fragment_home, container, true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val runnable = Runnable{
            val searchString = searchText.text.toString()
            val searchResult = executeSearchRequest(searchString)
            searchResultView.text = searchResult
        }

        searchButton.setOnClickListener {
            handler.post(runnable)
        }

    }

    private fun executeSearchRequest(searchString: String): String {
        //network request
        return "Task"
    }

}