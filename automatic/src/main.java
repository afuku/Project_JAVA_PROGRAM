import java.io.Console;
import KEN.TOOL.*;
import KEN.TOOL.KINNFOS.*;
public class main {

    static private Console c =System.console();
    static private K_ModeBusTransfer_HexFloatPointNumber ArmValue;
    static private KINNFOS_Basic kinnfos_basic;

    private static K_DOUBLE control;
    private static int      time[]    = {5, 3, 4, 3, 5, 3, 4, 3, 4, 4},
                            MotorId[] = {2, 4, 1, 2, 3, 2, 3, 2, 4, 5},
                            Moved[]   = {5, 5, 9, 2, 13, 7, 22, 10, 4, 9};
    private static boolean  IsNaga[]  = {true, false, false, true, true, true, true, true, true, true};

    public static void main(String[] args) throws InterruptedException {
        for(int i = 0; i < 10; i++) {
            control = K_DOUBLE.value(Moved[i] , IsNaga[i]);
            ArmValue = K_ModeBusTransfer_HexFloatPointNumber.creat(control);
            kinnfos_basic = new KINNFOS_Basic("192.168.1.30", 2000);
            kinnfos_basic.mean(Grammer.grammer(Command.MOVE, ArmValue.finalView2int(), MotorId[i], Grammer.FLOAT_POINT_NUMBER));
            Thread.sleep(time[i] * 1000);
        }

    }
}
