package ui.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by vevinmoza on 3/31/16.
 */
public class RegexSearch {

    public static String findString(String pageSource,String begin,String end){

        if (pageSource == null || begin == null || end == null) {
            return null;
        }
        int start = pageSource.indexOf(begin);
        if (start != -1) {
            int end1 = pageSource.indexOf(end, start + begin.length());
            if (end1 != -1) {
                return pageSource.substring(start + begin.length(), end1);
            }
        }
        return null;

       /*
        Pattern p = Pattern.compile("?<=\\"+begin+").*?(?=\\"+end);

        Matcher m = p.matcher(pageSource);
        System.out.println(m.find());
        return m.group(1);*/
        //System.out.println(m.group(1));
    }
    public static String getString(String regex,String pageSource){
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(pageSource);
        System.out.println(m.find());
        return m.group(1);

    }
    public static List<String> getMultipleString(String regex, String pageSource,int index){
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(pageSource);
        List<String> matches = new ArrayList<String>();
        while(m.find()){
            matches.add(m.group(index));
        }
return matches;
    }

}
