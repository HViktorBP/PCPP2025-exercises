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
 
Exercise:2.3
1.  Are there any race conditions?
Yes, There seems to be a race condition since the value of sum is < 2000000 and different in every run. 
2. Explain why race conditions appear when t1 and t2 use the Mystery object.?
t1 calls an instance method (addInstance() ) on the object m while t2 calls a static methode (addStatic() ) associated with the class Mystery. Even though both threads are operating in the context of the same Mystery object, they use different locks, which is why race condition occurs.
3. Explain why your new implementation does not have race conditions.
By synchronizing both instance and static methods on the same lock (Mystery.class), it guarantees mutual exclusion. This prevents two threads from updating sum at the same time, eliminating race conditions.
4. Is the use of this intrinsic lock on sum() necessary for this program? In other words, would there be race conditions if you remove the modifier synchronized from sum()
The synchronized modifier on sum() is not necessary here. Removing it does not cause race conditions. By the time main() calls sum(), all threads have already finished because of t1.join() and t2.join().