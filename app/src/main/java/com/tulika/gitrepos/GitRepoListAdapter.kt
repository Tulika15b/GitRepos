package com.tulika.gitrepos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tulika.gitrepos.data.GitRepo
import com.tulika.gitrepos.databinding.ListItemGitRepoBinding

class GitRepoListAdapter : ListAdapter<GitRepo, GitRepoListAdapter.GitRepoItemViewHolder>(GitRepoComparator()) {

    var expandedSet = mutableSetOf<Int>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GitRepoItemViewHolder {
        val binding = ListItemGitRepoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GitRepoItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GitRepoItemViewHolder, position: Int) {
        val currentItem = getItem(position)
        if(currentItem != null){
            holder.bind(currentItem, true)
            holder.itemView.setOnClickListener {
                if (expandedSet.contains(position)) {
                    removeExpand(position)
                    holder.
                } else {
                    addExpand(position)
                    holder.bind(currentItem, true)
                }
            }

        }
    }

    fun removeExpand(pos : Int){
        expandedSet.remove(pos)
    }

    fun addExpand(pos : Int){
        expandedSet.add(pos)
    }

    class GitRepoItemViewHolder(private val binding : ListItemGitRepoBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(gitRepo : GitRepo, isExpand : Boolean){
            binding.apply {
                Glide.with(itemView)
                    .load("https://avatars.githubusercontent.com/u/3430433?v=4")
                    .into(gitRepoIcon)

                gitRepoName.text = gitRepo.repoName
                gitRepoDescr.text = gitRepo.repoDescr
                gitRepoUrl.text = gitRepo.repoUrl
                gitRepoLang.text = gitRepo.repoLang
                gitRepoStars.text = gitRepo.stars.toString()
                gitRepoForks.text = gitRepo.forks.toString()
            }

            binding.root.setOnClickListener {

                if(isExpand){
                    binding.expandableRepoLayout.visibility = View.VISIBLE
                    binding.gitRepoUrl.visibility = View.VISIBLE
                }else{
                    binding.expandableRepoLayout.visibility = View.GONE
                    binding.gitRepoUrl.visibility = View.GONE
                }
            }
        }



    }

    class GitRepoComparator : DiffUtil.ItemCallback<GitRepo>(){
        override fun areItemsTheSame(oldItem: GitRepo, newItem: GitRepo)=
            oldItem.repoName == newItem.repoName

        override fun areContentsTheSame(oldItem: GitRepo, newItem: GitRepo)=
            oldItem == newItem
    }

}