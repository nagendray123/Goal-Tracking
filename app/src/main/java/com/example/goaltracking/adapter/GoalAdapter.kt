package com.example.goaltracking.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.goaltracking.R
import com.example.goaltracking.model.Goal

class GoalAdapter(private val context: Context, private val listener: GoalClickListener) : RecyclerView.Adapter<GoalAdapter.GoalViewHolder>() {

    // this arraylist is at show the data in given point of the RecyclerView
    private val goalList = ArrayList<Goal>()
    // this arraylist is at fetch from the database
    private val fullList = ArrayList<Goal>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GoalViewHolder {
       return GoalViewHolder(
           LayoutInflater.from(context).inflate(R.layout.item_list, parent, false)
       )
    }

    override fun onBindViewHolder(holder: GoalAdapter.GoalViewHolder, position: Int) {
        val currentGoal = goalList[position]
        holder.title.text = currentGoal.title
        holder.deadline.text = currentGoal.deadline
        holder.categories.text = currentGoal.categories
        holder.description.text = currentGoal.description
        holder.date.text = currentGoal.date

        // this method is use to goal_layout
        holder.goalsLayout.setOnClickListener {
            listener.onItemClicked(goalList[holder.adapterPosition])
        }
        holder.goalsLayout.setOnLongClickListener {
            listener.onLongItemClicked(goalList[holder.adapterPosition], holder.goalsLayout)
            true
        }
    }

    override fun getItemCount(): Int {
        return goalList.size
    }

    // this function is use to update Goal

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newList: List<Goal>){
        fullList.clear()
        fullList.addAll(newList)

        goalList.clear()
        goalList.addAll(fullList)
        notifyDataSetChanged()
    }

    inner class GoalViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val goalsLayout: CardView = itemView.findViewById(R.id.card_layout)
        val title: TextView = itemView.findViewById(R.id.tv_title)
        val deadline: TextView = itemView.findViewById(R.id.tv_deadline)
        val categories: TextView = itemView.findViewById(R.id.tv_categories)
        val description: TextView = itemView.findViewById(R.id.tv_description)
        val date: TextView = itemView.findViewById(R.id.tv_date)
    }

    interface GoalClickListener{
        // this function is use to edit Goal
        fun onItemClicked(goal: Goal)
        // this function is use to delete Goal
        fun onLongItemClicked(goal: Goal, cardView: CardView)
    }
}