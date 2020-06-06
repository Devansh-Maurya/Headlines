package maurya.devansh.headlines.screens

import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Devansh on 7/6/20
 */

abstract class BaseViewHolder<T>(itemView: View): RecyclerView.ViewHolder(itemView) {

    abstract fun bind(item: T)
}