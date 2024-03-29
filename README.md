   # CLI-Management-system-of-a-town-hall
	Acest program implementeaza un sistem de management al unei primarii, folosindu-se de diferite tipuri
de colectii pentru stocarea si manipularea datelelor. Functionalitatile programului implementate pana in momentul de fata sunt:
adaugarea utilizatorilor intr-o colectie de tip HashMap, realizarea unei cereri noi, in functie de tipul de utilizator, retragerea
unei cereri din sistem, afisarea tuturor cererilor in asteptare ale unui utilizator, afisarea tuturor cererilor in asteptare ale
unui birou.
	In fisierul ManagementPrimarie am folosit un HashMap pentru stocarea utilizatorilor, deoarece datele acestora pot fi
accesate usor folosindu-ne de cheile hashmap-ului, cheile fiind de tip string si reprezentand numele persoanelor. In main,
am creat un obiect de tip ManagementPrimarie, apoi am folosit functia citesteFisier pentru a citi comenzile aplicate cererilor,
de asemenea, am initializat si am inchis o variabila de tip printWriter pe care o sa o oferim ca atribut la alte functii si prin care
vom reusi sa scriem rezultatele in fisierul de output. In clasa ManagementPrimarie, functia "functionalitati" foloseste un
switch si argumentele extrase de pe o linie pentru a declansa executarea unei anumite comenzi. De asemenea, in aceeasi
clasa sunt implementate mai multe metode, precum: adaugaUtilizator, cerereNoua, afiseazaCereriInAsteptare, retrageCerere,
afiseazaCereriInAsteptareBirou, in care se decide in functie de tipul de utilizator care override al metodei abstracte sa se execute.
	Programul contine doua package-uri, cel al utilizatorilor si cel al birourilor. In package-ul utilizatorilor, am creat clasa
abstracta Utilizator in care sunt declarate mai multe metode, care sunt suprascrise in functie de tipul de utilizator.
Pe langa metodele dorite in cerinta, se afla si metoda getDate care formateaza/parseaza din String in Date o data
(datele sunt salvate ca string in clasa Cerere). In ciuda faptului ca sunt mai multe tipuri de utilizatori, implementarea
metodelor este aceeasi si au de asemenea si atribute comune. Un tip de utilizator, de exemplu Angajat, are, pe langa
atributele implicite (cele din cerinta), si un atribut de tip PriorityQueue, in care se vor stoca cererile in asteptare ale utilizatorului,
motivul folosirii acestei colectii fiind usurinta cu care putem sa ordonam dupa data la care s-a facut cererea, folosind un Comparator.
	Metodele implementate momentan sunt:
	- scriereCerere, care returneaza un string cu continutul cererii (fiecare utilizator are un format diferit);
	- creeazaCerere, in care este apelat scriereCerere pentru crearea continutului de text al cererii in functie de
    tipul de utilizator. Daca utilizatorului nu i se permite sa trimita un anumit tip de cerere, se va arunca o exceptie
    UtilizatorCerereNepermisa si se iese din metoda. Daca s-a initializat continutul cerereii,
    se creeaza un obiect de tip Cerere, care va fi adaugat in colectia de cereri a utilizatorului cu metoda add
    si se returneaza obiectul cerere pentru a fi adaugat si in coada biroului corespunzator;
	- retrageCerere, foloseste un iterator pentru a gasi cererea care a fost creata la data dată ca parametru si o sterge cu metoda remove
	- afisareCereriInAsteptare, care afiseaza cererile in asteptare ale utilizatorului, realizandu-se un shallow copy si initializandu-se o alta coada
    din care se va tot extrage varful pentru a fi afisat pana ce se goleste coada
	In package-ul birourilor, am creat clasa abstracta Birou, care are ca atribute un nume si o colectie de cereri in asteptare, implementata
tot printr-un PriorityQueue. Metodele implementate pana in momentul de fata (desi nu merg chiar corect) sunt adaugaCerereInAsteptare, stergeCereriInAsteptare
si afiseazaCereri. Aceste metode sunt implementate la fel ca metodele din package-ul utilizator.
