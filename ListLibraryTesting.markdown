# Hamcrest Matchers Guide

Hamcrest là một thư viện mạnh mẽ trong Java để viết các câu lệnh kiểm tra (assertions) dễ đọc và linh hoạt trong testing. Dưới đây là danh sách các **matcher** phổ biến của Hamcrest, được chia theo nhóm, kèm ví dụ minh họa.

## General Matchers

### `is(T)`
Kiểm tra giá trị hoặc tham chiếu có bằng giá trị được chỉ định không.

```java
String str1 = "text";
String str2 = "text";
assertThat(str1, is(str2)); // Kiểm tra str1 == str2
assertThat(1, is(1)); // Kiểm tra số 1 == 1
```

### `equalTo(T)`
Tương tự `is(T)`, kiểm tra giá trị bằng nhau.

```java
assertThat(1, equalTo(1));
assertThat(1, is(equalTo(1))); // Có thể kết hợp với is
```

### `not(T)`
Kiểm tra giá trị không bằng giá trị được chỉ định.

```java
assertThat(1, not(2)); // 1 không phải là 2
```

### `any(Class<T>)`
Kiểm tra đối tượng thuộc một lớp cụ thể.

```java
assertThat(1, any(Integer.class)); // 1 là Integer
```

### `instanceOf(Class<?>)`
Kiểm tra đối tượng là instance của một lớp.

```java
assertThat(1, instanceOf(Integer.class)); // 1 là instance của Integer
```

### `nullValue()` / `nullValue(Class<T>)`
Kiểm tra giá trị là `null`.

```java
Integer n = null;
assertThat(n, is(nullValue())); // n là null
assertThat(n, is(nullValue(Integer.class))); // n là null và thuộc Integer
```

### `notNullValue()` / `notNullValue(Class<T>)`
Kiểm tra giá trị không phải `null`.

```java
Integer n = 5;
assertThat(n, is(notNullValue())); // n không null
assertThat(n, is(notNullValue(Integer.class))); // n không null và thuộc Integer
```

### `is(in(Collection))`
Kiểm tra phần tử có trong tập hợp.

```java
assertThat("foo", is(in(Arrays.asList("bar", "foo")))); // "foo" có trong danh sách
```

### `is(oneOf(String elements))`
Kiểm tra phần tử là một trong các giá trị được liệt kê.

```java
assertThat("foo", is(oneOf("bar", "foo"))); // "foo" là một trong các giá trị
```

## Combining Multiple Matchers

### `allOf`
Kiểm tra tất cả các điều kiện (AND).

```java
assertThat("abc.com", allOf(startsWith("abc"), containsString("."), endsWith("com")));
// "abc.com" phải thỏa mãn: bắt đầu bằng "abc", chứa ".", kết thúc bằng "com"
```

### `anyOf`
Kiểm tra ít nhất một điều kiện (OR).

```java
assertThat("abc.com", anyOf(startsWith("abc"), containsString("."), endsWith("com")));
// "abc.com" thỏa mãn ít nhất một điều kiện
```

### `both`
Kiểm tra hai điều kiện (AND).

```java
String testString = "daenerys targaryen";
assertThat(testString, both(startsWith("daene")).and(containsString("yen")));
// testString phải bắt đầu bằng "daene" và chứa "yen"
```

### `either`
Kiểm tra một trong hai điều kiện (OR).

```java
assertThat(testString, either(startsWith("tar")).or(containsString("targaryen")));
// testString thỏa mãn một trong hai điều kiện
```

## String Matchers

### `isEmptyString()`
Kiểm tra chuỗi rỗng.

```java
assertThat("", is(emptyString())); // Chuỗi rỗng
```

### `emptyOrNullString()`
Kiểm tra chuỗi rỗng hoặc `null`.

```java
assertThat((String) null, is(emptyOrNullString())); // Chuỗi null
```

### `hasToString(String s)`
Kiểm tra `toString()` của đối tượng trả về giá trị cụ thể.

```java
assertThat(4, hasToString("4")); // toString của 4 là "4"
```

### `containsString(String s)`
Kiểm tra chuỗi chứa một đoạn cụ thể.

```java
assertThat(3.14, hasToString(containsString("."))); // toString của 3.14 chứa "."
```

### `equalToIgnoringCase`
Kiểm tra chuỗi bằng nhau, không phân biệt hoa thường.

```java
assertThat("Hello", equalToIgnoringCase("hello")); // "Hello" == "hello" (không phân biệt case)
```

## Iterable Matchers

### `everyItem(Matcher<U> itemMatcher)`
Kiểm tra mọi phần tử trong iterable thỏa mãn điều kiện.

```java
assertThat(Arrays.asList("bar", "baz"), everyItem(startsWith("ba")));
// Mọi phần tử bắt đầu bằng "ba"
```

### `hasItem(T)`
Kiểm tra iterable chứa một phần tử cụ thể.

```java
assertThat(Arrays.asList("foo", "bar"), hasItem("bar")); // Chứa "bar"
```

### `hasItems(Matcher<? super T>... itemMatchers)`
Kiểm tra iterable chứa các phần tử thỏa mãn matcher.

```java
assertThat(Arrays.asList("foo", "bar", "baz"), hasItems(endsWith("z"), endsWith("o")));
// Chứa phần tử kết thúc bằng "z" và "o"
```

### `hasItems(T... items)`
Kiểm tra iterable chứa các phần tử cụ thể.

