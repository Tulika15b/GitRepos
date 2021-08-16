package com.tulika.gitrepos

import android.util.Log
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

    var expandPos : Int = -1;

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GitRepoItemViewHolder {
        val binding = ListItemGitRepoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GitRepoItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GitRepoItemViewHolder, position: Int) {
        val currentItem = getItem(position)
        if(currentItem != null){
            holder.bind(currentItem)
        }
    }

    class GitRepoItemViewHolder(private val binding : ListItemGitRepoBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(gitRepo : GitRepo){
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
                Log.d("CLIKC", "ITEM CLICKED");
                if(!gitRepo.isExpanded){
                    binding.expandableRepoLayout.visibility = View.VISIBLE
                    binding.gitRepoUrl.visibility = View.VISIBLE
                    gitRepo.isExpanded = true
                }else{
                    binding.expandableRepoLayout.visibility = View.GONE
                    binding.gitRepoUrl.visibility = View.GONE
                    gitRepo.isExpanded = false
                }

               /* if(expandPos == -1){
                    binding.expandableRepoLayout.visibility = View.GONE
                    binding.gitRepoUrl.visibility = View.GONE
                    gitRepo.isExpanded = false
                    expandPos = bindingAdapterPosition
                }else{
                    if(expandPos == bindingAdapterPosition){
                        binding.expandableRepoLayout.visibility = View.VISIBLE
                        binding.gitRepoUrl.visibility = View.VISIBLE
                        gitRepo.isExpanded = true
                    }else{
                        binding.expandableRepoLayout.visibility = View.GONE
                        binding.gitRepoUrl.visibility = View.GONE
                        gitRepo.isExpanded = false
                    }
                }*/
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