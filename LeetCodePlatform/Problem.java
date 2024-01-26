package LeetCodePlatform;

import java.util.*;

public class Problem {
    public enum Difficulty {
        EASY,
        MEDIUM,
        HARD
    }

    public int id;
    public String title;
    public String desc;
    public String tag;
    public Difficulty difficulty;
    public HashSet<Contestent> SolvedBy;
    public float score;
    public float avgTime;
    public int likesCount;

    Problem(int id, String title, String desc, String tag, Difficulty difficulty, float score, int likesCount) {
        this.id = id;
        this.title = title;
        this.desc = desc;
        this.tag = tag;
        this.difficulty = difficulty;
        this.SolvedBy = new HashSet<Contestent>();
        this.score = score;
        this.avgTime = 0;
        this.likesCount = 0;
    }

    public void updateAvgSolvingTime(int time) {
        this.avgTime = ((this.avgTime * this.SolvedBy.size() - 1) + time) / this.SolvedBy.size();
    }
}
