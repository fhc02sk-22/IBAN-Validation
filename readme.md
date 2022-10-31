### IBAN-Validierung

Kompetenzen: Array, Schleifen, Berechnungen, Algorithmen, Ein-Ausgabe

**Aufgabenstellung**

Eine fiktive Bundesregierung möchte den Staatsbürgern Geld auf ihr Konto überweisen. Dazu muss von jedem Staatsbürger die Kontonummer (IBAN) erfasst werden. Da sich Personen jedoch hin und wieder vertippen, möchte man auf Nummer sicher gehen und eine Validierung implementieren lassen. Dabei soll die erfasste IBAN-Nummer auf Korrektheit überprüft werden.



**Überprüfung von IBAN**

Jedes Bankkonto (in der EU) hat eine Kontonummer, welche IBAN genannt wird. IBAN steht für internationale Bankkontonummer und ist über eine sogenannte ISO-Norm (**ISO 13616**-1:2020) standardisiert. Damit ist geregelt, dass die IBAN in vielen Ländern gleich aufgebaut ist.
Weitere Informationen findet man unter https://de.wikipedia.org/wiki/Internationale_Bankkontonummer

![dmc_karte_2_copyright_erste_bank](C:\Users\steph\Downloads\dmc_karte_2_copyright_erste_bank.png)

In Österreich beginnt die IBAN mit AT (für Austria) und ist 20 Zeichen lang. Die IBAN-Nummer ist auf jeder Bankomatkarte abgebildet und wird verwendet, wenn einem Empfänger Geld überwiesen werden soll. Hierzu braucht man die IBAN-Nummer des Empfängers. Damit man etwa aus Versehen, z.B.: aufgrund von einem Tippfehler nicht Geld auf ein falsches Konto überweist, beinhaltet die IBAN eine Prüfsumme. Mithilfe von diesem wird überprüft, ob die gesamte Kontonummer korrekt ist.

Es soll nun ein Programm geschrieben werden, welches die Prüfsumme validiert (überprüft).

**Aufbau für die IBAN "AT61 1904 3002 3457 3201":**

| Spalte            | Beschreibung                                                 |
| ----------------- | ------------------------------------------------------------ |
| Länderkürzel      | AT<br />(A = 10, T = 29 )                                    |
| Letzte 16 Stellen | 1904 3002 3457 3201 - eigentliche Kontoinformation           |
| Prüfzahl          | Kontonummer + Ländercode als Zahl (102900 => 10 für 'A', 29 für 'T', zusätzlich zwei 00) |
| Prüfsumme         | 61 (Index 2, 3)                                              |
| Validierung       | 61 ergibt (98 - Prüfzahl modulo 97)                          |



**Grundlagen**:

(1) Strings können mit Zahlen kombiniert werden. Zahlen können auch multipliziert werden.

```java
String iban = "" + (12 * 100); // ergibt als Text "1200"
```

(2) Folgendes Snippet extrahiert aus einem String die ersten beiden Zeichen:

```java
String iban = "AT611904300234573201";
String part = iBan.substring(0,1); // gets characters at position 0&1 => "AT"
```

(3) Folgendes Snippet extrahiert aus einem String Zeichen von einem bestimmten Index und retourniert diesen als int.

```java
String iban = "AT611904300234573201";
String part = iBan.substring(2,4); // gets characters at position 2,3 => "61"
int value = Integer.valueOf(part);   // Integer.valueOf(String) converts a String to an int (61)
```

