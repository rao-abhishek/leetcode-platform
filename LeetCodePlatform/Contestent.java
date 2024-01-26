package LeetCodePlatform;

import java.util.*;

public class Contestent {
    public String id;
    public String name;
    public String departmentName;
    public HashSet<Problem> SolvedProblems;
    public float score;

    Contestent(String id, String name, String departmentName) {
        this.id = id;
        this.name = name;
        this.departmentName = departmentName;
        this.SolvedProblems = new HashSet<Problem>();
    }

    public void addScore(float score) {
        this.score += score;
    }

}
