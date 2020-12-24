# BankSim

# Team Members: Chener Zhang, Mark Subora

### Initial Sequence Diagram
![Initial Sequence Diamgram](https://i.imgur.com/zfyjqh7.png)


---

### Explation of the race condition
![Race_cond1](https://raw.githubusercontent.com/3296Spring2020/banksim-multithreading-01-subora-zhang/master/Race_cd_1.PNG?token=AOKIXH2FIWUFJHHIOMDY6X26LWMDK)
![Race_Cond2](https://raw.githubusercontent.com/3296Spring2020/banksim-multithreading-01-subora-zhang/master/Race_cd_2.PNG?token=AOKIXH3ATUVBIGYZEI2POSS6LWMDQ)

The Race condition occurs when multiple theads are accessing the Withdraw/Deposit section. When this occurs both threads will read the correct balance then both will withdraw money, then when they update the balance only the last thread to update the balance will actually be recorded thus causing an incorrect balance to occur. When locks are placed around these calls no more then one thead can access the balance at a time, solving the race condition. 


###  Requirements
For this project we were required to complete five tasks dealing with multi-threads, locks, Deadlocks, and refactoring. For the first task were had to find the race condition, and then create a sequence diagram along with an explanation. For task two were had to implement a lock around the race condition we found in task one. Task three had us refactor the testing and implement sleep and wake all sleeping threads. Task four we had to implement a wait/notify solution so threads which tried to withdraw more than the account had needed to wait until more money was deposited into the account, when a deposit occured all threads waiting to withdraw were awoken. Task five was resolving a potential deadlock issue, but closing the bank when a thread has finished. We did implement everthing we were asked to do. 

###  Teamwork
We collaborated well, Chener Identified the race condition, and Mark created the Sequence diagram. Mark created the relocks for the race condition for task two, and Chener created a new class and refactored the test into that class to be called on in the bank class for task three. Mark and Chener both worked on task 4 and 5 using relocks, sleeping and waking threads using wait(), and notifyall() sytle function calls. Chener as more rapid in the coding, and revising of the code, and Mark wrote the written portion. 



###  Testing
To test our methods and code as we went, Chener implemented test via the print screen to see the values being withdrawn and deposited. She also implemented a test via the print screen to see which threads were pulling money from and depositiing to which threads. Addtionaly, we checked each others code by protecting the main codeline ensuring that each push as correct. We did discover a few bugs, but were able to fix theem. 


###  UML CLASS DIAGRAM
![Image of class diagram](https://github.com/3296Spring2020/paystationmain-04-mclarnon-subora-wetsocks/blob/master/Copy%20of%20Lab%204.jpg)

![Image of class diagram](https://raw.githubusercontent.com/3296Spring2020/banksim-multithreading-01-subora-zhang/master/UML_CLASS.PNG?token=AOKIXH7IWQ5OFEOS7M4MXTS6L7UCQ)
![Image of class diagram](https://i.imgur.com/ZVh7TCv.jpg)

