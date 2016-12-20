package ksayker.coloredlist.listeners;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * @author ksayker
 * @version 0.0.1
 * @since 20.12.16
 */
public class ColorCardViewItemClickListener implements View.OnClickListener {
    private RecyclerView mRvColors;
    private int mPreviousIndex;
    private boolean[] mSelectedItems;

    public ColorCardViewItemClickListener(RecyclerView rvColors, boolean[] selectedItems){
        mRvColors = rvColors;
        mSelectedItems = selectedItems;

        mPreviousIndex = -1;
    }

    @Override
    public void onClick(View view) {
        int itemIndex = mRvColors.getChildLayoutPosition(view);

        if (mPreviousIndex != -1){
            mSelectedItems[mPreviousIndex] = false;
            mRvColors.getAdapter().notifyItemChanged(mPreviousIndex);
        }
        if (mPreviousIndex == itemIndex){
            mSelectedItems[itemIndex] = false;
            mRvColors.getAdapter().notifyItemChanged(itemIndex);
            mPreviousIndex = -1;
        } else {
            mSelectedItems[itemIndex] = true;
            mRvColors.getAdapter().notifyItemChanged(itemIndex);
            mPreviousIndex = itemIndex;
        }
    }
}
