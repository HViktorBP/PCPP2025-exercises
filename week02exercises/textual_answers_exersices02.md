Exercise 1.1
2. To our opinion the solution provided can be considered a fiar solution.
While handling both reader and writers, the custom class also provides "prefer" flag.
This flag indicates that program should prefer reader to writer after writer gives back the lock,
and prefers writer to reader after reader gives back the lock.
3. Not explicitly but rather implicitly since the "synchronized" keyword used in methods provides us with property to notify all the other threads.
4. We think that it is possible to achieve starvation free code using synchronized or ReentrantLock, but it is only possible if programmer correctly think through of how his locking protocol should be implemented.