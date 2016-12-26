package ksayker.coloredlist.activitys;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import ksayker.coloredlist.R;
import ksayker.coloredlist.adapters.ColorAdapter;
import ksayker.coloredlist.parsers.XmlParser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private RecyclerView mRvColors;
    private RecyclerView.Adapter mColorsAdapter;
    private RecyclerView.LayoutManager mColorsLayoutManager;

    private boolean[] mSelectedItems;
    private int[] mColorValues;
    private String[] mColorNames;
    private int mColorDefaultBackground;

    private int mPreviousIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        initUI();
    }

    private void initData(){
        mPreviousIndex = -1;

        XmlParser.ColorContainer colorContainer = new XmlParser().parseColors(getResources().getXml(R.xml.colors));
        mColorNames = new String[colorContainer.colorNames.size()];
        colorContainer.colorNames.toArray(mColorNames);
        mColorValues = convertListIntegerToArrayInt(colorContainer.colorValues);

        mSelectedItems = new boolean[mColorNames.length];

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mColorDefaultBackground = getResources().getColor(R.color.color_card_view_default_color, getTheme());
        }else {
            mColorDefaultBackground = getResources().getColor(R.color.color_card_view_default_color);
        }
    }

    private void initUI(){
        mRvColors = (RecyclerView) findViewById(R.id.activity_main_rv_colors_list);
        mColorsLayoutManager = new LinearLayoutManager(this);
        mColorsAdapter = new ColorAdapter(
                getApplicationContext(),
                mColorNames,
                mColorValues,
                mColorDefaultBackground,
                this,
                mSelectedItems
        );

        mRvColors.setLayoutManager(mColorsLayoutManager);
        mRvColors.setAdapter(mColorsAdapter);
    }

    private int[] convertListIntegerToArrayInt(List<Integer> list){
        int[] result = new int[list.size()];
        int i = 0;
        for (Integer value : list){
            result[i++] = value;
        }

        return result;
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
