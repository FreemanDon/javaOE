package com.demo.stream;

import java.util.*;
import java.util.stream.Collectors;
import org.apache.commons.collections4.MapUtils;

/**
 * @author DJ
 * @version V1.0
 * @Package: com.demo.stream
 * @Description: 收集器
 * @Creation Date:2018-05-07
 */
public class CollectDemo {
    public static void main(String[] args) {
        //测试数据
        List<Student> students = Arrays.asList(
                new Student("张三1", 10, Gender.MALE, Grade.ONE),
                new Student("张三2", 9, Gender.MALE, Grade.THREE),
                new Student("张三3", 8, Gender.FEMALE, Grade.TWO),
                new Student("张三4", 13, Gender.FEMALE, Grade.FOUR),
                new Student("张三5", 7, Gender.FEMALE, Grade.THREE),
                new Student("张三6", 13, Gender.MALE, Grade.ONE),
                new Student("张三7", 13, Gender.FEMALE, Grade.THREE),
                new Student("张三8", 9, Gender.FEMALE, Grade.TWO),
                new Student("张三9", 6, Gender.MALE, Grade.ONE),
                new Student("张三10", 6, Gender.MALE, Grade.ONE),
                new Student("张三11", 14, Gender.FEMALE, Grade.FOUR),
                new Student("张三12", 13, Gender.MALE, Grade.FOUR));

        //得到所有学生的年龄列表 不会多生成一个类似lambda$0这样的函数
        //s->s.getAge()->Student::getAge
        Set<Integer> ages = students.stream().map(Student::getAge)
                //指定集合类型
                .collect(Collectors.toCollection(TreeSet::new));
        System.out.println("所有学生的年龄" + ages);

        //统计汇总信息
        IntSummaryStatistics agesSummaryStatistics = students.stream()
                .collect(Collectors.summarizingInt(Student::getAge));
        System.out.println("年龄汇总信息" + agesSummaryStatistics);

        //分块--partitioningBy 两种
        Map<Boolean, List<Student>> genders = students.stream().collect(
                Collectors.partitioningBy(s -> s.getGender() == Gender.MALE));
        MapUtils.verbosePrint(System.out, "男女学生列表", genders);

        //分组
        Map<Grade, List<Student>> grades = students.stream()
                .collect(Collectors.groupingBy(Student::getGrade));
        MapUtils.verbosePrint(System.out, "学生班级列表", grades);

        //得到所有班级学生的个数
        Map<Grade, Long> gradesCount = students.stream().collect(Collectors
                .groupingBy(Student::getGrade, Collectors.counting()));
        MapUtils.verbosePrint(System.out, "班级学生个数列表", gradesCount);

    }
}

class Student {
    private String name;
    private int age;
    private Gender gender;
    private Grade grade;

    public Student(String name, int age, Gender gender, Grade grade) {
        super();
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "[name=" + name + ", age=" + age + ", gender=" + gender
                + ", grade=" + grade + "]";
    }
}

enum Gender {
    MALE, FEMALE
}
enum Grade {
    ONE, TWO, THREE, FOUR;
}