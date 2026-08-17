# 可変HashMapキーで登録済み商品が見つからない

本ラボは、`equals` と `hashCode` に参加するフィールドを変更可能なキーとしてHashMapへ登録すると、変更後の検索が失敗する問題を再現します。

## 実行

```bash
mvn --batch-mode test
```

バグコミットは `ab8907b`、修正コミットは `f191aa5` です。修正後はSKUを安定した識別子としてMapキーに使います。

## 学習の流れ

| 段階 | 観測 |
| --- | --- |
| 再現 | 登録直後のキー変更後に `lookup=null` |
| 仮説 | HashMapがエントリを削除した |
| 切り分け | `hashCode`の変更前後とMapのキー設計を確認 |
| 修正 | 可変地域属性をキーから分離しSKUをキーにする |

## References

[1] [Java SE 21 API — HashMap](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/HashMap.html)
