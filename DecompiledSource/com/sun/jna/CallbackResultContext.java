package com.sun.jna;

import java.lang.reflect.Method;

public class CallbackResultContext extends ToNativeContext {
  private Method method;
  
  CallbackResultContext(Method paramMethod) {
    this.method = paramMethod;
  }
  
  public Method getMethod() {
    return this.method;
  }
}


/* Location:              /home/platinum/Documents/AndroidRE/com.guanxu.technology.snaptain_era_s5c_29_apps.evozi.com-dex2jar.jar!/com/sun/jna/CallbackResultContext.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */