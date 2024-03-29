package com.example.learningapi

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.learningapi.databinding.ActivityMainBinding
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val userList = ArrayList<UserDataItem>()
    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


ApiServer.createRetrofit().getData()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe {
                for (myData in it) {
                    userList.add(myData)
                }
                val adapter = UserAdpater(this, userList)
                binding.recyclerView.layoutManager = LinearLayoutManager(this)
                binding.recyclerView.adapter = adapter
            }


        val postData = UserPostData(1, "POST", "POST")
        ApiServer.createRetrofit().addPost(postData)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe {
                Toast.makeText(this, it.body, Toast.LENGTH_SHORT).show()
            }


    }}