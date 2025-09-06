 Exercise:2.3

1.  Are there any race conditions?
Yes, There seems to be a race condition since the value of sum is < 2000000 and different in every run. 

2. Explain why race conditions appear when t1 and t2 use the Mystery object.?
t1 calls an instance method (addInstance() ) on the object m while t2 calls a static methode (addStatic() ) associated with the class Mystery. Even though both threads are operating in the context of the same Mystery object, they use different locks, which is why race condition occurs.

3. Explain why your new implementation does not have race conditions.
By synchronizing both instance and static methods on the same lock (Mystery.class), it guarantees mutual exclusion. This prevents two threads from updating sum at the same time, eliminating race conditions.


4. Is the use of this intrinsic lock on sum() necessary for this program? In other words, would there be race conditions if you remove the modifier synchronized from sum()
The synchronized modifier on sum() is not necessary here. Removing it does not cause race conditions. By the time main() calls sum(), all threads have already finished because of t1.join() and t2.join().


