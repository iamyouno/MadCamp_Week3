package com.example.empathy_diary

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.firebase.auth.FirebaseAuth
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DiaryDifferent : AppCompatActivity() {

    private var retrofitClient = RetrofitClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_different_diary)

        var feedListDifferent = arrayListOf<Item_feed>()

        val mAdapter = FeedAdapter(this, feedListDifferent)

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_different)

        val linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = mAdapter

        val call = retrofitClient.apiService.getOppositeFeeds(FirebaseAuth.getInstance().uid.toString())
        call!!.enqueue(object : Callback<ArrayList<Item_feed>> {
            override fun onFailure(call: Call<ArrayList<Item_feed>>, t: Throwable) {
                Log.d("Error", "Get Feeds Error")
            }

            override fun onResponse(
                    call: Call<ArrayList<Item_feed>>,
                    response: Response<ArrayList<Item_feed>>
            ) {
                if (response.isSuccessful) {
                    val json_arr = response.body()
                    if (json_arr != null) {
                        for (item: Item_feed in json_arr) {
                            val text = item.feed_context
                            val date = item.feed_date
                            val likes = item.feed_likes
                            val pk = item.feed_pk
                            val emotion = item.emotion
                            val percent = item.percent
                            mAdapter?.addItem(Item_feed(date, text, likes, pk, emotion, percent))
                        }
                    }

                }
            }

        })

        val refreshLayout = findViewById<SwipeRefreshLayout>(R.id.swipe_layout_different)
        refreshLayout.setOnRefreshListener {
            refreshLayout.isRefreshing = false
//            feedListDifferent.add(Item_feed("12/23", "눈\n이\n왔\n다", 1, "d"))
            // 새로운 데이터 받아오기
            //
            //
//            mAdapter?.notifyDataSetChanged()

            Log.d("refresh", "refresh")
        }
    }

    override fun onBackPressed() {
        val intent = Intent(this@DiaryDifferent, MainActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.fadein, R.anim.fadeout)
    }
}