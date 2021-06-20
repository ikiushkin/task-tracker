package entity;

import java.util.List;

public class Report {
    private final Project project;
    private final User user;

    public Report(Project project, User user) {
        this.project = project;
        this.user = user;
    }

    @Override
    public String toString() {
        return "Report[" + project + ", user=" + user;
    }
}
