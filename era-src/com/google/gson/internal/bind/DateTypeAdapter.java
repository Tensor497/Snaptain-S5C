package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.internal.JavaVersion;
import com.google.gson.internal.PreJava9DateFormatProvider;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public final class DateTypeAdapter extends TypeAdapter<Date> {
  public static final TypeAdapterFactory FACTORY = new TypeAdapterFactory() {
      public <T> TypeAdapter<T> create(Gson param1Gson, TypeToken<T> param1TypeToken) {
        if (param1TypeToken.getRawType() == Date.class) {
          DateTypeAdapter dateTypeAdapter = new DateTypeAdapter();
        } else {
          param1Gson = null;
        } 
        return (TypeAdapter<T>)param1Gson;
      }
    };
  
  private final List<DateFormat> dateFormats = new ArrayList<DateFormat>();
  
  public DateTypeAdapter() {
    this.dateFormats.add(DateFormat.getDateTimeInstance(2, 2, Locale.US));
    if (!Locale.getDefault().equals(Locale.US))
      this.dateFormats.add(DateFormat.getDateTimeInstance(2, 2)); 
    if (JavaVersion.isJava9OrLater())
      this.dateFormats.add(PreJava9DateFormatProvider.getUSDateTimeFormat(2, 2)); 
  }
  
  private Date deserializeToDate(String paramString) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield dateFormats : Ljava/util/List;
    //   6: invokeinterface iterator : ()Ljava/util/Iterator;
    //   11: astore_2
    //   12: aload_2
    //   13: invokeinterface hasNext : ()Z
    //   18: ifeq -> 41
    //   21: aload_2
    //   22: invokeinterface next : ()Ljava/lang/Object;
    //   27: checkcast java/text/DateFormat
    //   30: astore_3
    //   31: aload_3
    //   32: aload_1
    //   33: invokevirtual parse : (Ljava/lang/String;)Ljava/util/Date;
    //   36: astore_3
    //   37: aload_0
    //   38: monitorexit
    //   39: aload_3
    //   40: areturn
    //   41: new java/text/ParsePosition
    //   44: astore_2
    //   45: aload_2
    //   46: iconst_0
    //   47: invokespecial <init> : (I)V
    //   50: aload_1
    //   51: aload_2
    //   52: invokestatic parse : (Ljava/lang/String;Ljava/text/ParsePosition;)Ljava/util/Date;
    //   55: astore_2
    //   56: aload_0
    //   57: monitorexit
    //   58: aload_2
    //   59: areturn
    //   60: astore_3
    //   61: new com/google/gson/JsonSyntaxException
    //   64: astore_2
    //   65: aload_2
    //   66: aload_1
    //   67: aload_3
    //   68: invokespecial <init> : (Ljava/lang/String;Ljava/lang/Throwable;)V
    //   71: aload_2
    //   72: athrow
    //   73: astore_1
    //   74: aload_0
    //   75: monitorexit
    //   76: aload_1
    //   77: athrow
    //   78: astore_3
    //   79: goto -> 12
    // Exception table:
    //   from	to	target	type
    //   2	12	73	finally
    //   12	31	73	finally
    //   31	37	78	java/text/ParseException
    //   31	37	73	finally
    //   41	56	60	java/text/ParseException
    //   41	56	73	finally
    //   61	73	73	finally
  }
  
  public Date read(JsonReader paramJsonReader) throws IOException {
    if (paramJsonReader.peek() == JsonToken.NULL) {
      paramJsonReader.nextNull();
      return null;
    } 
    return deserializeToDate(paramJsonReader.nextString());
  }
  
  public void write(JsonWriter paramJsonWriter, Date paramDate) throws IOException {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_2
    //   3: ifnonnull -> 14
    //   6: aload_1
    //   7: invokevirtual nullValue : ()Lcom/google/gson/stream/JsonWriter;
    //   10: pop
    //   11: aload_0
    //   12: monitorexit
    //   13: return
    //   14: aload_1
    //   15: aload_0
    //   16: getfield dateFormats : Ljava/util/List;
    //   19: iconst_0
    //   20: invokeinterface get : (I)Ljava/lang/Object;
    //   25: checkcast java/text/DateFormat
    //   28: aload_2
    //   29: invokevirtual format : (Ljava/util/Date;)Ljava/lang/String;
    //   32: invokevirtual value : (Ljava/lang/String;)Lcom/google/gson/stream/JsonWriter;
    //   35: pop
    //   36: aload_0
    //   37: monitorexit
    //   38: return
    //   39: astore_1
    //   40: aload_0
    //   41: monitorexit
    //   42: aload_1
    //   43: athrow
    // Exception table:
    //   from	to	target	type
    //   6	11	39	finally
    //   14	36	39	finally
  }
}


/* Location:              /home/platinum/Documents/AndroidRE/com.guanxu.technology.snaptain_era_s5c_29_apps.evozi.com-dex2jar.jar!/com/google/gson/internal/bind/DateTypeAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */