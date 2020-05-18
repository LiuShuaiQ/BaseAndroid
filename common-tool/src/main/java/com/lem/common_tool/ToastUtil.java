package com.lem.common_tool;

import android.content.Context;
import android.widget.Toast;

public class ToastUtil {
    public static void showShortToast(Context context, String toastStr) {
        Toast.makeText(context, toastStr, Toast.LENGTH_SHORT).show();
    }

    public static void showShortToast(Context context, int toastStrRes) {
        showShortToast(context, context.getResources().getString(toastStrRes));
    }
}
