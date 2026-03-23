# Comunicazione Client-Server UDP con Multicast

## Descrizione
Il programma realizza una comunicazione Client/Server utilizzando il protocollo UDP.
Il client invia un messaggio al server e riceve una risposta.
Successivamente il server invia un messaggio multicast ad un gruppo di host.

## Funzionamento
1. Il client inserisce un messaggio da tastiera
2. Il messaggio viene inviato al server tramite UDP
3. Il server riceve il messaggio e risponde con "OK"
4. Il server invia un messaggio multicast al gruppo
5. Il client si unisce al gruppo multicast e riceve il messaggio


## Porte utilizzate
- 5000 → comunicazione client-server
- 1900 → comunicazione multicast


## Note
- Il protocollo UDP è connectionless, quindi non garantisce la consegna dei pacchetti
- Il multicast permette l'invio di un messaggio a più host contemporaneamente