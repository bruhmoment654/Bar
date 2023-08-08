package com.example.cocktailbar.ui.main

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cocktailbar.databinding.ActivityMainBinding
import com.example.cocktailbar.subscribe
import com.example.cocktailbar.ui.main.adapter.ItemAdapter
import com.example.cocktailbar.ui.newcocktail.NewCocktailActivity


class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val itemAdapter = ItemAdapter {
        openActivity<NewCocktailActivity>(ItemAdapter.composeParams(it))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION)

        init()
        initListeners()
        updateUi()
    }

    override fun onResume() {
        super.onResume()
        viewModel.fetchCocktails()
    }


    private fun init() {
        with(binding.rvCocktails) {
            layoutManager = LinearLayoutManager(context)
            adapter = itemAdapter
        }
    }

    private fun initListeners() = with(binding) {
        newCocktailBtn.setOnClickListener {
            openActivity<NewCocktailActivity>()
        }
    }

    private fun updateUi() = with(binding) {
        subscribe(viewModel, onNewState = {
            val hasCocktails = it.cocktails.isNotEmpty()
            rvCocktails.isVisible = hasCocktails
            emptyLayout.root.isVisible = !hasCocktails
            itemAdapter.submitList(it.cocktails)
        })
    }

    private inline fun <reified A : Activity> openActivity(bundle: Bundle = bundleOf()) {
        Intent(this@MainActivity, A::class.java).apply {
            putExtras(bundle)
            startActivity(this)
        }
    }

}

