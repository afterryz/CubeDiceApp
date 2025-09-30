package com.example.cube

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HistoryAdapter(
    private val rolls: MutableList<Roll>,
) : RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val textView: TextView = view.findViewById(R.id.textView)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_roll, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.textView.text = rolls[position].text
        }

    override fun getItemCount(): Int = rolls.size
    fun updateRolls(newRolls: List<Roll>) {
        rolls.clear()
        rolls.addAll(newRolls)
        notifyDataSetChanged()
    }
}