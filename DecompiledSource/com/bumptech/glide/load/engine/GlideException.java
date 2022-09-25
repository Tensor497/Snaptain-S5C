package com.bumptech.glide.load.engine;

import android.util.Log;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.Key;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public final class GlideException extends Exception {
  private static final StackTraceElement[] EMPTY_ELEMENTS = new StackTraceElement[0];
  
  private static final long serialVersionUID = 1L;
  
  private final List<Throwable> causes;
  
  private Class<?> dataClass;
  
  private DataSource dataSource;
  
  private String detailMessage;
  
  private Exception exception;
  
  private Key key;
  
  public GlideException(String paramString) {
    this(paramString, Collections.emptyList());
  }
  
  public GlideException(String paramString, Throwable paramThrowable) {
    this(paramString, Collections.singletonList(paramThrowable));
  }
  
  public GlideException(String paramString, List<Throwable> paramList) {
    this.detailMessage = paramString;
    setStackTrace(EMPTY_ELEMENTS);
    this.causes = paramList;
  }
  
  private void addRootCauses(Throwable paramThrowable, List<Throwable> paramList) {
    Iterator<Throwable> iterator;
    if (paramThrowable instanceof GlideException) {
      iterator = ((GlideException)paramThrowable).getCauses().iterator();
      while (iterator.hasNext())
        addRootCauses(iterator.next(), paramList); 
    } else {
      paramList.add(iterator);
    } 
  }
  
  private static void appendCauses(List<Throwable> paramList, Appendable paramAppendable) {
    try {
      appendCausesWrapped(paramList, paramAppendable);
      return;
    } catch (IOException iOException) {
      throw new RuntimeException(iOException);
    } 
  }
  
  private static void appendCausesWrapped(List<Throwable> paramList, Appendable paramAppendable) throws IOException {
    int i = paramList.size();
    for (int j = 0; j < i; j = k) {
      Appendable appendable = paramAppendable.append("Cause (");
      int k = j + 1;
      appendable.append(String.valueOf(k)).append(" of ").append(String.valueOf(i)).append("): ");
      Throwable throwable = paramList.get(j);
      if (throwable instanceof GlideException) {
        ((GlideException)throwable).printStackTrace(paramAppendable);
      } else {
        appendExceptionMessage(throwable, paramAppendable);
      } 
    } 
  }
  
  private static void appendExceptionMessage(Throwable paramThrowable, Appendable paramAppendable) {
    try {
      paramAppendable.append(paramThrowable.getClass().toString()).append(": ").append(paramThrowable.getMessage()).append('\n');
      return;
    } catch (IOException iOException) {
      throw new RuntimeException(paramThrowable);
    } 
  }
  
  private void printStackTrace(Appendable paramAppendable) {
    appendExceptionMessage(this, paramAppendable);
    appendCauses(getCauses(), new IndentedAppendable(paramAppendable));
  }
  
  public Throwable fillInStackTrace() {
    return this;
  }
  
  public List<Throwable> getCauses() {
    return this.causes;
  }
  
  public String getMessage() {
    StringBuilder stringBuilder = new StringBuilder(71);
    stringBuilder.append(this.detailMessage);
    Class<?> clazz = this.dataClass;
    String str2 = "";
    if (clazz != null) {
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append(", ");
      stringBuilder1.append(this.dataClass);
      str1 = stringBuilder1.toString();
    } else {
      str1 = "";
    } 
    stringBuilder.append(str1);
    if (this.dataSource != null) {
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append(", ");
      stringBuilder1.append(this.dataSource);
      String str = stringBuilder1.toString();
    } else {
      str1 = "";
    } 
    stringBuilder.append(str1);
    String str1 = str2;
    if (this.key != null) {
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append(", ");
      stringBuilder1.append(this.key);
      str1 = stringBuilder1.toString();
    } 
    stringBuilder.append(str1);
    List<Throwable> list = getRootCauses();
    if (list.isEmpty())
      return stringBuilder.toString(); 
    if (list.size() == 1) {
      stringBuilder.append("\nThere was 1 cause:");
    } else {
      stringBuilder.append("\nThere were ");
      stringBuilder.append(list.size());
      stringBuilder.append(" causes:");
    } 
    for (Throwable throwable : list) {
      stringBuilder.append('\n');
      stringBuilder.append(throwable.getClass().getName());
      stringBuilder.append('(');
      stringBuilder.append(throwable.getMessage());
      stringBuilder.append(')');
    } 
    stringBuilder.append("\n call GlideException#logRootCauses(String) for more detail");
    return stringBuilder.toString();
  }
  
  public Exception getOrigin() {
    return this.exception;
  }
  
  public List<Throwable> getRootCauses() {
    ArrayList<Throwable> arrayList = new ArrayList();
    addRootCauses(this, arrayList);
    return arrayList;
  }
  
  public void logRootCauses(String paramString) {
    List<Throwable> list = getRootCauses();
    int i = list.size();
    int j;
    for (j = 0; j < i; j = k) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Root cause (");
      int k = j + 1;
      stringBuilder.append(k);
      stringBuilder.append(" of ");
      stringBuilder.append(i);
      stringBuilder.append(")");
      Log.i(paramString, stringBuilder.toString(), list.get(j));
    } 
  }
  
  public void printStackTrace() {
    printStackTrace(System.err);
  }
  
  public void printStackTrace(PrintStream paramPrintStream) {
    printStackTrace(paramPrintStream);
  }
  
  public void printStackTrace(PrintWriter paramPrintWriter) {
    printStackTrace(paramPrintWriter);
  }
  
  void setLoggingDetails(Key paramKey, DataSource paramDataSource) {
    setLoggingDetails(paramKey, paramDataSource, (Class<?>)null);
  }
  
  void setLoggingDetails(Key paramKey, DataSource paramDataSource, Class<?> paramClass) {
    this.key = paramKey;
    this.dataSource = paramDataSource;
    this.dataClass = paramClass;
  }
  
  public void setOrigin(Exception paramException) {
    this.exception = paramException;
  }
  
  private static final class IndentedAppendable implements Appendable {
    private static final String EMPTY_SEQUENCE = "";
    
    private static final String INDENT = "  ";
    
    private final Appendable appendable;
    
    private boolean printedNewLine = true;
    
    IndentedAppendable(Appendable param1Appendable) {
      this.appendable = param1Appendable;
    }
    
    private CharSequence safeSequence(CharSequence param1CharSequence) {
      CharSequence charSequence = param1CharSequence;
      if (param1CharSequence == null)
        charSequence = ""; 
      return charSequence;
    }
    
    public Appendable append(char param1Char) throws IOException {
      boolean bool = this.printedNewLine;
      boolean bool1 = false;
      if (bool) {
        this.printedNewLine = false;
        this.appendable.append("  ");
      } 
      if (param1Char == '\n')
        bool1 = true; 
      this.printedNewLine = bool1;
      this.appendable.append(param1Char);
      return this;
    }
    
    public Appendable append(CharSequence param1CharSequence) throws IOException {
      param1CharSequence = safeSequence(param1CharSequence);
      return append(param1CharSequence, 0, param1CharSequence.length());
    }
    
    public Appendable append(CharSequence param1CharSequence, int param1Int1, int param1Int2) throws IOException {
      param1CharSequence = safeSequence(param1CharSequence);
      boolean bool = this.printedNewLine;
      boolean bool1 = false;
      if (bool) {
        this.printedNewLine = false;
        this.appendable.append("  ");
      } 
      bool = bool1;
      if (param1CharSequence.length() > 0) {
        bool = bool1;
        if (param1CharSequence.charAt(param1Int2 - 1) == '\n')
          bool = true; 
      } 
      this.printedNewLine = bool;
      this.appendable.append(param1CharSequence, param1Int1, param1Int2);
      return this;
    }
  }
}


/* Location:              /home/platinum/Documents/AndroidRE/com.guanxu.technology.snaptain_era_s5c_29_apps.evozi.com-dex2jar.jar!/com/bumptech/glide/load/engine/GlideException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */