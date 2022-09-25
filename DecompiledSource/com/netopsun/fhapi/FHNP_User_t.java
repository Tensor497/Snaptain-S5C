package com.netopsun.fhapi;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import java.util.Arrays;
import java.util.List;

public class FHNP_User_t extends Structure {
  public byte[] btRes = new byte[2];
  
  public byte btUserGroup;
  
  public byte btUserOnline;
  
  public byte[] chPassword = new byte[32];
  
  public byte[] chUser = new byte[32];
  
  public FHNP_User_t() {}
  
  public FHNP_User_t(Pointer paramPointer) {
    super(paramPointer);
  }
  
  public FHNP_User_t(byte[] paramArrayOfbyte1, byte[] paramArrayOfbyte2, byte paramByte1, byte paramByte2, byte[] paramArrayOfbyte3) {
    if (paramArrayOfbyte1.length == this.chUser.length) {
      this.chUser = paramArrayOfbyte1;
      if (paramArrayOfbyte2.length == this.chPassword.length) {
        this.chPassword = paramArrayOfbyte2;
        this.btUserGroup = (byte)paramByte1;
        this.btUserOnline = (byte)paramByte2;
        if (paramArrayOfbyte3.length == this.btRes.length) {
          this.btRes = paramArrayOfbyte3;
          return;
        } 
        throw new IllegalArgumentException("Wrong array size !");
      } 
      throw new IllegalArgumentException("Wrong array size !");
    } 
    throw new IllegalArgumentException("Wrong array size !");
  }
  
  protected List<String> getFieldOrder() {
    return Arrays.asList(new String[] { "chUser", "chPassword", "btUserGroup", "btUserOnline", "btRes" });
  }
  
  public static class ByReference extends FHNP_User_t implements Structure.ByReference {}
  
  public static class ByValue extends FHNP_User_t implements Structure.ByValue {}
}


/* Location:              /home/platinum/Documents/AndroidRE/com.guanxu.technology.snaptain_era_s5c_29_apps.evozi.com-dex2jar.jar!/com/netopsun/fhapi/FHNP_User_t.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */