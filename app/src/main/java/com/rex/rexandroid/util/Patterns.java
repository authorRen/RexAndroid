/*
 * Copyright (C) 2007 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.rex.rexandroid.util;

import android.text.TextUtils;

import java.util.regex.Pattern;

/**
 * 正则表达式相关工具类
 */
public final class Patterns {
    /** 正则表达式需要转义的字符，当试图匹配这些字符时会报 java.util.regex.PatternSyntaxException */
    public static final String[] ESC_CHAR = {"\\", "$", "(", ")", "*", "+", ".", "[", "]", "?", "^", "{", "}", "|"};
    public static final String ESC_TEXT = "\\$()*+.[]?^{}|";
    /** 是否包含正则特殊字符的正则表达式，当试图匹配这些字符时会报 java.util.regex.PatternSyntaxException */
    //1.至少包含一个特殊字符 2. 可以包含其他字符
    public static final String ESC_REGEX = "^.*[\\\\?^|$*+.()\\[\\]{}]+.*$";
    
    /** 1.至少一个汉字 2.全部都是汉字  */
    public static final String ALL_CHINESE_REGEX = "^[\\u4e00-\\u9fa5]+$";

    private Patterns() {
    }

    /** EMAIL CHECK. */
    public static final Pattern EMAIL_ADDRESS
            = Pattern.compile(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+"
    );

    public static final Pattern PHONE
            = Pattern.compile("17\\d{9}||13\\d{9}||14\\d{9}||15\\d{9}||18\\d{9}||16\\d{9}||19\\d{9}$");

    /**
     * 昵称检测 数字英文中文下划线
     */
    public static final Pattern NICK_NAME = Pattern.compile("^[a-zA-Z0-9_\u4e00-\u9fa5]+$");

    /** 密码限制，必须英文和数字组合. */
    public static final Pattern PWD_LIMIT = Pattern.compile("[a-zA-Z0-9]{6,16}");
    /** 验证码格式. */
    public static final Pattern YZM_LIMIT = Pattern.compile("[0-9]{6}");

    /** 是否包含正则表达式的特殊字符 */
    public static boolean containESCChar(String keyword) {
        return !StringUtil.isNullOrEmpty(keyword) && keyword.matches(ESC_REGEX);
    }

    /** 当 keyword 包含 {@link #ESC_TEXT} 时会报 PatternSyntaxException。把 keyword 中的特殊字符进行转义(每个特殊字符前添加\\) */
    public static Pattern convertPattern(String keyword) {
        if (keyword == null || TextUtils.isEmpty(keyword.trim())) {
            return null;
        }
        //先判断是否包含特殊字符，避免进入循环查找
        if (containESCChar(keyword)) {
            for (String key : ESC_CHAR) {
                if (keyword.contains(key)) {
                    keyword = keyword.replace(key, "\\" + key);
                }
            }
        }
        try {
            //可能抛出 PatternSyntaxException
            return Pattern.compile(keyword);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}