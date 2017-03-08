package week3;

import java.util.ArrayList;

public class VisitorPatternOriginal {

    public static void main(String[] args) {
        SUTD oneSUTD = new SUTD();

        ArrayList<Employee> employee = oneSUTD.getEmployee();

        //auditing
        for (int i = 0; i < employee.size(); i++) {
            employee.get(i).accept(oneSUTD);
        }
    }
}


interface Visitor {
    void visit(Visitable v);
}


class SUTD implements Visitor {
    private ArrayList<Employee> employee;

    public SUTD() {
        employee = new ArrayList<Employee>();
        employee.add(new Professor("Sun Jun", 0));
        employee.add(new AdminStuff("Stacey", 5));
        employee.add(new Student("Allan", 3));
    }

    public void visit(Visitable v) {
        if (v instanceof Professor) {
            System.out.println("Prof: " + ((Professor) v).getName() + ((Professor) v).getNo_of_publications());
        }
        if (v instanceof AdminStuff) {
            System.out.println("Prof: " + ((AdminStuff) v).getName() + ((AdminStuff) v).getEfficiency());
        }
        if (v instanceof Student) {
            System.out.println("Prof: " + ((Student) v).getName() + ((Student) v).getGPA());
        }
    }

    public ArrayList<Employee> getEmployee() {
        return employee;
    }
}


interface Visitable {
    void accept(Visitor v);
}

class Employee implements Visitable {
    public void accept(Visitor v) {
        v.visit(this);
    }
}

class Professor extends Employee {
    private String name;
    private int no_of_publications;

    public Professor(String name, int no_of_publications) {
        this.name = name;
        this.no_of_publications = no_of_publications;
    }

    public String getName() {
        return name;
    }

    public int getNo_of_publications() {
        return no_of_publications;
    }
}

class AdminStuff extends Employee {
    private String name;
    private float efficiency;

    public AdminStuff(String name, float efficiency) {
        this.name = name;
        this.efficiency = efficiency;
    }

    public String getName() {
        return name;
    }

    public float getEfficiency() {
        return efficiency;
    }
}

class Student extends Employee {
    private String name;
    private float GPA;

    public Student(String name, float GPA) {
        this.name = name;
        this.GPA = GPA;
    }

    public String getName() {
        return name;
    }

    public float getGPA() {
        return GPA;
    }
}