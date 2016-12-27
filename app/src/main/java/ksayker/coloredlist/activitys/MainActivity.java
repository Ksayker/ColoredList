package ksayker.coloredlist.activitys;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ksayker.coloredlist.R;
import ksayker.coloredlist.adapters.ColorAdapter;
import ksayker.coloredlist.data.ItemColor;
import ksayker.coloredlist.parsers.XmlParser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private RecyclerView mRvColors;
    private RecyclerView.Adapter mColorsAdapter;
    private RecyclerView.LayoutManager mColorsLayoutManager;

    private ArrayList<ItemColor> mItemColors;

    private int mColorDefaultBackground;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        initUI();
    }

    private void initData(){
        mItemColors = new XmlParser().parseColors(getResources().getXml(R.xml.colors));

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
                mItemColors,
                mColorDefaultBackground,
                this
        );

        mRvColors.setLayoutManager(mColorsLayoutManager);
        mRvColors.setAdapter(mColorsAdapter);
    }

    @Override
    public void onClick(View view) {
        int itemIndex = mRvColors.getChildLayoutPosition(view);
        if (mItemColors.get(itemIndex).isSelected()){
            mItemColors.get(itemIndex).setSelected(false);
        } else {
            for (int i = 0; i < mItemColors.size(); i++) {
                if (mItemColors.get(i).isSelected()){
                    mItemColors.get(i).setSelected(false);
                    break;
                }
            }
            mItemColors.get(itemIndex).setSelected(true);
        }
        mRvColors.getAdapter().notifyDataSetChanged();
    }
}
