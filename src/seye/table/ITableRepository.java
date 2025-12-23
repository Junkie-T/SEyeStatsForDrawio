package seye.table;

public interface ITableRepository {
	public AbstractTable get();
	public void write(AbstractTable table);
}

// 案① writeだけ子インターフェイスに
// 案② writeを実装した抽象クラスを作成
