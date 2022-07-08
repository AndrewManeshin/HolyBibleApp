package com.example.holybibleapp.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.holybibleapp.R

class BibleAdapter(
    private val retry: Retry,
    private val collapseListener: CollapseListener
) : RecyclerView.Adapter<BibleAdapter.BibleViewHolder>() {

    private val books = ArrayList<BookUI>()

    fun update(new: List<BookUI>) {
        val diffUtilCallback = DiffUtilCallback(books, new)
        val result = DiffUtil.calculateDiff(diffUtilCallback)
        books.clear()
        books.addAll(new)
        result.dispatchUpdatesTo(this)
    }

    override fun getItemViewType(position: Int) = when (books[position]) {
        is BookUI.Base -> 0
        is BookUI.Fail -> 1
        is BookUI.Testament -> 2
        is BookUI.Progress -> 3
        else -> -1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = when (viewType) {
        0 -> BibleViewHolder.Base(R.layout.book_layout.makeView(parent))
        1 -> BibleViewHolder.Fail(R.layout.fail_fullscreen.makeView(parent), retry)
        2 -> BibleViewHolder.Testament(R.layout.testament.makeView(parent), collapseListener)
        else -> BibleViewHolder.FullScreenProgress(R.layout.progress_fullscreen.makeView(parent))
    }

    override fun onBindViewHolder(holder: BibleViewHolder, position: Int) {
        holder.bind(books[position])
    }

    override fun getItemCount() = books.size

    abstract class BibleViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        open fun bind(book: BookUI) {}

        class FullScreenProgress(view: View) : BibleViewHolder(view)

        abstract class Info(view: View) : BibleViewHolder(view) {
            private val name = itemView.findViewById<TextView>(R.id.textView)
            override fun bind(book: BookUI) {
                book.map(object : BookUI.StringMapper {
                    override fun map(text: String) {
                        name.text = text
                    }
                })
            }
        }

        class Base(view: View) : Info(view)

        class Testament(view: View, private val collapse: CollapseListener) : Info(view) {
            private val collapseView = itemView.findViewById<ImageView>(R.id.collapseView)
            override fun bind(book: BookUI) {
                super.bind(book)
                itemView.setOnClickListener {
                    book.collapseOrExpand(collapse)
                }
                book.showCollapsed(object : BookUI.CollapseMapper{
                    override fun show(collapsed: Boolean) {
                        val iconId = if (collapsed) {
                            R.drawable.ic_baseline_expand_more_24
                        } else {
                            R.drawable.ic_baseline_expand_less_24
                        }
                        collapseView.setImageResource(iconId)
                    }
                })
            }
        }

        class Fail(view: View, private val retry: Retry) : BibleViewHolder(view) {
            private val message = itemView.findViewById<TextView>(R.id.massageTextView)
            private val button = itemView.findViewById<Button>(R.id.tryAgainButton)
            override fun bind(book: BookUI) {
                book.map(object : BookUI.StringMapper {
                    override fun map(text: String) {
                        message.text = text
                    }
                })

                button.setOnClickListener {
                    retry.tryAgain()
                }
            }
        }
    }

    interface CollapseListener {
        fun collapseOrExpand(id: Int)
    }

    interface Retry {
        fun tryAgain()
    }

    private fun Int.makeView(parent: ViewGroup) =
        LayoutInflater.from(parent.context).inflate(this, parent, false)
}

