package ksayker.coloredlist.activitys;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.util.LinkedList;
import java.util.List;

import ksayker.coloredlist.R;
import ksayker.coloredlist.adapters.ColorAdapter;
import ksayker.coloredlist.listeners.ColorCardViewItemClickListener;
import ksayker.coloredlist.parsers.XmlParser;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRvColors;
    private RecyclerView.Adapter mColorsAdapter;
    private RecyclerView.LayoutManager mColorsLayoutManager;

    private boolean[] mSelectedItems;
    private int[] mColorValues;
    private String[] mColorNames;
    private int mColorDefaultBackground;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        initUI();
    }

    private void initData(){
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
                new ColorCardViewItemClickListener(mRvColors, mSelectedItems),
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
}
