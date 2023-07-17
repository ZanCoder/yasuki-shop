package com.poly.Yasuki.service;

import com.poly.Yasuki.entity.MyCategory;

import java.util.List;

public interface MyCategoryService {
    List<MyCategory> getAllCategory();

    MyCategory create(MyCategory category1);
}
