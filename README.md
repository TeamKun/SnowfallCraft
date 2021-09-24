# SnowfallCraft

指定プレイヤーの周囲で降雪するプラグイン。

## 動作環境

- Minecraft 1.16.5
- PaperMC 1.16.5

## コマンド

    /snowfall start 降雪開始
    /snowfall stop 降雪終了
    /snowfall player <player> 降雪対象プレイヤーの設定
    /snowfall radius <number> 降雪半径の設定　＜初期値：50＞
    /snowfall period <number> 降雪間隔の設定　＜初期値：10＞

## その他の仕様

- 降雪間隔はミリ秒、降雪間隔ごとに雪ブロックが1個降雪（降雪間隔1ミリ秒＝1秒間に1000個が最大降雪量となる）
- 降雪半径・降雪間隔は1以上の整数のみ有効
- 降雪ポイントの最も高い位置にあるブロックのY座標または降雪対象プレイヤーのY座標のいずれか高い方から＋50したY座標から降雪
- プレイヤーのランダム指定可