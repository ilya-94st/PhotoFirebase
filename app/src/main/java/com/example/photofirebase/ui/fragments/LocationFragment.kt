package com.example.photofirebase.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.photofirebase.adapter.PictureAdapter
import com.example.photofirebase.base.BaseFragment
import com.example.photofirebase.databinding.FragmentLocationBinding
import com.example.photofirebase.other.Constants
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext


class LocationFragment : BaseFragment<FragmentLocationBinding>() {
    private val collection = Firebase.firestore.collection(Constants.NAME_FIREBASE)
    val imageRef = Firebase.storage.reference

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentLocationBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listFiles()
        frameAdapter()
    }
    private fun listFiles() = CoroutineScope(Dispatchers.IO).launch {
        try {
           val images = imageRef.child("images/").listAll().await()
            val imageUrls = mutableListOf<String>()
            for(image in images.items) {
                val url = image.downloadUrl.await()
                imageUrls.add(url.toString())
            }
            withContext(Dispatchers.Main){
                val imageAdapter = PictureAdapter(imageUrls)
                binding.rvPicture.adapter = imageAdapter
            }
        } catch (e: Exception) {
            withContext(Dispatchers.Main) {
                toast("${e.message}")
            }
        }
    }
    private fun frameAdapter() {
        (binding.rvPicture.layoutManager as GridLayoutManager).spanCount = 3
    }
}