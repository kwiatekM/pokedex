package pl.kwiatekmichal.pokedex.core.util

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class SpaceItemDecoration(
    private val type: SpaceItemDecorationType = SpaceItemDecorationType.Vertical,
    private val offset: Int
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        when (type) {
            SpaceItemDecorationType.Vertical -> addVerticalOffsets(outRect, view, parent, offset)
            else -> super.getItemOffsets(outRect, view, parent, state)
        }
    }

    private fun addVerticalOffsets(outRect: Rect, view: View, parent: RecyclerView, margin: Int) {
        with(outRect) {
            left = margin
            if (parent.getChildAdapterPosition(view) == 0) {
                top = margin
            }
            right = margin
            bottom = margin
        }
    }

    sealed class SpaceItemDecorationType() {
        object Vertical : SpaceItemDecorationType()
    }
}