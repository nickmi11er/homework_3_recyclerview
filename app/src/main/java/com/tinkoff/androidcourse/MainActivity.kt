package com.tinkoff.androidcourse

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper

class MainActivity : AppCompatActivity() {
    private lateinit var recycler: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recycler = findViewById(R.id.recycler)

        val wg = WorkerGenerator()
        val adapter = WorkerAdapter(wg.generateWorkers(20))

        val lm = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recycler.layoutManager = lm
        recycler.addItemDecoration(DividerDecoration(this))
        recycler.itemAnimator?.addDuration = 1000
        recycler.itemAnimator?.changeDuration = 1000
        recycler.itemAnimator?.moveDuration = 1000
        recycler.itemAnimator?.removeDuration = 1000

        val helper = ItemTouchHelper(MyTouchHelperCallback(adapter))
        helper.attachToRecyclerView(recycler)

        recycler.adapter = adapter

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
            adapter.addWorker(wg.generateWorker())
            recycler.scrollToPosition(0)
        }

    }
}
