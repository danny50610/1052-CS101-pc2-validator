import java.util.Formatter;

// Copy from https://github.com/beingryu/algospot-pc2-validator
public class ValidationResult {
    private String outcome;
    private String message;

    public ValidationResult(String outcome, String message) {
        this.outcome = outcome;
        this.message = message;
    }

    public String toPC2Result(String resultFileName) {
        StringBuilder sb = new StringBuilder();
        Formatter f = new Formatter(sb);

        f.format("<?xml version=\"1.0\"?>\n<result outcome=\"%s\" security=\"%s\">%s</result>",
                outcome,
                resultFileName,
                message);

        return sb.toString();
    }

    public String getMessage() {
        return message;
    }
}