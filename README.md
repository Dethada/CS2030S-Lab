# Lab

## Do Not Edit
- Event
- Simulation
- Simulator
- Queue
- Lab*

## Edit
- ShopSimulation
- ShopEvent
    - [x] extract out ShopEventArrival, etc...
        - [ ] Add doc strings
    - [x] make all fields private
    - [x] Counters class?
        - [x] get available counter
        - [x] function to mark counter as available/unavailable
    - [ ] Customer class?

## Lab 1 Comments
Referring to your \'Counters\' class, this form of encapsulation is not very scalable. Perhaps you might want to create a \'Shop\' class which HAS-A \'Counter\' class. You might realise in Lab2 that having a \'Customer\' class might be extremely helpful too.
