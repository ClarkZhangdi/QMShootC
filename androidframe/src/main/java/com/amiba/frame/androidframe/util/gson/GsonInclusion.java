package com.amiba.frame.androidframe.util.gson;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * com.amiba.frame.androidframe.util.gson
 * Created by wudl on 17/9/7.
 */

public class GsonInclusion implements ExclusionStrategy {

    private List<String> inclusionFields;//要被转换的属性名称集合

    /**
     * 添加只需要被转换的属性名称
     *
     * @param fieldName 属性名称的可变数组
     * @return
     */
    public GsonInclusion addInclusionFields(String... fieldName) {
        if (inclusionFields == null) {
            inclusionFields = new ArrayList<>();
        }
        if (fieldName != null && fieldName.length > 0) {
            Collections.addAll(inclusionFields, fieldName);
        }
        return this;
    }

    @Override
    public boolean shouldSkipField(FieldAttributes f) {
        String fieldName = f.getName();
        return !inclusionFields.contains(fieldName);
    }

    @Override
    public boolean shouldSkipClass(Class<?> clazz) {
        // FIXME Auto-generated method stub
        return false;
    }

    public List<String> getinclusionFields() {
        return inclusionFields;
    }

    public void setinclusionFields(List<String> inclusionFields) {
        this.inclusionFields = inclusionFields;
    }
}
