# intive CalcRestApi

Aplikacja webowa umożliwiająca obliczenia na liczbach rzeczywistych, wektorach i macierzach.

<h3>Uruchomienie programu</h3>

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

```
http://localhost:8080/swagger-ui/index.html
```

<h3>Instrukcja użytkowania</h3>

Po uruchomieniu przeglądarki i przejściu pod wskazany adres powinien ukazać się taki widok:
![swagger1](https://user-images.githubusercontent.com/55435936/107758942-e0f16280-6d27-11eb-990b-e39b4defa456.jpg)

Kliknij strzałkę zaznaczoną czerwonym kółkiem. Ukaże Ci się lista możliwych do wykonania operacji:

![swagger2](https://user-images.githubusercontent.com/55435936/107759067-1302c480-6d28-11eb-8bb5-84588e9eaae4.jpg)

Aby wejść w wybraną zakładkę, kliknij, aby się rozwinęła. Następnie naciśnij przycisk `Try it out`.



![swagger3](https://user-images.githubusercontent.com/55435936/107759201-3fb6dc00-6d28-11eb-9fad-272c4c850892.jpg)

Teraz w zaznaczonym poniżej białym polu możesz podać swoje wartości. Podaj dwie wartości oraz typ operacji w postaci JSON.

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

![swagger4](https://user-images.githubusercontent.com/55435936/107759525-bb188d80-6d28-11eb-929d-8f3a9f92610f.jpg)




//todo opisać resztę metod


