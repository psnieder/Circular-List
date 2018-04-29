package circularlist;

public interface ListIterator<E> {
  boolean hasNext(); // Returns true if this list iterator has more elements while traversing in the forward direction.
  boolean hasPrevious(); // Returns true if this list iterator has more elements while traversing in the reverse direction.
  E next(); // Returns the next Element.
  int nextIndex(); // Returns the index of the next element.
  E previous(); // Returns the previous Element
  int previousIndex(); // Returns the index of the previous element.
  void remove(); // Removes from the list the last element that was returned.
  void set(E e); // Replaces the last element returned. (Optional for this assignment)
}