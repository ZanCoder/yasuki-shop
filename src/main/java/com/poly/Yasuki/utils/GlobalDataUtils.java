package com.poly.Yasuki.utils;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class GlobalDataUtils {
    public static  List<String> getTitleCompare(){
        List<String> listTitleCompare = new ArrayList<>();
        listTitleCompare.add("Loại da phù hợp");
        listTitleCompare.add("Giải pháp cho tình trạng da");
        listTitleCompare.add("Ưu điểm nổi bật");
        listTitleCompare.add("Hiệu quả sử dụng");
        listTitleCompare.add("Độ an toàn");
        listTitleCompare.add("Bảo quản");
        return listTitleCompare;
    }
}
