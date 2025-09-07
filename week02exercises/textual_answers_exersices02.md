Exercise 2.1
2. To our opinion the solution provided can be considered a fiar solution.
While handling both reader and writers, the custom class also provides "prefer" flag.
This flag indicates that program should prefer reader to writer after writer gives back the lock,
and prefers writer to reader after reader gives back the lock.
3. Not explicitly but rather implicitly since the "synchronized" keyword used in methods provides us with property to notify all the other threads.
4. We think that it is possible to achieve starvation free code using synchronized or ReentrantLock, but it is only possible if programmer correctly think through of how his locking protocol should be implemented.

Exercise 2.2
1. The initial program does run forever. There is no synchronization between threads (main thread and t thread). So threads running in different CPUs cannot access each other's Core registers. While we changed a value in one thread, the other cannot see that change.
2. Adding synchronization between threads ensures visibility. Without it,the CPU is allowed to keep the value of mi in different storages. By using synch., we establish an execution order betwee the read and the write on "mi" performed by the two threads.
3. No. Because there would be no synchronization between set and get operations. We need to add synchronized to both methods to keep the value in shared memory.
4. Yes, now the program terminates without direct usage of locks. It's because /volatile/ variables in Java are not stored in CPU registers or low levels of cache. And every writes to the /volatile/ variable flush registers and low level cache to shared memory levels. This being said, these variables cannot be used to ensure mutual exclusion. They ensure visibility.
It does not provide atomicity, beyond read/write. For example counter++ on volatile int is not thread safe, because it's a read-modify-write operation. It also does not block other threads from entering critical sections at the same time.