package Music;

public class RandImage {
	
	String path;
	int x, y;
	
	RandImage(String _path, int _x, int _y) {
		path = _path;
		x = _x;
		y = _y;
	}
	String getPath() {
		return path;
	}
	int getX() {
		return x;
	}
	int getY() {
		return y;
	}
}
