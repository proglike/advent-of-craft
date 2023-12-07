package ci.dependencies;

public class Project {
    private final boolean buildsSuccessfully;
    private final TestStatus testStatus;

    private Project(boolean buildsSuccessfully, TestStatus testStatus) {
        this.buildsSuccessfully = buildsSuccessfully;
        this.testStatus = testStatus;
    }

    public static ProjectBuilder builder() {
        return new ProjectBuilder();
    }

    public boolean hasTests() {
        return testStatus != TestStatus.NO_TESTS;
    }

    public StepExecutionStatus runTestsIfPresents() {
        if (!hasTests()) {
            return StepExecutionStatus.NO_EXECUTION;
        }
        return runTests();
    }

    private StepExecutionStatus runTests() {
        return testStatus == TestStatus.PASSING_TESTS ? StepExecutionStatus.SUCCESS : StepExecutionStatus.FAILURE;
    }

    public StepExecutionStatus deploy() {
        return buildsSuccessfully ? StepExecutionStatus.SUCCESS : StepExecutionStatus.FAILURE;
    }

    public static class ProjectBuilder {
        private boolean buildsSuccessfully;
        private TestStatus testStatus;

        public ProjectBuilder setTestStatus(TestStatus testStatus) {
            this.testStatus = testStatus;
            return this;
        }

        public ProjectBuilder setDeploysSuccessfully(boolean buildsSuccessfully) {
            this.buildsSuccessfully = buildsSuccessfully;
            return this;
        }

        public Project build() {
            return new Project(buildsSuccessfully, testStatus);
        }
    }
}
