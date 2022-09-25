package androidx.core.graphics;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.fonts.FontVariationAxis;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import androidx.core.content.res.FontResourcesParserCompat;
import androidx.core.provider.FontsContractCompat;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.Map;

public class TypefaceCompatApi26Impl extends TypefaceCompatApi21Impl {
  private static final String ABORT_CREATION_METHOD = "abortCreation";
  
  private static final String ADD_FONT_FROM_ASSET_MANAGER_METHOD = "addFontFromAssetManager";
  
  private static final String ADD_FONT_FROM_BUFFER_METHOD = "addFontFromBuffer";
  
  private static final String CREATE_FROM_FAMILIES_WITH_DEFAULT_METHOD = "createFromFamiliesWithDefault";
  
  private static final String FONT_FAMILY_CLASS = "android.graphics.FontFamily";
  
  private static final String FREEZE_METHOD = "freeze";
  
  private static final int RESOLVE_BY_FONT_TABLE = -1;
  
  private static final String TAG = "TypefaceCompatApi26Impl";
  
  protected final Method mAbortCreation;
  
  protected final Method mAddFontFromAssetManager;
  
  protected final Method mAddFontFromBuffer;
  
  protected final Method mCreateFromFamiliesWithDefault;
  
  protected final Class mFontFamily;
  
  protected final Constructor mFontFamilyCtor;
  
  protected final Method mFreeze;
  
  public TypefaceCompatApi26Impl() {
    ClassNotFoundException classNotFoundException2;
    ClassNotFoundException classNotFoundException3;
    ClassNotFoundException classNotFoundException4;
    ClassNotFoundException classNotFoundException5;
    ClassNotFoundException classNotFoundException6;
    ClassNotFoundException classNotFoundException7;
    ClassNotFoundException classNotFoundException1 = null;
    try {
      Class clazz = obtainFontFamily();
      Constructor constructor = obtainFontFamilyCtor(clazz);
      Method method1 = obtainAddFontFromAssetManagerMethod(clazz);
      Method method2 = obtainAddFontFromBufferMethod(clazz);
      Method method3 = obtainFreezeMethod(clazz);
      Method method4 = obtainAbortCreationMethod(clazz);
      Method method5 = obtainCreateFromFamiliesWithDefaultMethod(clazz);
    } catch (ClassNotFoundException classNotFoundException8) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Unable to collect necessary methods for class ");
      stringBuilder.append(classNotFoundException8.getClass().getName());
      Log.e("TypefaceCompatApi26Impl", stringBuilder.toString(), classNotFoundException8);
      ClassNotFoundException classNotFoundException9 = null;
      classNotFoundException8 = classNotFoundException9;
      classNotFoundException4 = classNotFoundException8;
      classNotFoundException2 = classNotFoundException4;
      classNotFoundException3 = classNotFoundException2;
      classNotFoundException7 = classNotFoundException3;
      classNotFoundException6 = classNotFoundException3;
      classNotFoundException5 = classNotFoundException2;
      classNotFoundException3 = classNotFoundException8;
      classNotFoundException8 = classNotFoundException9;
      classNotFoundException2 = classNotFoundException1;
    } catch (NoSuchMethodException noSuchMethodException) {}
    this.mFontFamily = (Class)classNotFoundException2;
    this.mFontFamilyCtor = (Constructor)classNotFoundException3;
    this.mAddFontFromAssetManager = (Method)classNotFoundException4;
    this.mAddFontFromBuffer = (Method)classNotFoundException5;
    this.mFreeze = (Method)classNotFoundException6;
    this.mAbortCreation = (Method)classNotFoundException7;
    this.mCreateFromFamiliesWithDefault = (Method)noSuchMethodException;
  }
  
  private void abortCreation(Object paramObject) {
    try {
      this.mAbortCreation.invoke(paramObject, new Object[0]);
    } catch (IllegalAccessException|java.lang.reflect.InvocationTargetException illegalAccessException) {}
  }
  
  private boolean addFontFromAssetManager(Context paramContext, Object paramObject, String paramString, int paramInt1, int paramInt2, int paramInt3, FontVariationAxis[] paramArrayOfFontVariationAxis) {
    try {
      return ((Boolean)this.mAddFontFromAssetManager.invoke(paramObject, new Object[] { paramContext.getAssets(), paramString, Integer.valueOf(0), Boolean.valueOf(false), Integer.valueOf(paramInt1), Integer.valueOf(paramInt2), Integer.valueOf(paramInt3), paramArrayOfFontVariationAxis })).booleanValue();
    } catch (IllegalAccessException|java.lang.reflect.InvocationTargetException illegalAccessException) {
      return false;
    } 
  }
  
  private boolean addFontFromBuffer(Object paramObject, ByteBuffer paramByteBuffer, int paramInt1, int paramInt2, int paramInt3) {
    try {
      return ((Boolean)this.mAddFontFromBuffer.invoke(paramObject, new Object[] { paramByteBuffer, Integer.valueOf(paramInt1), null, Integer.valueOf(paramInt2), Integer.valueOf(paramInt3) })).booleanValue();
    } catch (IllegalAccessException|java.lang.reflect.InvocationTargetException illegalAccessException) {
      return false;
    } 
  }
  
  private boolean freeze(Object paramObject) {
    try {
      return ((Boolean)this.mFreeze.invoke(paramObject, new Object[0])).booleanValue();
    } catch (IllegalAccessException|java.lang.reflect.InvocationTargetException illegalAccessException) {
      return false;
    } 
  }
  
  private boolean isFontFamilyPrivateAPIAvailable() {
    boolean bool;
    if (this.mAddFontFromAssetManager == null)
      Log.w("TypefaceCompatApi26Impl", "Unable to collect necessary private methods. Fallback to legacy implementation."); 
    if (this.mAddFontFromAssetManager != null) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  private Object newFamily() {
    try {
      return this.mFontFamilyCtor.newInstance(new Object[0]);
    } catch (IllegalAccessException|InstantiationException|java.lang.reflect.InvocationTargetException illegalAccessException) {
      return null;
    } 
  }
  
  protected Typeface createFromFamiliesWithDefault(Object paramObject) {
    try {
      Object object = Array.newInstance(this.mFontFamily, 1);
      Array.set(object, 0, paramObject);
      return (Typeface)this.mCreateFromFamiliesWithDefault.invoke(null, new Object[] { object, Integer.valueOf(-1), Integer.valueOf(-1) });
    } catch (IllegalAccessException|java.lang.reflect.InvocationTargetException illegalAccessException) {
      return null;
    } 
  }
  
  public Typeface createFromFontFamilyFilesResourceEntry(Context paramContext, FontResourcesParserCompat.FontFamilyFilesResourceEntry paramFontFamilyFilesResourceEntry, Resources paramResources, int paramInt) {
    if (!isFontFamilyPrivateAPIAvailable())
      return super.createFromFontFamilyFilesResourceEntry(paramContext, paramFontFamilyFilesResourceEntry, paramResources, paramInt); 
    Object object = newFamily();
    if (object == null)
      return null; 
    FontResourcesParserCompat.FontFileResourceEntry[] arrayOfFontFileResourceEntry = paramFontFamilyFilesResourceEntry.getEntries();
    int i = arrayOfFontFileResourceEntry.length;
    for (paramInt = 0; paramInt < i; paramInt++) {
      FontResourcesParserCompat.FontFileResourceEntry fontFileResourceEntry = arrayOfFontFileResourceEntry[paramInt];
      if (!addFontFromAssetManager(paramContext, object, fontFileResourceEntry.getFileName(), fontFileResourceEntry.getTtcIndex(), fontFileResourceEntry.getWeight(), fontFileResourceEntry.isItalic(), FontVariationAxis.fromFontVariationSettings(fontFileResourceEntry.getVariationSettings()))) {
        abortCreation(object);
        return null;
      } 
    } 
    return !freeze(object) ? null : createFromFamiliesWithDefault(object);
  }
  
  public Typeface createFromFontInfo(Context paramContext, CancellationSignal paramCancellationSignal, FontsContractCompat.FontInfo[] paramArrayOfFontInfo, int paramInt) {
    if (paramArrayOfFontInfo.length < 1)
      return null; 
    if (!isFontFamilyPrivateAPIAvailable()) {
      FontsContractCompat.FontInfo fontInfo = findBestInfo(paramArrayOfFontInfo, paramInt);
      ContentResolver contentResolver = paramContext.getContentResolver();
      try {
        ParcelFileDescriptor parcelFileDescriptor = contentResolver.openFileDescriptor(fontInfo.getUri(), "r", paramCancellationSignal);
        if (parcelFileDescriptor == null) {
          if (parcelFileDescriptor != null)
            parcelFileDescriptor.close(); 
          return null;
        } 
        try {
          Typeface.Builder builder = new Typeface.Builder();
          this(parcelFileDescriptor.getFileDescriptor());
          return builder.setWeight(fontInfo.getWeight()).setItalic(fontInfo.isItalic()).build();
        } finally {
          paramCancellationSignal = null;
        } 
      } catch (IOException iOException) {
        return null;
      } 
    } 
    Map map = FontsContractCompat.prepareFontData((Context)iOException, paramArrayOfFontInfo, paramCancellationSignal);
    Object object = newFamily();
    if (object == null)
      return null; 
    int i = paramArrayOfFontInfo.length;
    boolean bool = false;
    for (byte b = 0; b < i; b++) {
      FontsContractCompat.FontInfo fontInfo = paramArrayOfFontInfo[b];
      ByteBuffer byteBuffer = (ByteBuffer)map.get(fontInfo.getUri());
      if (byteBuffer != null) {
        if (!addFontFromBuffer(object, byteBuffer, fontInfo.getTtcIndex(), fontInfo.getWeight(), fontInfo.isItalic())) {
          abortCreation(object);
          return null;
        } 
        bool = true;
      } 
    } 
    if (!bool) {
      abortCreation(object);
      return null;
    } 
    if (!freeze(object))
      return null; 
    object = createFromFamiliesWithDefault(object);
    return (object == null) ? null : Typeface.create((Typeface)object, paramInt);
  }
  
  public Typeface createFromResourcesFontFile(Context paramContext, Resources paramResources, int paramInt1, String paramString, int paramInt2) {
    if (!isFontFamilyPrivateAPIAvailable())
      return super.createFromResourcesFontFile(paramContext, paramResources, paramInt1, paramString, paramInt2); 
    Object object = newFamily();
    if (object == null)
      return null; 
    if (!addFontFromAssetManager(paramContext, object, paramString, 0, -1, -1, (FontVariationAxis[])null)) {
      abortCreation(object);
      return null;
    } 
    return !freeze(object) ? null : createFromFamiliesWithDefault(object);
  }
  
  protected Method obtainAbortCreationMethod(Class paramClass) throws NoSuchMethodException {
    return paramClass.getMethod("abortCreation", new Class[0]);
  }
  
  protected Method obtainAddFontFromAssetManagerMethod(Class paramClass) throws NoSuchMethodException {
    return paramClass.getMethod("addFontFromAssetManager", new Class[] { AssetManager.class, String.class, int.class, boolean.class, int.class, int.class, int.class, FontVariationAxis[].class });
  }
  
  protected Method obtainAddFontFromBufferMethod(Class paramClass) throws NoSuchMethodException {
    return paramClass.getMethod("addFontFromBuffer", new Class[] { ByteBuffer.class, int.class, FontVariationAxis[].class, int.class, int.class });
  }
  
  protected Method obtainCreateFromFamiliesWithDefaultMethod(Class<?> paramClass) throws NoSuchMethodException {
    Method method = Typeface.class.getDeclaredMethod("createFromFamiliesWithDefault", new Class[] { Array.newInstance(paramClass, 1).getClass(), int.class, int.class });
    method.setAccessible(true);
    return method;
  }
  
  protected Class obtainFontFamily() throws ClassNotFoundException {
    return Class.forName("android.graphics.FontFamily");
  }
  
  protected Constructor obtainFontFamilyCtor(Class paramClass) throws NoSuchMethodException {
    return paramClass.getConstructor(new Class[0]);
  }
  
  protected Method obtainFreezeMethod(Class paramClass) throws NoSuchMethodException {
    return paramClass.getMethod("freeze", new Class[0]);
  }
}


/* Location:              /home/platinum/Documents/AndroidRE/com.guanxu.technology.snaptain_era_s5c_29_apps.evozi.com-dex2jar.jar!/androidx/core/graphics/TypefaceCompatApi26Impl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */