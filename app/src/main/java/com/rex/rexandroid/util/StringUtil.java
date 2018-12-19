package com.rex.rexandroid.util;

import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;


import com.rex.rexandroid.component.RexApp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具类
 * Created by HuoGuangXu on 2016/9/14.
 */

public class StringUtil {
    /**
     * 判断字符串是否为null或""
     */
    public static boolean isNullOrEmpty(String string) {
        return string == null || string.trim().isEmpty();
    }

    public static boolean isNotNullOrEmpty(String string) {
        return !isNullOrEmpty(string);
    }

    /**
     * 判断可变字符串是否包含空(null或empty)字符串
     */
    public static boolean isAnyEmpty(String... strings) {
        if (strings == null || strings.length < 1) {
            return true;
        }
        for (String str : strings) {
            if (isNullOrEmpty(str)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 字符串都不为null或""返回true,否则返回false.
     */
    public static boolean isNoneEmpty(final String... strings) {
        return !isAnyEmpty(strings);
    }

    /** 如果 text == null ,返回空字符串 */
    public static String getStringExceptNull(String text) {
        return text != null ? text : "";
    }

    /** 如果 text == null ,返回默认字符串 */
    public static String getStringExceptNull(String text, String defaultText) {
        return text != null ? text : defaultText;
    }

    /**
     * 判断数组是否为null或""
     */
    public static boolean isArrayEmpty(String[] array) {
        return array == null || array.length == 0;
    }

    /**
     * 设置字符串中指定字符的样式。<br/>
     * keyword 为"\"时，Pattern.compile会报 PatternSyntaxException。
     *
     * @param context 上下文
     * @param text    字符串
     * @param keyword 关键字
     * @param colorId 指定字体的颜色.-1不设置颜色
     * @param sizeId  指定字体的大小.-1不设置字体大小
     * @return 特殊的字符串
     */
    public static SpannableStringBuilder buildSingleTextStyle(Context context, String text, String keyword, @ColorRes int colorId, @DimenRes int sizeId) {
        if (context == null || StringUtil.isNullOrEmpty(text)) {
            return SpannableStringBuilder.valueOf("");
        }
        SpannableStringBuilder style = SpannableStringBuilder.valueOf(text);
        if (StringUtil.isNullOrEmpty(keyword)) {
            return style;
        }
        Pattern p = Patterns.convertPattern(keyword);
        if (p == null) {
            return style;
        }
        Matcher matcher = p.matcher(text);
        while (matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();
            if (colorId != -1) {
                style.setSpan(new ForegroundColorSpan(ContextCompat.getColor(context, colorId)), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
            if (sizeId != -1) {
                style.setSpan(new AbsoluteSizeSpan((int) context.getResources().getDimension(sizeId)), start, end, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
            }
        }
        return style;
    }

    /**
     * 设置字符串中指定字符的样式
     *
     * @param context 上下文
     * @param text    字符串
     * @param start   关键字开始位置
     * @param end     关键字结束位置
     * @param colorId 关键字的颜色.-1不设置颜色
     * @param sizeId  关键字的大小.-1不设置字体大小
     */
    public static SpannableStringBuilder buildSingleTextStyle(Context context, @NonNull String text, int start, int end, @ColorRes int colorId, @DimenRes int sizeId) {
        if (context == null || StringUtil.isNullOrEmpty(text)) {
            return SpannableStringBuilder.valueOf("");
        }
        SpannableStringBuilder style = SpannableStringBuilder.valueOf(text);
        if (colorId != -1) {
            style.setSpan(new ForegroundColorSpan(ContextCompat.getColor(context, colorId)), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        if (sizeId != -1) {
            style.setSpan(new AbsoluteSizeSpan((int) context.getResources().getDimension(sizeId)), start, end, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        }
        return style;
    }

    /**
     * 设置字符串中指定多个字符的样式
     *
     * @param context       上下文
     * @param text          字符串
     * @param keywordArray  关键字数组。要一一对应。
     * @param colorResArray 颜色值资源数组。要一一对应。-1不设置颜色
     * @param sizeResArray  字符大小资源数组。要一一对应。-1不设置大小
     * @return 特殊的字符串
     */
    public static SpannableStringBuilder buildMulTextStyle(Context context, @NonNull String text, String[] keywordArray, @ColorRes int[] colorResArray, @DimenRes int[] sizeResArray) {
        if (context == null || StringUtil.isNullOrEmpty(text)) {
            return SpannableStringBuilder.valueOf("");
        }
        SpannableStringBuilder spannableBuilder = SpannableStringBuilder.valueOf(text);

        for (int i = 0; i < keywordArray.length; i++) {
            Pattern p = Patterns.convertPattern(keywordArray[i]);
            if (p == null) {
                continue;
            }
            final Matcher matcher = p.matcher(text);
            while (matcher.find()) {
                int start = matcher.start();
                int end = matcher.end();
                if (colorResArray[i] != -1) {
                    spannableBuilder.setSpan(new ForegroundColorSpan(ContextCompat.getColor(context, colorResArray[i])), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                }
                if (sizeResArray[i] != -1) {
                    spannableBuilder.setSpan(new AbsoluteSizeSpan((int) context.getResources().getDimension(sizeResArray[i]), false), start, end, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
                }
            }
        }
        return spannableBuilder;
    }

    /**
     * 给特点文字的设置点击事件
     *
     * @param textView      TextView 对象
     * @param text          TextView 显示的文字
     * @param keyword       响应点击事件的文字
     * @param clickTextSpan 点击事件回调
     * @param isOnlyFirst   是否只匹配首页关键字
     */
    public static void applyClickSpan(TextView textView, String text, String keyword, ClickTextSpan clickTextSpan, boolean isOnlyFirst) {
        if (textView == null || StringUtil.isAnyEmpty(text, keyword)) {
            return;
        }
        SpannableStringBuilder style = new SpannableStringBuilder(text);
        Pattern p = Patterns.convertPattern(keyword);
        if (p == null) {
            return;
        }
        Matcher matcher = p.matcher(text);
        while (matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();
            style.setSpan(clickTextSpan, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            if (isOnlyFirst) {
                break;
            }
        }
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        textView.setText(style);
    }

    public static String getString(@StringRes int resId, Object... formatArgs) {
        return RexApp.getAppContext().getString(resId, formatArgs);
    }

    public static String getString(@StringRes int resId) {
        return RexApp.getAppContext().getString(resId);
    }

    /** 获取第一个不为null/""的字符串 */
    public static String getFirstNotNullOrEmpty(final String... strings) {
        if (strings == null || strings.length <= 0) {
            return "";
        }
        for (String str : strings) {
            if (StringUtil.isNotNullOrEmpty(str)) {
                return str;
            }
        }
        return "";
    }

    /** 去掉字符串中的空格。包括回车、换行和制表符。 */
    public static String trimAllSpace(String text) {
        if (isNullOrEmpty(text)) {
            return "";
        }
        return text.replaceAll("\\s*", "");
    }

    /** 把字符串中的空格、回车、换行和制表符替换成指定字符 */
    public static String replaceAllSpace(String text, @NonNull String replacement) {
        if (isNullOrEmpty(text)) {
            return "";
        }
        if (isNullOrEmpty(replacement)) {
            return text;
        }
        return text.replaceAll("\\s+", replacement);
    }

    /**
     * 联结多个非 null 字符串。
     *
     * @param exceptEmpty 是否排除""字符串
     */
    public static String concat(boolean exceptEmpty, String... texts) {
        if (texts == null || texts.length <= 0) {
            return "";
        }
        final StringBuilder sb = new StringBuilder();
        for (String text : texts) {
            if (text == null) {
                continue;
            }
            if (exceptEmpty) {
                if (!TextUtils.isEmpty(text.trim())) {
                    sb.append(text);
                }
            } else {
                sb.append(text);
            }
        }
        return sb.toString();
    }
}