package com.adel.quranapprxjavaversion.presentation.homeScreen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adel.quranapprxjavaversion.data.models.SurahModel
import com.adel.quranapprxjavaversion.databinding.SurahItemBinding

class SurahAdapter(var surahList: List<SurahModel>, private var onSurahClick: (Int) -> Unit) :
    RecyclerView.Adapter<SurahAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: SurahItemBinding =
            SurahItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            surahList[position].let { item ->
                binding.tvSurahArabicName.text = item.arabicName
                binding.tvSurahName.text = item.name
                binding.tvSurahType.text = "${item.type} - "
                binding.tvSurahVerseNum.text = item.verseNum.toString()
                itemView.setOnClickListener { onSurahClick(position + 1) }
            }
            binding.tvSurahNum.text = (position + 1).toString()
        }
    }

    override fun getItemCount(): Int = surahList.size

    inner class ViewHolder(val binding: SurahItemBinding) : RecyclerView.ViewHolder(binding.root)
}
