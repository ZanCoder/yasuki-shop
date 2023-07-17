package com.poly.Yasuki.service;

import com.poly.Yasuki.entity.GroupCategory;
import com.poly.Yasuki.entity.MyCategory;

import java.util.List;

public interface GroupCategoryService {
    List<GroupCategory> getAllCategoryGroupIsActive();

    GroupCategory create(GroupCategory groupCategory1);
}
