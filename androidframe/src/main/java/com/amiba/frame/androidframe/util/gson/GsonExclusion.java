package com.amiba.frame.androidframe.util.gson;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * com.amiba.frame.androidframe.util.gson
 * Created by wudl on 17/9/7.
 * gson转换器的过滤器：转换的时候不转换被指定的属性名称
 */

public class GsonExclusion implements ExclusionStrategy {

    private List<String> exclusionFields;//要被排除的属性名称集合

    /**
     * 添加需要被排除的属性名称
     *
     * @param fieldName
     * @return
     */
    public GsonExclusion addExclusionField(String... fieldName) {
        if (exclusionFields == null) {
            exclusionFields = new ArrayList<>();
        }
        if (fieldName != null && fieldName.length > 0) {
            Collections.addAll(exclusionFields, fieldName);
        }
        return this;
    }

    @Override
    public boolean shouldSkipField(FieldAttributes f) {
        String fieldName = f.getName();
        return exclusionFields.contains(fieldName);
    }

    @Override
    public boolean shouldSkipClass(Class<?> clazz) {
        // FIXME Auto-generated method stub
        return false;
    }

    public List<String> getExclusionFields() {
        return exclusionFields;
    }

    public void setExclusionFields(List<String> exclusionFields) {
        this.exclusionFields = exclusionFields;
    }
}
