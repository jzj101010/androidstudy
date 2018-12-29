package com.suishou.ems.Util;

import android.text.TextUtils;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by j on 2018/9/5 0005.
 */

public class TvUtils {
    public static DecimalFormat amountFormat = new DecimalFormat("#,###.####");
    public static DecimalFormat amountFormat2 = new DecimalFormat("#.##");

    private TvUtils() {
    }

    public static boolean isEmptyTv(TextView textView) {
        return textView == null || "".equals(textView.getText().toString());
    }

    static public boolean isEmptyTv(TextView textView, boolean b, String toa) {
        boolean re = textView == null || "".equals(textView.getText().toString());
        if (re && b) {
            Utils.showToast(toa);
        }
        return re;
    }

    /**
     * 对接口返回时间处理
     *
     * @param time
     * @param emptyDefaultStr time为空 或 null 返回默认
     * @param isShowSecond    是否显示时分秒
     * @return null或“”  返回 defaultStr
     * 时间戳      返回转换后时间字符串
     * 转换失败（不是时间戳格式)    返回原始值
     */
    public static String TimeStrHandle(String time, String emptyDefaultStr, boolean isShowSecond) {
        String result;
        if (TextUtils.isEmpty(time) || "null".equals(time)) {
            result = emptyDefaultStr;
        } else {
            try {
                if (isShowSecond) {
                    result = getSdfHms().format(new Date(Long.parseLong(time)));
                } else {
                    result = getSdf().format(new Date(Long.parseLong(time)));
                }
            } catch (Exception e) {
                result = time;
            }
        }
        return result;
    }

    public static String TimeStrHandle(String time, boolean isShowSecond) {
        return TimeStrHandle(time, "", isShowSecond);
    }

    /**
     * 对接口返回金钱数字处理
     *
     * @param number
     * @param emptyDefaultStr time为空 或 null 返回默认
     * @param isShowComma     是否使用逗号分割 （千分号）
     * @return null或“”  返回 defaultStr
     * 时间戳      返回转换后字符串
     * 转换失败（不是时间戳格式)    返回原始值
     */
    public static String numberStrHandle(String number, String emptyDefaultStr, boolean isShowComma, DecimalFormat formatObj) {
        String result;
        if (TextUtils.isEmpty(number) || "null".equals(number)) {
            result = emptyDefaultStr;
        } else {
            try {
                if (formatObj == null) {
                    if (isShowComma) {
                        result = amountFormat.format(Double.valueOf(number));
                    } else {
                        result = amountFormat2.format(Double.valueOf(number));
                    }
                } else {
                    result = formatObj.format(Double.valueOf(number));
                }
            } catch (Exception e) {
                result = number;
            }
        }
        return result;
    }

    public static String numberStrHandle(String number, boolean isShowComma) {
        return numberStrHandle(number, "", isShowComma, null);
    }

    public static String numberStrHandle(String number, String emptyDefaultStr, boolean isShowComma) {
        return numberStrHandle(number, emptyDefaultStr, isShowComma, null);
    }

    public static String numberStrHandle(String number, String emptyDefaultStr, DecimalFormat formatObj) {
        return numberStrHandle(number, emptyDefaultStr, false, formatObj);
    }

    public static String numberStrHandle(String number, DecimalFormat formatObj) {
        return numberStrHandle(number, "", false, formatObj);
    }

    public static SimpleDateFormat getSdfHms() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }


    public static SimpleDateFormat getSdf() {
        return new SimpleDateFormat("yyyy-MM-dd");
    }

    /**
     * 日期格式字符串转换成时间戳
     *
     * @param date_str     字符串日期
     * @param isShowSecond 是否有秒 如：yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String date2TimeStamp(String date_str, boolean isShowSecond) {
        try {
            SimpleDateFormat sdf = isShowSecond ? getSdfHms() : getSdf();
            return String.valueOf(sdf.parse(date_str).getTime() / 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date_str;
    }


    public static String dateSpliHMS(String time, String defaultStr) {

        String result;
        if (TextUtils.isEmpty(time) || "null".equals(time)) {
            result = defaultStr;
        } else {
            try {
                String[] str = time.split(" ");
                result = str[0];
            } catch (Exception e) {
                result = time;
            }
        }
        return result;
    }

    public static String dateSpliHMS(String time) {
        return dateSpliHMS(time, "");
    }
}
