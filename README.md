# Coding Dojo - Serienbrief
 
 In diesem Beispielprojekt können Serienbriefe generiert werden. Diese werden dann von einer fiktiven
 Template Engine gerendert. 
 
##Aufgabe
Die Klasse ```SerienBriefServiceImpl``` muss *schöner* geschrieben werden. Die Funktion dieser Klasse ist an sich korrekt und wird auch mittels Unit-Tests überprüft.
 
Beim Refactoring dieser Klassen muss folgendes im Hinterkopf behalten werden: 
- Wie kann die Erweiterbarkeit der Klasse verbessert werden?
- Wie kann die Einschränkung, dass zurzeit nur eine Erziehungsberechtigte Person zurückgegeben werden kann, verbessert werden?


##Schule Repository
Es existiert ein ganz einfaches Schul-Repository, welches Objekte des Typs ```Klasse``` liefert. Dieses Repo muss für diese Übung nicht angepasst werden.

Wenn aber weitere Operationen gewünscht sind. Feel Free.

##Template Engine

Die Template Engine würde ```SerienBrief``` Objekte auf folgendes Template anwenden:

```
Template-Type: schule.eltern.gespraech.gut
------------------------------------------
Template-Text:
Hallo {{Eltern.Nachname}}
Ihr Kind {{SuS.Vorname}} hat ...      
 ```
 
 Die Werte in den ```{{ }}``` werden von der Template-Engine mit den ```Values``` vom ```SerienBrief``` ersetzt.
 