package com.example.home.presentation.News

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.home.databinding.FragmentNewsBinding
import com.example.home.di.HomeComponent
import com.example.home.di.HomeComponentViewModel
import com.example.home.di.Routes
import com.example.home.di.Screen
import com.example.home.presentation.News.recView.NewsAdapter
import com.example.home.presentation.News.states.NewsState
import com.example.home.presentation.News.viewModel.NewsViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsFragment : Fragment() {

    private var _binding: FragmentNewsBinding? = null
    private val binding
        get() = _binding!!

    private var newsAdapter: NewsAdapter? = null
    private val homeComponent: HomeComponent by lazy {
        ViewModelProvider(this)[HomeComponentViewModel::class.java].homeComponent as HomeComponent
    }

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private val newsViewModel: NewsViewModel by viewModels{
        factory
    }
    private var route: Routes? = null

    override fun onAttach(context: Context) {
        homeComponent.inject(this@NewsFragment)
        super.onAttach(context)
        route = (context as? Routes)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            btnGoToWeather.setOnClickListener {
                route?.navigateTo(Screen.WeatherScreen)
            }
            btnGoToHome.setOnClickListener {
                route?.navigateTo(Screen.HomeScreen)
            }

            newsAdapter = NewsAdapter()

            newsRecView.adapter = newsAdapter
            newsRecView.layoutManager = LinearLayoutManager(requireActivity())

            viewLifecycleOwner.lifecycleScope.launch {
                newsViewModel.news.collect { state ->
                    println("state: $state")
                    when(state){
                        NewsState.Idle -> {}
                        NewsState.Loading ->{}
                        is NewsState.Error -> {}
                        is NewsState.Success -> {
                            newsAdapter?.updateAdapter(state.newsList)
                        }
                    }
                }
            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        route = null
        newsAdapter = null
    }

}