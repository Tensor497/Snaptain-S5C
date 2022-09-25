package com.netopsun.fhapi;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import java.util.Arrays;
import java.util.List;

public class FHNP_Picture_t extends Structure {
  public byte btChannel;
  
  public byte btLockFlag;
  
  public byte btPicType;
  
  public byte btRes;
  
  public long ullDataSize;
  
  public long ullFrameCount;
  
  public long ullStartTime;
  
  public long ullStopTime;
  
  public FHNP_Picture_t() {}
  
  public FHNP_Picture_t(byte paramByte1, byte paramByte2, byte paramByte3, byte paramByte4, long paramLong1, long paramLong2, long paramLong3, long paramLong4) {
    this.btChannel = (byte)paramByte1;
    this.btPicType = (byte)paramByte2;
    this.btLockFlag = (byte)paramByte3;
    this.btRes = (byte)paramByte4;
    this.ullStartTime = paramLong1;
    this.ullStopTime = paramLong2;
    this.ullDataSize = paramLong3;
    this.ullFrameCount = paramLong4;
  }
  
  public FHNP_Picture_t(Pointer paramPointer) {
    super(paramPointer);
  }
  
  protected List<String> getFieldOrder() {
    return Arrays.asList(new String[] { "btChannel", "btPicType", "btLockFlag", "btRes", "ullStartTime", "ullStopTime", "ullDataSize", "ullFrameCount" });
  }
  
  public static class ByReference extends FHNP_Picture_t implements Structure.ByReference {}
  
  public static class ByValue extends FHNP_Picture_t implements Structure.ByValue {}
}


/* Location:              /home/platinum/Documents/AndroidRE/com.guanxu.technology.snaptain_era_s5c_29_apps.evozi.com-dex2jar.jar!/com/netopsun/fhapi/FHNP_Picture_t.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */