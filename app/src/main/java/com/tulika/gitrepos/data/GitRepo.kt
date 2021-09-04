package com.tulika.gitrepos.data

import androidx.annotation.Nullable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.*

@Entity(tableName = "gitRepo")
data class GitRepo(

    @PrimaryKey
    @SerializedName("id")
    val id : String,

    @SerializedName("name")
    val repoName : String,

    @SerializedName("html_url")
    val repoUrl : String,

    @SerializedName("description")
    @Nullable
    val repoDescr : String?,

    @SerializedName("forks_count")
    val forks : Int,

    @SerializedName("stargazers_count")
    val stars : Int,

    @SerializedName("language")
    @Nullable
    val repoLang : String?,

    var isExpanded: Boolean = false,

    //var installedAt : Date
)