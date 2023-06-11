package com.crecema.my.spring.boot.common.util;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class JsonUtilsTest {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    private static class Person {
        private String name;
        private Sex sex;
        private int age;
    }

    @Getter
    @AllArgsConstructor
    private enum Sex {
        FEMALE(0, "女"),
        MAIL(1, "男");
        @JsonValue
        private final int code;
        private final String desc;
        @JsonCreator
        public Sex valueOf(int code) {
            return Arrays.stream(values())
                    .filter(sex -> sex.code == code)
                    .findFirst()
                    .orElse(null);
        }
    }

    @Test
    public void testToJson() {
        Person person = new Person("Haoran Ma", Sex.MAIL, 24);
        Assertions.assertEquals("{\"name\":\"Haoran Ma\",\"sex\":1,\"age\":24}", JsonUtils.toJson(person));
    }

    @Test
    public void testToObject() {
        String json = "{\"name\":\"Haoran Ma\",\"sex\":1,\"age\":24}";
        Person person = JsonUtils.toObject(json, Person.class);
        Assertions.assertNotNull(person);
        Assertions.assertEquals("Haoran Ma", person.getName());
        Assertions.assertEquals(1, person.getSex().getCode());
        Assertions.assertEquals(24, person.getAge());
    }

    @Test
    public void testToMap() {
        String json = "{\"name\":\"Haoran Ma\",\"sex\":1,\"age\":24}";
        Map<String, Object> map = JsonUtils.toMap(json, String.class, Object.class);
        Assertions.assertNotNull(map);
        Assertions.assertEquals("Haoran Ma", map.get("name"));
        Assertions.assertEquals(1, map.get("sex"));
        Assertions.assertEquals(24, map.get("age"));

        Map<String, Object> map2 = JsonUtils.toMap(JsonUtils.toObject(json, Person.class), String.class, Object.class);
        Assertions.assertNotNull(map);
        Assertions.assertEquals("Haoran Ma", map2.get("name"));
        Assertions.assertEquals(1, map2.get("sex"));
        Assertions.assertEquals(24, map2.get("age"));
    }

    @Test
    public void testToList() {
        List<Person> personList = List.of(
                new Person("Haoran Ma", Sex.MAIL, 24),
                new Person("Ma Haoran", Sex.MAIL, 22)
        );
        String json = JsonUtils.toJson(personList);
        List<Person> list = JsonUtils.toList(json, Person.class);
        Assertions.assertNotNull(list);
        Assertions.assertEquals(2, list.size());
        Assertions.assertEquals("Haoran Ma", list.get(0).getName());
        Assertions.assertEquals("Ma Haoran", list.get(1).getName());
    }

    @Test
    public void testToArray() {
        List<Person> personList = List.of(
                new Person("Haoran Ma", Sex.MAIL, 24),
                new Person("Ma Haoran", Sex.MAIL, 22)
        );
        String json = JsonUtils.toJson(personList);
        Person[] array = JsonUtils.toArray(json, Person.class);
        Assertions.assertNotNull(array);
        Assertions.assertEquals(2, array.length);
        Assertions.assertEquals("Haoran Ma", array[0].getName());
        Assertions.assertEquals("Ma Haoran", array[1].getName());
    }

}
