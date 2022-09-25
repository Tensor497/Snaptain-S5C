package com.netopsun.fhapi;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import java.util.Arrays;
import java.util.List;

public class LPFHNP_RecScheduleSegment_t_struct extends Structure {
  public byte btEnable;
  
  public byte btRecAudio;
  
  public byte[] btReserve = new byte[2];
  
  public byte btStartHour;
  
  public byte btStartMinute;
  
  public byte btStopHour;
  
  public byte btStopMinute;
  
  public LPFHNP_RecScheduleSegment_t_struct() {}
  
  public LPFHNP_RecScheduleSegment_t_struct(byte paramByte1, byte paramByte2, byte paramByte3, byte paramByte4, byte paramByte5, byte paramByte6, byte[] paramArrayOfbyte) {
    this.btEnable = (byte)paramByte1;
    this.btRecAudio = (byte)paramByte2;
    this.btStartHour = (byte)paramByte3;
    this.btStartMinute = (byte)paramByte4;
    this.btStopHour = (byte)paramByte5;
    this.btStopMinute = (byte)paramByte6;
    if (paramArrayOfbyte.length == this.btReserve.length) {
      this.btReserve = paramArrayOfbyte;
      return;
    } 
    throw new IllegalArgumentException("Wrong array size !");
  }
  
  public LPFHNP_RecScheduleSegment_t_struct(Pointer paramPointer) {
    super(paramPointer);
  }
  
  protected List<String> getFieldOrder() {
    return Arrays.asList(new String[] { "btEnable", "btRecAudio", "btStartHour", "btStartMinute", "btStopHour", "btStopMinute", "btReserve" });
  }
  
  public static class ByReference extends LPFHNP_RecScheduleSegment_t_struct implements Structure.ByReference {}
  
  public static class ByValue extends LPFHNP_RecScheduleSegment_t_struct implements Structure.ByValue {}
}


/* Location:              /home/platinum/Documents/AndroidRE/com.guanxu.technology.snaptain_era_s5c_29_apps.evozi.com-dex2jar.jar!/com/netopsun/fhapi/LPFHNP_RecScheduleSegment_t_struct.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */