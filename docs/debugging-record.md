# デバッグ記録

## 最初に観測した事実

商品を登録したキーの地域を `JP` から `US` へ変更すると、同じキーオブジェクトで検索しても `null` になる。

## 再現手順

コミット `ab8907b` で `mvn --batch-mode test` を実行すると、`[evidence] lookup=null`、`expected: <registered> but was: <null>` となる。

## 観測

キーの `equals` と `hashCode` はSKUと地域の両方を参照している。登録時と検索時で地域が変わるため、キーのハッシュ値も変わる。

## 仮説比較

| 仮説 | 実験 | 結果 |
| --- | --- | --- |
| HashMapが登録値を自動削除した | `size`とentryを確認する | キー変更後に検索契約だけが壊れるため棄却 |
| キーのハッシュ値が変わった | 変更前後のフィールドを確認する | 採用 |
| 地域が安定識別子である | SKUだけで検索する最小実験 | 地域は属性であり棄却 |

## 原因

HashMapの検索はキーのハッシュと等価性を使う。`equals`／`hashCode`に参加する可変フィールドを変更すると、登録時のバケットと検索時のハッシュが一致しなくなる。[1]

## 修正

地域をHashMapキーから分離し、安定したSKUをキーにした。修正コミットは `f191aa5` である。

## 再発防止テスト

元のテストを残し、`lookup=registered` を確認する。修正後は `Tests run: 1, Failures: 0, Errors: 0`、`BUILD SUCCESS` となる。

## References

[1] [Java SE 21 API — HashMap](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/HashMap.html)
