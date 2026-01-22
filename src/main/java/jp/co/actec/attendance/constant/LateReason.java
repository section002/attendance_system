package jp.co.actec.attendance.constant;

public enum LateReason {
    TRAIN_DELAY(1, "電車遅延"),
    OVERSLEEP(2, "寝坊"),
    HOSPITAL(3, "通院"),
    OTHER(99, "その他");

    private final int code;
    private final String label;

    LateReason(int code, String label) {
        this.code = code;
        this.label = label;
    }

    public int getCode() {
        return code;
    }

    public String getLabel() {
        return label;
    }
}