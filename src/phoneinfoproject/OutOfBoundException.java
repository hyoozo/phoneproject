package phoneinfoproject;

public class OutOfBoundException extends Exception{
    public OutOfBoundException(String message) {
        super(message);
    }

    public static void checkBound(int num, int bound1, int bound2) throws OutOfBoundException {
        if (num < bound1 || bound2 < num) {
            throw new OutOfBoundException(num + "에 해당하는 선택은 존재하지 않습니다.\n메뉴 선택을 처음부터 다시 진행합니다.");
        }
    }

}
