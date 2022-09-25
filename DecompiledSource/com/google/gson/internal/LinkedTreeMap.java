package com.google.gson.internal;

import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

public final class LinkedTreeMap<K, V> extends AbstractMap<K, V> implements Serializable {
  private static final Comparator<Comparable> NATURAL_ORDER = new Comparator<Comparable>() {
      public int compare(Comparable<Comparable> param1Comparable1, Comparable param1Comparable2) {
        return param1Comparable1.compareTo(param1Comparable2);
      }
    };
  
  Comparator<? super K> comparator;
  
  private EntrySet entrySet;
  
  final Node<K, V> header;
  
  private KeySet keySet;
  
  int modCount;
  
  Node<K, V> root;
  
  int size;
  
  public LinkedTreeMap() {
    this((Comparator)NATURAL_ORDER);
  }
  
  public LinkedTreeMap(Comparator<? super K> paramComparator) {
    Comparator<Comparable> comparator;
    this.size = 0;
    this.modCount = 0;
    this.header = new Node<K, V>();
    if (paramComparator == null)
      comparator = NATURAL_ORDER; 
    this.comparator = (Comparator)comparator;
  }
  
  private boolean equal(Object paramObject1, Object paramObject2) {
    return (paramObject1 == paramObject2 || (paramObject1 != null && paramObject1.equals(paramObject2)));
  }
  
  private void rebalance(Node<K, V> paramNode, boolean paramBoolean) {
    while (paramNode != null) {
      int i;
      int j;
      Node<K, V> node1 = paramNode.left;
      Node<K, V> node2 = paramNode.right;
      boolean bool = false;
      byte b = 0;
      if (node1 != null) {
        i = node1.height;
      } else {
        i = 0;
      } 
      if (node2 != null) {
        j = node2.height;
      } else {
        j = 0;
      } 
      int k = i - j;
      if (k == -2) {
        node1 = node2.left;
        Node<K, V> node = node2.right;
        if (node != null) {
          i = node.height;
        } else {
          i = 0;
        } 
        j = b;
        if (node1 != null)
          j = node1.height; 
        i = j - i;
        if (i == -1 || (i == 0 && !paramBoolean)) {
          rotateLeft(paramNode);
        } else {
          rotateRight(node2);
          rotateLeft(paramNode);
        } 
        if (paramBoolean)
          break; 
      } else if (k == 2) {
        node2 = node1.left;
        Node<K, V> node = node1.right;
        if (node != null) {
          i = node.height;
        } else {
          i = 0;
        } 
        j = bool;
        if (node2 != null)
          j = node2.height; 
        i = j - i;
        if (i == 1 || (i == 0 && !paramBoolean)) {
          rotateRight(paramNode);
        } else {
          rotateLeft(node1);
          rotateRight(paramNode);
        } 
        if (paramBoolean)
          break; 
      } else if (k == 0) {
        paramNode.height = i + 1;
        if (paramBoolean)
          break; 
      } else {
        paramNode.height = Math.max(i, j) + 1;
        if (!paramBoolean)
          break; 
      } 
      paramNode = paramNode.parent;
    } 
  }
  
  private void replaceInParent(Node<K, V> paramNode1, Node<K, V> paramNode2) {
    Node<K, V> node = paramNode1.parent;
    paramNode1.parent = null;
    if (paramNode2 != null)
      paramNode2.parent = node; 
    if (node != null) {
      if (node.left == paramNode1) {
        node.left = paramNode2;
      } else {
        node.right = paramNode2;
      } 
    } else {
      this.root = paramNode2;
    } 
  }
  
  private void rotateLeft(Node<K, V> paramNode) {
    Node<K, V> node1 = paramNode.left;
    Node<K, V> node2 = paramNode.right;
    Node<K, V> node3 = node2.left;
    Node<K, V> node4 = node2.right;
    paramNode.right = node3;
    if (node3 != null)
      node3.parent = paramNode; 
    replaceInParent(paramNode, node2);
    node2.left = paramNode;
    paramNode.parent = node2;
    byte b = 0;
    if (node1 != null) {
      i = node1.height;
    } else {
      i = 0;
    } 
    if (node3 != null) {
      j = node3.height;
    } else {
      j = 0;
    } 
    paramNode.height = Math.max(i, j) + 1;
    int j = paramNode.height;
    int i = b;
    if (node4 != null)
      i = node4.height; 
    node2.height = Math.max(j, i) + 1;
  }
  
