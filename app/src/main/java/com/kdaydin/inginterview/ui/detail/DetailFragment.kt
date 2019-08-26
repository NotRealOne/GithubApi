package com.kdaydin.inginterview.ui.detail

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.kdaydin.inginterview.MainActivity
import com.kdaydin.inginterview.R
import com.kdaydin.inginterview.data.Repository
import com.kdaydin.inginterview.databinding.DetailFragmentBinding


class DetailFragment : Fragment() {
    var binding: DetailFragmentBinding? = null
    var repo: Repository? = null

    companion object {
        fun newInstance() = DetailFragment()
    }

    private lateinit var viewModel: DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(layoutInflater, R.layout.detail_fragment, container, false)
        return binding!!.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(DetailViewModel::class.java)
        binding?.viewModel = viewModel
        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Glide.with(this).load(repo?.owner?.avatar_url).listener(object : RequestListener<Drawable> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                binding?.avatarIV?.visibility = View.GONE
                return false
            }

            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                binding?.avatarIV?.visibility = View.VISIBLE
                binding?.avatarIV?.setImageDrawable(resource)
                return false
            }
        }).transform(CenterCrop(), RoundedCorners(18)).into(binding?.avatarIV!!)
        binding?.ownerTV?.text = repo?.owner?.login
        binding?.starCountTV?.text =
            getString(R.string.star_count).replace("%d", repo?.stargazers_count.toString())
        binding?.issueCountTV?.text =
            getString(R.string.issue_count).replace("%d", repo?.open_issues_count.toString())
        (activity as MainActivity).supportActionBar?.title = repo?.name
        (activity as MainActivity).supportActionBar?.setHomeAsUpIndicator(R.drawable.baseline_navigate_before_24)
        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}
