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
- コメント書く

### メモ
- features  
操作のまとまりを並べる  
　処理フローの順番を整理  
operatorを操作  
pageを意識しない  

- operators  
処理フローを書く  
　要素を操作する順番を管理  
pageと1対1で紐づく  
　pageを増やしたらoperatorも増やす  

- pages.mediator  
pageの情報を持つ  
　要素を管理  

- pages.colleague

