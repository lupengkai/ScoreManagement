import java.util.Comparator;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Created by tage on 12/9/15.
 */
public class Student {
    static LinkedList<Student> studentList = new LinkedList<>();
    static int num = 0;
    static StudentComparator sc = new StudentComparator();
    private String id;
    private String name;
    private double score;

    public Student(String id, String name, double score) {
        this.id = id;
        this.name = name;
        this.score = score;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public void display() {
        System.out.print("Rank: " + studentList.indexOf(this) + 1);
        System.out.print("  ID: " + this.getId());
        System.out.print("  Name: " + this.getName());
        System.out.println("  Score: " + this.getScore());

    }

    static void add(String id, String name, double score) {
        studentList.add(new Student(id, name, score));
        studentList.sort(sc);
        num++;
    }

    static void delete(String id) {
        studentList.remove(find(id));
        num--;
    }

    static int find(String id) {
        int index = 0;
        for (Student s : studentList) {
            if (s.getId().equals(id)) {
                studentList.indexOf(s);
                return index;
            }
        }
        return -1;
    }

    static void revise(String id, double score) {
        studentList.get(find(id)).setScore(score);
    }

    static void view(String id) {
        studentList.get(find(id)).display();
    }

    static void allView() {
        for (Student s : studentList) {
            s.display();
        }
    }

    static void numQuery() {
        System.out.println("Num of Students: " + num);
    }

    static void input() throws Exception{
        String name = null;
        String id = null;
        Double score = 0.0;
        while (true) {
            System.out.println("Please input : Add Delete Revise Rank Num Exit");
            Scanner sc = new Scanner(System.in);
            String op = sc.next();
            switch (Opreation.toOpreation(op.toUpperCase())) {
                case ADD:
                    System.out.println("Input ID:");
                    id = sc.next();
                    System.out.println("Input Name:");
                    name = sc.next();
                    System.out.println("Input Score:");
                    score = sc.nextDouble();
                    add(id, name, score);
                    break;
                case DELETE:
                    System.out.println("Input ID:");
                    id = sc.next();
                    delete(id);
                    break;
                case REVISE:
                    System.out.println("Input ID:");
                    id = sc.next();
                    System.out.println("Input Score:");
                    score = sc.nextDouble();
                    revise(id, score);
                    break;
                case RANK:
                    allView();
                    break;
                case NUM:
                    numQuery();
                    break;
                case EXIT:
                    System.exit(0);
                default:
                    break;
            }

        }
    }


    public static void main(String[] args) {
        try {
            input();
        } catch (Exception e) {
            main(args);
        }
    }
}

    class StudentComparator implements Comparator<Student> {
        @Override
        public int compare(Student o1, Student o2) {
            if (o1.getScore() > o2.getScore()) return 1;
            if (o1.getScore() < o2.getScore()) return -1;
            return 0;
        }
    }

    enum Opreation {
        ADD, DELETE, REVISE, RANK, NUM, EXIT;

        public static Opreation toOpreation(String str) {
            return valueOf(str);
        }
    }