```java
assertThat(Arrays.asList("foo", "bar", "baz"), hasItems("baz", "foo"));
// Chứa "baz" và "foo"
```

### `emptyIterable()`
Kiểm tra iterable rỗng.

```java
assertThat(new ArrayList<>(), is(emptyIterable())); // Danh sách rỗng
```

### `contains()`
Kiểm tra iterable chứa các phần tử theo đúng thứ tự.

```java
List<String> strings = Arrays.asList("why", "hello", "there");
assertThat(strings, contains("why", "hello")); // Chứa "why", "hello" theo thứ tự
```

### `containsInAnyOrder`
Kiểm tra iterable chứa các phần tử, không quan tâm thứ tự.

```java
assertThat(strings, containsInAnyOrder("hello", "why", "there"));
// Chứa các phần tử, không cần đúng thứ tự
```

### `iterableWithSize`
Kiểm tra iterable có kích thước cụ thể.

```java
Set<Integer> set = new HashSet<>(Arrays.asList(3, 4, 5));
assertThat(set, iterableWithSize(3)); // Kích thước là 3
assertThat(set, iterableWithSize(lessThan(4))); // Kích thước < 4
```

## Collection Matchers

### `hasSize(int)`
Kiểm tra collection có kích thước cụ thể.

```java
List<Integer> testList = Arrays.asList(1, 2, 3, 4, 5);
assertThat(testList, hasSize(5)); // Kích thước là 5
assertThat(testList, hasSize(lessThan(10))); // Kích thước < 10
```

### `empty()`
Kiểm tra collection rỗng.

```java
assertThat(new ArrayList<>(), is(empty())); // Danh sách rỗng
```

## Array Matchers

### `array()`
Kiểm tra mảng với các matcher cho từng phần tử.

```java
String[] test = {"To be", "or not to be", "that is the question!"};
assertThat(test, array(startsWith("To"), containsString("not"), instanceOf(String.class)));
// Mảng thỏa mãn các matcher
```

### `hasItemInArray(T)`
Kiểm tra mảng chứa một phần tử cụ thể.

```java
Integer[] test = {1, 2, 7, 5, 4, 8};
assertThat(test, hasItemInArray(4)); // Chứa 4
assertThat(test, hasItemInArray(is(greaterThan(6)))); // Chứa số > 6
```

### `arrayContaining(E...)`
Kiểm tra mảng chứa các phần tử theo đúng thứ tự.

```java
String[] strings = {"why", "hello", "there"};
assertThat(strings, is(arrayContaining("why", "hello", "there")));
// Mảng chứa đúng các phần tử theo thứ tự
```

### `arrayContainingInAnyOrder(E...)`
Kiểm tra mảng chứa các phần tử, không quan tâm thứ tự.

```java
assertThat(strings, is(arrayContainingInAnyOrder("there", "why", "hello")));
// Mảng chứa các phần tử, không cần đúng thứ tự
```

### `arrayWithSize(int)`
Kiểm tra mảng có kích thước cụ thể.

```java
String[] strings = {"why", "hello", "there"};
assertThat(strings, is(arrayWithSize(3))); // Kích thước là 3
assertThat(strings, is(arrayWithSize(greaterThan(2)))); // Kích thước > 2
```

### `emptyArray()`
Kiểm tra mảng rỗng.

```java
assertThat(new String[]{}, is(emptyArray())); // Mảng rỗng
```

## Bean Matchers

### `hasProperty(String)`
Kiểm tra bean có thuộc tính cụ thể.

```java
JTextField testBean = new JTextField();
testBean.setText("Hello, World!");
assertThat(testBean, hasProperty("text")); // Có thuộc tính "text"
```

### `hasProperty(String, Matcher<?>)`
Kiểm tra bean có thuộc tính với giá trị thỏa mãn matcher.

```java
assertThat(testBean, hasProperty("text", startsWith("H")));
// Thuộc tính "text" bắt đầu bằng "H"
```

### `samePropertyValuesAs(T)`
Kiểm tra hai bean có cùng giá trị thuộc tính.

```java
Bean one = new Bean();
one.setText("text");
one.setNumber(3);

Bean two = new Bean();
two.setText("text");
two.setNumber(3);

assertThat(one, samePropertyValuesAs(two)); // Hai bean có cùng giá trị thuộc tính
```

## Number Matchers

### `closeTo(double, double)`
Kiểm tra số gần với giá trị mong đợi trong khoảng sai số.

```java
Double testValue = 6.3;
assertThat(testValue, is(closeTo(6, 0.5))); // 6.3 gần 6 trong khoảng ±0.5
```

### `closeTo(BigDecimal, BigDecimal)`
Kiểm tra số BigDecimal gần với giá trị mong đợi.

```java
BigDecimal value = new BigDecimal("6.3");
assertThat(value, is(closeTo(new BigDecimal("6"), new BigDecimal("0.5"))));
```

## Kết luận
Hamcrest cung cấp một bộ matcher phong phú để viết các câu lệnh kiểm tra linh hoạt và dễ đọc. Bạn có thể kết hợp các matcher để kiểm tra các điều kiện phức tạp, từ chuỗi, mảng, collection, đến bean và số. Để sử dụng, hãy thêm dependency Hamcrest vào dự án:

```xml
<dependency>
    <groupId>org.hamcrest</groupId>
    <artifactId>hamcrest</artifactId>
    <version>2.2</version>
    <scope>test</scope>
</dependency>
```