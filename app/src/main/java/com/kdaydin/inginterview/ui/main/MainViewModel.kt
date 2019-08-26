package com.kdaydin.inginterview.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kdaydin.inginterview.data.Repository
import com.kdaydin.inginterview.network.RetrofitReq
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    var userName = ""
    var retrofitReq = RetrofitReq()
    var repoList: MutableLiveData<List<Repository>> = MutableLiveData()

    fun searchUserRepos() {
        val repoCallback = retrofitReq.getGithubApi()?.getReposByUsername(userName)
        repoCallback?.enqueue(object : Callback<List<Repository>> {
            override fun onFailure(call: Call<List<Repository>>, t: Throwable) {
                repoList.value = null
            }

            override fun onResponse(
                call: Call<List<Repository>>,
                response: Response<List<Repository>>
            ) {
                repoList.value = response.body()
            }
        })
    }
}
