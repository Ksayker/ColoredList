package ksayker.coloredlist.parsers;

import android.graphics.Color;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * @author ksayker
 * @version 0.0.1
 * @since 26.12.16
 */
public class XmlParser {
    private static final String KEY_TAG_COLOR = "color";

    private static final String KEY_ATTRIBUTE_COLOR_NAME = "name";
    private static final String KEY_ATTRIBUTE_COLOR_VALUE = "color";

    public ColorContainer parseColors(XmlPullParser xmlPullParser){
        ColorContainer colorContainer = new ColorContainer();
        try {
            while (xmlPullParser.getEventType() != XmlPullParser.END_DOCUMENT){
                switch (xmlPullParser.getEventType()){
                    case XmlPullParser.START_TAG:
                        if (xmlPullParser.getName().equals(KEY_TAG_COLOR)){
                            //read color name
                            for (int i = 0 , n = xmlPullParser.getAttributeCount(); i < n; i++) {
                                if (xmlPullParser.getAttributeName(i).equals(KEY_ATTRIBUTE_COLOR_NAME)){
                                    colorContainer.colorNames.add(xmlPullParser.getAttributeValue(i));
                                    break;
                                }
                            }
                            //read color value
                            for (int i = 0 , n = xmlPullParser.getAttributeCount(); i < n; i++) {
                                if (xmlPullParser.getAttributeName(i).equals(KEY_ATTRIBUTE_COLOR_VALUE)){
                                    colorContainer.colorValues.add(Color.parseColor(xmlPullParser.getAttributeValue(i)));
                                    break;
                                }
                            }
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

        return colorContainer;
    }

    public class ColorContainer{
        public List<String> colorNames;
        public List<Integer> colorValues;

        public ColorContainer(){
            colorNames = new LinkedList<>();
            colorValues = new LinkedList<>();
        }
    }
}
