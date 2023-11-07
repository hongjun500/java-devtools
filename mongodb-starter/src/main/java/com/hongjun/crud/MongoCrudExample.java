package com.hongjun.crud;

import com.hongjun.connection.MongoConn;
import com.mongodb.client.result.InsertOneResult;
import org.bson.Document;

import static com.mongodb.client.model.Filters.*;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;

/**
 * @author hongjun500
 * @date 2023/11/7 15:30
 * @tool ThinkPadX1隐士
 * Created with 2022.2.2.IntelliJ IDEA
 * Description: Mongo crud example
 */

public class MongoCrudExample {
	public static final String INVENTORY = "inventory";
	/**
	 *  添加单个文档
	 */
	public void save(){
		Document canvas = new Document("item", "canvas")
				.append("qty", 100)
				.append("tags", singletonList("cotton"));
		Document size = new Document("h", 28)
				.append("w", 35.5)
				.append("uom", "cm");
		canvas.put("size", size);
		InsertOneResult result = MongoConn.connect().getCollection(INVENTORY).insertOne(canvas);
	}

	/**
	 * 添加多个文档
	 */
	public void saves(){
		Document journal = new Document("item", "journal")
				.append("qty", 25)
				.append("tags", asList("blank", "red"));

		Document journalSize = new Document("h", 14)
				.append("w", 21)
				.append("uom", "cm");
		journal.put("size", journalSize);

		Document mat = new Document("item", "mat")
				.append("qty", 85)
				.append("tags", singletonList("gray"));

		Document matSize = new Document("h", 27.9)
				.append("w", 35.5)
				.append("uom", "cm");
		mat.put("size", matSize);

		Document mousePad = new Document("item", "mousePad")
				.append("qty", 25)
				.append("tags", asList("gel", "blue"));

		Document mousePadSize = new Document("h", 19)
				.append("w", 22.85)
				.append("uom", "cm");
		mousePad.put("size", mousePadSize);

		MongoConn.connect().getCollection(INVENTORY).insertMany(asList(journal, mat, mousePad));
	}

	/**
	 * 删除一个符合条件的文档
	 * @param field 字段
	 * @param value 值
	 */
	public void delOne(String field, String value){
		MongoConn.connect().getCollection(INVENTORY).deleteOne(eq(field, value));
	}

	public void delMany(){
		MongoConn.connect().getCollection(INVENTORY).deleteMany(
				and(gte("w", "21"),
				eq("uom","cm")));
	}
}
