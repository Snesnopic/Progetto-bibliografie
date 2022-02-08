# Progetto bibliografie

Progetto gestione basi di dati + programma con GUI Java per gestione di un sistema informatico di bibliografie. Per
UNINA  
Traccia:  
Si sviluppi un sistema informativo, composto da una base di dati relazionale e da un applicativo Java dotato di GUI (
Swing o JavaFX), per la gestione di bibliografie. Il sistema permette agli utenti di salvare e organizzare i propri
riferimenti bibliografici. In particolare, è possibile inserire/modificare/rimuovere riferimenti bibliografici di
diverso tipo (e.g.: articoli scientifici su conferenza o rivista, libri, risorse on-line, dataset, etc.). Ciascun
riferimento è caratterizzato da un titolo univoco, un elenco di autori, una data, un URL (obbligatorio solo per risorse
on-line), un DOI (facoltativo, ma univoco ove presente), e una descrizione testuale in cui l’utente può indicare aspetti
significativi. Inoltre, un riferimento può essere associato a un insieme di rimandi, ovvero di altri riferimenti
presenti nel sistema che vengono menzionati nel testo. Un utente, infine, può definire un insieme di categorie
personalizzate e possibilmente gerarchiche, e associare ciascun riferimento a una o più categorie. Per organizzazione
gerarchica delle categorie si intende la possibilità di specificare che una certa categoria (e.g.: “Informatica”) ha una
o più sottocategorie (e.g.: “Basi di Dati” o “Testing”). Non è possibile introdurre dipendenze cicliche, ovvero non è
possibile che una categoria sia una sottocategoria (anche transitivamente) di sé stessa. L’appartenenza a una
sottocategoria implica l’appartenenza a tutte le sue super-categorie. Non è pertanto possibile associare esplicitamente
a un riferimento una categoria e una sua super-categoria. Il sistema permette infine di effettuare interrogazioni
avanzate, con possibilità di filtraggio per una o più categorie, per data, per parole chiave e per autore. Inoltre, è
possibile ordinare i riferimenti per numero di citazioni ricevute, ovvero per il numero di volte in cui il riferimento è
presente nei rimandi di altri riferimenti.
