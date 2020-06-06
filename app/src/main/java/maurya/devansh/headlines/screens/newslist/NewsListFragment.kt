package maurya.devansh.headlines.screens.newslist

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import maurya.devansh.headlines.HeadlinesApplication
import maurya.devansh.headlines.R
import javax.inject.Inject

class NewsListFragment : Fragment() {

    @Inject
    lateinit var newsViewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: NewsListViewModel

    override fun onAttach(context: Context) {
        (context.applicationContext as HeadlinesApplication).appComponent.inject(this)
        super.onAttach(context)
        viewModel = ViewModelProvider(this, newsViewModelFactory).get(NewsListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news_feed, container, false)
    }


}