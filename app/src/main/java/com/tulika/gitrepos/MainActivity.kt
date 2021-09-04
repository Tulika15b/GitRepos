package com.tulika.gitrepos

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.facebook.shimmer.ShimmerFrameLayout
import com.tulika.gitrepos.data.remote.GitRepoResource
import com.tulika.gitrepos.databinding.ActivityMainBinding
import com.tulika.gitrepos.databinding.LayoutShimmerLoadingBinding
import com.tulika.gitrepos.viewmodel.GitRepoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: GitRepoViewModel by viewModels()
    lateinit var shimmerLayout : ShimmerFrameLayout

    private val repoListAdapter = GitRepoListAdapter()
    lateinit var binding : ActivityMainBinding
    lateinit var loadingBinding : LayoutShimmerLoadingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        loadingBinding = LayoutShimmerLoadingBinding.inflate(layoutInflater)

        setContentView(binding.root)
        setupUI()
        setupFetchObservers()

    }

    fun setupUI(){
        shimmerLayout = loadingBinding.shimmerFrameLayout

        binding.apply {

            swipeRefreshLayout.setOnRefreshListener {
                binding.swipeRefreshLayout.isRefreshing = true
                Log.d("SWIPE", "YAY")
                //setupUIObservers()
                repoListAdapter.submitList(null)
                setupFetchObservers()
            }

            gitRepoRecyclerView.apply {
                adapter = repoListAdapter
                layoutManager = LinearLayoutManager(this@MainActivity)
            }

            gitRepoRecyclerView.addItemDecoration(
                DividerItemDecoration(
                    gitRepoRecyclerView.context,
                    (gitRepoRecyclerView.layoutManager as LinearLayoutManager).orientation
                )
            )
        }
    }

    fun setupFetchObservers(){
        viewModel.reposList.observe(this@MainActivity){ result ->
            repoListAdapter.submitList(result.data)
            binding.swipeRefreshLayout.isRefreshing = false
            // gitRepoRecyclerView.isVisible = result is GitRepoResource.Success && !result.data?.isEmpty()!!

            binding.gitRepoErrorState.visibility = when(result is GitRepoResource.Error && result.data.isNullOrEmpty()){
                true -> View.VISIBLE
                false -> View.GONE
            }

            binding.gitRepoLoadingState.visibility = when(result is GitRepoResource.Loading){
                true -> View.VISIBLE
                false -> View.GONE
            }

            result.error?.message?.let { Log.d("State", it) }

        }
    }

}