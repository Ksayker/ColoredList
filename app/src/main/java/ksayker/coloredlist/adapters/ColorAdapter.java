package ksayker.coloredlist.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import ksayker.coloredlist.R;
import ksayker.coloredlist.data.ItemColor;

/**
 * @author ksayker
 * @version 0.0.1
 * @since 20.12.16
 */
public class ColorAdapter extends RecyclerView.Adapter<ColorAdapter.ColorViewHolder> {
    private View.OnClickListener mListener;
    private Context mContext;

    private ArrayList<ItemColor> mItemColors;

    private int mColorDefaultBackground;

    public ColorAdapter(Context contexts, ArrayList<ItemColor> itemColors, int colorDefaultBackground, View.OnClickListener listener){
        mContext = contexts;
        mItemColors = itemColors;
        mColorDefaultBackground = colorDefaultBackground;
        mListener = listener;
    }

    private void changeSizeCardView(CardView cardView, float newSize){
        ViewGroup.LayoutParams lp = cardView.getLayoutParams();
        lp.height = (int) newSize;
        cardView.setLayoutParams(lp);
    }

    @Override
    public ColorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View rootView = layoutInflater.inflate(R.layout.card_view_color_item, parent, false);
        ColorViewHolder colorViewHolder = new ColorViewHolder(rootView);

        rootView.setOnClickListener(mListener);
        return colorViewHolder;
    }


    @Override
    public void onBindViewHolder(ColorViewHolder holder, int position) {
        holder.mTvColorName.setText(mItemColors.get(position).getName());

        if (mItemColors.get(position).isSelected()){
            holder.mCardView.setCardBackgroundColor(mItemColors.get(position).getColor());
            holder.mTvColorName.setTextColor(mColorDefaultBackground);
            changeSizeCardView(holder.mCardView, mContext.getResources().getDimension(R.dimen.activity_main_size_selected_item));
        } else {
            holder.mCardView.setCardBackgroundColor(mColorDefaultBackground);
            holder.mTvColorName.setTextColor(mItemColors.get(position).getColor());
            changeSizeCardView(holder.mCardView, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
    }

    @Override
    public int getItemCount() {
        return mItemColors.size();
    }

    class ColorViewHolder extends RecyclerView.ViewHolder{
        TextView mTvColorName;
        CardView mCardView;

        public ColorViewHolder(View itemView) {
            super(itemView);
            mTvColorName = (TextView) itemView.findViewById(R.id.card_view_tv_color_name);
            mCardView = (CardView) itemView.findViewById(R.id.card_view_cv_color_item);
        }
    }
}
