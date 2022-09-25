package com.netopsun.fhapi;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import java.util.Arrays;
import java.util.List;

public class FHDS_PubNetAddr_t extends Structure {
  public byte[] btRes = new byte[2];
  
  public byte[] sMAC = new byte[18];
  
  public FHDS_IPAddr_t stGateway;
  
  public FHDS_IPAddr_t stIP;
  
  public FHDS_IPAddr_t stMaskIP;
  
  public short wPort;
  
  public FHDS_PubNetAddr_t() {}
  
  public FHDS_PubNetAddr_t(Pointer paramPointer) {
    super(paramPointer);
  }
  
  public FHDS_PubNetAddr_t(byte[] paramArrayOfbyte1, FHDS_IPAddr_t paramFHDS_IPAddr_t1, FHDS_IPAddr_t paramFHDS_IPAddr_t2, FHDS_IPAddr_t paramFHDS_IPAddr_t3, short paramShort, byte[] paramArrayOfbyte2) {
    if (paramArrayOfbyte1.length == this.sMAC.length) {
      this.sMAC = paramArrayOfbyte1;
      this.stIP = paramFHDS_IPAddr_t1;
      this.stMaskIP = paramFHDS_IPAddr_t2;
      this.stGateway = paramFHDS_IPAddr_t3;
      this.wPort = (short)paramShort;
      if (paramArrayOfbyte2.length == this.btRes.length) {
        this.btRes = paramArrayOfbyte2;
        return;
      } 
      throw new IllegalArgumentException("Wrong array size !");
    } 
    throw new IllegalArgumentException("Wrong array size !");
  }
  
  protected List<String> getFieldOrder() {
    return Arrays.asList(new String[] { "sMAC", "stIP", "stMaskIP", "stGateway", "wPort", "btRes" });
  }
  
  public static class ByReference extends FHDS_PubNetAddr_t implements Structure.ByReference {}
  
  public static class ByValue extends FHDS_PubNetAddr_t implements Structure.ByValue {}
}


/* Location:              /home/platinum/Documents/AndroidRE/com.guanxu.technology.snaptain_era_s5c_29_apps.evozi.com-dex2jar.jar!/com/netopsun/fhapi/FHDS_PubNetAddr_t.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */