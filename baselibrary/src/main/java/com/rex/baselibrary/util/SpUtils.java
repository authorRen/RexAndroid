package com.rex.baselibrary.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v4.util.SimpleArrayMap;

import java.util.Map;

/**
 * Date:2018/6/30
 * author:Rex
 * <p>
 * description:sp工具类
 */
public class SpUtils {
    private static final String TAG = "SPUtils";
    private static final SimpleArrayMap<String, SpUtils> SP_UTILS_MAP = new SimpleArrayMap<>();
    private SharedPreferences sp;
    private SharedPreferences.Editor edit;

    @SuppressLint("CommitPrefEdits")
    private SpUtils(Context context, String spName) {
        sp = context.getSharedPreferences(spName, Context.MODE_PRIVATE);
        edit = sp.edit();
    }

    public static SpUtils getInstance(Context context) {
        return getInstance(context, "");
    }

    /**
     * 获取sp实例
     *
     * @param spName sp名
     * @return {@link SpUtils}
     */
    private static SpUtils getInstance(Context context, String spName) {
        if (isSpace(spName)) {
            spName = TAG;
        }
        SpUtils spUtils = SP_UTILS_MAP.get(spName);
        if (spUtils == null) {
            spUtils = new SpUtils(context, spName);
            SP_UTILS_MAP.put(spName, spUtils);
        }
        return spUtils;
    }

    /**
     * sp中写入String
     *
     * @param key       键
     * @param value     值
     */
    public void putString(@NonNull String key, @NonNull String value) {
        putString(key, value, false);
    }

    /**
     * sp中的String
     *
     * @param key       键
     * @param value     值
     * @param isCommit  {@code true}: {@link SharedPreferences.Editor#commit()}<br>
     *                 {@code false}: {@link SharedPreferences.Editor#apply()}
     */
    public void putString(@NonNull String key, @NonNull String value, boolean isCommit) {
        if (isCommit) {
            edit.putString(key, value).commit();
        } else {
            edit.putString(key, value).apply();
        }
    }

    /**
     * sp中读取String
     *
     * @param key   键
     * @return      存在返回对应值，不存在返回默认值{@code ""}
     */
    public String getString(@NonNull String key) {
        return getString(key, "");
    }

    /**
     *sp中读取String
     *
     * @param key   键
     * @param defaultValue  默认值
     * @return  存在返回对应值，不存在返回默认值{@code defaultValue}
     */
    public String getString(@NonNull String key, String defaultValue) {
        return sp.getString(key, defaultValue);
    }

    /**
     * sp存入int
     *
     * @param key       鍵
     * @param value     值
     */
    public void putInt(@NonNull String key, int value) {
        putInt(key, value, false);
    }

    /**
     * sp存入int
     *
     * @param key       键
     * @param value     值
     * @param isCommit  {@code true}: {@link SharedPreferences.Editor#commit()}<br>
     *                 {@code false}: {@link SharedPreferences.Editor#apply()}
     */
    public void putInt(@NonNull String key, int value, boolean isCommit) {
        if (isCommit) {
            edit.putInt(key, value).commit();
        } else {
            edit.putInt(key, value).apply();
        }
    }

    /**
     * sp中获取 int
     *
     * @param key 键
     * @return    存在返回对应值，不存在返回默认值-1
     */
    public int getInt(@NonNull String key) {
        return sp.getInt(key, -1);
    }

    /**
     * sp中获取 int
     *
     * @param key           键
     * @param defaultValue  默认值
     * @return  存在返回对应值，不存在返回默认值{@code defaultValue}
     */
    public int getInt(@NonNull String key, int defaultValue) {
        return sp.getInt(key, defaultValue);
    }

    public void putLong(@NonNull String key, long value) {
        putLong(key, value, false);
    }

    public void putLong(@NonNull String key, long value, boolean isCommit) {
        if (isCommit) {
            edit.putLong(key, value).commit();
        } else {
            edit.putLong(key, value).apply();
        }
    }

    public long getLong(@NonNull String key) {
        return sp.getLong(key, -1L);
    }

    public long getLong(@NonNull String key, long defaultValue) {
        return sp.getLong(key, defaultValue);
    }

    public void putBoolean(@NonNull String key, boolean value) {
        putBoolean(key, value, false);
    }

    public void putBoolean(@NonNull String key, boolean value, boolean isCommit) {
        if (isCommit) {
            edit.putBoolean(key, value).commit();
        } else {
            edit.putBoolean(key, value).apply();
        }
    }

    public boolean getBoolean(@NonNull String key) {
        return sp.getBoolean(key, false);
    }

    public boolean getBoolean(@NonNull String key, boolean defaultValue) {
        return sp.getBoolean(key, defaultValue);
    }

    /**
     * sp中获取所有键值对
     *
     * @return Map对象
     */
    public Map<String, ?> getAll() {
        return sp.getAll();
    }

    /**
     * sp中是否存在此key
     *
     * @param key   键
     * @return      {@code true}: 存在<br>{@code false}: 不存在
     */
    public boolean contains(@NonNull String key) {
        return sp.contains(key);
    }

    /**
     * sp移除该key
     *
     * @param key 健
     */
    public void remove(@NonNull String key) {
        remove(key, false);
    }

    /**
     * sp移除该key
     *
     * @param key       健
     * @param isCommit  {@code true}: {@link SharedPreferences.Editor#commit()}<br>
     *                 {@code false}: {@link SharedPreferences.Editor#apply()}
     */
    public void remove(@NonNull String key, final boolean isCommit) {
        if (isCommit) {
            edit.remove(key).commit();
        } else {
            edit.remove(key).apply();
        }
    }

    /**
     * SP 中清除所有数据
     */
    public void clear() {
        clear(false);
    }

    /**
     * SP 中清除所有数据
     */
    public void clear(boolean isCommit) {
        if (isCommit) {
            edit.clear().commit();
        } else {
            edit.clear().apply();
        }
    }

    private static boolean isSpace(String s) {
        if (s == null) {
            return true;
        }
        for (int i = 0; i < s.length(); i++) {
            if (!Character.isWhitespace(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
