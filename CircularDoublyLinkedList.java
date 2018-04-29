package circularlist;

import java.util.ListIterator;
import java.util.NoSuchElementException;

public class CircularDoublyLinkedList<E> implements IList211<E>, Iterable<E> {
  
  private DLinkedNode head;
  private DLinkedNode tail;
  private int size;
  
  public CircularDoublyLinkedList() {
    this.head = null;
    this.tail = null;
    this.size = 0;
  }
  
  public CircularDoublyLinkedList(E[] data) {
    this.head = null;
    this.tail = null;
    this.size = 0;
    
    for (int i = 0; i < data.length; i++) {
      this.add(data[i]);
    }
  }
  
  private class DLinkedNode {
    E data;
    DLinkedNode next;
    DLinkedNode prev;
    
    public DLinkedNode(E data, DLinkedNode next, DLinkedNode prev) {
      this.data = data;
      this.next = next;
      this.prev = prev;
    }
  }
  
  private class CircularDoublyLinkedListIterator implements ListIterator<E> {
    
    private DLinkedNode nextNode;
    private DLinkedNode lastReturnNode;
    private int indexCount;
    
    public CircularDoublyLinkedListIterator() {
      this.nextNode = head;
      this.indexCount = 0;
      this.lastReturnNode = null;
    }

    @Override
    public boolean hasNext() {
      return size > 0;
    }

    @Override
    public boolean hasPrevious() {
      return size > 0;
    }

    @Override
    public E next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      } //end if statement
      E temp = nextNode.data;
      lastReturnNode = nextNode;
      nextNode = nextNode.next;
      indexCount++;
      if (nextNode == head) {
        indexCount = 0;
      }
      return temp;
    }

    @Override
    public int nextIndex() {
      if (hasNext()) {
        return indexCount;
      } //end if statement
      return size;
    } //end nextIndex

    @Override
    public E previous() {
      if (!hasPrevious()) {
        throw new NoSuchElementException();
      } //end if statement
      nextNode = nextNode.prev;
      indexCount--;
      if (nextNode == tail) {
        indexCount = size - 1;
      }
      lastReturnNode = nextNode;
      return nextNode.data;
    } //end previous

    @Override
    public int previousIndex() {
      if (hasPrevious()) {
        if (indexCount == 0) {
          return size - 1;
        } else {
          return indexCount - 1;
        }
      } //end if statement
      return -1;
    } //end previousIndex
    
    @Override
    public void remove() {
      if (lastReturnNode == null) {
        return;
      }
      if (lastReturnNode == head) {
        head.next.prev = head.prev;
        head = head.next;
        circle();
      } else if (lastReturnNode == tail) {
        tail.prev.next = tail.next;
        tail = tail.prev;
        circle();
        indexCount = 0;
      } else {
        lastReturnNode.next.prev = lastReturnNode.prev;
        lastReturnNode.prev.next = lastReturnNode.next;
      }
      size--;
      lastReturnNode = null;
    }

    @Override
    public void set(E e) {
      // TODO Auto-generated method stub
    }

    @Override
    public void add(E e) {
      // TODO Auto-generated method stub  
    }
    
  }

  @Override
  public ListIterator<E> iterator() {
    return new CircularDoublyLinkedListIterator();
  }

  @Override
  public E get(int index) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException();
    } //end if statement
    DLinkedNode temp = head;
    for (int i = 0; i < index; i++) {
      temp = temp.next;
    } //end for loop
    return temp.data;
  }

  @Override
  public E set(int index, E e) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException();
    } //end if statement
    if (e.equals(null)) {
      throw new NullPointerException();
    } //end if statement
    DLinkedNode temp = head;
    for (int i = 0; i < index; i++) {
      temp = temp.next;
    } //end set
    E oldData = temp.data;
    temp.data = e;
    return oldData;
  }

  @Override
  public int indexOf(Object o) {
    DLinkedNode temp = head;
    for (int i = 0; i < size && temp != null; i++) {
      if (temp.data.equals(o)) {
        return i;
      } //end if statement
      temp = temp.next;
    } //end for loop
    return -1;
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public boolean add(E e) {
    if (e.equals(null)) {
      throw new NullPointerException();
    } //end if statement
    DLinkedNode node = new DLinkedNode(e, null, null);
    if (size == 0) {
      head = node;
      tail = node;
      circle();
    } else {
      node.next = tail.next;
      node.prev = tail;
      tail.next = node;
      tail = node;
      circle();
    } //end else statement
    size++;
    return true;
  }

  @Override
  public void add(int index, E e) {
    if (index < 0 || index > size) {
      throw new IndexOutOfBoundsException();
    } //end if statement
    if (e.equals(null)) {
      throw new NullPointerException();
    } //end if statement
    DLinkedNode node = new DLinkedNode(e, null, null);
    if (index == 0) { 
      node.next = head;
      node.prev = head.prev;
      head.prev = node;
      head = node;
      circle();
    } else if (index == size) { 
      node.next = tail.next;
      node.prev = tail;
      tail.next = node;
      tail = node;
      circle();
    } else {
      DLinkedNode temp = head;
      for (int i = 0; i < index; i++) {
        temp = temp.next;
      } //end for loop
      node.next = temp;
      node.prev = temp.prev;
      temp.prev.next = node;
      temp.prev = node;
    } //end else statement
    size++; 
    
  }

  @Override
  public E remove(int index) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException();
    } //end if statement
    DLinkedNode temp;
    if (index == 0) { //if you want to remove the head
      temp = head;
      head.next.prev = head.prev;
      head = head.next;
      circle();
      size--;
      return temp.data;
    } else if (index == (size - 1)) { //if you want to remove the tail
      temp = tail;
      tail.prev.next = tail.next;
      tail = tail.prev;
      circle();
      size--;
      return temp.data;
    } else {
      temp = head;
      for (int i = 0; i < index; i++) {
        temp = temp.next;
      } //end for loop
      temp.prev.next = temp.next;
      temp.next.prev = temp.prev;
      size--;
      return temp.data;
    } //end else
  } 
  
  private void circle() {
    head.prev = tail;
    tail.next = head;
  }
  
}
