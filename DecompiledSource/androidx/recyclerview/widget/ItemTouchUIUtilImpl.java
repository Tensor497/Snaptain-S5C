package androidx.recyclerview.widget;

import android.graphics.Canvas;
import android.os.Build;
import android.view.View;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.R;

class ItemTouchUIUtilImpl implements ItemTouchUIUtil {
  static final ItemTouchUIUtil INSTANCE = new ItemTouchUIUtilImpl();
  
  private static float findMaxElevation(RecyclerView paramRecyclerView, View paramView) {
    int i = paramRecyclerView.getChildCount();
    float f = 0.0F;
    byte b = 0;
    while (b < i) {
      float f1;
      View view = paramRecyclerView.getChildAt(b);
      if (view == paramView) {
        f1 = f;
      } else {
        float f2 = ViewCompat.getElevation(view);
        f1 = f;
        if (f2 > f)
          f1 = f2; 
      } 
      b++;
      f = f1;
    } 
    return f;
  }
  
  public void clearView(View paramView) {
    if (Build.VERSION.SDK_INT >= 21) {
      Object object = paramView.getTag(R.id.item_touch_helper_previous_elevation);
      if (object instanceof Float)
        ViewCompat.setElevation(paramView, ((Float)object).floatValue()); 
      paramView.setTag(R.id.item_touch_helper_previous_elevation, null);
    } 
    paramView.setTranslationX(0.0F);
    paramView.setTranslationY(0.0F);
  }
  
  public void onDraw(Canvas paramCanvas, RecyclerView paramRecyclerView, View paramView, float paramFloat1, float paramFloat2, int paramInt, boolean paramBoolean) {
    if (Build.VERSION.SDK_INT >= 21 && paramBoolean && paramView.getTag(R.id.item_touch_helper_previous_elevation) == null) {
      float f = ViewCompat.getElevation(paramView);
      ViewCompat.setElevation(paramView, findMaxElevation(paramRecyclerView, paramView) + 1.0F);
      paramView.setTag(R.id.item_touch_helper_previous_elevation, Float.valueOf(f));
    } 
    paramView.setTranslationX(paramFloat1);
    paramView.setTranslationY(paramFloat2);
  }
  
  public void onDrawOver(Canvas paramCanvas, RecyclerView paramRecyclerView, View paramView, float paramFloat1, float paramFloat2, int paramInt, boolean paramBoolean) {}
  
  public void onSelected(View paramView) {}
}


/* Location:              /home/platinum/Documents/AndroidRE/com.guanxu.technology.snaptain_era_s5c_29_apps.evozi.com-dex2jar.jar!/androidx/recyclerview/widget/ItemTouchUIUtilImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */