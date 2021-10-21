<h1>設置機械手臂動作指令</h1>
<h3>需匯入的類別</h3>
匯入時，請先確認下載的jar檔是否有匯入成功至intellij idea IDE，否則匯入以下類別會失敗或無法執行。
<pre><code>import KEN.TOOL.*;
import KEN.TOOL.KINNFOS.*;
</code></pre>
<h3>程式語法說明</h3>
若要對機械手臂下指令，則要先宣告一個 <code>KINNFOS_Basic</code> 型別的變數，宣告語法如下：
<pre><code>KINNFOS_Basic <i><b>var_name</b></i> = new KINNFOS_Basic(<i><b>IP</b></i>, <i><b>PORT</b></i>);
</code></pre>
<ul>
  <li>
    <b>var_name</b></br> 
    自行設定一個變數名稱
  </li>
  <li>
    <b>IP</b></br>
    為一個 String，輸入IP位址，預設狀態下請輸入：192.168.1.30
  </li>
  <li>
    <b>PORT</b></br>
    為一個整數 int，輸入序列埠編號，預設狀態下請輸入：2000
  </li>
</ul>
通常只要這樣寫即可：
<pre><code>KINNFOS_Basic ArmControl = new KINNFOS_Basic("192.168.1.30", 2000);</code></pre>
變數宣告完畢後，我們就開始控制機械手臂移動，則其中一軸馬達的轉動程式語法如下（轉動是以絕對位置進行轉動）：
<pre><code><i><b>var_name</b></i>.mean(Gramer.grammer(Command.MOVE, <i><b>MOVE_rad</b></i>, <i><b>Motor_id</b></i>, Grammer.FLOAT_POINT_NUMBER));
</code></pre>
<ul>
  <li>
    <b>var_name</b></br> 
    對應已宣告的變數之變數名稱
  </li>
  <li>
    <b>MOVE_rad</b></br>
    為一個 int陣列，gj 輸入馬達需旋轉的軸角度，單位為rad，但 1rad = 10度
  </li>
  <li>
    <b>Motor_id</b></br>
    為一個整數 int，輸入要轉動的馬達編號
  </li>
</ul>
由於 MOVE_rad 為int陣列，因此無法直接輸入數值，需透過轉換方式進行轉換，可利用 <code>K_DOUBLE</code> 與 <code>K_ModeBusTransfer_HexFloatPointNumber</code> 型別進行轉換，前者
可轉換成任意進制的數值，後者為轉換成機械手臂專用的數值格式，語法分別如下：
<pre><code>K_DOUBLE var_name = KDOUBLE.value(<i><b>Value</b></i>, <i><b>IsNagative</b></i>);</code></pre>
<ul>
  <li>
    <b>var_name</b></br> 
    自行設定一個變數名稱
  </li>
  <li>
    <b>Value</b></br>
    為一個雙晶浮點數 double，輸入要轉換的數值，最小值為0
  </li>
</ul>
<pre><code>K_ModeBusTransfer_HexFloatPointNumber <i><b>var_name</b></i> = K_ModeBusTransfer_HexFloatPointNumber.creat(<i><b>K_DOUBLE_Value</b></i>);
</code></pre>
<ul>
  <li>
    <b>var_name</b></br> 
    自行設定一個變數名稱
  </li>
  <li>
    <b>K_DOUBLE_Value</b></br>
    為一個 K_DOUBLE 型別，輸入要轉換成機械手臂專用數值格式的 K_DOUBLE 變數，若 K_DOUBLE 變數未符合機械手臂轉換條件，則會產生錯誤，並且為null
  </li>
</ul>
數值轉換的使用範例如下：
<pre><code>K_DOUBLE kvalue = K_DOUBLE.value(23.5, true);  //將 -23.5 進行轉換
K_ModeBusTransfer_HexFloatPointNumber ArmValue = K_ModeBusTransfer_HexFloatPointNumber.creat(kvalue);  //將 kvalue 轉換成機械手臂專用數值格式
</code></pre>
在有些情況下，寫可以這樣表示（與上面的一樣）：
<pre><code>K_ModeBusTransfer_HexFloatPointNumber ArmValue = K_ModeBusTransfer_HexFloatPointNumber.creat(K_DOUBLE.value(23.5, true));
</code></pre>
最後要把轉換成機械手臂專用數值套用到馬達轉動程式上，則在 MOVE_rad 區套用 <code><i><b>var_name</b></i>.finalView2int()</code> 即可，將上面的範例套用到馬達轉動程式上：
<pre><code><i><b>var_name</b></i>.mean(Gramer.grammer(Command.MOVE, ArmValue.finalView2int(), 1, Grammer.FLOAT_POINT_NUMBER));
//讓第一個馬達轉動 -23.5rad （絕對位置轉動）
</code></pre>

<h3>範例</h3>
我們進行一個範例，當程式啟動時，可以輸入要旋轉的馬達及旋轉值（單位rad），直到按下任意鍵程式執行結束，程式如下：
<pre><code>//ArmControl.java
import KEN.TOOL.*;
import KEN.TOOL.KINNFOS.*;
import java.io.Console;
public class ArmControl {
  private static Console c = System.console();
  public static void main(String[] args) {
    while(isExit) {
      System.out.print("請輸入要轉動的馬達編號，編號值為 1~6，任意鍵為離開：");
      try {
        int MotorNum = Integer.parseInt(c.readLine());
        if(MotorNum > 6 || MotorNum <= 0) throw new Exception();
        try {
          System.out.print("請輸入要轉動的rad：");
          double motor_rad = Double.parseDouble(c.readLine());
          boolean isNagative = flase;
          if(motor_rad < 0) isNagative = true;
          K_DOUBLE kvalue = K_DOUBLE.value(motor_rad, isNagative);
          K_ModeBusTransfer_HexFloatPointNumber ArmValue = K_ModeBusTransfer_HexFloatPointNumber.creat(kvalue);
          KINNFOS_Basic ArmControl = new KINNFOS_Basic("192.168.1.30", 2000);
          ArmControl.mean(Gramer.grammer(Command.MOVE, ArmValue.finalView2int(), MotorNum, Grammer.FLOAT_POINT_NUMBER));
          System.out.println("移動已完畢");
        } catch(Exception e1) {
          System.out.println("有錯誤，請重來");
          continue;
        }
      } catch(Exception e0) {
        System.out.println("程式已結束，再見～～");
        break;
      }
    }
  }
}
</code></pre>
<b>注意</b></br>
由於此程式（介紹的類別）是透過乙太網路進行傳輸訊號來控制機械手臂，因此，程式執行時，先確認乙太網路是否有連接成功，否則會無法做訊號傳輸導致機械手臂不會反應。</br>
若是用無線的方式進行控制機械手臂，程式執行前請先確認是否有連接相對應的無線基地台，否則也會無法做訊號傳輸導致機械手臂不會反應。
