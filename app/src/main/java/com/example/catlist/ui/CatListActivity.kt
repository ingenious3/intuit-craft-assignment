package com.example.catlist.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.catlist.App
import com.example.catlist.R
import com.example.catlist.data.CatListItem
import com.example.catlist.ui.adapter.CatListItemAdapter
import com.example.catlist.viewmodel.CatViewModel
import com.example.catlist.viewmodel.CatViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class CatListActivity : AppCompatActivity() {

    private val appComponent by lazy { App.component }

    @Inject
    lateinit var viewModelFactory: CatViewModelFactory

    private lateinit var viewModel: CatViewModel

    private val adapter by lazy {
        CatListItemAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        appComponent.inject(this)

        initViewModel()
        initObserver()
        setupRecyclerView()
        getListData()

    }

    private fun setupRecyclerView() {

        val linearLayoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false)
        rv_catlist.layoutManager = linearLayoutManager
        val dividerItemDecoration = DividerItemDecoration(this, linearLayoutManager.orientation).apply {
            setDrawable(AppCompatResources.getDrawable(this@CatListActivity, R.drawable.divider)!!)
        }
        rv_catlist.addItemDecoration(dividerItemDecoration)
        adapter.setOnItemClickListener(object : CatListItemAdapter.AdapterListener {
            override fun onItemClick(catListItem: CatListItem) {
                Log.e("TAG-51", catListItem.toString())
            }

            override fun onLoadMore() {
                if (viewModel.pageNumber >= 0) {
                    getListData()
                }
            }
        })
        rv_catlist.adapter = adapter
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(CatViewModel::class.java)
    }

    private fun initObserver() {

        viewModel.listResult.observe(this, Observer {
            if (it != null) {
                pb_loading.visibility = View.GONE
                rv_catlist.visibility = View.VISIBLE
                adapter.updateList(it)
            }
        })

        viewModel.errorGetData.observe(this, Observer {
            if (viewModel.listResult?.value?.isEmpty() ?: true) {
                pb_loading.visibility = View.GONE
                tv_error.visibility = View.VISIBLE
            } else {
                pb_loading.visibility = View.GONE
            }
        })

    }

    private fun getListData() {
        pb_loading.visibility = View.VISIBLE
        viewModel.getData()
    }

}