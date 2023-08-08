package com.example.cocktailbar.ui.newcocktail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cocktailbar.databinding.ActivityNewCocktailBinding
import com.example.cocktailbar.ui.newcocktail.adapter.IngredientsAdapter

class NewCocktailActivity : AppCompatActivity() {


    private val ingredientsAdapter = IngredientsAdapter()
    private val binding by lazy {
        ActivityNewCocktailBinding.inflate(layoutInflater)
    }
    private val viewModel by viewModels<NewCocktailViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        init()
        initListeners()
    }


    private fun init() =
        with(binding.rvIngredients) {
            layoutManager = LinearLayoutManager(context)
            adapter = ingredientsAdapter


        }


    private fun initListeners() = with(binding) {
        val getImage = registerForActivityResult(
            ActivityResultContracts.GetContent()
        ) {

            binding.ivImageCocktail.setImageURI(it ?: Uri.EMPTY)
            viewModel.updateImage(it ?: Uri.EMPTY)

        }
        saveBtn.setOnClickListener {
            Log.w("WWWWW", "ADDED")
            viewModel.save()
            onBackPressedDispatcher.onBackPressed()
        }
        cancelBtn.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
        titleTit.doOnTextChanged { text, _, _, _ ->
            titleTil.isErrorEnabled = false
            viewModel.updateName(text.toString())
        }
        recipeTit.doOnTextChanged { text, _, _, _ ->
            viewModel.updateRecipe(text.toString())

        }
        descriptionTit.doOnTextChanged { text, _, _, _ ->
            viewModel.updateDescription(text.toString())
        }
        ivImageCocktail.setOnClickListener {
            getImage.launch("image/*")
        }


    }
}