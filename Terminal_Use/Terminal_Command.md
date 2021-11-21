<h1>終端機指令介紹</h1>
<ol>
  <li><h3>cd</h3>
    cd 是一個進入、退出資料夾的指令，語法為：<code>cd <b>進入的資料夾或是資料夾位址</b></code>，例如：
    <ul>
      <li>進入家目錄：<code>cd ~</code></li>
      <li>進入根目錄：<code>cd /</code></li>
      <li>進入根目錄裡的home目錄裡的kenlee目錄：<code>cd /home/kenlee</code></li>
      <li>進入此目錄裡的java目錄裡的EX目錄：<code>cd ./java/EX</code></li>
      <li>回上一個目錄：<code>cd ..</code></li>
    </ul>
  </li>
  <li><h3>mkdir</h3>
    mkdir 是建立一個目錄或多個目錄的指令，語法為：<code>mkdir <b>建立資料夾名稱1 [建立資料夾名稱2] [建立資料夾名稱3]...</b></code>例如：
    <ul>
      <li>建立KEN資料夾：<code>mkdir KEN</code></li>
      <li>在某個資料夾建立A資料夾、B資料夾、C資料夾：<code>mkdir A B C</code></li>
      <li>在家目錄中建立COMPILE資料夾：<code>mkdir ~/COMPILE</code></li>
    </ul>
    若要在一個不存在的目錄，建立資料夾，則語法如下：<code>mkdir -p <b>建立資料夾名稱1 [建立資料夾名稱2] [建立資料夾名稱3]...</b></code>
    <ul>
      <li>在家目錄裡的KEN目錄裡建立LOVE資料夾，但KEN目錄不存在：<code>mkdir -p ~/KEN/LOVE</code></li>
      <li>在家目錄裡的DOC資料夾裡的PAGE建立PAPER1資料夾，DOC資料夾存在，PAGE資料夾不存在：<code>mkdir -p ~/DOC/PAGE/PAPER1</code></li>
    </ul>
  </li>
  <li><h3>rm</h3>
    刪除一個或多個檔案或目錄，若刪除一個或多個目錄，則語法為：<code>rm <b>檔案名稱或檔案位置1 [檔案名稱或檔案位置2] [檔案名稱或檔案位置3]...</b></code>
    <ul>
      <li>在此目錄刪除一個檔案：<code>rm example.txt</code></li>
      <li>在此目錄刪除多個檔案：<code>rm example.txt Tool.cpp Menu.docx</code></li>
    </ul>
    若要刪除空目錄（目錄裡面沒有目錄及檔案），則語法為：<code>rm -d <b>目錄或目錄位置1 [目錄或目錄位置2] [目錄或目錄位置3]...</b></code>，若要刪除此資料
    夾裡所有的目錄及檔案，則語法為：<code>rm -r <b>目錄或目錄位置1 [目錄或目錄位置2] [目錄或目錄位置3]...</b></code>
</ol>
