# FeathearFocast

## 概要
* 練習用に作成

## ステータス

api接続でハマる

**エミュレータ：**

* wifiの設定ができずはまる

**実機：**

- wifiは繋がってるので多分大丈夫
- api接続時にエラー
  - どうもmainスレッドからnetアクセスができない仕様らしく、そのあたりをいじらないといけないっぽい
  - コードに参考サイトは載せてる



## TODO

- ~~hellow world~~

- ~~ボタンを押したらラベルが変わる~~

- ~~git登録~~
    - [GitHub](https://github.com/kuwacchi38/FeathearFocast/tree/master)

- README.mdが表示されない

- ブランチ名の表示

- 天気予報のapiにリクエストできる
    - [シェルで天気予報表示](https://qiita.com/stc1988/items/70e27508e2febc20571d)

      ```bash
      curl -s http://weather.livedoor.com/forecast/webservice/json/v1\?city\=270000 | jq -r '.forecasts[] | select(.dateLabel == "今日").telop'
      ```
    - [Android Studioでwifiに接続する](https://teratail.com/questions/2462)


- リクエストした内容を受け取る
- ラベルを変更する


