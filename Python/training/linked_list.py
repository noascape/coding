class Node:
    """A node in a singly linked list."""
    def __init__(self, data=None, next_node=None):
        self.data = data
        self.next = next_node

class SinglyLinkedList:
    """A simple singly linked list implementation."""
    def __init__(self):
        self.head = None
        self.size = 0

    def __len__(self):
        return self.size

    def is_empty(self):
        return self.size == 0

    def prepend(self, data):
        """Insert a new node at the beginning of the list."""
        new_node = Node(data, self.head)
        self.head = new_node
        self.size += 1

    def append(self, data):
        """Insert a new node at the end of the list."""
        new_node = Node(data)
        if self.head is None:
            self.head = new_node
        else:
            current = self.head
            while current.next:
                current = current.next
            current.next = new_node
        self.size += 1

    def find(self, key):
        """Search for the first node containing 'key' and return it, else None."""
        current = self.head
        while current:
            if current.data == key:
                return current
            current = current.next
        return None

    def remove(self, key):
        """Remove first occurrence of 'key' in the list. Raises ValueError if not found."""
        current = self.head
        previous = None
        while current and current.data != key:
            previous = current
            current = current.next
        if current is None:
            raise ValueError(f"{key} not found in list.")
        if previous is None:
            self.head = current.next
        else:
            previous.next = current.next
        self.size -= 1

    def __iter__(self):
        """Iterate over the elements of the list."""
        current = self.head
        while current:
            yield current.data
            current = current.next

    def __repr__(self):
        return '->'.join(str(item) for item in self)

# Example usage:
if __name__ == "__main__":
    ll = SinglyLinkedList()
    ll.append(10)
    ll.append(20)
    ll.prepend(5)
    print(f"List contents: {ll}")  # List contents: 5->10->20
    print(f"Size: {len(ll)}")      # Size: 3
    node = ll.find(10)
    print(f"Found node with data: {node.data}" if node else "Not found")
    ll.remove(10)
    print(f"After removal: {ll}")  # After removal: 5->20
