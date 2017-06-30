package com.xiaoan.obd.obdproject.utils;

/**
 * author：Administrator on 2017/1/7 16:07
 * company: xxxx
 * email：1032324589@qq.com
 */

import android.content.Context;
import android.view.WindowManager;

import com.xiaoan.obd.obdproject.app.APP;

/**
 * dp、sp 转换为 px 的工具类
 *
 * @author fxsky 2012.11.12
 *
 */
public class DisplayUtil {
    /**
     * 将px值转换为dip或dp值，保证尺寸大小不变
     *
     * @param pxValue
     * @param scale
     *            （DisplayMetrics类中属性density）
     * @return
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 将dip或dp值转换为px值，保证尺寸大小不变
     *
     * @param dipValue
     * @param scale
     *            （DisplayMetrics类中属性density）
     * @return
     */
    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    /**
     * 将px值转换为sp值，保证文字大小不变
     *
     * @param pxValue
     * @param fontScale
     *            （DisplayMetrics类中属性scaledDensity）
     * @return
     */
    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param spValue
     * @param fontScale
     *            （DisplayMetrics类中属性scaledDensity）
     * @return
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * The context.
     */
    private static Context mContext = APP.getInstances();

    /**
     * 获取屏幕宽度
     *
     * @return
     */
    public static int getWindowWidth() {
        WindowManager wm = (WindowManager) mContext.getSystemService(
                Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();

        return width;
    }

    /**
     * 获取屏幕高度
     *
     * @return
     */
    public static int getWindowHeight() {
        WindowManager wm = (WindowManager) mContext.getSystemService(
                Context.WINDOW_SERVICE);
        int height = wm.getDefaultDisplay().getHeight();

        return height;
    }
}
