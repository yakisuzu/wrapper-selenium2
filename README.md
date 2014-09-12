# 環境
jdk1.6以上

## WebDriver
IE 2.39.0
(2.40.0以上はsendkeysが遅い)

# TODO
- 自己証明書対応
 - IE
 - FF
 - CH
- showmodal,open対応
- jsを流すIF対応
- jsのダイアログ制御対応
- systemプロパティ
 - プロパティクラスのsuper作成
- test書く
 - test書く範囲は

### やること
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
	mediatorとoperatorは1対1  
	要素を取得する際は、operatorを通してcolleagueを取得する  
	mediatorを増やしたらoperatorも増やす  
	colleagueを増やしてもoperatorは増えない  

- mediator  
 - 目的  
	page情報の管理  
	driverとか  
	operatorがcolleagueを操作する入り口  
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


