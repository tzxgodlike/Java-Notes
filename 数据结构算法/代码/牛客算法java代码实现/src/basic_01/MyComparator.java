package basic_01;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.TreeSet;

public class MyComparator {
    public static class Student{
        public String name;
        public int id;
        public int age;

        public Student(String name, int id, int age){
            this.age = age;
            this.id = id;
            this.name = name;
        }
    }
    //按ID升序
    public static class IdAscendingComparator implements Comparator<Student>{
        @Override
        public int compare(Student o1, Student o2) {
            return o1.id - o2.id;
        }
    }

    public static class IdDescendingComparator implements Comparator<Student> {

        @Override
        public int compare(Student o1, Student o2) {
            return o2.id - o1.id;
        }

    }

    public static class AgeAscendingComparator implements Comparator<Student> {

        @Override
        public int compare(Student o1, Student o2) {
            return o1.age - o2.age;
        }

    }

    public static class AgeDescendingComparator implements Comparator<Student> {

        @Override
        public int compare(Student o1, Student o2) {
            return o2.age - o1.age;
        }

    }

    public static void printStudents(Student[] students) {
        for (Student student : students) {
            System.out.println("Name : " + student.name + ", Id : " + student.id + ", Age : " + student.age);
        }
        System.out.println("===========================");
    }

    public static void main(String[] args) {
        Student student1 = new Student("A", 1, 23);
        Student student2 = new Student("B", 2, 21);
        Student student3 = new Student("C", 3, 22);

        //按内存地址排序
        Student[] students = new Student[] { student3, student2, student1 };
        printStudents(students);
        //按ID升序
        Arrays.sort(students, new IdAscendingComparator());
        printStudents(students);

        Arrays.sort(students, new IdDescendingComparator());
        printStudents(students);

        Arrays.sort(students, new AgeAscendingComparator());
        printStudents(students);

        Arrays.sort(students, new AgeDescendingComparator());
        printStudents(students);

        //优先级队列
        PriorityQueue<Student> heap = new PriorityQueue<>(new IdAscendingComparator());
        heap.add(student1);
        heap.add(student2);
        heap.add(student3);
        while (!heap.isEmpty()){
            //堆顶弹出 大小减一
            Student student = heap.poll();
            System.out.println("Name : " + student.name + ", Id : " + student.id + ", Age : " + student.age);

        }

        TreeSet<Student> treeSet = new TreeSet<>(new IdAscendingComparator());
    }

}
