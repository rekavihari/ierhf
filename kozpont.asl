/* Initial beliefs */
time(0). // percekben megadva

/* Initial goals */

!start.
//TODO new_room_goal(terem_n�v, goal_temp, start_time, end_time)
/* Plans */

+!start <- .print("Kozpont elindult!"); ?time(T); !clock(T).

// the event !run is created by the GUI
+run(A,T,S,E) <- !room_cmp(A,T,S,E).

+!room_cmp(A,T,S,E) : A="Etterem" <- .print(A,"   ertesitve!  temp: ",T); .send(restaurant,achieve,setOptimalTemperature(T,S,E)).
+!room_cmp(A,T,S,E) : A="IB026" <- .print(A,"   ertesitve!  temp: ",T); .send(ib026,achieve,setOptimalTemperature(T,S,E)).
+!room_cmp(A,T,S,E) : A="IB027" <- .print(A,"   ertesitve!  temp: ",T); .send(ib027,achieve,setOptimalTemperature(T,S,E)).
+!room_cmp(A,T,S,E) : A="Raktar" <- .print(A,"   ertesitve!  temp: ",T); .send(storage,achieve,setOptimalTemperature(T,S,E)).

+!timeGui(T) <- time(T); .wait(1000); !clock(T).

+!clock(T) : T<1440 <- -time(T); +time(T+5); !timeGui(T+5).
+!clock(T) : T>=1440 <- -time(T); +time(0); !timeGui(0).

// válaszolunk az adott ágensnek, hogy mennyi az aktuális idő, amivel szinkronizáljon
+!timeRequest[source(A)] : true <- .print(A," idot szinkronizalt."); ?time(T); .send(A,achieve,timeSync(T)).

+agentReady[source(A)] : true <- .print(A," Helyiseg elerte az optimalis homersekletet").
