package com.adel.quranapprxjavaversion.presentation.surahDetails

import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.adel.quranapprxjavaversion.data.models.VerseModel
import com.adel.quranapprxjavaversion.databinding.FragmentSurahDetailsBinding
import com.adel.quranapprxjavaversion.domian.entities.Result

class SurahDetailsFragment : Fragment() {

    lateinit var viewModel: SurahDetailsViewModel

    private lateinit var binding: FragmentSurahDetailsBinding
    private lateinit var navController: NavController
    var allVersesList: MutableList<VerseModel> = mutableListOf()
    lateinit var adapter: VerseAdapter
    var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navController = findNavController()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(SurahDetailsViewModel::class.java)
        adapter = VerseAdapter(allVersesList, { audioLink ->
            if (mediaPlayer == null) {
                mediaPlayer = MediaPlayer().apply {
                    setAudioAttributes(
                        AudioAttributes.Builder()
                            .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                            .setUsage(AudioAttributes.USAGE_MEDIA)
                            .build()
                    )
                    setDataSource(audioLink)
                    prepare()
                    start()
                    setOnCompletionListener {
                        mediaPlayer!!.release()
                        mediaPlayer = null
                    }
                }
            }
        })
        binding.rcVerses.layoutManager = LinearLayoutManager(requireContext())
        binding.rcVerses.adapter = adapter
        viewModel.surahDetails.observe(requireActivity(), {
            when (it) {
                is Result.Loading -> {
                }
                is Result.Success -> {
                    with(it.data) {
                        binding.tvSurahName.text = name
                        binding.tvSurahTitleName.text = name
                        binding.tvSurahArabicName.text = arabicName
                        binding.tvSurahType.text = "$type - "
                        binding.tvSurahVerseNum.text = "$verseNum VERSES"
                    }
                }
                is Result.Error -> null
            }
        })

        viewModel.verseList.observe(requireActivity(), {
            when (it) {
                is Result.Loading -> {
                    binding.scrollViewSurahDetails.visibility = View.GONE
                    binding.progressLoading.visibility = View.VISIBLE
                }
                is Result.Success -> {
                    binding.scrollViewSurahDetails.visibility = View.VISIBLE
                    binding.progressLoading.visibility = View.GONE
                    allVersesList.clear()
                    allVersesList.addAll(it.data)
                    adapter.notifyDataSetChanged()
                }
                is Result.Error -> Toast.makeText(
                    requireContext(),
                    "error ${it.error.javaClass.simpleName}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
        binding.ivBack.setOnClickListener {
            navController.navigateUp()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSurahDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }
}
