package LeetCodePlatform;

import java.util.*;
import java.util.stream.Collectors;

import LeetCodePlatform.Problem.Difficulty;

public class Platform {
    private static Platform platformInstance = null;
    HashMap<String, Contestent> users;
    HashMap<Integer, Problem> problems;

    private Platform() {
        this.users = new HashMap<String, Contestent>();
        this.problems = new HashMap<Integer, Problem>();
    }

    public static Platform getPlatformInstance() {
        if (platformInstance == null) {
            platformInstance = new Platform();
        }
        return platformInstance;
    }

    public void addProblem(String title, String desc, String tag, Difficulty difficulty, int score) {
        int id = problems.size() + 1;
        Problem newProblem = new Problem(id, title, desc, tag, difficulty, score, 0);
        problems.put(id, newProblem);
    }

    public void addUser(String email, String name, String department) {
        Contestent newUser = new Contestent(email, name, department);
        users.put(email, newUser);
    }

    public ArrayList<Problem> fetchProblems(Difficulty difficulty) {
        return fetchProblems(difficulty, true, -1);
    }

    public ArrayList<Problem> fetchProblems(Difficulty difficulty, Boolean ascendingSortByScore) {
        return fetchProblems(difficulty, ascendingSortByScore, -1);
    }

    public ArrayList<Problem> fetchProblems(Difficulty difficulty, Boolean ascendingSortByScore, int limit) {
        ArrayList<Problem> fetctedProblems = new ArrayList<Problem>();

        for (Problem problem : this.problems.values()) {
            if (problem.difficulty == difficulty) {
                fetctedProblems.add(problem);
            }
        }

        Collections.sort(fetctedProblems, new Comparator<Problem>() {
            @Override
            public int compare(Problem p1, Problem p2) {
                if (ascendingSortByScore) {
                    return Float.compare(p1.score, p2.score);
                } else {
                    return Float.compare(p2.score, p1.score);
                }

            }
        });

        if (limit >= 0) {
            fetctedProblems = (ArrayList<Problem>) fetctedProblems.stream().limit(limit).collect(Collectors.toList());
        }
        return fetctedProblems;
    }

    public ArrayList<Problem> solve(int problemId, String userId, int time) {
        Problem problem = problems.get(problemId);
        Contestent user = users.get(userId);
        float score = problem.score;

        problem.SolvedBy.add(user);
        problem.updateAvgSolvingTime(time);

        user.SolvedProblems.add(problem);
        user.addScore(score / time);

        return this.fetchProblems(problem.difficulty, false, 5);
    }

    public Contestent getLeader() {
        Contestent leader = Collections.max(users.values(), new Comparator<Contestent>() {

            @Override
            public int compare(Contestent o1, Contestent o2) {
                return Float.compare(o1.score, o2.score);
            }

        });

        return leader;
    }

}
