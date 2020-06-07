package maurya.devansh.headlines.screens.newslist

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_news_list.*
import maurya.devansh.headlines.HeadlinesApplication
import maurya.devansh.headlines.R
import maurya.devansh.headlines.screens.newslist.recyclerview.NewsListAdapter
import maurya.devansh.headlines.screens.newslist.viewmodel.NewsListViewModel
import javax.inject.Inject

class NewsListFragment : Fragment() {

    @Inject
    lateinit var newsViewModelFactory: ViewModelProvider.Factory
    private val viewModel: NewsListViewModel by lazy {
        ViewModelProvider(this, newsViewModelFactory).get(NewsListViewModel::class.java)
    }

    override fun onAttach(context: Context) {
        (context.applicationContext as HeadlinesApplication).appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_news_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = NewsListAdapter()
        recyclerView.adapter = adapter

        viewModel.getNewsHeadlines().observe(viewLifecycleOwner, Observer {
                adapter.submitList(it)
        })
    }
}
