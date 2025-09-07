# Exercises 1.1
## 1. 
I get values of 19 million but different every time because there is a Race Condition in the program;
The counter logic is not atomic. It means that we have shared memory, counter, that two threads are trying
to access.
## 2. 
It is not guaranteed that the output is always 200.
Because incrementation in LongCounter is not atomic, we have counter in a shared memory, thus
Race Conditions may occur. When we use millions the chance for RC to occur is much higher than when we use 100
## 3. 
It will not make a difference because the counter logic is still not atomic and
threads use shared memory. There are no differences in results as we still have a race condition in the program
## 4. 
I have defined a critical section in the program, it is the part that used shared memory (counter variable).
By using .lock() and .unlock() we are ensuring that the critical section with shared memory can only be
accessed by one thread at the same time. So we are achieving a Mutual Exclusion using lock methods.
Thus we have established an execution order for the counter operation.
## 5. 
I have defined a critical section with the least amount of code, the increment operation.
Only that part is modifying shared memory.
## 6. 
It verifies my answer as decomp provides similar byte code
## 7. 
Without using lock we will have a race condition as we had in increment() method. The problem stays the same,
the threads are accessing shared memory and creating a race condition. Every time we will get a number of
around -40000 +-.
By adding a lock/unlock  around count-=1 we defined a critical section and that shared memory is accessed(modified)
by one thread at a time.

# Exercise 1.2
# 2.
For describing interleaving we considered the next code:
```
while (true) { -- (1)
// p.print() - but we decoded it into statements
System.out.print("-"); -- (2)
try { Thread.sleep(50); } catch (InterruptedException exn) { } -- (3)
System.out.print("|"); -- (4)
}
```

For two dashes to appear near to each other one of the many solutions can be the next interleaving:
t1(1), t2(1), t1(2), t2(2), t1(3), t2(3), t1(4), ... ( result: --| )

And for two bars:
... t1(1), t1(2), t1(3), t2(4), t1(4) ... ( result: -|| )

## 3.
To describe why this code works perfectly we consider the next code:
For describing interleaving we considered the next code:

```
while (true) { -- (1)
   try {
   lock.lock(); -- (2)
   System.out.print("-"); -- (3)
   try { Thread.sleep(50); } catch (InterruptedException exn) { } -- (4)
   System.out.print("|"); -- (5)
   } finally {
   lock.lock(); -- (6)
   }
}
```

And lets review one of the interleavings that we previously stated (a bit modified including the operations of lock/unlock):

t1(1), t2(1), t1(2), t2(2), t1(3), t2(3), t1(4), t2(4), t1(5), ... ( result: --| )

t1(2) must be after t2(6) and t2(2) must be before t1(6). That means that program will look either like this:

t1(1), t2(1), t1(2), t1(3), t1(4), t1(5), t1(6), t2(2) ...
or
t2(1), t1(1), t2(2), t2(3), t2(4), t2(5), t2(6), t1(2) ...

The same works in case of bars.

By adding a lock, we provide an atomicity for the print method of Printer class ensuring that only one thread can use that method therefore resulting in a correct printing of the sequence.

# Exercise 1.3
In our case, we have both race condition and data race in our critical section (which is { if (counter < MAX_PEOPLE_COVID) { counter++; }} ).
By adding a lock to our solution we leave an execution to this critical section only to one thread at a time.
This means that checking the value of counter and writing to it will be performed atomically by one thread (i.e. no data will be lost).

# Exercises 1.4
## 1.
Goetz is programmer-centric:
“why should you use concurrency when writing code?”
The note is more abstract and based on examples from the real world:
“what kind of concurrency exists in the world?”

Resource utilization and Exploitation:
Both are about efficiency: use resources better by adding concurrency (e.g., parallelizing tasks, using idle
CPU while waiting for I/O).
Fairness and Hidden:
Fairness is mostly handled by underlying systems (OS scheduler, runtime). This maps closer to “hidden concurrency,”
since the programmer doesn’t implement fairness themselves.
Convenience and	Inherent:
Convenience overlaps with inherent concurrency: sometimes the problem naturally has concurrent parts,
and expressing them as concurrent tasks is simpler than forcing sequential logic.




## 2.
1. Everyday, I and a lot of people use websites and the  webservers that host said websites use concurrency
   to handle simultaneous requests. (Inherent)
2. I play videogames, and gpu has to use concurrency to draw all the pixels to display images and cpu has to
   evaluate and calculate user inputs. (Exploitation)
3. An operating system decides which program gets the most resources, when 1 program is active and the other one
   is hidden(running in the background). (Hidden)

# Exercises 1.5
      Operating System: Apple macOS 15.3.1 (Sequoia) build 24D70
      Is it emulated? No
      --- Hardware Information ---
      Number of Cores: 8
      Size of Main Memory: 8 GB
      Cache Size: 12582912 bytes
      Cache Type: Unified
      <----------->
      Cache Size: 4194304 bytes
      Cache Type: Unified
      <----------->
      Cache Size: 131072 bytes
      Cache Type: Instruction
      <----------->
      Cache Size: 196608 bytes
      Cache Type: Instruction
      <----------->
      Cache Size: 131072 bytes
      Cache Type: Data
      <----------->
      Cache Size: 65536 bytes
      Cache Type: Data
      <----------->
      ---- Code Execution Time ----
      Sum of 1 to 100 = 5050
      Execution Time: 750nanoseconds