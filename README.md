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
    /snowfall amount <number> 降雪量の設定　＜初期値：100＞

## その他の仕様

- 降雪量は1Tickあたりの雪ブロック数
- 降雪半径・降雪量は1以上の整数のみ有効
- 降雪ポイントの最も高い位置にあるブロックのY座標または降雪対象プレイヤーのY座標のいずれか高い方から＋50したY座標から降雪
- プレイヤーのランダム指定可