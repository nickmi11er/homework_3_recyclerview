package com.tinkoff.androidcourse

import android.support.v4.content.ContextCompat
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*


class WorkerAdapter(var workers: ArrayList<Worker>) :
        RecyclerView.Adapter<BaseViewHolder<Worker>>(), ItemTouchHelperAdapter {
    private val MALE_VIEW_TYPE = 1

    private val FEMALE_VIEW_TYPE = 2
    fun addWorker(worker: Worker) {
        workers.add(0, worker)
        notifyItemInserted(0)
    }

    fun addWorkers(newWorkers: ArrayList<Worker>) {
        val disposable = Single.fromCallable {
            DiffUtil.calculateDiff(WorkerDiffUtilCallback(workers, newWorkers), false)
        }
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { diffResult ->
                    workers.addAll(0, newWorkers)
                    diffResult.dispatchUpdatesTo(this)
                }
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

    override fun onItemMove(fromPos: Int, toPos: Int) {
        if (fromPos < toPos) {
            for (i in fromPos until toPos) Collections.swap(workers, i, i + 1)
        } else {
            for (i in fromPos downTo toPos + 1) Collections.swap(workers, i, i - 1)
        }
        notifyItemMoved(fromPos, toPos)
    }

    override fun onItemDismiss(pos: Int) {
        workers.removeAt(pos)
        notifyItemRemoved(pos)
    }

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