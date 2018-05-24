/* Initial beliefs */
// Aktuális idő
time(0).
// Elvárt kezdő időpontja az optimális klímának (perc értékben)
start(0).
// Elvárt befejezési időpontja az optimális klímának (perc értékben)
end(0).

//Változtatás (hűtés/fűtés) előtti hőmérséklet
oldTemperature(25).
// Aktuális hőmérséklet
temp(25).
// Optimális hőmérséklet
optimalTemperature(12).

// az étterem ágens hiedelme arról, hogy hány fokot képes változtatni (hűteni/fűteni) hőmérsékletén egy időegység alatt
heatHalfcelsPertime(5).
coolHalfcelsPertime(5).

// biztosítani kell-e optimális hőmérsékletet?
state(0).

/* Initial goals */
!start.

/* Plans */
// Raktár konzol panel létrehozása, és az idő szinkronizálása a központ alapján
+!start <- gui.konzol("Raktar"); .wait(0); .send(kozpont,achieve,timeRequest).

// idő kezdeti szinkronizálása és óra indítása
+!timeSync(T) <- ?time(O); -time(O); +time(T); !clock.
// óra léptetése
+!clock <- !run; .wait(1000); ?time(T); 
		if (T<1440) { -time(T); +time(T+5); }
				else { -time(T); +time(0); }
			!clock.

// Működés rész. Kiírja a központi konzolra az aktuális hőmérsékletet,
// valamint ha kell, akkor elindítja az optimális klíma létrehozását
+!run <- ?temp(T); room_temp(28,T); 
		?state(ST); if(ST == 1){?time(TI); ?end(E);
						if(TI > E){-state(ST); +state(0);}
						else {?optimalTemperature(O); !cmp(T,O); .print("aktualis: ",T," - optimalis: ",O);}
						}.

// Megvizsgálja, hogy fűteni kell-e, és ha igen, fűt
+!cmp(A,B) : A<B <- ?start(S); ?heatHalfcelsPertime(P); ?time(T);
					if (S-(((B-A)/(P))*5) <= T){
						.print("emeles"); !heat;
					}.

// Megvizsgálja, hogy hűteni kell-e, és ha igen, hűt
+!cmp(A,B) : A>B <- ?start(S); ?coolHalfcelsPertime(P); ?time(T);
					if (S-(((A-B)/(P))*5) <= T){
						.print("csokkentes"); !cool; 
					}.

// Megvizsgálja, hogy elérte-e az optimális hőmérsékletet, és ha igen, értesíti a központot					
+!cmp(A,B) : A=B <- .print("egyenlo"); .send(kozpont,tell,agentReady).

// Fűtést indít
+!heat <- ?temp(T); .send(heater,achieve,heat(T)).
			
// Hűtést indít
+!cool <- ?temp(T); .send(cooler,achieve,cool(T)).

// Terem fűtött hőmérséklet beellításának szimulálása
+!heatTemperature(T) <- ?temp(TE); ?oldTemperature(O); -oldTemperature(O); +oldTemperature(TE); -temp(TE); +temp(T);
					?heatHalfcelsPertime(H); -heatHalfcelsPertime(H); +heatHalfcelsPertime(T-TE).
// Terem hűtött hőmérséklet beellításának szimulálása
+!coolTemperature(T) <- ?temp(TE); ?oldTemperature(O); -oldTemperature(O); +oldTemperature(TE); -temp(TE); +temp(T);
					?coolHalfcelsPertime(H); -coolHalfcelsPertime(H); +coolHalfcelsPertime(TE-T).

// optimális hőmérséklet paraméterek beállítása
+!setOptimalTemperature(T,S,E) <- ?optimalTemperature(O); -optimalTemperature(O); +optimalTemperature(T);
						?start(ST); -start(ST); +start(S);
						?end(EN); -end(EN); +end(E);
						?state(L); -state(L); +state(1).
						

// optimális hőmérséklet paraméterek beállítása azonnal, terem_panelen keresztül
+!setOptimalTemperature_now(T,E) <- ?optimalTemperature(O); -optimalTemperature(O); +optimalTemperature(T);
							?end(N); -end(N); +end(E);
							?start(S); -start(S); ?time(B); +start(B);
							?state(L); -state(L); +state(1).
