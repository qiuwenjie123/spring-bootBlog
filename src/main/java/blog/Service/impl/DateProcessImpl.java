package blog.Service.impl;

import blog.Service.DateProcess;
import org.springframework.stereotype.Service;


import java.text.SimpleDateFormat;
import java.util.Date;


@Service
public class DateProcessImpl implements DateProcess {

    @Override
    public String cutToShort(Date date,int type) {
        String s=null;
        if(type==1){
            s="yyyy.MM.dd ";
        }else{
            s="yyyy.MM.dd HH:mm:ss";
        }
        SimpleDateFormat simple=new SimpleDateFormat(s);
        return simple.format(date);
    }

}
