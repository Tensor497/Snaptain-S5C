package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import java.lang.ref.WeakReference;

class TintResources extends ResourcesWrapper {
  private final WeakReference<Context> mContextRef;
  
  public TintResources(Context paramContext, Resources paramResources) {
    super(paramResources);
    this.mContextRef = new WeakReference<Context>(paramContext);
  }
  
  public Drawable getDrawable(int paramInt) throws Resources.NotFoundException {
    Drawable drawable = super.getDrawable(paramInt);
    Context context = this.mContextRef.get();
    if (drawable != null && context != null)
      ResourceManagerInternal.get().tintDrawableUsingColorFilter(context, paramInt, drawable); 
    return drawable;
  }
}


/* Location:              /home/platinum/Documents/AndroidRE/com.guanxu.technology.snaptain_era_s5c_29_apps.evozi.com-dex2jar.jar!/androidx/appcompat/widget/TintResources.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */