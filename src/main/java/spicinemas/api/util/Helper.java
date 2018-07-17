package spicinemas.api.util;

import org.jooq.tools.json.JSONArray;

public class Helper {

    public static String [] convertJsonArrayToStringArray(JSONArray jsonArray)
    {
        String[] strArr=null;
        if(jsonArray!=null) {
            strArr = new String[jsonArray.size()];
            {
                for(int i =0;i<strArr.length;i++)
                    strArr[i]=(String)jsonArray.get(i);
            }
        }

        return strArr;
    }
}
