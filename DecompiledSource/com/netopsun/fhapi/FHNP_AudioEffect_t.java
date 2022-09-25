package com.netopsun.fhapi;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import java.util.Arrays;
import java.util.List;

public class FHNP_AudioEffect_t extends Structure {
  public int aiagc;
  
  public int aihpe;
  
  public int aimute;
  
  public int aivol;
  
  public int aohpe;
  
  public int aomute;
  
  public int aopga;
  
  public int aovol;
  
  public FHNP_AudioEffect_t() {}
  
  public FHNP_AudioEffect_t(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8) {
    this.aivol = paramInt1;
    this.aiagc = paramInt2;
    this.aihpe = paramInt3;
    this.aimute = paramInt4;
    this.aovol = paramInt5;
    this.aopga = paramInt6;
    this.aohpe = paramInt7;
    this.aomute = paramInt8;
  }
  
  public FHNP_AudioEffect_t(Pointer paramPointer) {
    super(paramPointer);
  }
  
  protected List<String> getFieldOrder() {
    return Arrays.asList(new String[] { "aivol", "aiagc", "aihpe", "aimute", "aovol", "aopga", "aohpe", "aomute" });
  }
  
  public static class ByReference extends FHNP_AudioEffect_t implements Structure.ByReference {}
  
  public static class ByValue extends FHNP_AudioEffect_t implements Structure.ByValue {}
}


/* Location:              /home/platinum/Documents/AndroidRE/com.guanxu.technology.snaptain_era_s5c_29_apps.evozi.com-dex2jar.jar!/com/netopsun/fhapi/FHNP_AudioEffect_t.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */