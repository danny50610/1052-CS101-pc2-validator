
public class NPuzzleValidator extends Validator {

    @Override
    public ValidationResult validate(String[] in, String[] out) {
        String allOutput = String.join("", out);

        int N = Integer.parseInt(in[0]);
        int[][] puzzle = new int[N][N];
        int zeroR = -1;
        int zeroC = -1;

        for (int r = 0; r < N; r++) {
            String[] line = in[r + 1].split(" ");
            for (int c = 0; c < N; c++) {
                puzzle[r][c] = Integer.parseInt(line[c]);

                if (puzzle[r][c] == 0) {
                    zeroR = r;
                    zeroC = c;
                }
            }
        }

        assert zeroR != -1 && zeroC != -1 : "Can't find zero";

        for (int i = 0; i < allOutput.length(); i++) {
            char dir = allOutput.charAt(i);
            if (dir == 'u' || dir == 'd' || dir == 'r' || dir == 'l') {
                int targetR = zeroR + getDR(dir);
                int targetC = zeroC + getDC(dir);

                if (!isInRange(targetR, N) || !isInRange(targetC, N)) {
                    return new ValidationResult("No - Wrong Answer", "Puzzle out of range (in " + i + " step)(start from 0)");
                }

                puzzle[zeroR][zeroC] = puzzle[targetR][targetC];
                puzzle[targetR][targetC] = 0;

                zeroR = targetR;
                zeroC = targetC;
            }
            else {
                return new ValidationResult("No - Wrong Answer",
                        "Output contains illegal operation (in " + i + " step)(start from 0)");
            }
        }

        // check puzzle
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (puzzle[r][c] != 0 && puzzle[r][c] != r * N + c + 1) {
                    return new ValidationResult("No - Wrong Answer", "Wrong puzzle");
                }
            }
        }

        return new ValidationResult("Yes", "Correct.");
    }

    private int getDR(char dir) {
        if (dir == 'u') {
            return -1;
        }
        else if (dir == 'd') {
            return 1;
        }
        else if (dir == 'r') {
            return 0;
        }
        else if (dir == 'l') {
            return 0;
        }
        return Integer.MIN_VALUE;
    }

    private int getDC(char dir) {
        if (dir == 'u') {
            return 0;
        }
        else if (dir == 'd') {
            return 0;
        }
        else if (dir == 'r') {
            return 1;
        }
        else if (dir == 'l') {
            return -1;
        }
        return Integer.MIN_VALUE;
    }

    private boolean isInRange(int value, int N) {
        return 0 <= value && value < N;
    }
}