  private void rotateRight(Node<K, V> paramNode) {
    Node<K, V> node1 = paramNode.left;
    Node<K, V> node2 = paramNode.right;
    Node<K, V> node3 = node1.left;
    Node<K, V> node4 = node1.right;
    paramNode.left = node4;
    if (node4 != null)
      node4.parent = paramNode; 
    replaceInParent(paramNode, node1);
    node1.right = paramNode;
    paramNode.parent = node1;
    byte b = 0;
    if (node2 != null) {
      i = node2.height;
    } else {
      i = 0;
    } 
    if (node4 != null) {
      j = node4.height;
    } else {
      j = 0;
    } 
    paramNode.height = Math.max(i, j) + 1;
    int j = paramNode.height;
    int i = b;
    if (node3 != null)
      i = node3.height; 
    node1.height = Math.max(j, i) + 1;
  }
  
  private Object writeReplace() throws ObjectStreamException {
    return new LinkedHashMap<Object, Object>(this);
  }
  
  public void clear() {
    this.root = null;
    this.size = 0;
    this.modCount++;
    Node<K, V> node = this.header;
    node.prev = node;
    node.next = node;
  }
  
  public boolean containsKey(Object paramObject) {
    boolean bool;
    if (findByObject(paramObject) != null) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public Set<Map.Entry<K, V>> entrySet() {
    EntrySet entrySet = this.entrySet;
    if (entrySet == null) {
      entrySet = new EntrySet();
      this.entrySet = entrySet;
    } 
    return entrySet;
  }
  
  Node<K, V> find(K paramK, boolean paramBoolean) {
    StringBuilder stringBuilder;
    boolean bool;
    Comparator<? super K> comparator = this.comparator;
    Node<K, V> node2 = this.root;
    if (node2 != null) {
      Comparable<K> comparable;
      if (comparator == NATURAL_ORDER) {
        comparable = (Comparable)paramK;
      } else {
        comparable = null;
      } 
      while (true) {
        Node<K, V> node;
        if (comparable != null) {
          bool = comparable.compareTo(node2.key);
        } else {
          bool = comparator.compare(paramK, node2.key);
        } 
        if (bool == 0)
          return node2; 
        if (bool < 0) {
          node = node2.left;
        } else {
          node = node2.right;
        } 
        if (node == null)
          break; 
        node2 = node;
      } 
    } else {
      bool = false;
    } 
    if (!paramBoolean)
      return null; 
    Node<K, V> node3 = this.header;
    if (node2 == null) {
      if (comparator != NATURAL_ORDER || paramK instanceof Comparable) {
        node1 = new Node<K, V>(node2, paramK, node3, node3.prev);
        this.root = node1;
        this.size++;
        this.modCount++;
        return node1;
      } 
      stringBuilder = new StringBuilder();
      stringBuilder.append(node1.getClass().getName());
      stringBuilder.append(" is not Comparable");
      throw new ClassCastException(stringBuilder.toString());
    } 
    Node<K, V> node1 = new Node<K, V>((Node<K, V>)stringBuilder, (K)node1, node3, node3.prev);
    if (bool) {
      ((Node)stringBuilder).left = node1;
    } else {
      ((Node)stringBuilder).right = node1;
    } 
    rebalance((Node<K, V>)stringBuilder, true);
    this.size++;
    this.modCount++;
    return node1;
  }
  
  Node<K, V> findByEntry(Map.Entry<?, ?> paramEntry) {
    boolean bool;
    Node<K, V> node = findByObject(paramEntry.getKey());
    if (node != null && equal(node.value, paramEntry.getValue())) {
      bool = true;
    } else {
      bool = false;
    } 
    if (bool) {
      paramEntry = node;
    } else {
      paramEntry = null;
    } 
    return (Node)paramEntry;
  }
  
  Node<K, V> findByObject(Object paramObject) {
    Node<K, V> node1 = null;
    Node<K, V> node2 = node1;
    if (paramObject != null)
      try {
        node2 = find((K)paramObject, false);
      } catch (ClassCastException classCastException) {
        node2 = node1;
      }  
    return node2;
  }
  
  public V get(Object<K, V> paramObject) {
    paramObject = (Object<K, V>)findByObject(paramObject);
    if (paramObject != null) {
      V v = ((Node)paramObject).value;
    } else {
      paramObject = null;
    } 
    return (V)paramObject;
  }
  
  public Set<K> keySet() {
    KeySet keySet = this.keySet;
    if (keySet == null) {
      keySet = new KeySet();
      this.keySet = keySet;
    } 
    return keySet;
  }
  
  public V put(K paramK, V paramV) {
    if (paramK != null) {
      Node<K, V> node = find(paramK, true);
      V v = node.value;
      node.value = paramV;
      return v;
    } 
    throw new NullPointerException("key == null");
  }
  
  public V remove(Object<K, V> paramObject) {
    paramObject = (Object<K, V>)removeInternalByKey(paramObject);
    if (paramObject != null) {
      V v = ((Node)paramObject).value;
    } else {
      paramObject = null;
    } 
    return (V)paramObject;
  }
  
  void removeInternal(Node<K, V> paramNode, boolean paramBoolean) {
    if (paramBoolean) {
      paramNode.prev.next = paramNode.next;
      paramNode.next.prev = paramNode.prev;
    } 
    Node<K, V> node1 = paramNode.left;
    Node<K, V> node2 = paramNode.right;
    Node<K, V> node3 = paramNode.parent;
    int i = 0;
    if (node1 != null && node2 != null) {
      boolean bool;
      if (node1.height > node2.height) {
        node2 = node1.last();
      } else {
        node2 = node2.first();
      } 
      removeInternal(node2, false);
      node1 = paramNode.left;
      if (node1 != null) {
        bool = node1.height;
        node2.left = node1;
        node1.parent = node2;
        paramNode.left = null;
      } else {
        bool = false;
      } 
      node1 = paramNode.right;
      if (node1 != null) {
        i = node1.height;
        node2.right = node1;
        node1.parent = node2;
        paramNode.right = null;
      } 
      node2.height = Math.max(bool, i) + 1;
      replaceInParent(paramNode, node2);
      return;
    } 
    if (node1 != null) {
      replaceInParent(paramNode, node1);
      paramNode.left = null;
    } else if (node2 != null) {
      replaceInParent(paramNode, node2);
      paramNode.right = null;
    } else {
      replaceInParent(paramNode, null);
    } 
    rebalance(node3, false);
    this.size--;
    this.modCount++;
  }
  
  Node<K, V> removeInternalByKey(Object<K, V> paramObject) {
    paramObject = (Object<K, V>)findByObject(paramObject);
    if (paramObject != null)
      removeInternal((Node<K, V>)paramObject, true); 
    return (Node<K, V>)paramObject;
  }
  
  public int size() {
    return this.size;
  }
  
  class EntrySet extends AbstractSet<Map.Entry<K, V>> {
    public void clear() {
      LinkedTreeMap.this.clear();
    }
    
    public boolean contains(Object param1Object) {
      boolean bool;
      if (param1Object instanceof Map.Entry && LinkedTreeMap.this.findByEntry((Map.Entry<?, ?>)param1Object) != null) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public Iterator<Map.Entry<K, V>> iterator() {
      return new LinkedTreeMap<K, V>.LinkedTreeMapIterator<Map.Entry<K, V>>() {
          public Map.Entry<K, V> next() {
            return nextNode();
          }
        };
    }
    
    public boolean remove(Object param1Object) {
      if (!(param1Object instanceof Map.Entry))
        return false; 
      param1Object = LinkedTreeMap.this.findByEntry((Map.Entry<?, ?>)param1Object);
      if (param1Object == null)
        return false; 
      LinkedTreeMap.this.removeInternal((LinkedTreeMap.Node)param1Object, true);
      return true;
    }
    
    public int size() {
      return LinkedTreeMap.this.size;
    }
  }
  
  class null extends LinkedTreeMapIterator<Map.Entry<K, V>> {
    public Map.Entry<K, V> next() {
      return nextNode();
    }
  }
  
  final class KeySet extends AbstractSet<K> {
    public void clear() {
      LinkedTreeMap.this.clear();
    }
    
    public boolean contains(Object param1Object) {
      return LinkedTreeMap.this.containsKey(param1Object);
    }
    
    public Iterator<K> iterator() {
      return new LinkedTreeMap<K, V>.LinkedTreeMapIterator<K>() {
          public K next() {
            return (nextNode()).key;
          }
        };
    }
    
    public boolean remove(Object param1Object) {
      boolean bool;
      if (LinkedTreeMap.this.removeInternalByKey(param1Object) != null) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public int size() {
      return LinkedTreeMap.this.size;
    }
  }
  
  class null extends LinkedTreeMapIterator<K> {
    public K next() {
      return (nextNode()).key;
    }
  }
  
  private abstract class LinkedTreeMapIterator<T> implements Iterator<T> {
    int expectedModCount = LinkedTreeMap.this.modCount;
    
    LinkedTreeMap.Node<K, V> lastReturned = null;
    
    LinkedTreeMap.Node<K, V> next = LinkedTreeMap.this.header.next;
    
    public final boolean hasNext() {
      boolean bool;
      if (this.next != LinkedTreeMap.this.header) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    final LinkedTreeMap.Node<K, V> nextNode() {
      LinkedTreeMap.Node<K, V> node = this.next;
      if (node != LinkedTreeMap.this.header) {
        if (LinkedTreeMap.this.modCount == this.expectedModCount) {
          this.next = node.next;
          this.lastReturned = node;
          return node;
        } 
        throw new ConcurrentModificationException();
      } 
      throw new NoSuchElementException();
    }
    
    public final void remove() {
      LinkedTreeMap.Node<K, V> node = this.lastReturned;
      if (node != null) {
        LinkedTreeMap.this.removeInternal(node, true);
        this.lastReturned = null;
        this.expectedModCount = LinkedTreeMap.this.modCount;
        return;
      } 
      throw new IllegalStateException();
    }
  }
  
  static final class Node<K, V> implements Map.Entry<K, V> {
    int height;
    
    final K key;
    
    Node<K, V> left;
    
    Node<K, V> next;
    
    Node<K, V> parent;
    
    Node<K, V> prev;
    
    Node<K, V> right;
    
    V value;
    
    Node() {
      this.key = null;
      this.prev = this;
      this.next = this;
    }
    
    Node(Node<K, V> param1Node1, K param1K, Node<K, V> param1Node2, Node<K, V> param1Node3) {
      this.parent = param1Node1;
      this.key = param1K;
      this.height = 1;
      this.next = param1Node2;
      this.prev = param1Node3;
      param1Node3.next = this;
      param1Node2.prev = this;
    }
    
    public boolean equals(Object param1Object) {
      // Byte code:
      //   0: aload_1
      //   1: instanceof java/util/Map$Entry
      //   4: istore_2
      //   5: iconst_0
      //   6: istore_3
      //   7: iload_3
      //   8: istore #4
      //   10: iload_2
      //   11: ifeq -> 108
      //   14: aload_1
      //   15: checkcast java/util/Map$Entry
      //   18: astore_1
      //   19: aload_0
      //   20: getfield key : Ljava/lang/Object;
      //   23: astore #5
      //   25: aload #5
      //   27: ifnonnull -> 45
      //   30: iload_3
      //   31: istore #4
      //   33: aload_1
      //   34: invokeinterface getKey : ()Ljava/lang/Object;
      //   39: ifnonnull -> 108
      //   42: goto -> 62
      //   45: iload_3
      //   46: istore #4
      //   48: aload #5
      //   50: aload_1
      //   51: invokeinterface getKey : ()Ljava/lang/Object;
      //   56: invokevirtual equals : (Ljava/lang/Object;)Z
      //   59: ifeq -> 108
      //   62: aload_0
      //   63: getfield value : Ljava/lang/Object;
      //   66: astore #5
      //   68: aload #5
      //   70: ifnonnull -> 88
      //   73: iload_3
      //   74: istore #4
      //   76: aload_1
      //   77: invokeinterface getValue : ()Ljava/lang/Object;
      //   82: ifnonnull -> 108
      //   85: goto -> 105
      //   88: iload_3
      //   89: istore #4
      //   91: aload #5
      //   93: aload_1
      //   94: invokeinterface getValue : ()Ljava/lang/Object;
      //   99: invokevirtual equals : (Ljava/lang/Object;)Z
      //   102: ifeq -> 108
      //   105: iconst_1
      //   106: istore #4
      //   108: iload #4
      //   110: ireturn
    }
    
    public Node<K, V> first() {
      Node<K, V> node1 = this.left;
      Node<K, V> node2 = this;
      while (node1 != null) {
        Node<K, V> node = node1.left;
        node2 = node1;
        node1 = node;
      } 
      return node2;
    }
    
    public K getKey() {
      return this.key;
    }
    
    public V getValue() {
      return this.value;
    }
    
    public int hashCode() {
      int j;
      K k = this.key;
      int i = 0;
      if (k == null) {
        j = 0;
      } else {
        j = k.hashCode();
      } 
      V v = this.value;
      if (v != null)
        i = v.hashCode(); 
      return j ^ i;
    }
    
    public Node<K, V> last() {
      Node<K, V> node1 = this.right;
      Node<K, V> node2 = this;
      while (node1 != null) {
        Node<K, V> node = node1.right;
        node2 = node1;
        node1 = node;
      } 
      return node2;
    }
    
    public V setValue(V param1V) {
      V v = this.value;
      this.value = param1V;
      return v;
    }
    
    public String toString() {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(this.key);
      stringBuilder.append("=");
      stringBuilder.append(this.value);
      return stringBuilder.toString();
    }
  }
}


/* Location:              /home/platinum/Documents/AndroidRE/com.guanxu.technology.snaptain_era_s5c_29_apps.evozi.com-dex2jar.jar!/com/google/gson/internal/LinkedTreeMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */