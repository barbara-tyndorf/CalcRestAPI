# intive CalcRestApi

Aplikacja webowa umożliwiająca obliczenia na liczbach rzeczywistych, wektorach i macierzach oraz zapis wykonanych obliczeń do pliku, lub lokalnie do bazy danych H2.

### Wymagania wstępne

 Java 11 lub wyższa do pobrania [tutaj.](https://adoptopenjdk.net/)

### Uruchomienie programu

Aby zainstalować aplikację wywołaj następujące komendy w konsoli:

```
git clone https://github.com/barbara-tyndorf/intive_RestAPI.git
cd intive_RestAPI
```

Uruchom aplikację za pomocą komendy:

```
mvn clean package spring-boot:repackage
cd target
java -jar calc-0.0.1-SNAPSHOT.jar
```

Następnie uruchom swoją przeglądarkę i wejdź pod adres:

http://localhost:8080/swagger-ui/index.html

### Instrukcja użytkowania

Po uruchomieniu przeglądarki i przejściu pod wskazany adres powinien ukazać się taki widok:

![swagger1](108628663-827d6000-745c-11eb-860a-c0db200493ad.jpg)

Kliknij strzałkę zaznaczoną czerwonym kółkiem. Ukaże Ci się lista możliwych do wykonania operacji:

![swagger2](108628681-93c66c80-745c-11eb-906c-7f46ef2cf683.jpg)

Kliknij na wybraną zakładkę, aby się rozwinęła. Następnie naciśnij przycisk `Try it out`.

![swagger3](108628695-a0e35b80-745c-11eb-93f8-8436fb9b937e.jpg)

Wywołanie `getOperations` wyświetli plik z możliwymi do wykonania operacjami.

W przypadku wywołania `calc` masz możliwość wykonania obliczeń.

Teraz w zaznaczonym poniżej białym polu możesz podać swoje wartości. Podaj dwie wartości oraz typ operacji w postaci JSON.

![swagger5](109204687-f3e34880-77a5-11eb-8a54-a02fe8f09dc4.jpg)

Przykładowe dane wejściowe i możliwe działania:

- dwie liczby rzeczywiste:

  - dodawanie `+`
  - odejmowanie `-`
  - mnożenie `*`
  - dzielenie `/`
  - potęgowanie `^`, zakres wykładnika <0,128>
  - pierwiastkowanie drugiego stopnia `#`

  ```
  {
      "digitA": 5.5,
      "digitB": 2.5,
      "operation": "-"
  }
  ```

  ```
  {
      "digitA": 9,
      "operation": "#"
  }
  ```

- liczba rzeczywista i wektor (maksymalnie 4-elementowy) lub macierz (maksymalnie 4x4)

  - mnożenie

    ```
    {
        "digitA": 2,
        "vectorB": [1,2,3],
        "operation": "*"
    }
    ```

    lub

    ```
    {
        "digitA": 2,
        "matrixB": [
        		[1,2,3],
        		[3,2,1]
        		],
        "operation": "*"
    }
    ```

- dwa wektory (maksymalnie 4-elementowe, oba wektory muszą posiadać tyle samo elementów)

  - dodawanie `+`

  - odejmowanie `-`

    ```
    {
        "vectorA": [3,2,1],
        "vectorB": [1,2,3],
        "operation": "+"
    }
    ```

- dwie macierze (maksymalny wymiar 4 x 4)

  - dodawanie `+ `(obie macierze muszą posiadać takie same wymiary)
  - odejmowanie `-` (j.w.)
  - mnożenie `*` (druga macierz musi mieć tyle samo kolumn, ile wierszy ma pierwsza)

  ```
  {
      "matrixA": [
      		[3,2,1],
      		[1,2,3]
      		],
      "matrixB": [
      		[1,2,3],
      		[3,2,1]
      		],
      "operation": "+"
  }
  ```

  lub

  ```
  {
      "matrixA": [
      		[3,2,1],
      		[1,2,3]
      		],
      "matrixB": [
      		[1,2],
      		[3,1],
      		[1,3]
      		],
      "operation": "*"
  }
  ```

- macierz i wektor

  - mnożenie `*` (wektor, musi on mieć tyle składowych, ile kolumn ma macierz)

  ```
  {
      "matrixA": [
      		[3,2,1],
      		[1,2,3]
      		],
      "vectorB": [1,2,3],
      "operation": "*"
  }
  ```

Aby wykonać obliczenia kliknij `Execute`.

![swagger4](109205115-72d88100-77a6-11eb-8531-2770707c9d0d.jpg)

Następnie otrzymasz wynik obliczeń, ewentualnie komunikat, jeśli wprowadzone dane nie pozwalają na wykonanie obliczenia oraz status odpowiedzi.

![swagger6](109209002-53902280-77ab-11eb-832e-4a703aff146d.jpg)

`getOperationsHistory` wyświetli listę ostatnich operacji.

`deleteAllOperations` wyczyści listę operacji.

`getPossibleOperationRange` w zależności od wybranej opcji zapisu danych, wyświetli:

- dostępne pliki historii operacji,
- listę id operacji możliwych do pobrania.

`getOperationsFromRange` wyświetli:

- listę operacji z konkretnego pliku, po podaniu jego nazwy
- listę operacji z zakresu podanych id od (start) do (end), jeśli nie podasz wartości końcowej (end) otrzymasz listę operacji od początku zakresu (start) do ostatniej operacji.

<!--TODO opis korzystania z Postmana-->

### Sterowanie zapisem historii operacji

Domyślnie aplikacja zapisuje historię operacji do plików w katalogu `/historia`  w folderze tymczasowym. Lokalizację katalogu tymczasowego można uzyskać wywołując metodę:

```
System.out.println(System.getProperty("java.io.tmpdir"));
```

Aby zapisywać historię do bazy danych należy przypisać wartość `db` do `calc.history-service` w pliku:

```
~main\resources\application.properties
```

![props](109210222-d796da00-77ac-11eb-8a8a-21c63e5d68d0.jpg)

W przypadku zapisu do bazy danych H2 można wykonywać dowolne działania w ramach zapytań SQL. W tym celu należy wprowadzić w przeglądarce adres:

http://localhost:8080/h2-console

Pojawi się okno logowania, jak poniżej:

![h2](109211558-ad461c00-77ae-11eb-97a6-0283189b7a46.jpg)

Sprawdź czy adres URL, User Name oraz Password są zgodne z powyższymi. Następnie naciśnij `Connect`.

Teraz możesz wyszukać potrzebne dane za pomocą zapytań SQL. W polu zaznaczonym dużym kołem wpisz swoje zapytanie. Następnie wciśnij run (zielony przycisk play, na który wskazuje strzałka). 

![h2-zapytanie](109216816-5263f300-77b5-11eb-8a1f-484bc187f21a.jpg)

Poniżej pojawi się tablica wybranych rekordów.

![h2-wynik](109216887-66a7f000-77b5-11eb-8b73-295a955bc488.jpg)



## Autor

**Barbara Tyndorf** - [LinkedIn Profile](https://www.linkedin.com/in/barbara-tyndorf/)