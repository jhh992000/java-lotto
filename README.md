# 로또 - TDD

---
## 1단계 - 학습 테스트 실습

### String 클래스에 대한 학습 테스트
###요구사항 1
* "1,2"을 ,로 split 했을 때 1과 2로 잘 분리되는지 확인하는 학습 테스트를 구현한다.
* "1"을 ,로 split 했을 때 1만을 포함하는 배열이 반환되는지에 대한 학습 테스트를 구현한다.
* 힌트1 : 배열로 반환하는 값의 경우 assertj의 contains()를 활용해 반환 값이 맞는지 검증한다.
* 힌트2 : 배열로 반환하는 값의 경우 assertj의 containsExactly()를 활용해 반환 값이 맞는지 검증한다.
###요구사항 2
* "(1,2)" 값이 주어졌을 때 String의 substring() 메소드를 활용해 ()을 제거하고 "1,2"를 반환하도록 구현한다.
###요구사항 3
* "abc" 값이 주어졌을 때 String의 charAt() 메소드를 활용해 특정 위치의 문자를 가져오는 학습 테스트를 구현한다.
* String의 charAt() 메소드를 활용해 특정 위치의 문자를 가져올 때 위치 값을 벗어나면 StringIndexOutOfBoundsException이 발생하는 부분에 대한 학습 테스트를 구현한다.
* JUnit의 @DisplayName을 활용해 테스트 메소드의 의도를 드러낸다.
* 힌트 : [AssertJ Exception Assertions](https://joel-costigliola.github.io/assertj/assertj-core-features-highlight.html#exception-assertion) 문서 참고

### Set Collection에 대한 학습 테스트
###요구사항 1
* Set의 size() 메소드를 활용해 Set의 크기를 확인하는 학습테스트를 구현한다.
###요구사항 2
* Set의 contains() 메소드를 활용해 1, 2, 3의 값이 존재하는지를 확인하는 학습테스트를 구현하려한다.
* 구현하고 보니 다음과 같이 중복 코드가 계속해서 발생한다.
* JUnit의 ParameterizedTest를 활용해 중복 코드를 제거해 본다.
* 힌트 : [Guide to JUnit 5 Parameterized Tests](https://www.baeldung.com/parameterized-tests-junit-5)
###요구사항 3
* 요구사항 2는 contains 메소드 결과 값이 true인 경우만 테스트 가능하다. 입력 값에 따라 결과 값이 다른 경우에 대한 테스트도 가능하도록 구현한다.
* 예를 들어 1, 2, 3 값은 contains 메소드 실행결과 true, 4, 5 값을 넣으면 false 가 반환되는 테스트를 하나의 Test Case로 구현한다.
* 힌트 : [Guide to JUnit 5 Parameterized Tests](https://www.baeldung.com/parameterized-tests-junit-5) 문서에서 @CsvSource를 활용한다.

## assertj 활용

---
## 2단계 - 문자열 덧셈 계산기

### 문자열 덧셈 계산기를 통한 TDD/리팩토링 실습
### 기능 요구사항
* 쉼표(,) 또는 콜론(:)을 구분자로 가지는 문자열을 전달하는 경우 구분자를 기준으로 분리한 각 숫자의 합을 반환 (예: “” => 0, "1,2" => 3, "1,2,3" => 6, “1,2:3” => 6)
* 앞의 기본 구분자(쉼표, 콜론)외에 커스텀 구분자를 지정할 수 있다. 커스텀 구분자는 문자열 앞부분의 “//”와 “\n” 사이에 위치하는 문자를 커스텀 구분자로 사용한다. 예를 들어 “//;\n1;2;3”과 같이 값을 입력할 경우 커스텀 구분자는 세미콜론(;)이며, 결과 값은 6이 반환되어야 한다.
* 문자열 계산기에 숫자 이외의 값 또는 음수를 전달하는 경우 RuntimeException 예외를 throw한다.
### 프로그래밍 요구사항
* indent(들여쓰기) depth를 2단계에서 1단계로 줄여라.
    *  depth의 경우 if문을 사용하는 경우 1단계의 depth가 증가한다. if문 안에 while문을 사용한다면 depth가 2단계가 된다.
* 메소드의 크기가 최대 10라인을 넘지 않도록 구현한다.
    * method가 한 가지 일만 하도록 최대한 작게 만들어라.
* else를 사용하지 마라.
### 기능 요구사항 분리 및 힌트
1. 빈 문자열 또는 null 값을 입력할 경우 0을 반환해야 한다.(예 : “” => 0, null => 0)
2. 숫자 하나를 문자열로 입력할 경우 해당 숫자를 반환한다.(예 : “1”)
3. 숫자 두개를 컴마(,) 구분자로 입력할 경우 두 숫자의 합을 반환한다.(예 : “1,2”)
4. 구분자를 컴마(,) 이외에 콜론(:)을 사용할 수 있다. (예 : “1,2:3” => 6)
5. “//”와 “\n” 문자 사이에 커스텀 구분자를 지정할 수 있다. (예 : “//;\n1;2;3” => 6)
6. 음수를 전달할 경우 RuntimeException 예외가 발생해야 한다. (예 : “-1,2,3”)

---
## 3단계 - 로또(자동)
### 기능 요구사항
* 로또 구입 금액을 입력하면 구입 금액에 해당하는 로또를 발급해야 한다.
* 로또 1장의 가격은 1000원이다.

```
구입금액을 입력해 주세요.
14000
14개를 구매했습니다.
[8, 21, 23, 41, 42, 43]
[3, 5, 11, 16, 32, 38]
[7, 11, 16, 35, 36, 44]
[1, 8, 11, 31, 41, 42]
[13, 14, 16, 38, 42, 45]
[7, 11, 30, 40, 42, 43]
[2, 13, 22, 32, 38, 45]
[23, 25, 33, 36, 39, 41]
[1, 3, 5, 14, 22, 45]
[5, 9, 38, 41, 43, 44]
[2, 8, 9, 18, 19, 21]
[13, 14, 18, 21, 23, 35]
[17, 21, 29, 37, 42, 45]
[3, 8, 27, 30, 35, 44]

지난 주 당첨 번호를 입력해 주세요.
1, 2, 3, 4, 5, 6

당첨 통계
---------
3개 일치 (5000원)- 1개
4개 일치 (50000원)- 0개
5개 일치 (1500000원)- 0개
6개 일치 (2000000000원)- 0개
총 수익률은 0.35입니다.(기준이 1이기 때문에 결과적으로 손해라는 의미임)
```

### 힌트
* 로또 자동 생성은 Collections.shuffle() 메소드 활용한다.
* Collections.sort() 메소드를 활용해 정렬 가능하다.
* ArrayList의 contains() 메소드를 활용하면 어떤 값이 존재하는지 유무를 판단할 수 있다.

### 프로그래밍 요구사항
* 모든 기능을 TDD로 구현해 단위 테스트가 존재해야 한다. 단, UI(System.out, System.in) 로직은 제외
* 핵심 로직을 구현하는 코드와 UI를 담당하는 로직을 구분한다.
* UI 로직을 InputView, ResultView와 같은 클래스를 추가해 분리한다.
* indent(인덴트, 들여쓰기) depth를 2를 넘지 않도록 구현한다. 1까지만 허용한다.
    * 예를 들어 while문 안에 if문이 있으면 들여쓰기는 2이다.
    * 힌트: indent(인덴트, 들여쓰기) depth를 줄이는 좋은 방법은 함수(또는 메소드)를 분리하면 된다.
* 함수(또는 메소드)의 길이가 15라인을 넘어가지 않도록 구현한다.
    * 함수(또는 메소드)가 한 가지 일만 잘 하도록 구현한다.
* 자바 코드 컨벤션을 지키면서 프로그래밍한다.
    * 참고문서: [https://google.github.io/styleguide/javaguide.html](https://google.github.io/styleguide/javaguide.html) 또는 [https://myeonguni.tistory.com/1596](https://myeonguni.tistory.com/1596)
* else 예약어를 쓰지 않는다.
    * 힌트: if 조건절에서 값을 return하는 방식으로 구현하면 else를 사용하지 않아도 된다.
    * else를 쓰지 말라고 하니 switch/case로 구현하는 경우가 있는데 switch/case도 허용하지 않는다.

### 기능 목록 및 commit 로그 요구사항
* 기능을 구현하기 전에 README.md 파일에 구현할 기능 목록을 정리해 추가한다.
* git의 commit 단위는 앞 단계에서 README.md 파일에 정리한 기능 목록 단위로 추가한다.
    * 참고문서: [AngularJS Commit Message Conventions](AngularJS Commit Message Conventions)
#### AngularJS Commit Message Conventions 중
* commit message 종류를 다음과 같이 구분
    ```
    feat (feature)
    fix (bug fix)
    docs (documentation)
    style (formatting, missing semi colons, …)
    refactor
    test (when adding missing tests)
    chore (maintain)
    ```

### 기능 목록 정의 (TODO)
- 로또구매
  - 구매금액을 입력받는다. (금액이 음수인경우 예외처리, 1,000원 이하인경우 예외처리)
  - 구매금액으로 살 수 있는 로또 개수를 구한다. (개당 1000원)
  - 구매가능한 개수만큼 로또를 구입한다. 
  - 로또 1개당 번호 1~45번 까지 6개의 번호를 생성한다. (중복이 없어야 한다. 오름차순 정렬)
  - 구매한 로또를 모두 출력한다.
- 당첨확인
  - 지난주 당첨번호를 입력받는다. ( 콤마 기준으로 6개의 숫자, 중복제거, 1~45번 범위내)
  - 지난주 당첨번호와 구매한 로또들의 번호를 비교해서 당첨 결과를 반환한다.
- 결과출력
  - 당첨결과를 출력한다. (당첨순위, 당첨금액합, 당첨개수)
  - 총 수익률을 계산하여 출력한다. ( 총수익률은 당첨금액의합/총구매금액, 소수점2자리까지 출력)

### 도메인 설계

- InputView
  - [ ] 구매금액을 입력받는다.
    - [ ] 숫자여부 확인 (필요한가? 필요없을듯, 그냥 예외발생시킴)
    - [ ] 양수여부 확인
    - [ ] 1000원이상인지 확인 (로또 1장 가격)
  - [ ] 구매한 로또 개수를 출력한다.
  - [ ] 구매한 로또번호들(구매개수만큼)을 모두 출력한다.
  - [ ] 지난 주 당첨 번호를 입력받는다. ( 콤마 기준으로 6개의 숫자, 중복제거, 1~45번 범위내)
- LottoService
  - [ ] 구매금액으로 살 수 있는 로또 개수를 구한다.
  - [ ] 구매개수만큼 로또를 구입한다. (구입한 로또 리스트를 반환)
- LottoNumbers (일급콜렉션)
  - [ ] 1~45번(상수)까지 랜덤한 6개의 로또번호를 생성한다. 
  - [ ] 당첨 로또번호 리스트를 입력받아 자신의 로또번호와 비교하여 랭킹을 반환한다.
    - [ ] 2개의 로또번호 리스트를 비교하여 일치하는 개수를 리턴한다.
    - [ ] 일치개수에 따라 랭킹을 반환한다.
  - [ ] 당첨된 로또 리스트를 반환한다. (꽝이 아닌것들)
  - [ ] 총 수익율을 반환한다 (당첨금액의 합/총 구매금액)
- LottoNumber (원시값 포장)
  - [x] 로또 번호 속성을 가진다.
    - [x] 1~45 범위내의 숫자여야 한다.
  - [x] 당첨번호와 비교하여 일치 여부를 반환한다.
- RandomNumbersGenerator (인터페이스)
  - [ ] 랜덤번호 리스트를 생성하여 반환한다.
- LottoRandomNumbersGenerator (구현체)
  - [ ] 1~45범위의 랜덤번호 리스트를 생성하여 반환한다. (중복이 없어야함, Collection.shuffle() 메서드 활용)
  - [ ] 리스트를 오름차순 정렬시킨다.
- Rank (enum) (등수,당첨금액)
  - [ ] 1등, 6개일치, 2,000,000,000원
  - [ ] 2등, 5개일치, 1,500,000원
  - [ ] 3등, 4개일치, 50,000원
  - [ ] 4등, 3개일치, 5,000원
  - [ ] 꽝, 2개이하일치, 0원
  - [ ] 일치개수에 따른 Rank 를 반환한다.
- ResultView
  - [ ] 당첨 통계를 출력한다.
    - [ ] 당첨된 로또 리스트를 출력한다. (일치개수기준으로 내림차순)
    - [ ] 총 수익률을 출력한다. (소수점2자리까지)

