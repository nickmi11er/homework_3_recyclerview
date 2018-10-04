package com.tinkoff.androidcourse

import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView


class WorkerAdapter(val workers: ArrayList<Worker>) : RecyclerView.Adapter<BaseViewHolder<Worker>>() {

    private val MALE_VIEW_TYPE = 1
    private val FEMALE_VIEW_TYPE = 2

    fun addWorker(worker: Worker) {
        workers.add(0, worker)
        notifyItemInserted(0)
    }

    override fun onCreateViewHolder(container: ViewGroup, viewType: Int): BaseViewHolder<Worker> {
        val viewId =
                if (viewType == MALE_VIEW_TYPE) R.layout.view_worker_male
                else R.layout.view_worker_female

        return WorkerViewHolder(LayoutInflater.from(container.context).inflate(viewId, container, false))
    }

    override fun onBindViewHolder(holder: BaseViewHolder<Worker>, position: Int) {
        holder.bind(workers[position])
    }

    override fun getItemViewType(position: Int): Int =
            if (workers[position].gender == Gender.MALE) MALE_VIEW_TYPE
            else FEMALE_VIEW_TYPE

    override fun getItemCount(): Int = workers.size

    class WorkerViewHolder(itemView: View) : BaseViewHolder<Worker>(itemView) {
        val nameView = itemView.findViewById<TextView>(R.id.name)
        val positionView = itemView.findViewById<TextView>(R.id.position)
        val ageView = itemView.findViewById<TextView>(R.id.age)
        val avatarView = itemView.findViewById<ImageView>(R.id.avatar)

        override fun bind(item: Worker) {
            item.apply {
                nameView.text = name
                positionView.text = position
                ageView.text = "Age: $age"
                avatarView.setImageDrawable(ContextCompat.getDrawable(itemView.context, photo!!))
            }
        }
    }
}