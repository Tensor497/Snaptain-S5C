package com.netopsun.fhapi;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import java.util.Arrays;
import java.util.List;

public class FHNP_SmartEncodeAttr_t extends Structure {
  public int bBackgroudModel;
  
  public int bMbconsist;
  
  public int bTexture;
  
  public int gopTHNum;
  
  public int[] minGop = new int[6];
  
  public int[] thVal = new int[5];
  
  public FHNP_SmartEncodeAttr_t() {}
  
  public FHNP_SmartEncodeAttr_t(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int[] paramArrayOfint1, int[] paramArrayOfint2) {
    this.bTexture = paramInt1;
    this.bBackgroudModel = paramInt2;
    this.bMbconsist = paramInt3;
    this.gopTHNum = paramInt4;
    if (paramArrayOfint1.length == this.thVal.length) {
      this.thVal = paramArrayOfint1;
      if (paramArrayOfint2.length == this.minGop.length) {
        this.minGop = paramArrayOfint2;
        return;
      } 
      throw new IllegalArgumentException("Wrong array size !");
    } 
    throw new IllegalArgumentException("Wrong array size !");
  }
  
  public FHNP_SmartEncodeAttr_t(Pointer paramPointer) {
    super(paramPointer);
  }
  
  protected List<String> getFieldOrder() {
    return Arrays.asList(new String[] { "bTexture", "bBackgroudModel", "bMbconsist", "gopTHNum", "thVal", "minGop" });
  }
  
  public static class ByReference extends FHNP_SmartEncodeAttr_t implements Structure.ByReference {}
  
  public static class ByValue extends FHNP_SmartEncodeAttr_t implements Structure.ByValue {}
}


/* Location:              /home/platinum/Documents/AndroidRE/com.guanxu.technology.snaptain_era_s5c_29_apps.evozi.com-dex2jar.jar!/com/netopsun/fhapi/FHNP_SmartEncodeAttr_t.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */