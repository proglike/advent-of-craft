package ci;

import ci.dependencies.*;

public class Pipeline {
    private final Config config;
    private final Emailer emailer;
    private final Logger log;

    public Pipeline(Config config, Emailer emailer, Logger log) {
        this.config = config;
        this.emailer = emailer;
        this.log = log;
    }

    public void run(Project project) {

        StepExecutionStatus testResult = project.runTestsIfPresents();
        logTestResult(testResult);

        StepExecutionStatus deployResult =
                testResult == StepExecutionStatus.FAILURE ?
                        StepExecutionStatus.NO_EXECUTION :
                        project.deploy();
        logDeployResult(deployResult);

        sendMailReport(deployResult);
    }

    private void sendMailReport(StepExecutionStatus deployExecutionStatus) {
        if (config.sendEmailSummary()) {
            log.info("Sending email");
            emailer.send(computeMailMessage(deployExecutionStatus));
        } else {
            log.info("Email disabled");
        }
    }

    private static String computeMailMessage(StepExecutionStatus deployExecutionStatus) {
        return switch (deployExecutionStatus) {
            case SUCCESS -> "Deployment completed successfully";
            case FAILURE -> "Deployment failed";
            case NO_EXECUTION -> "Tests failed";
        };
    }

    private void logDeployResult(StepExecutionStatus deployExecutionStatus) {
        switch (deployExecutionStatus) {
            case SUCCESS -> log.info("Deployment successful");
            case FAILURE -> log.error("Deployment failed");
        }
    }

    private void logTestResult(StepExecutionStatus testExecutionStatus) {
        switch (testExecutionStatus) {
            case NO_EXECUTION -> log.info("No tests");
            case SUCCESS -> log.info("Tests passed");
            case FAILURE -> log.error("Tests failed");
        }
    }
}
