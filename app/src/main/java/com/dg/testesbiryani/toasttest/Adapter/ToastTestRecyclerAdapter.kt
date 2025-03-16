package com.dg.testesbiryani.toasttest.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dg.testesbiryani.R

class ToastTestRecyclerAdapter(val list: List<String>, private val clickListener: (Int) -> Unit) :
    RecyclerView.Adapter<ToastTestRecyclerAdapter.ToastTestViewHolder>() {

    private var mainList : MutableList<String> = mutableListOf()

    init {
        mainList.addAll(list)
    }

    class ToastTestViewHolder(view: View, clickListener: (Int) -> Unit) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.test_item_view)
        val checkbox : CheckBox = view.findViewById(R.id.checkbox)

        init {
            view.setOnClickListener {
                clickListener(adapterPosition)
            }

            checkbox.setOnClickListener {
                clickListener(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToastTestViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_test_toast, parent, false)
        return ToastTestViewHolder(view, clickListener)
    }

    override fun getItemCount(): Int {
        return mainList.size
    }

    override fun onBindViewHolder(holder: ToastTestViewHolder, position: Int) {
        holder.textView.text = mainList[position]
        holder.checkbox.isChecked = false
    }

    fun updateList(listUpdated : List<String>) {
        mainList.clear()
        mainList.addAll(listUpdated)
        notifyDataSetChanged()
    }
}