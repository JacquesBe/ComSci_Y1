import java.awt.Color;
import java.util.Scanner;

public class Voronoi {

	public static double distance(Point2D point1, Point2D point2) {
		double r;
		r = Math.sqrt((point1.x() - point2.x()) * (point1.x() - point2.x())
				+ (point1.y() - point2.y()) * (point1.y() - point2.y()));
		return r;
	}

	public static int findClosestPoint(Point2D point, Point2D[] sites) {
		double[] dist = new double[50];
		int close;
		for (int i = 0; i < 50; i++) {
			dist[i] = distance(point, sites[i]);
		}
		close = 0;
		for (int k = 1; k < 50; k++) {
			if (dist[k] < dist[close]) {
				close = k;
			}
		}
		return close;
	}

	public static int[][] buildVoronoiMap(Point2D[] sites, int width, int height) {

		int[][] map = new int[width][height];
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				Point2D current = new Point2D(i, j);
				map[i][j] = findClosestPoint(current, sites);
			}
		}

		return map;
	}

	public static void plotVoronoiMap(Point2D[] sites, Color[] colors, int[][] indexmap) {
		Scanner ex = new Scanner(System.in);
		boolean exit = false;
		Picture visual = new Picture(1920, 1080);
		for (int i = 0; i < 50; i++) {
			visual.set((int) sites[i].x(), (int) sites[i].y(), Color.black);
		}
		boolean binoph = true;
		boolean binops = true;

		while (exit == false) {
			for (int i = 0; i < 50; i++) {
				int choose = (int) (Math.random() * 2);
				float[] hsbvals = new float[3];
				int r = colors[i].getRed();
				int b = colors[i].getBlue();
				int g = colors[i].getGreen();
				Color.RGBtoHSB(r, g, b, hsbvals);
				switch (choose) {

				case 0:

					if (hsbvals[0] >= 1) {
						binoph = false;
						hsbvals[0] -= 0.005;
					} else if (hsbvals[0] <= 0) {
						hsbvals[0] += 0.005;
						binoph = true;
					} else if (!binoph) {
						hsbvals[0] -= 0.005;
					} else if (binoph) {
						hsbvals[0] += 0.005;

					}
					break;
				case 1:
					if (hsbvals[1] >= 1) {
						binops = false;
						hsbvals[1] -= 0.005;
					} else if (hsbvals[0] <= 0) {
						hsbvals[1] += 0.005;
						binops = true;
					} else if (!binops) {
						hsbvals[1] -= 0.005;
					} else if (binops) {
						hsbvals[1] += 0.005;

					}
					break;
				}
				colors[i] = Color.getHSBColor(hsbvals[0], hsbvals[1], hsbvals[2]);
			}

			for (int i = 0; i < 1920; i++) {
				for (int j = 0; j < 1080; j++) {
					visual.set(i, j, colors[indexmap[i][j]]);

				}
			}
			visual.show();
		}
		ex.close();
	}

	public static void main(String[] args) {
		int width = 1920;
		int height = 1080;

		int nSites = 50;

		Color[] colors = new Color[nSites];
		Point2D[] sites = new Point2D[nSites];
		for (int i = 0; i < 50; i++) {
			colors[i] = new Color((int) (Math.random() * 255), (int) (Math.random() * 255),
					(int) (Math.random() * 255));
		}
		for (int i = 0; i < 50; i++) {
			sites[i] = new Point2D((Math.random() * 1920), (Math.random() * 1080));

		}

		int[][] indexmap = buildVoronoiMap(sites, width, height);
		plotVoronoiMap(sites, colors, indexmap);
	}
}