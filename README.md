# 環境
jdk1.6以上

## WebDriver
- IE 2.39.0(32bit)  
2.40.0-2.42.2(32bit)、64bitはsendkeysが遅い
- FF  
開発環境
- GC  
未確認

### TODO
- 自己証明書対応
 - FF
 - GC
- showmodal,open対応
- jsを流すIF対応
- jsのダイアログ制御対応
- test書く
 - test書く範囲は

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


