package types.enums;

public enum Direction {
    UP(8), DOWN(-1), LEFT(4), RIGHT(12);

    private int code;

    private Direction(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
