package com.bumptech.glide.load.engine.bitmap_recycle;

import java.util.Map;
import java.util.TreeMap;

class PrettyPrintTreeMap<K, V> extends TreeMap<K, V> {
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("( ");
    for (Map.Entry<K, V> entry : entrySet()) {
      stringBuilder.append('{');
      stringBuilder.append(entry.getKey());
      stringBuilder.append(':');
      stringBuilder.append(entry.getValue());
      stringBuilder.append("}, ");
    } 
    if (!isEmpty())
      stringBuilder.replace(stringBuilder.length() - 2, stringBuilder.length(), ""); 
    stringBuilder.append(" )");
    return stringBuilder.toString();
  }
}


/* Location:              /home/platinum/Documents/AndroidRE/com.guanxu.technology.snaptain_era_s5c_29_apps.evozi.com-dex2jar.jar!/com/bumptech/glide/load/engine/bitmap_recycle/PrettyPrintTreeMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */