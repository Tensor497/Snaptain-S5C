package com.netopsun.fhapi;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import java.util.Arrays;
import java.util.List;

public class LPFHNP_PTZProtocol_t_struct extends Structure {
  public byte btCruiseCount;
  
  public byte btPresetCount;
  
  public byte btRes;
  
  public byte btTrackCout;
  
  public byte[] chProtocolName = new byte[32];
  
  public int[] dwBaudRate = new int[32];
  
  public LPFHNP_PTZProtocol_t_struct() {}
  
  public LPFHNP_PTZProtocol_t_struct(Pointer paramPointer) {
    super(paramPointer);
  }
  
  public LPFHNP_PTZProtocol_t_struct(byte[] paramArrayOfbyte, int[] paramArrayOfint, byte paramByte1, byte paramByte2, byte paramByte3, byte paramByte4) {
    if (paramArrayOfbyte.length == this.chProtocolName.length) {
      this.chProtocolName = paramArrayOfbyte;
      if (paramArrayOfint.length == this.dwBaudRate.length) {
        this.dwBaudRate = paramArrayOfint;
        this.btPresetCount = (byte)paramByte1;
        this.btCruiseCount = (byte)paramByte2;
        this.btTrackCout = (byte)paramByte3;
        this.btRes = (byte)paramByte4;
        return;
      } 
      throw new IllegalArgumentException("Wrong array size !");
    } 
    throw new IllegalArgumentException("Wrong array size !");
  }
  
  protected List<String> getFieldOrder() {
    return Arrays.asList(new String[] { "chProtocolName", "dwBaudRate", "btPresetCount", "btCruiseCount", "btTrackCout", "btRes" });
  }
  
  public static class ByReference extends LPFHNP_PTZProtocol_t_struct implements Structure.ByReference {}
  
  public static class ByValue extends LPFHNP_PTZProtocol_t_struct implements Structure.ByValue {}
}


/* Location:              /home/platinum/Documents/AndroidRE/com.guanxu.technology.snaptain_era_s5c_29_apps.evozi.com-dex2jar.jar!/com/netopsun/fhapi/LPFHNP_PTZProtocol_t_struct.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */