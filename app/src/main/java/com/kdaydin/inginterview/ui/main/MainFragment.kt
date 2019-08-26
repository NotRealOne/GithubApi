package com.kdaydin.inginterview.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.kdaydin.inginterview.MainActivity
import com.kdaydin.inginterview.R
import com.kdaydin.inginterview.adapters.RepositoryAdapter
import com.kdaydin.inginterview.data.Repository
import com.kdaydin.inginterview.databinding.MainFragmentBinding
import com.kdaydin.inginterview.ui.detail.DetailFragment

class MainFragment : Fragment() {
    var binding: MainFragmentBinding? = null


    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.main_fragment, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).supportActionBar?.title = getString(R.string.app_name)
        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        binding?.viewModel = viewModel
        // TODO: Use the ViewModel
        viewModel.repoList.observe(this,
            Observer<List<Repository>> {
                if (it.isNullOrEmpty()) {
                    binding?.repoListRv?.visibility = View.GONE
                    binding?.emptyRepoTV?.visibility = View.VISIBLE
                } else {
                    binding?.repoListRv?.visibility = View.VISIBLE
                    binding?.emptyRepoTV?.visibility = View.GONE
                }
                val adapter = RepositoryAdapter(it ?: listOf())
                binding?.repoListRv?.adapter = adapter
                adapter.listener = object : RepositoryAdapter.OnItemClickListener {
                    override fun onItemClick(item: Repository) {
                        val detailFragment = DetailFragment.newInstance()
                        detailFragment.repo = item
                        fragmentManager?.beginTransaction()
                            ?.addToBackStack(DetailFragment::class.java.simpleName)
                            ?.replace(
                                R.id.container,
                                detailFragment,
                                DetailFragment::class.java.simpleName
                            )
                            ?.commitAllowingStateLoss()
                    }
                }

            })
    }

}
