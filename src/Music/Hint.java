package Music;

public class Hint {
	String head;
	String desc;
	String path;
	int size;
	Hint(String _head, String _desc, String _path, int _size) {
		head = _head;
		desc = _desc;
		path = _path;
		size = _size;
	}
	String getHead() {
		return head;
	}
	String getDesc() {
		return desc;
	}
	String getPath() {
		return path;
	}
	int getSize() {
		return size;
	}
}
