package com.adel.quranapprxjavaversion.presentation.homeScreen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.adel.quranapprxjavaversion.data.models.SurahModel
import com.adel.quranapprxjavaversion.databinding.FragmentHomeBinding
import com.adel.quranapprxjavaversion.domian.entities.Result

class HomeFragment : Fragment() {
    private lateinit var navController: NavController
    private lateinit var binding: FragmentHomeBinding
    private var allSurahList: MutableList<SurahModel> = mutableListOf()
    private lateinit var adapter: SurahAdapter
    private lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navController = findNavController()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(HomeViewModel::class.java)
        binding.rcAllSurah.layoutManager = LinearLayoutManager(requireContext())
        adapter = SurahAdapter(allSurahList, { surahNum ->
            // when click on surah item
            val actionToSurahDetailsFragment =
                HomeFragmentDirections.actionHomeFragmentToSurahDetailsFragment(surahNum)
            navController.navigate(actionToSurahDetailsFragment)
        })
        binding.rcAllSurah.adapter = adapter
        viewModel.surahList.observe(requireActivity(), {
            when (it) {
                 is Result.Loading -> {
                    binding.progressLoading.visibility = View.VISIBLE
                    binding.scrollViewSurah.visibility = View.GONE
                    binding.lnError.visibility = View.GONE
                }
                is Result.Success -> {
                    binding.progressLoading.visibility = View.GONE
                    binding.scrollViewSurah.visibility = View.VISIBLE
                    binding.lnError.visibility = View.GONE
                    allSurahList.clear()
                    allSurahList.addAll(it.data)
                    adapter.notifyDataSetChanged()
                }
                is Result.Error -> {
                    Log.d("aaaaaaaaaaa", it.error.javaClass.simpleName)
                    binding.progressLoading.visibility = View.GONE
                    binding.scrollViewSurah.visibility = View.GONE
                    binding.lnError.visibility = View.VISIBLE
                }
            }
        })
        binding.btnTryAgain.setOnClickListener {
            viewModel.loadData()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }
}
