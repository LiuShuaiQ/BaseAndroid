package com.lem.common_tool;

import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;

public class NumInputFilter implements InputFilter {

    private int mNumDecimalDigits = 2;
    private int mNumIntDigits = 4;

    public NumInputFilter(int digits, int intLength) {
        mNumDecimalDigits = digits;
        mNumIntDigits = intLength;
    }

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        // 删除等特殊字符，直接返回
        if ("".equals(source.toString())) {
            return null;
        }
        if (".".equals(source.toString())) {
            if (TextUtils.isEmpty(dest.toString())) {
                return "";
            } else {
                return null;
            }
        }
        String dValue = dest.toString();
        if (!dValue.contains(".")) {
            String intVal = dValue;
            int diff = intVal.length() + 1 - mNumIntDigits;
            if (diff > 0) {
                return source.subSequence(start, end - diff);
            }
        }
        String[] splitArray = dValue.split("\\.");
        if (splitArray.length > 1) {
            String dotValue = splitArray[1];
            int diff = dotValue.length() + 1 - mNumDecimalDigits;
            if (diff > 0) {
                return source.subSequence(start, end - diff);
            }
        }

        return null;
    }
}
