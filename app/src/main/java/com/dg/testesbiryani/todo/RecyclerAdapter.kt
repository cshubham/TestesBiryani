package com.dg.testesbiryani.todo

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.compose.ui.graphics.Color
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dg.testesbiryani.R

class RecyclerAdapter(private val listTodo: List<String>, private val clickListener: (Int) -> Unit) :
    RecyclerView.Adapter<RecyclerAdapter.BaseViewHolder>() {

    var listData : MutableList<String> = mutableListOf()

//    init {
//        listData.addAll(listTodo)
//    }

    open class BaseViewHolder(view: View) : RecyclerView.ViewHolder(view)

    class ViewHolder(view: View, clickListener: (Int) -> Unit) : BaseViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.item)

        init {
            view.setOnClickListener {
                clickListener(adapterPosition)
            }
        }
    }

    class ViewHolderImg (view: View, clickListener: (Int) -> Unit) : BaseViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.item)

        init {
            view.setOnClickListener {
                clickListener(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val viewH = if (viewType == 0) {
            LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item, parent, false).let {
                    ViewHolder(it, clickListener)
                }
        } else {
            LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item_image, parent, false).let {
                    ViewHolderImg(it, clickListener)
                }
        }
        return viewH
    }

    override fun getItemViewType(position: Int): Int {
        return if (position % 5 == 0) 1 else 0
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        Log.d("toast", "onBindvm " + position + " " + listData[position])
        if (holder is ViewHolder)
            holder.textView.text = listData[position]
        else if(holder is ViewHolderImg)
            holder.imageView.setImageResource(R.drawable.ic_launcher_foreground)
    }

    fun updatedata(listTodo: List<String>) {
//        listData.clear()
//        listData.addAll(listTodo)

        val diffResult = DiffUtil.calculateDiff(MyDiffUtil(this.listData, listTodo))
        listData = listTodo.toMutableList() // Update the list

        Log.d("toast" ,"check diff result " + diffResult)
        diffResult.dispatchUpdatesTo(this)  // Apply changes
    }
}


class MyDiffUtil(
    private val oldList: List<String>,
    private val newList: List<String>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    // Check if items are the same (e.g., based on an ID)
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

    // Check if content inside items has changed
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}