package com.netopsun.fhapi;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import java.util.Arrays;
import java.util.List;

public class LPFHNP_EncodeVideo_t_struct extends Structure {
  public byte btBRCtrl;
  
  public byte btDeinter;
  
  public byte btDenoise;
  
  public byte btResolution;
  
  public int iBitRate;
  
  public int iFrameRate;
  
  public int iGOP;
  
  public LPFHNP_EncodeVideo_t_struct() {}
  
  public LPFHNP_EncodeVideo_t_struct(byte paramByte1, byte paramByte2, byte paramByte3, byte paramByte4, int paramInt1, int paramInt2, int paramInt3) {
    this.btResolution = (byte)paramByte1;
    this.btBRCtrl = (byte)paramByte2;
    this.btDenoise = (byte)paramByte3;
    this.btDeinter = (byte)paramByte4;
    this.iBitRate = paramInt1;
    this.iFrameRate = paramInt2;
    this.iGOP = paramInt3;
  }
  
  public LPFHNP_EncodeVideo_t_struct(Pointer paramPointer) {
    super(paramPointer);
  }
  
  protected List<String> getFieldOrder() {
    return Arrays.asList(new String[] { "btResolution", "btBRCtrl", "btDenoise", "btDeinter", "iBitRate", "iFrameRate", "iGOP" });
  }
  
  public static class ByReference extends LPFHNP_EncodeVideo_t_struct implements Structure.ByReference {}
  
  public static class ByValue extends LPFHNP_EncodeVideo_t_struct implements Structure.ByValue {}
}


/* Location:              /home/platinum/Documents/AndroidRE/com.guanxu.technology.snaptain_era_s5c_29_apps.evozi.com-dex2jar.jar!/com/netopsun/fhapi/LPFHNP_EncodeVideo_t_struct.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */