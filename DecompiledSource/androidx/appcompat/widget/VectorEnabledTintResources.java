package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import java.lang.ref.WeakReference;

public class VectorEnabledTintResources extends Resources {
  public static final int MAX_SDK_WHERE_REQUIRED = 20;
  
  private static boolean sCompatVectorFromResourcesEnabled = false;
  
  private final WeakReference<Context> mContextRef;
  
  public VectorEnabledTintResources(Context paramContext, Resources paramResources) {
    super(paramResources.getAssets(), paramResources.getDisplayMetrics(), paramResources.getConfiguration());
    this.mContextRef = new WeakReference<Context>(paramContext);
  }
  
  public static boolean isCompatVectorFromResourcesEnabled() {
    return sCompatVectorFromResourcesEnabled;
  }
  
  public static void setCompatVectorFromResourcesEnabled(boolean paramBoolean) {
    sCompatVectorFromResourcesEnabled = paramBoolean;
  }
  
  public static boolean shouldBeUsed() {
    boolean bool;
    if (isCompatVectorFromResourcesEnabled() && Build.VERSION.SDK_INT <= 20) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public Drawable getDrawable(int paramInt) throws Resources.NotFoundException {
    Context context = this.mContextRef.get();
    return (context != null) ? ResourceManagerInternal.get().onDrawableLoadedFromResources(context, this, paramInt) : super.getDrawable(paramInt);
  }
  
  final Drawable superGetDrawable(int paramInt) {
    return super.getDrawable(paramInt);
  }
}


/* Location:              /home/platinum/Documents/AndroidRE/com.guanxu.technology.snaptain_era_s5c_29_apps.evozi.com-dex2jar.jar!/androidx/appcompat/widget/VectorEnabledTintResources.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */