package androidx.core.content.res;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.util.StateSet;
import android.util.Xml;
import androidx.core.R;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public final class ColorStateListInflaterCompat {
  public static ColorStateList createFromXml(Resources paramResources, XmlPullParser paramXmlPullParser, Resources.Theme paramTheme) throws XmlPullParserException, IOException {
    int i;
    AttributeSet attributeSet = Xml.asAttributeSet(paramXmlPullParser);
    while (true) {
      i = paramXmlPullParser.next();
      if (i != 2 && i != 1)
        continue; 
      break;
    } 
    if (i == 2)
      return createFromXmlInner(paramResources, paramXmlPullParser, attributeSet, paramTheme); 
    XmlPullParserException xmlPullParserException = new XmlPullParserException("No start tag found");
    throw xmlPullParserException;
  }
  
  public static ColorStateList createFromXmlInner(Resources paramResources, XmlPullParser paramXmlPullParser, AttributeSet paramAttributeSet, Resources.Theme paramTheme) throws XmlPullParserException, IOException {
    String str = paramXmlPullParser.getName();
    if (str.equals("selector"))
      return inflate(paramResources, paramXmlPullParser, paramAttributeSet, paramTheme); 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(paramXmlPullParser.getPositionDescription());
    stringBuilder.append(": invalid color state list tag ");
    stringBuilder.append(str);
    throw new XmlPullParserException(stringBuilder.toString());
  }
  
  public static ColorStateList inflate(Resources paramResources, int paramInt, Resources.Theme paramTheme) {
    try {
      return createFromXml(paramResources, (XmlPullParser)paramResources.getXml(paramInt), paramTheme);
    } catch (Exception exception) {
      Log.e("CSLCompat", "Failed to inflate ColorStateList.", exception);
      return null;
    } 
  }
  
  private static ColorStateList inflate(Resources paramResources, XmlPullParser paramXmlPullParser, AttributeSet paramAttributeSet, Resources.Theme paramTheme) throws XmlPullParserException, IOException {
    int i = paramXmlPullParser.getDepth() + 1;
    int[][] arrayOfInt3 = new int[20][];
    int[] arrayOfInt4 = new int[arrayOfInt3.length];
    byte b = 0;
    while (true) {
      int j = paramXmlPullParser.next();
      if (j != 1) {
        int k = paramXmlPullParser.getDepth();
        if (k >= i || j != 3) {
          if (j != 2 || k > i || !paramXmlPullParser.getName().equals("item"))
            continue; 
          TypedArray typedArray = obtainAttributes(paramResources, paramTheme, paramAttributeSet, R.styleable.ColorStateListItem);
          int m = typedArray.getColor(R.styleable.ColorStateListItem_android_color, -65281);
          float f = 1.0F;
          if (typedArray.hasValue(R.styleable.ColorStateListItem_android_alpha)) {
            f = typedArray.getFloat(R.styleable.ColorStateListItem_android_alpha, 1.0F);
          } else if (typedArray.hasValue(R.styleable.ColorStateListItem_alpha)) {
            f = typedArray.getFloat(R.styleable.ColorStateListItem_alpha, 1.0F);
          } 
          typedArray.recycle();
          int n = paramAttributeSet.getAttributeCount();
          int[] arrayOfInt = new int[n];
          j = 0;
          for (k = 0; j < n; k = i2) {
            int i1 = paramAttributeSet.getAttributeNameResource(j);
            int i2 = k;
            if (i1 != 16843173) {
              i2 = k;
              if (i1 != 16843551) {
                i2 = k;
                if (i1 != R.attr.alpha) {
                  if (paramAttributeSet.getAttributeBooleanValue(j, false)) {
                    i2 = i1;
                  } else {
                    i2 = -i1;
                  } 
                  arrayOfInt[k] = i2;
                  i2 = k + 1;
                } 
              } 
            } 
            j++;
          } 
          arrayOfInt = StateSet.trimStateSet(arrayOfInt, k);
          arrayOfInt4 = GrowingArrayUtils.append(arrayOfInt4, b, modulateColorAlpha(m, f));
          arrayOfInt3 = GrowingArrayUtils.<int[]>append(arrayOfInt3, b, arrayOfInt);
          b++;
          continue;
        } 
      } 
      break;
    } 
    int[] arrayOfInt1 = new int[b];
    int[][] arrayOfInt2 = new int[b][];
    System.arraycopy(arrayOfInt4, 0, arrayOfInt1, 0, b);
    System.arraycopy(arrayOfInt3, 0, arrayOfInt2, 0, b);
    return new ColorStateList(arrayOfInt2, arrayOfInt1);
  }
  
  private static int modulateColorAlpha(int paramInt, float paramFloat) {
    return paramInt & 0xFFFFFF | Math.round(Color.alpha(paramInt) * paramFloat) << 24;
  }
  
  private static TypedArray obtainAttributes(Resources paramResources, Resources.Theme paramTheme, AttributeSet paramAttributeSet, int[] paramArrayOfint) {
    TypedArray typedArray;
    if (paramTheme == null) {
      typedArray = paramResources.obtainAttributes(paramAttributeSet, paramArrayOfint);
    } else {
      typedArray = paramTheme.obtainStyledAttributes(paramAttributeSet, paramArrayOfint, 0, 0);
    } 
    return typedArray;
  }
}


/* Location:              /home/platinum/Documents/AndroidRE/com.guanxu.technology.snaptain_era_s5c_29_apps.evozi.com-dex2jar.jar!/androidx/core/content/res/ColorStateListInflaterCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */