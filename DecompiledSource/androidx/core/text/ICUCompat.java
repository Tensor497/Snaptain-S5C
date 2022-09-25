package androidx.core.text;

import android.icu.util.ULocale;
import android.os.Build;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Locale;

public final class ICUCompat {
  private static final String TAG = "ICUCompat";
  
  private static Method sAddLikelySubtagsMethod;
  
  private static Method sGetScriptMethod;
  
  static {
    if (Build.VERSION.SDK_INT < 21) {
      try {
        Class<?> clazz = Class.forName("libcore.icu.ICU");
        if (clazz != null) {
          sGetScriptMethod = clazz.getMethod("getScript", new Class[] { String.class });
          sAddLikelySubtagsMethod = clazz.getMethod("addLikelySubtags", new Class[] { String.class });
        } 
      } catch (Exception exception) {
        sGetScriptMethod = null;
        sAddLikelySubtagsMethod = null;
        Log.w("ICUCompat", exception);
      } 
    } else if (Build.VERSION.SDK_INT < 24) {
      try {
        sAddLikelySubtagsMethod = Class.forName("libcore.icu.ICU").getMethod("addLikelySubtags", new Class[] { Locale.class });
      } catch (Exception exception) {
        throw new IllegalStateException(exception);
      } 
    } 
  }
  
  private static String addLikelySubtags(Locale paramLocale) {
    String str = paramLocale.toString();
    try {
      if (sAddLikelySubtagsMethod != null)
        return (String)sAddLikelySubtagsMethod.invoke(null, new Object[] { str }); 
    } catch (IllegalAccessException illegalAccessException) {
      Log.w("ICUCompat", illegalAccessException);
    } catch (InvocationTargetException invocationTargetException) {
      Log.w("ICUCompat", invocationTargetException);
    } 
    return str;
  }
  
  private static String getScript(String paramString) {
    try {
      if (sGetScriptMethod != null)
        return (String)sGetScriptMethod.invoke(null, new Object[] { paramString }); 
    } catch (IllegalAccessException illegalAccessException) {
      Log.w("ICUCompat", illegalAccessException);
    } catch (InvocationTargetException invocationTargetException) {
      Log.w("ICUCompat", invocationTargetException);
    } 
    return null;
  }
  
  public static String maximizeAndGetScript(Locale paramLocale) {
    if (Build.VERSION.SDK_INT >= 24)
      return ULocale.addLikelySubtags(ULocale.forLocale(paramLocale)).getScript(); 
    if (Build.VERSION.SDK_INT >= 21) {
      try {
        return ((Locale)sAddLikelySubtagsMethod.invoke(null, new Object[] { paramLocale })).getScript();
      } catch (InvocationTargetException invocationTargetException) {
        Log.w("ICUCompat", invocationTargetException);
      } catch (IllegalAccessException illegalAccessException) {
        Log.w("ICUCompat", illegalAccessException);
      } 
      return paramLocale.getScript();
    } 
    String str = addLikelySubtags(paramLocale);
    return (str != null) ? getScript(str) : null;
  }
}


/* Location:              /home/platinum/Documents/AndroidRE/com.guanxu.technology.snaptain_era_s5c_29_apps.evozi.com-dex2jar.jar!/androidx/core/text/ICUCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */