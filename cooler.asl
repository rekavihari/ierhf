// Agent heater in project project.mas2j

// Hűtés szimulálása

/* Initial beliefs and rules */

// Egy időegység alatt 0.5 fokkal hűt
coolHalfcelsPertime(1).

/* Plans */

+!cool(T)[source(A)]: true <- ?coolHalfcelsPertime(P); .send(A,achieve,coolTemperature(T-P)).
