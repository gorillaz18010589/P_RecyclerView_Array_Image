package com.example.p_recyclerview_array_image;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class DrawableUtil {
    private Context context;

    public DrawableUtil(Context context) {
        this.context = context;
    }

    /**
     * 批量获取指定图片 imageResourceId
     *
     * @param imgName 图片名字的部分或全部
     * @return 图片名字中包含有imgName的所有图片 imageResourceId
     * <p>
     * 使用示例： 如有5张图片: image_0 ,image_1 ,image_2 , image_3 ,image_4 ;
     * 调用方法 getImageResourId("image_")  ,则会得到上面的5张图片的imageResourceId
     * 如果传入的imgName为全名则返回的就是一张图片
     */
    public List<Integer> getImageResourId(String imgName) {
        List<Integer> imgList = new ArrayList<>();
        Resources resources = context.getResources();
        Field[] fields = R.drawable.class.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            fields[i].setAccessible(true);
            String name = fields[i].getName();
            if (name.contains(imgName)) {
                int resId = resources.getIdentifier(name, "drawable", context.getPackageName());
                imgList.add(resId);
            }
        }
        return imgList;
    }

    /**
     * 批量获取指定图片 drawable对象
     *
     * @param imgName 图片名字的部分或全部
     * @return 图片名字中包含有imgName的所有图片 drawable对象
     *   <p>
     * 使用示例： 如有5张图片: image_0 ,image_1 ,image_2 , image_3 ,image_4 ;
     * 调用方法 getImageResourId("image_")  ,则会得到上面的5张图片的drawable
     * 如果传入的imgName为全名则返回的就是一张图片
     */
    public  List<Drawable> getDrawable(String imgName) {
        List<Drawable> imgList = new ArrayList<>();
        Resources resources = context.getResources();
        Field[] fields = R.drawable.class.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            fields[i].setAccessible(true);
            String name = fields[i].getName();
            if (name.contains(imgName)) {
                int resId = resources.getIdentifier(name, "drawable", context.getPackageName());
                Drawable drawable = resources.getDrawable(resId);
                imgList.add(drawable);
            }
        }
        return imgList;
    }

    /**
     * 将Drawable转换为Bitmap
     * @param drawable
     * @return
     */
    public    Bitmap drawableToBitmap(Drawable drawable) {
        //取drawable的宽高
        int width = drawable.getIntrinsicWidth();
        int height = drawable.getIntrinsicHeight();
        //取drawable的颜色格式
        Bitmap.Config config = drawable.getOpacity() != PixelFormat.OPAQUE
                ? Bitmap.Config.ARGB_8888
                : Bitmap.Config.RGB_565;
        //创建对应的bitmap
        Bitmap bitmap = Bitmap.createBitmap(width, height, config);
        //创建对应的bitmap的画布
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, width, height);
        //把drawable内容画到画布中
        drawable.draw(canvas);
        return bitmap;
    }
}
