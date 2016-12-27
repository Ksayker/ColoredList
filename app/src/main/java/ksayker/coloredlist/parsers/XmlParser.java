package ksayker.coloredlist.parsers;

import android.graphics.Color;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import ksayker.coloredlist.data.ItemColor;

/**
 * @author ksayker
 * @version 0.0.1
 * @since 26.12.16
 */
public class XmlParser {
    private static final String KEY_TAG_COLOR = "color";

    private static final String KEY_ATTRIBUTE_COLOR_NAME = "name";
    private static final String KEY_ATTRIBUTE_COLOR_VALUE = "color";

    public ArrayList<ItemColor> parseColors(XmlPullParser xmlPullParser){
        ArrayList<ItemColor> itemColors = new ArrayList<>();
        String colorName = null;
        int colorValue = Integer.MIN_VALUE;
        try {
            while (xmlPullParser.getEventType() != XmlPullParser.END_DOCUMENT){
                switch (xmlPullParser.getEventType()){
                    case XmlPullParser.START_TAG:
                        if (xmlPullParser.getName().equals(KEY_TAG_COLOR)){
                            //read color name
                            for (int i = 0 , n = xmlPullParser.getAttributeCount(); i < n; i++) {
                                if (xmlPullParser.getAttributeName(i).equals(KEY_ATTRIBUTE_COLOR_NAME)){
                                    colorName = xmlPullParser.getAttributeValue(i);
                                    break;
                                }
                            }
                            //read color value
                            for (int i = 0 , n = xmlPullParser.getAttributeCount(); i < n; i++) {
                                if (xmlPullParser.getAttributeName(i).equals(KEY_ATTRIBUTE_COLOR_VALUE)){
                                    colorValue = Color.parseColor(xmlPullParser.getAttributeValue(i));
                                    break;
                                }
                            }
                            itemColors.add(new ItemColor(false, colorValue, colorName));
                        }
                        break;
                    default:
                        break;
                }
                xmlPullParser.next();
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return itemColors;
    }
}
