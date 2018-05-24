/* Initial beliefs */
// Aktu�lis id�
time(0).
// Optim�lis kl�ma biztos�t�s�nak elv�rt kezd� id�pontja (percekben)
start(0).
// Optim�lis kl�ma biztos�t�s�nak elv�rt befejez� id�pontja (percekben)
end(0).

// H�t�s/F�t�s el�tti h�m�rs�klet
oldTemperature(20).
// Aktu�lis h�m�rs�klet
temp(20).
// Optim�lis h�mr�s�klet
optimalTemperature(12).

// a terem �gens hiedelme arr�l, hogy h�ny fokot k�pes f�teni/h�teni egy id�egys�g alatt
heatHalfcelsPertime(5).
coolHalfcelsPertime(5).

// kell-e optim�lis h�m�rs�kletet biztos�tani?
state(0).

/* Initial goals */
!start.

/* Plans */
// Terem konzol panel l�trehoz�sa, �s id� szinkroniz�l�sa k�zpont alapj�n
+!start <- gui.konzol("ROOM IB027"); .wait(0); .send(kozpont,achieve,timeRequest).

// id� szinkroniz�l�s kezdetben, �s �ra ind�t�sa
+!timeSync(T) <- ?time(O); -time(O); +time(T); !clock.
// �ra l�ptet�se
+!clock <- !run; .wait(1000); ?time(T); 
		if (T<1440) { -time(T); +time(T+5); }
				else { -time(T); +time(0); }
			!clock.

// M�k�d�st biztos�t� r�sz. Ki�rja a k�zponti konzolra az aktu�lis h�m�rs�kletet,
// valamint ha kell, akkor elind�tja az optim�lis kl�ma l�trehoz�s�t
+!run <- ?temp(T); room_temp(27,T); 
		?state(ST); if(ST == 1){?time(TI); ?end(E);
						if(TI > E){-state(ST); +state(0);}
						else {?optimalTemperature(O); !cmp(T,O); .print("aktualis: ",T," - optimalis: ",O);}
						}.

// Megvizsg�lja, hogy f�teni kell-e, �s ha igen, f�t
+!cmp(A,B) : A<B <- ?start(S); ?heatHalfcelsPertime(P); ?time(T);
					if (S-(((B-A)/(P))*5) <= T){
						.print("fel"); !heat;
					}.

// Megvizsg�lja, hogy h�teni kell-e, �s ha igen, h�t
+!cmp(A,B) : A>B <- ?start(S); ?coolHalfcelsPertime(P); ?time(T);
					if (S-(((A-B)/(P))*5) <= T){
						.print("le"); !cool; 
					}.

// Megvizsg�lja, hogy el�rte-e az optim�lis h�m�rs�kletet, �s ha igen, �rtes�ti a k�zpontot					
+!cmp(A,B) : A=B <- .print("egyenlo"); .send(kozpont,tell,agentReady).

// F�t�st ind�t
+!heat <- ?temp(T); .send(heater,achieve,heat(T)).
			
// H�t�st ind�t
+!cool <- ?temp(T); .send(cooler,achieve,cool(T)).

// Terem f�t�tt h�m�rs�klet be�ll�t�s�nak szimul�l�sa
+!heatTemperature(T) <- ?temp(TE); ?oldTemperature(O); -oldTemperature(O); +oldTemperature(TE); -temp(TE); +temp(T);
					?heatHalfcelsPertime(H); -heatHalfcelsPertime(H); +heatHalfcelsPertime(T-TE).
// Terem h�t�tt h�m�rs�klet be�ll�t�s�nak szimul�l�sa
+!coolTemperature(T) <- ?temp(TE); ?oldTemperature(O); -oldTemperature(O); +oldTemperature(TE); -temp(TE); +temp(T);
					?coolHalfcelsPertime(H); -coolHalfcelsPertime(H); +coolHalfcelsPertime(TE-T).

// optim�lis h�m�rs�klet param�terek be�ll�t�sa
+!setOptimalTemperature(T,S,E) <- ?optimalTemperature(O); -optimalTemperature(O); +optimalTemperature(T);
						?start(ST); -start(ST); +start(S);
						?end(EN); -end(EN); +end(E);
						?state(L); -state(L); +state(1).
						

// optim�lis h�m�rs�klet param�terek be�ll�t�sa azonnal, terem_panelen kereszt�l
+!setOptimalTemperature_now(T,E) <- ?optimalTemperature(O); -optimalTemperature(O); +optimalTemperature(T);
							?end(N); -end(N); +end(E);
							?start(S); -start(S); ?time(B); +start(B);
							?state(L); -state(L); +state(1).
