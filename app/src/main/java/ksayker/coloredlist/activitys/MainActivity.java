package ksayker.coloredlist.activitys;

import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;

import ksayker.coloredlist.R;
import ksayker.coloredlist.adapters.ColorAdapter;
import ksayker.coloredlist.listeners.ColorCardViewItemClickListener;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRvColors;
    private RecyclerView.Adapter mColorsAdapter;
    private RecyclerView.LayoutManager mColorsLayoutManager;
    private boolean[] mSelectedItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        initUI();
    }

    private void initData(){
        mSelectedItems = new boolean[getResources().getStringArray(R.array.color_pair_keys).length];
    }

    private void initUI(){
        int colorDefaultBackground;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            colorDefaultBackground = getResources().getColor(R.color.color_card_view_default_color, getTheme());
        }else {
            colorDefaultBackground = getResources().getColor(R.color.color_card_view_default_color);
        }

        mRvColors = (RecyclerView) findViewById(R.id.activity_main_rv_colors_list);
        mColorsLayoutManager = new LinearLayoutManager(this);
        mColorsAdapter = new ColorAdapter(
                getApplicationContext(),
                getResources().getStringArray(R.array.color_pair_keys),
                getResources().getIntArray(R.array.color_pair_values),
                colorDefaultBackground,
                new ColorCardViewItemClickListener(mRvColors, mSelectedItems),
                mSelectedItems
        );

        mRvColors.setLayoutManager(mColorsLayoutManager);
        mRvColors.setAdapter(mColorsAdapter);
    }
}