(4) Arbeiten mit Ascii-Werten von Zeichen (siehe [ASCII-Tabelle (torsten-horn.de)](https://www.torsten-horn.de/techdocs/ascii.htm)): 'A' hat den Ascii-Wert 65. Man kann mit Zeichen auch "rechnen". 'B' - 'A' ergibt den Wert 1 (da 'B' in der Tabelle den Wert 66 hat, 'A' den Wert 65)

```java
char charA = 'A'; 
int intA = charA; // intA hat den Wert 65, da 'A' den Ascii-Wert 65 hat
intA = intA - 'A'; // intA hat nun den Wert 0, da von 65 der Character 'A' (Ascii-Wert 65) abgezogen wird
```

(5) Mit folgendem Snippet kann eine Character-Ziffer (z.B.: '4') in den richtigen Integer-Wert umgewandelt werden:

```java
char charfour = '4'; // Ziffer '4' als Character definieren, Ascii-Wert 52
int intfour = charfour; // intfour hat den Wert 52, da '4' den Ascii-Wert 52 hat
intfour = intfour - '0'; // intfour hat nun den Wert 0, da von 52 der Character '0' (Ascii-Wert 48) abgezogen wird
```



**Iteration 1:**

Vom Benutzer ist mittels einem Scanner ein IBAN als String einzulesen.



Schreiben Sie eine Klasse *IbanValidationService* mit zwei privaten Attributen:

```java
private String iBan;
private int calculatedCheckSum;
```

Implementieren Sie einen Konstruktor, wo Sie einen Wert für *iBan* übernehmen und initialisieren Sie *calculatedCheckSum* mit -1;



Schreiben Sie eine Methode *public String getCountryCode()*, welche die ersten beiden Zeichen von iBan retourniert (Snippet 2).

Schreiben Sie eine Methode *public int getCheckSum()*, welche die Checksumme (Index 2 & 3) als int-Wert retourniert (Snippet 3).

Schreiben Sie eine Methode *public String getAccountNumber()*, welche die Kontonummer retourniert.

Ausgabe auf der Konsole:

```
Eingabe von IBAN:
AT611904300234573201

Ländercode: AT
Prüfsumme: 61
Kontonummer: 1904300234573201
```



**Iteration 2:**

Die Ländercodes müssen in Zahlen umgewandelt werden. 'A' entspricht einer 10, 'B' einer 11, 'T' einer 29 (AT somit 1029) usw.. Zusätzlich zu den beiden Buchstaben müssen auch noch zwei 0 angehängt werden.

Schreiben Sie eine Methode *public String getCountryCodeNumber()*, welche die Buchstaben des Ländercodes in Zahlen umwandelt. Orientieren Sie sich an den oben definierten Grundlagen (Snippet 4 und Snippet 1).

Schreiben Sie eine Methode *public String getFullCode()*, welche *getAccountNumber()* und *getCountryCodeNumber()* wieder miteinander verbindet. ("1904300234573201" + "102900" = "1904300234573201102900")

Die Ausgabe soll nun wie folgt abgeändert werden.

```
Eingabe von IBAN:
AT611904300234573201

Ländercode: AT (102900)
Prüfsumme: 61
Kontonummer: 1904300234573201
Prüfzahl: 1904300234573201102900
```



**Iteration 3:**

Es gibt leider keinen Datentyp, mit dem derart große Zahlen, wie die Prüfzahl mit 20 und mehr Stellen, abgebildet werden können. In dem beschriebenen Beispiel wurde auch bislang nur mit einem String gearbeitet.
Aus diesem Grund kann auch Modulo nicht so einfach verwendet werden und muss selbst implementiert werden. Dazu muss der String zuerst in einen int[] (int-Array) umgewandelt werden.



Schreiben Sie eine Methode *public int[] buildArray()*, welche

- int-Array in der Länge des Strings von getFullCode() erstellt
- Laufen Sie den String von getFullCode() vom Zeichen 0 bis zum letzten Zeichen durch
- Wandeln Sie das jeweilige Zeichen in den Zahlenwert um (Orientieren Sie sich am Snippet 5) und speichern Sie dieses im Array
- retournieren Sie den Array



In einer Demo-Anwendung können Sie den Array nun wie folgt ausgeben (lassen):

```java
int[] arr = ibanValidationService.buildArray();
System.out.println("arr = " + Arrays.toString(arr));
```



**Iteration 4:**

Die Modulo-Berechnung muss nun mit dem Array, Wert für Wert, durchgeführt werden. Hier ein fiktives Beispiel (welches einer Division aus der Grundschule gleicht):

```
Ziffer für Ziffer, wird die Berechnung wie folgt durchgeführt: Beispiel für 543 % 17 = 16

((10 * 0) + 5) % 17 = 5 
	10 * Ergebnis der letzten Berechnung (gibt es nicht, daher 0) + aktuelle Zahl (5) modulo 17
((10 * 5) + 4) % 17 = 3
	10 * Ergebnis der letzten Berechnung (5) + aktuelle Zahl (4) modulo 17
((10 * 3) + 3) % 17 = 16
	10 * Ergebnis der letzten Berechnung (3) + aktuelle Zahl (3) modulo 17
```

Anmerkung: Am Anfang hat eine Hilfsvariable den Wert 0, diese wird mal 10 multipliziert und mit der ersten Zahl (5) addiert. Die Summe wird modulo 17 gerechnet. Im zweiten Durchlauf hat die Hilfsvariable den Wert 5, diese wird mal 10 multipliziert und mit der zweiten Zahl (4) addiert. Die Summe wird modulo 17 gerechnet. usw.

Implementieren Sie die Methode *public int calcCheckSum()*. Der IBAN-Array (Methode *buildArray()*) wird nun in einer Schleife vollständig durchgelaufen. Berechnen Sie den Modulo-Wert (**Modulo 97**) entsprechend oben stehenden Algorithmus.

Die korrekte Prüfsumme ergibt sich nun wie folgt: 98 - (Prüfzahl % 97) und wird der Variable *calculatedCheckSum* zugewiesen. Das Ergebnis wird dazu auch retourniert.



Schreiben Sie noch eine Methode *public boolean isIbanCorrect()*, welche den Wert von *calculatedCheckSum* mit der Methode *getCheckSum()* vergleicht und *true* retourniert, wenn die Werte überein stimmen.



**Beispiele für IBANs**:

AT61 1904 3002 3457 3201

AT02 2050 3021 0102 3600



Ein paar Tipps finden Sie auch hier: [IBAN-Validation/IbanValidationService.java at master · fhc02sk-22/IBAN-Validation · GitHub](https://github.com/fhc02sk-22/IBAN-Validation/blob/master/src/IbanValidationService.java)





