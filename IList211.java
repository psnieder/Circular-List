package circularlist;

public interface IList211<E> {
  E get(int index);
  E set(int index, E element);
  int indexOf(Object obj);
  int size();
  boolean add(E e);
  void add(int index, E element);
  E remove(int index);
}
