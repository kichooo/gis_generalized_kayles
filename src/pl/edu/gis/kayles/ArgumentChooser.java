package pl.edu.gis.kayles;

import java.util.Random;

import pl.edu.gis.kayles.util.Graph;
import pl.edu.gis.kayles.util.Vertex;


/**
 * Console program, which finds best parameters for the heuristic function.
 */
public class ArgumentChooser {
	public static boolean fight(float pa1, float pa2, float pa3, float pb1,
			float pb2, float pb3) {
		Random r = new Random();
		Graph graph = new Graph(r.nextInt(), r.nextInt(300) + 1,
				r.nextInt(800));
		for (;;) {
			if (graph.isGameOver())
				return false;
			Vertex v1 = graph.makeDecision(pa1, pa2, pa3);
			if (v1 == null) {
				System.out.println("di[a");
			}
			graph.kaylesRemove(v1);
			if (graph.isGameOver())
				return true;
			Vertex v2 = graph.makeDecision(pb1, pb2, pb3);
			graph.kaylesRemove(v2);
		}
	}

	public static void main(String[] args) {
		float best1 = 0.33f;
		float best2 = 0.33f;
		float best3 = 0.33f;
		for (int i = 0; i < 100; i++) {
			Random r = new Random();
			float p1 = r.nextInt(100);
			float p2 = r.nextInt(100);
			float p3 = r.nextInt(100);
			float arg1 = p1 / (p1 + p2 + p3);
			float arg2 = p2 / (p1 + p2 + p3);
			float arg3 = p3 / (p1 + p2 + p3);
			int wynik = 0;
			for (int j = 0; j < 120; j++) {
				if (fight(best1, best2, best3, p1, p2, p3)) {
					wynik++;
				}
				if (wynik < 100) {
					best1 = arg1;
					best2 = arg2;
					best3 = arg3;
				}
			}
			System.out.println("Iteriation number " + i + " passed.");
		}
		System.out.println("Arguments found: Farthest: " + best1 + ", Extended "+  best2  + ", Proximity: " +  best3 );
		// TODO Auto-generated method stub

	}

}
