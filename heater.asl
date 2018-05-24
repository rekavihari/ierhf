// Agent heater in project project.mas2j

// Fűtés szimulálása

/* Initial beliefs and rules */

// Egy időegység alatt 0.5 fokkal fűt
heatHalfcelsPertime(1).

/* Plans */

+!heat(T)[source(A)]: true <- ?heatHalfcelsPertime(P); .send(A,achieve,heatTemperature(T+P)).
