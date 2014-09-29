# 環境
jdk1.7以上

## WebDriver
- IE 2.39.0(32bit)  
  2.40.0-2.42.2(32bit)、64bitはsendkeysが遅い  
 - 環境設定  
   https://code.google.com/p/selenium/wiki/InternetExplorerDriver#Required_Configuration  
  - The IEDriverServer exectuable must be downloaded and placed in your PATH.  
  - On IE 7 or higher on Windows Vista or Windows 7, you must set the Protected Mode settings for each zone to be the same value. The value can be on or off, as long as it is the same for every zone. To set the Protected Mode settings, choose "Internet Options..." from the Tools menu, and click on the Security tab. For each zone, there will be a check box at the bottom of the tab labeled "Enable Protected Mode".  
  - Additionally, "Enhanced Protected Mode" must be disabled for IE 10 and higher. This option is found in the Advanced tab of the Internet Options dialog.  
  - The browser zoom level must be set to 100% so that the native mouse events can be set to the correct coordinates.  
  - For IE 11 only, you will need to set a registry entry on the target computer so that the driver can maintain a connection to the instance of Internet Explorer it creates. For 32-bit Windows installations, the key you must examine in the registry editor is HKEY_LOCAL_MACHINE\SOFTWARE\Microsoft\Internet Explorer\Main\FeatureControl\FEATURE_BFCACHE. For 64-bit Windows installations, the key is HKEY_LOCAL_MACHINE\SOFTWARE\Wow6432Node\Microsoft\Internet Explorer\Main\FeatureControl\FEATURE_BFCACHE. Please note that the FEATURE_BFCACHE subkey may or may not be present, and should be created if it is not present. Important: Inside this key, create a DWORD value named iexplore.exe with the value of 0.   
  - インターネットオプション > 設定 > [マイコンピューターのファイルでのアクティブコンテンツの実行を許可する]チェック
- FF  
開発環境
- GC  
未確認

### TODO
- 自己証明書対応
 - FF
 - GC
- showmodal,open対応
- jsのダイアログ制御対応
- test書く

### できること
- features  
 - 目的  
	1メソッドで1ケースの操作  
	処理フローの順番を整理  
 - 影響  
	operatorを操作  
	operator以外を意識しない  

- operators  
 - 目的  
	1メソッドで1つのフロー  
	要素を操作する順番を管理  
 - 影響  
	operatorとmediatorはn対1  
	operatorで使用するmediatorは1件のみだが、mediatorは複数のoperatorで使用できる
	要素を取得する際は、mediatorを通してcolleagueを取得する  

- mediator  
 - 目的  
	page情報の管理  
	driverとか  
	operatorからcolleagueを操作する入り口  
 - 影響  
	mediatorとcolleagueは1対n  
	mediatorは複数のcolleague情報を持つ  

- colleague  
 - 目的  
	要素の管理  
	colleagueを継承して要素を増やしていくことができる  
	googleであれば、top用のcolleagueがsuperになり、メール用のcolleagueや、カレンダー用のcolleagueを作成する  
	　対応するmediatorは1つ  
 - 影響  
	1件のmediator情報を持つ  


